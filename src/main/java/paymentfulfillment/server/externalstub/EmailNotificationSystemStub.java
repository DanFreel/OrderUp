package paymentfulfillment.server.externalstub;


import paymentfulfillment.server.core.Result;
import paymentfulfillment.server.core.Result.Status;

public class EmailNotificationSystemStub {
    public Result sendEmail(String recipient, String message) {
        return new Result(Status.SUCCESS, "Sent email to " + recipient + " with body " + message);
    }
}
