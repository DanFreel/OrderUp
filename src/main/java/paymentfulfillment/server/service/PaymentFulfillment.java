package paymentfulfillment.server.service;

import paymentfulfillment.server.service.message.PaymentFulfillmentRequest;
import paymentfulfillment.server.service.message.PaymentFulfillmentResponse;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface PaymentFulfillment {
    @WebMethod
    PaymentFulfillmentResponse service(PaymentFulfillmentRequest request);
}
