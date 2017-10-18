package paymentfulfillment.server.service;


import paymentfulfillment.server.core.Result;
import paymentfulfillment.server.externalstub.SystemStubSource;
import paymentfulfillment.server.rule.RulesEngine;
import paymentfulfillment.server.rule.RulesLoader;
import paymentfulfillment.server.service.message.PaymentFulfillmentRequest;
import paymentfulfillment.server.service.message.PaymentFulfillmentResponse;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@WebService(endpointInterface = "paymentfulfillment.server.service.PaymentFulfillment")
public class PaymentFulfillmentImpl implements PaymentFulfillment{

    private static final Logger LOGGER = Logger.getLogger(PaymentFulfillmentImpl.class.getName());
    private RulesEngine rulesEngine;

    public PaymentFulfillmentImpl(RulesLoader rulesLoader) {
        rulesEngine = new RulesEngine(rulesLoader);
    }

    PaymentFulfillmentImpl(RulesEngine rulesEngine) {
        this.rulesEngine = rulesEngine;
    }

    @Override
    public PaymentFulfillmentResponse service(PaymentFulfillmentRequest request) {
        LOGGER.log(Level.INFO, "Received PaymentFulfillmentRequest.");
        List<Optional<Result>> resultOptions = rulesEngine.applyRules(request);

        List<Result> results =
            resultOptions
                .stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        if (results.stream().anyMatch(r -> r.getStatus().equals(Result.Status.FAILURE)))
            LOGGER.log(Level.SEVERE, "Failure detected while processing rules. Results: \n" + results);
        else
            LOGGER.log(Level.INFO, "Request processed successfully. Results: \n" + results);

        return new PaymentFulfillmentResponse(results.toString());
    }

    public static void main(String[] args) {
        LOGGER.log(Level.INFO, "Starting server...");
        Endpoint.publish("http://localhost:9999/ws/orderup",
                         new PaymentFulfillmentImpl(new RulesLoader(new SystemStubSource())));
    }
}
