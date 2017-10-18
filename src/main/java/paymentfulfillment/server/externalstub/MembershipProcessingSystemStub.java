package paymentfulfillment.server.externalstub;


import paymentfulfillment.server.core.Result;
import paymentfulfillment.server.core.Result.Status;

public class MembershipProcessingSystemStub {
    public Result createMembership(long membershipId, String memberName) {
        return new Result(Status.SUCCESS, "Created membership with id " + membershipId + " and name " + memberName);
    }

    public Result upgradeMembership(long membershipId, String memberName) {
        return new Result(Status.SUCCESS, "Upgraded membership with id " + membershipId + " and name " + memberName);
    }
}
