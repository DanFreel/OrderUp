package paymentfulfillment.server.service;


import org.junit.Test;
import paymentfulfillment.server.core.Result;
import paymentfulfillment.server.rule.RulesEngine;
import paymentfulfillment.server.rule.RulesLoader;
import paymentfulfillment.server.service.message.PaymentFulfillmentRequest;
import paymentfulfillment.server.service.message.PaymentFulfillmentResponse;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PaymentFulfillmentImplTest {
    @Test
    public void testServiceSuccess() {
        runTest(Result.Status.SUCCESS);
    }

    @Test
    public void testServiceFailure() {
        runTest(Result.Status.FAILURE);
    }

    private void runTest(Result.Status status) {
        RulesEngine engine = makeRulesEngine(status);
        PaymentFulfillmentImpl serv = new PaymentFulfillmentImpl(engine);

        PaymentFulfillmentResponse response = serv.service(new PaymentFulfillmentRequest());
        assertNotNull(response);
        assertEquals("[" + new Result(status, "message").toString() + "]", response.getResult());
    }

    private RulesEngine makeRulesEngine(Result.Status status) {
        return new RulesEngine(new RulesLoader(Collections.emptyList())) {
            @Override
            public List<Optional<Result>> applyRules(PaymentFulfillmentRequest request) {
                return Collections.singletonList(Optional.of(new Result(status, "message")));
            }
        };
    }
}
