package paymentfulfillment.server.rule.handler;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import paymentfulfillment.server.core.Result;
import paymentfulfillment.server.core.producttype.MembershipSubType;
import paymentfulfillment.server.core.producttype.ProductSubType;
import paymentfulfillment.server.core.producttype.ProductType;
import paymentfulfillment.server.externalstub.EmailNotificationSystemStub;
import paymentfulfillment.server.service.message.purchasedetails.EmailNotificationDetails;

import static org.junit.Assert.assertEquals;

public class EmailNotificationHandlerTest extends HandlerTestSetup {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private final String EXPECTED_EMAIL = "email";
    private final String EXPECTED_BODY = "body";

    @Test
    public void testHandle() {
        EmailNotificationHandler handler = new EmailNotificationHandler(makeSystemStub());
        Result result = handler.handle(makeDetails());
        assertEquals(Result.Status.SUCCESS, result.getStatus());
    }

    @Test
    public void testFailValidation() {
        EmailNotificationHandler handler = new EmailNotificationHandler(makeSystemStub());

        exception.expect(IllegalArgumentException.class);
        handler.handle(makeIncompatibleDetails());
    }

    private EmailNotificationSystemStub makeSystemStub() {
        return new EmailNotificationSystemStub() {
            @Override
            public Result sendEmail(String recipient, String message) {
                assertEquals(EXPECTED_EMAIL, recipient);
                assertEquals(EXPECTED_BODY, message);
                return new Result(Result.Status.SUCCESS, "");
            }
        };
    }

    private EmailNotificationDetails makeDetails() {
        return new EmailNotificationDetails() {
            @Override
            public ProductType getProductType() {
                return ProductType.MEMBERSHIP;
            }

            @Override
            public ProductSubType getProductSubType() {
                return MembershipSubType.CREATE;
            }

            @Override
            public String getEmailAddress() {
                return EXPECTED_EMAIL;
            }

            @Override
            public String getEmailBody() {
                return EXPECTED_BODY;
            }
        };
    }
}
