package paymentfulfillment.server.rule;


import paymentfulfillment.server.core.Result;
import paymentfulfillment.server.service.message.PaymentFulfillmentRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RulesEngine {
    private List<Rule> rules;

    public RulesEngine(RulesLoader loader) {
        rules = loader.loadRules();
    }

    public List<Optional<Result>> applyRules(PaymentFulfillmentRequest request) {
        return rules.stream().map(rule -> rule.runRule(request.getDetails())).collect(Collectors.toList());
    }
}
