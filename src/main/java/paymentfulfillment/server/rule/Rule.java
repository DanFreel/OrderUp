package paymentfulfillment.server.rule;


import paymentfulfillment.server.core.Result;
import paymentfulfillment.server.rule.handler.PaymentFulfillmentHandler;
import paymentfulfillment.server.service.message.purchasedetails.PurchaseDetails;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

class Rule {
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());
    private String ruleName;
    private Predicate<PurchaseDetails> condition;
    private PaymentFulfillmentHandler handler;

    Rule(String ruleName, Predicate<PurchaseDetails> condition, PaymentFulfillmentHandler handler) {
        LOGGER.log(Level.INFO, "Creating rule: " + ruleName);
        this.ruleName = ruleName;
        this.condition = condition;
        this.handler = handler;
    }

    Optional<Result> runRule(PurchaseDetails details) {
        if(condition.test(details)) {
            LOGGER.log(Level.INFO, "Running rule: " + ruleName);
            return Optional.of(handler.handle(details));
        }

        LOGGER.log(Level.INFO, "Skipping rule: " + ruleName);
        return Optional.empty();
    }
}
