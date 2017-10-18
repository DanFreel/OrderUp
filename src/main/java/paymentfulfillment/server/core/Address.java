package paymentfulfillment.server.core;

import javax.xml.bind.annotation.*;


@XmlRootElement(name = "Address", namespace = "http://core.server.paymentfulfillment/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Address", namespace = "http://core.server.paymentfulfillment/")
public class Address {
    @XmlElement(name = "addressLine1", namespace = "")
    private String addressLine1;

    @XmlElement(name = "addressLine2", namespace = "")
    private String addressLine2;

    @XmlElement(name = "postalCode", namespace = "")
    private String postalCode;

    @XmlElement(name = "city", namespace = "")
    private String city;

    @XmlElement(name = "country", namespace = "")
    private String country;

    public Address() {

    }

    public Address(String addressLine1, String addressLine2, String postalCode, String city, String country) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
