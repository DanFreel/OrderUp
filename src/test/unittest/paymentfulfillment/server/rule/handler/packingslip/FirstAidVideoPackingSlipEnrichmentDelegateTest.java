package paymentfulfillment.server.rule.handler.packingslip;


import org.junit.Test;
import paymentfulfillment.server.core.Address;
import paymentfulfillment.server.core.PackingSlip;
import paymentfulfillment.server.core.producttype.PhysicalSubType;
import paymentfulfillment.server.rule.handler.PackingSlipHandler;
import paymentfulfillment.server.rule.handler.packingslip.util.MakeShippingDetailsUtil;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FirstAidVideoPackingSlipEnrichmentDelegateTest {
    @Test
    public void testHandleSkiVideo() {
        runTest(PhysicalSubType.VIDEO, "Learning to Ski", 2);
    }

    @Test
    public void testHandleNonSkiVideo() {
        runTest(PhysicalSubType.VIDEO, "someVideo", 1);
    }

    @Test
    public void testHandleNonVideo() {
        runTest(PhysicalSubType.BOOK, "someItem", 1);
    }

    private void runTest(PhysicalSubType subType, String itemName, int expectedSlips) {
        FirstAidVideoPackingSlipEnrichmentDelegate del = new FirstAidVideoPackingSlipEnrichmentDelegate();

        List<PackingSlip> slips =
                Collections.singletonList(new PackingSlip("cust",
                                                          itemName,
                                                          new Address(),
                                                          PackingSlipHandler.PackingSlipIntent.SHIPPING));
        del.handle(slips, MakeShippingDetailsUtil.makeDetails(subType, "cust", itemName));

        assertEquals(expectedSlips, slips.get(0).getOrderItems().size());
    }
}
