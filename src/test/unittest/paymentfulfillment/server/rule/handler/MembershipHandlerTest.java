package paymentfulfillment.server.rule.handler;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import paymentfulfillment.server.core.Result;
import paymentfulfillment.server.core.Result.Status;
import paymentfulfillment.server.core.producttype.MembershipSubType;
import paymentfulfillment.server.externalstub.MembershipProcessingSystemStub;
import paymentfulfillment.server.service.message.purchasedetails.MembershipDetails;

import static org.junit.Assert.assertEquals;

public class MembershipHandlerTest extends HandlerTestSetup {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private final long ID = 123456;
    private final String NAME = "name";

    @Test
    public void testHandleCreate() {
        testHandle(MembershipSubType.CREATE);
    }

    @Test
    public void testHandlerUpgrade() {
        testHandle(MembershipSubType.UPGRADE);
    }

    @Test
    public void testFailValidation() {
        MembershipHandler handler = new MembershipHandler(makeSystemStub());

        exception.expect(IllegalArgumentException.class);
        handler.handle(makeIncompatibleDetails());
    }

    private void testHandle(MembershipSubType subType) {
        MembershipHandler handler = new MembershipHandler(makeSystemStub());
        Result result = handler.handle(makeDetails(subType));

        assertEquals(Status.SUCCESS, result.getStatus());
        assertEquals(subType.name(), result.getReason());
    }

    private MembershipProcessingSystemStub makeSystemStub() {
        return new MembershipProcessingSystemStub() {
            @Override
            public Result createMembership(long membershipId, String memberName) {
                assertDetails(membershipId, memberName);
                return new Result(Result.Status.SUCCESS, MembershipSubType.CREATE.name());
            }

            @Override
            public Result upgradeMembership(long membershipId, String memberName) {
                assertDetails(membershipId, memberName);
                return new Result(Result.Status.SUCCESS, MembershipSubType.UPGRADE.name());
            }

            private void assertDetails(long membershipId, String memberName) {
                assertEquals(ID, membershipId);
                assertEquals(NAME, memberName);
            }
        };
    }

    private MembershipDetails makeDetails(MembershipSubType subType) {
        return new MembershipDetails(subType, ID, NAME, "email");
    }
}
