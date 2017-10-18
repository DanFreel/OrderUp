package paymentfulfillment.server.rule.handler.packingslip;


import paymentfulfillment.server.core.PackingSlip;
import paymentfulfillment.server.core.producttype.PhysicalSubType;
import paymentfulfillment.server.service.message.purchasedetails.ShippingDetails;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FirstAidVideoPackingSlipEnrichmentDelegate implements PackingSlipEnrichmentDelegate {
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());
    private static final String VID_NAME_REQUIRING_FIRST_AID_VID = "Learning to Ski";
    private static final String FIRST_AID_VID_NAME = "First Aid";

    @Override
    public void handle(List<PackingSlip> slips, ShippingDetails details) {
        if(isEligible(details)) {
            LOGGER.log(Level.INFO, "Adding " + FIRST_AID_VID_NAME + " to all packing slips.");
            slips.forEach(slip -> slip.addOrderItem(FIRST_AID_VID_NAME));
        }
    }

    private Boolean isEligible(ShippingDetails details) {
        return PhysicalSubType.VIDEO.equals(details.getProductSubType())
                && VID_NAME_REQUIRING_FIRST_AID_VID.equals(details.getPurchasedItem());
    }
}
