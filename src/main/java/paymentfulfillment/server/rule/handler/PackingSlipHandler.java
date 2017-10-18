package paymentfulfillment.server.rule.handler;


import paymentfulfillment.server.core.Result;
import paymentfulfillment.server.core.PackingSlip;
import paymentfulfillment.server.externalstub.PackingSlipSystemStub;
import paymentfulfillment.server.rule.handler.packingslip.PackingSlipEnrichmentDelegate;
import paymentfulfillment.server.service.message.purchasedetails.PurchaseDetails;
import paymentfulfillment.server.service.message.purchasedetails.ShippingDetails;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PackingSlipHandler extends PaymentFulfillmentHandler {
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());
    private List<PackingSlipEnrichmentDelegate> packingSlipDelegates;
    private PackingSlipSystemStub packingSlipSystemStub;
    private PackingSlipIntent packingSlipIntent;

    public enum PackingSlipIntent {
        SHIPPING,
        ROYALTY_DEP
    }

    public PackingSlipHandler(List<PackingSlipEnrichmentDelegate> packingSlipDelegates,
                              PackingSlipSystemStub packingSlipSystemStub,
                              PackingSlipIntent packingSlipIntent) {
        this.packingSlipDelegates = new ArrayList<>(packingSlipDelegates);
        this.packingSlipSystemStub = packingSlipSystemStub;
        this.packingSlipIntent = packingSlipIntent;
    }

    @Override
    protected Result handleImpl(PurchaseDetails details) {
        LOGGER.log(Level.INFO, "Creating packing slip");
        ShippingDetails shippingDetails = (ShippingDetails) details;
        List<PackingSlip> slips = new ArrayList<>(Collections.singletonList(
            new PackingSlip(
                    shippingDetails.getCustomerName(),
                    shippingDetails.getPurchasedItem(),
                    shippingDetails.getShippingAddress(),
                    packingSlipIntent)));

        LOGGER.log(Level.INFO, "Applying additional rules to create packing slips.");
        packingSlipDelegates.forEach(del -> del.handle(slips, shippingDetails));

        return packingSlipSystemStub.generatePackingSlips(slips);
    }

    @Override
    protected void validateRequest(PurchaseDetails details) {
        if(!(details instanceof ShippingDetails))
            throw new IllegalArgumentException("Expected ShippingDetails, but received " + details);
    }
}
