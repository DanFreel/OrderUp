package paymentfulfillment.server.rule;


import paymentfulfillment.server.core.Result;
import paymentfulfillment.server.core.producttype.ProductSubType;
import paymentfulfillment.server.core.producttype.ProductType;
import paymentfulfillment.server.rule.handler.PaymentFulfillmentHandler;
import paymentfulfillment.server.service.message.purchasedetails.PurchaseDetails;

class RuleTestsSetup {
    Rule makeRule(Boolean isSuccessful) {
        return makeRule(isSuccessful, ProductType.MEMBERSHIP);
    }

    Rule makeRule(Boolean isSuccessful, ProductType type) {
        return
            new Rule(
                "Rule Name",
                d -> type.equals(d.getProductType()),
                makeHandler(isSuccessful));
    }

    private PaymentFulfillmentHandler makeHandler(Boolean isSuccessful) {
        return new PaymentFulfillmentHandler() {
            @Override
            protected Result handleImpl(PurchaseDetails details) {
                Result.Status status = isSuccessful ? Result.Status.SUCCESS : Result.Status.FAILURE;
                return new Result(status, "This is the result message");
            }

            @Override
            protected void validateRequest(PurchaseDetails details) { }
        };
    }

    PurchaseDetails makeDetails(ProductType type, ProductSubType subType) {
        return new PurchaseDetails() {
            @Override
            public ProductType getProductType() {
                return type;
            }

            @Override
            public ProductSubType getProductSubType() {
                return subType;
            }
        };
    }
}
