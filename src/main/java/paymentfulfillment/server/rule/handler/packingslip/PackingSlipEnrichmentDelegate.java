package paymentfulfillment.server.rule.handler.packingslip;


import paymentfulfillment.server.core.PackingSlip;
import paymentfulfillment.server.service.message.purchasedetails.ShippingDetails;

import java.util.List;

public interface PackingSlipEnrichmentDelegate {
    void handle(List<PackingSlip> slips, ShippingDetails details);
}
