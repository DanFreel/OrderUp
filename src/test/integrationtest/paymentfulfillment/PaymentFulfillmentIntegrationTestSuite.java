package paymentfulfillment;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import paymentfulfillment.server.PaymentFulfillmentTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({PaymentFulfillmentTest.class})
public class PaymentFulfillmentIntegrationTestSuite {
}
