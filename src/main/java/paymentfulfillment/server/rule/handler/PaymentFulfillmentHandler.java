package paymentfulfillment.server.rule.handler;


import paymentfulfillment.server.core.Result;
import paymentfulfillment.server.service.message.purchasedetails.PurchaseDetails;

abstract public class PaymentFulfillmentHandler {
    public Result handle(PurchaseDetails details) {
        validateRequest(details);
        return handleImpl(details);
    }

    abstract protected Result handleImpl(PurchaseDetails details);
    abstract protected void validateRequest(PurchaseDetails details);
}
