package paymentfulfillment.server.rule.handler;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import paymentfulfillment.server.core.PackingSlip;
import paymentfulfillment.server.core.Result;
import paymentfulfillment.server.core.Result.Status;
import paymentfulfillment.server.core.producttype.PhysicalSubType;
import paymentfulfillment.server.externalstub.PackingSlipSystemStub;
import paymentfulfillment.server.rule.handler.PackingSlipHandler.PackingSlipIntent;
import paymentfulfillment.server.rule.handler.packingslip.PackingSlipEnrichmentDelegate;
import paymentfulfillment.server.rule.handler.packingslip.util.MakeShippingDetailsUtil;
import paymentfulfillment.server.service.message.purchasedetails.ShippingDetails;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PackingSlipHandlerTest extends HandlerTestSetup {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private final String EXPECTED_NAME = "name";

    @Test
    public void testHandleNoDelegate() {
        PackingSlipHandler handler =
            new PackingSlipHandler(Collections.emptyList(), makeSystemStub(1), PackingSlipIntent.SHIPPING);

        Result result = handler.handle(makeDetails());
        assertEquals(Status.SUCCESS, result.getStatus());
    }

    @Test
    public void testHandleWithDelegate() {
        PackingSlipHandler handler =
            new PackingSlipHandler(Collections.singletonList(makeDelegate()),
                                   makeSystemStub(2),
                                   PackingSlipIntent.SHIPPING);

        Result result = handler.handle(makeDetails());
        assertEquals(Status.SUCCESS, result.getStatus());
    }

    @Test
    public void testFailValidation() {
        PackingSlipHandler handler =
            new PackingSlipHandler(Collections.emptyList(), makeSystemStub(1), PackingSlipIntent.SHIPPING);

        exception.expect(IllegalArgumentException.class);
        handler.handle(makeIncompatibleDetails());
    }

    private PackingSlipSystemStub makeSystemStub(int expectedNumSlips) {
        return new PackingSlipSystemStub() {
            @Override
            public Result generatePackingSlips(List<PackingSlip> slips) {
                assertEquals(expectedNumSlips, slips.size());
                PackingSlip slip = slips.get(0);
                assertEquals(EXPECTED_NAME, slip.getCustomerName());
                assertEquals(1, slip.getOrderItems().size());
                assertEquals(PackingSlipIntent.SHIPPING, slip.getPackingSlipIntent());

                return new Result(Status.SUCCESS, "");
            }
        };
    }

    private PackingSlipEnrichmentDelegate makeDelegate() {
        return (List<PackingSlip> slips, ShippingDetails details) -> slips.add(slips.get(0));
    }

    private ShippingDetails makeDetails() {
        return MakeShippingDetailsUtil.makeDetails(PhysicalSubType.BOOK, EXPECTED_NAME, "Some Item");
    }
}
