package paymentfulfillment.server.rule;


import org.junit.Test;
import paymentfulfillment.server.core.Result;
import paymentfulfillment.server.core.producttype.MembershipSubType;
import paymentfulfillment.server.core.producttype.ProductType;
import paymentfulfillment.server.service.message.PaymentFulfillmentRequest;
import paymentfulfillment.server.service.message.purchasedetails.PurchaseDetails;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RulesEngineTest extends RuleTestsSetup {
    @Test
    public void testSingleRule() {
        Rule rule = makeRule(true);
        runWithRulesTest(Collections.singletonList(rule), 1, 1, 1);
    }

    @Test
    public void testMultipleRules() {
        List<Rule> rules = Arrays.asList(makeRule(true), makeRule(false), makeRule(true, ProductType.PHYSICAL));
        runWithRulesTest(rules, 3, 2, 1);
    }

    @Test
    public void testNoRules() {
        RulesEngine engine = new RulesEngine(new RulesLoader(Collections.emptyList()));
        PurchaseDetails details = makeDetails(ProductType.MEMBERSHIP, MembershipSubType.UPGRADE);

        List<Optional<Result>> results = engine.applyRules(new PaymentFulfillmentRequest(details));
        assertTrue("Did not expect any results", results.isEmpty());
    }

    private void runWithRulesTest(List<Rule> rules, int expTotal, int expPresent, int expSuccess) {
        RulesEngine engine = new RulesEngine(new RulesLoader(rules));
        PurchaseDetails details = makeDetails(ProductType.MEMBERSHIP, MembershipSubType.UPGRADE);

        List<Optional<Result>> resultOpts = engine.applyRules(new PaymentFulfillmentRequest(details));
        assertEquals(expTotal, resultOpts.size());

        List<Result> results =
            resultOpts
                .stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        assertEquals(expPresent, results.size());
        assertEquals(
            expSuccess,
            results
                .stream()
                .filter(res -> res.getStatus().equals(Result.Status.SUCCESS))
                .count());
    }
}
