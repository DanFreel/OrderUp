package paymentfulfillment.server.service.message;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "PaymentFulfillmentResponse", namespace = "http://message.service.server.paymentfulfillment/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentFulfillmentResponse", namespace = "http://message.service.server.paymentfulfillment/")
public class PaymentFulfillmentResponse {
    @XmlElement(name = "result", namespace = "")
    private String result;

    public PaymentFulfillmentResponse() {

    }

    public PaymentFulfillmentResponse(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
