package paymentfulfillment.server;


import org.junit.Test;
import paymentfulfillment.server.core.Address;
import paymentfulfillment.server.core.producttype.MembershipSubType;
import paymentfulfillment.server.core.producttype.PhysicalSubType;
import paymentfulfillment.server.externalstub.SystemStubSource;
import paymentfulfillment.server.rule.RulesLoader;
import paymentfulfillment.server.service.PaymentFulfillmentImpl;
import paymentfulfillment.server.service.message.PaymentFulfillmentRequest;
import paymentfulfillment.server.service.message.PaymentFulfillmentResponse;
import paymentfulfillment.server.service.message.purchasedetails.MembershipDetails;
import paymentfulfillment.server.service.message.purchasedetails.PhysicalProductDetails;
import paymentfulfillment.server.service.message.purchasedetails.PurchaseDetails;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PaymentFulfillmentTest {
    private static final String MEMBERSHIP_MSG_TEMPLATE =
            "[Status: SUCCESS - Reason: %s membership with id 123456 and name James Bond, " +
            "Status: SUCCESS - Reason: Sent email to 007@mi6.com with body Dear James Bond, this is a " +
            "notification to let you know the following operation was performed on membership [ID = 123456]: " +
            "%s. Thank you.]";

    private static final String PHYSICAL_NON_BOOK_MSG_TEMPLATE =
            "[Status: SUCCESS - Reason: Generating the following packing slips: " +
            "[Customer Name: Random Shopper - Ordered Items: [%s] - packingSlipIntent: SHIPPING], " +
            "Status: SUCCESS - Reason: Generated commission for Some Agent]";

    @Test
    public void testMembershipCreation() {
        String expectedMsg = String.format(MEMBERSHIP_MSG_TEMPLATE, "Created", "CREATE");
        runTest(makeMembershipDetails(MembershipSubType.CREATE), expectedMsg);
    }

    @Test
    public void testMembershipUpgrade() {
        String expectedMsg = String.format(MEMBERSHIP_MSG_TEMPLATE, "Upgraded", "UPGRADE");
        runTest(makeMembershipDetails(MembershipSubType.UPGRADE), expectedMsg);
    }

    @Test
    public void testBookOrder() {
        String expectedMsg = "[Status: SUCCESS - Reason: Generating the following packing slips: " +
                "[Customer Name: Random Shopper - Ordered Items: [book1] - packingSlipIntent: SHIPPING, " +
                "Customer Name: Random Shopper - Ordered Items: [book1] - packingSlipIntent: ROYALTY_DEP], " +
                "Status: SUCCESS - Reason: Generated commission for Some Agent]";
        runTest(makePhysicalProductDetails(PhysicalSubType.BOOK, "book1"), expectedMsg);
    }

    @Test
    public void testVideoSkiOrder() {
        String expectedMsg = String.format(PHYSICAL_NON_BOOK_MSG_TEMPLATE, "Learning to Ski, First Aid");
        runTest(makePhysicalProductDetails(PhysicalSubType.VIDEO, "Learning to Ski"), expectedMsg);
    }

    @Test
    public void testVideoOrder() {
        String expectedMsg = String.format(PHYSICAL_NON_BOOK_MSG_TEMPLATE, "video1");
        runTest(makePhysicalProductDetails(PhysicalSubType.VIDEO, "video1"), expectedMsg);
    }

    @Test
    public void testGameOrder() {
        String expectedMsg = String.format(PHYSICAL_NON_BOOK_MSG_TEMPLATE, "Game1");
        runTest(makePhysicalProductDetails(PhysicalSubType.GAME, "Game1"), expectedMsg);
    }

    private void runTest(PurchaseDetails details, String expectedResponse) {
        PaymentFulfillmentImpl serv = new PaymentFulfillmentImpl(new RulesLoader(new SystemStubSource()));
        PaymentFulfillmentRequest req = new PaymentFulfillmentRequest(details);

        PaymentFulfillmentResponse resp = serv.service(req);
        assertNotNull(resp);
        assertEquals(expectedResponse, resp.getResult());
    }

    private MembershipDetails makeMembershipDetails(MembershipSubType subType) {
        return new MembershipDetails(subType, 123456, "James Bond", "007@mi6.com");
    }

    private PhysicalProductDetails makePhysicalProductDetails(PhysicalSubType subType, String itemName) {
        Address dummyAddress = new Address("Some Address",
                                           "That is Not Validate",
                                           "Some Postal Code",
                                           "Some City",
                                           "Some Country");

        return new PhysicalProductDetails(subType, "Random Shopper", itemName, dummyAddress, "Some Agent");
    }
}
