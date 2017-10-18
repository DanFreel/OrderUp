package paymentfulfillment.server.externalstub;


import paymentfulfillment.server.core.Result;
import paymentfulfillment.server.core.Result.Status;

public class CommissionSystemStub {
    public Result generateCommission(String agentName) {
        return new Result(Status.SUCCESS, "Generated commission for " + agentName);
    }
}
