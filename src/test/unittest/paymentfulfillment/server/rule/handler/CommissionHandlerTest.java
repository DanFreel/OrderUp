package paymentfulfillment.server.rule.handler;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import paymentfulfillment.server.core.Result;
import paymentfulfillment.server.core.producttype.PhysicalSubType;
import paymentfulfillment.server.core.producttype.ProductSubType;
import paymentfulfillment.server.core.producttype.ProductType;
import paymentfulfillment.server.externalstub.CommissionSystemStub;
import paymentfulfillment.server.service.message.purchasedetails.CommissionDetails;

import static org.junit.Assert.assertEquals;

public class CommissionHandlerTest extends HandlerTestSetup {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private final String EXPECTED_AGENT_NAME = "agentName";

    @Test
    public void testHandle() {
        CommissionHandler handler = new CommissionHandler(makeSystemStub());
        Result result = handler.handle(makeDetails());
        assertEquals(Result.Status.SUCCESS, result.getStatus());
    }

    @Test
    public void testFailValidation() {
        CommissionHandler handler = new CommissionHandler(makeSystemStub());

        exception.expect(IllegalArgumentException.class);
        handler.handle(makeIncompatibleDetails());
    }

    private CommissionSystemStub makeSystemStub() {
        return new CommissionSystemStub() {
            @Override
            public Result generateCommission(String agentName) {
                assertEquals(EXPECTED_AGENT_NAME, agentName);
                return new Result(Result.Status.SUCCESS, "");
            }
        };
    }

    private CommissionDetails makeDetails() {
        return new CommissionDetails() {
            @Override
            public ProductType getProductType() {
                return ProductType.PHYSICAL;
            }

            @Override
            public ProductSubType getProductSubType() {
                return PhysicalSubType.BOOK;
            }

            @Override
            public String getAgentName() {
                return EXPECTED_AGENT_NAME;
            }
        };
    }
}
