package paymentfulfillment.server.rule.handler;


import paymentfulfillment.server.core.Result;
import paymentfulfillment.server.externalstub.CommissionSystemStub;
import paymentfulfillment.server.service.message.purchasedetails.CommissionDetails;
import paymentfulfillment.server.service.message.purchasedetails.PurchaseDetails;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CommissionHandler extends PaymentFulfillmentHandler {
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());
    private CommissionSystemStub commissionSystemStub;

    public CommissionHandler(CommissionSystemStub commissionSystemStub) {
        this.commissionSystemStub = commissionSystemStub;
    }

    @Override
    protected Result handleImpl(PurchaseDetails details) {
        LOGGER.log(Level.INFO, "Applying commission to purchase.");
        CommissionDetails commissionDetails = (CommissionDetails) details;
        return commissionSystemStub.generateCommission(commissionDetails.getAgentName());
    }

    @Override
    protected void validateRequest(PurchaseDetails details) {
        if(!(details instanceof CommissionDetails))
            throw new IllegalArgumentException(
                    "Expected CommissionDetails, but received " + details);
    }
}
