package paymentfulfillment.server.rule;


import org.junit.Test;
import paymentfulfillment.server.core.Result;
import paymentfulfillment.server.core.Result.Status;
import paymentfulfillment.server.core.producttype.MembershipSubType;
import paymentfulfillment.server.core.producttype.PhysicalSubType;
import paymentfulfillment.server.core.producttype.ProductType;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RuleTest extends RuleTestsSetup {

    @Test
    public void testRuleAppliedSuccess() {
        runRuleAppliedTest(true, Status.SUCCESS);
    }

    @Test
    public void testRuleAppliedFailure() {
        runRuleAppliedTest(false, Status.FAILURE);
    }

    @Test
    public void testRuleNotApplied() {
        Rule rule = makeRule(true);

        assertFalse(
             "Not expecting any Results returned by the rule",
             rule.runRule(makeDetails(ProductType.PHYSICAL, PhysicalSubType.GAME)).isPresent());
    }

    private void runRuleAppliedTest(boolean isSuccessful, Result.Status expectedStatus) {
        Rule rule = makeRule(isSuccessful);
        Optional<Result> resultOpt = rule.runRule(makeDetails(ProductType.MEMBERSHIP, MembershipSubType.CREATE));

        assertTrue("Expecting Result returned by the rule", resultOpt.isPresent());
        assertEquals(expectedStatus, resultOpt.get().getStatus());
    }
}
