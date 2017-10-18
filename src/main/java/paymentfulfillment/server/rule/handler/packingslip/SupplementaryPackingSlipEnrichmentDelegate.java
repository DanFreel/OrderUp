package paymentfulfillment.server.rule.handler.packingslip;


import paymentfulfillment.server.core.PackingSlip;
import paymentfulfillment.server.rule.handler.PackingSlipHandler.PackingSlipIntent;
import paymentfulfillment.server.service.message.purchasedetails.PurchaseDetails;
import paymentfulfillment.server.service.message.purchasedetails.ShippingDetails;

import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SupplementaryPackingSlipEnrichmentDelegate implements PackingSlipEnrichmentDelegate {
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());
    private Predicate<PurchaseDetails> shouldAddPackingSlip;
    private PackingSlipIntent newSlipIntent;

    public SupplementaryPackingSlipEnrichmentDelegate(Predicate<PurchaseDetails> shouldAddPackingSlip,
                                                      PackingSlipIntent newSlipIntent) {
        this.shouldAddPackingSlip = shouldAddPackingSlip;
        this.newSlipIntent = newSlipIntent;
    }

    @Override
    public void handle(List<PackingSlip> slips, ShippingDetails details) {
        if(shouldAddPackingSlip.test(details)) {
            LOGGER.log(Level.INFO,
                       "Creating new packing slip with provided details with slip intent: " + newSlipIntent);

            slips.add(new PackingSlip(
                details.getCustomerName(),
                details.getPurchasedItem(),
                details.getShippingAddress(),
                newSlipIntent));
        }
    }
}
