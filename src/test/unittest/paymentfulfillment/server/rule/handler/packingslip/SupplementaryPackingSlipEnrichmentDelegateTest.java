package paymentfulfillment.server.rule.handler.packingslip;


import org.junit.Test;
import paymentfulfillment.server.core.PackingSlip;
import paymentfulfillment.server.core.producttype.PhysicalSubType;
import paymentfulfillment.server.rule.handler.PackingSlipHandler;
import paymentfulfillment.server.rule.handler.packingslip.util.MakeShippingDetailsUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SupplementaryPackingSlipEnrichmentDelegateTest {
    @Test
    public void testHandleExtraSlip() {
        runTest(PhysicalSubType.GAME, 1);
    }

    @Test
    public void testHandleNoExtraSlip() {
        runTest(PhysicalSubType.VIDEO, 0);
    }

    private void runTest(PhysicalSubType subType, int expectedSlips) {
        SupplementaryPackingSlipEnrichmentDelegate del =
            new SupplementaryPackingSlipEnrichmentDelegate(
                d -> d.getProductSubType().equals(PhysicalSubType.GAME),
                PackingSlipHandler.PackingSlipIntent.ROYALTY_DEP);

        List<PackingSlip> slips = new ArrayList<>();
        del.handle(slips, MakeShippingDetailsUtil.makeDetails(subType, "cust", "item"));

        assertEquals(expectedSlips, slips.size());
    }
}
