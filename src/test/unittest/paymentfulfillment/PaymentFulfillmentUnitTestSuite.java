package paymentfulfillment;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import paymentfulfillment.server.rule.RuleTest;
import paymentfulfillment.server.rule.RulesEngineTest;
import paymentfulfillment.server.rule.handler.CommissionHandlerTest;
import paymentfulfillment.server.rule.handler.EmailNotificationHandlerTest;
import paymentfulfillment.server.rule.handler.MembershipHandlerTest;
import paymentfulfillment.server.rule.handler.PackingSlipHandlerTest;
import paymentfulfillment.server.rule.handler.packingslip.FirstAidVideoPackingSlipEnrichmentDelegateTest;
import paymentfulfillment.server.rule.handler.packingslip.SupplementaryPackingSlipEnrichmentDelegateTest;
import paymentfulfillment.server.service.PaymentFulfillmentImplTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        RuleTest.class,
        RulesEngineTest.class,
        PackingSlipHandlerTest.class,
        MembershipHandlerTest.class,
        CommissionHandlerTest.class,
        EmailNotificationHandlerTest.class,
        FirstAidVideoPackingSlipEnrichmentDelegateTest.class,
        SupplementaryPackingSlipEnrichmentDelegateTest.class,
        PaymentFulfillmentImplTest.class
})
public class PaymentFulfillmentUnitTestSuite {
}
