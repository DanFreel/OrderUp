package paymentfulfillment.server.service.message;


import paymentfulfillment.server.service.message.purchasedetails.PurchaseDetails;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "PaymentFulfillmentRequest", namespace = "http://message.service.server.paymentfulfillment/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentFulfillmentRequest", namespace = "http://message.service.server.paymentfulfillment/")
public class PaymentFulfillmentRequest {
    @XmlElement(name = "details", namespace = "")
    private PurchaseDetails details;

    public PaymentFulfillmentRequest() {

    }

    public PaymentFulfillmentRequest(PurchaseDetails details) {
        this.details = details;
    }

    public PurchaseDetails getDetails() {
        return details;
    }
}
