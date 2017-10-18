package paymentfulfillment.server.core;

import javax.xml.bind.annotation.*;


@XmlRootElement(name = "Result", namespace = "http://core.server.paymentfulfillment/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Result", namespace = "http://core.server.paymentfulfillment/")
public class Result {
    public enum Status {
        SUCCESS,
        FAILURE
    }

    @XmlElement(name = "status", namespace = "")
    private Status status;

    @XmlElement(name = "reason", namespace = "")
    private String reason;

    public Result() {

    }

    public Result(Status status, String reason) {
        this.status = status;
        this.reason = reason;
    }

    public Status getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Status: " + status + " - Reason: " + reason;
    }
}
