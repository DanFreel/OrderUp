package paymentfulfillment.server.service.message.purchasedetails;


import paymentfulfillment.server.core.Address;
import paymentfulfillment.server.core.producttype.PhysicalSubType;
import paymentfulfillment.server.core.producttype.ProductType;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "PhysicalProductDetails", namespace = "http://purchasedetails.message.service.server.paymentfulfillment/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PhysicalProductDetails", namespace = "http://purchasedetails.message.service.server.paymentfulfillment/")
public class PhysicalProductDetails implements ShippingDetails, CommissionDetails {
    @XmlElement(name = "productSubType", namespace = "")
    private PhysicalSubType productSubType;

    @XmlElement(name = "customerName", namespace = "")
    private String customerName;

    @XmlElement(name = "purchasedItem", namespace = "")
    private String purchasedItem;

    @XmlElement(name = "shippingAddress", namespace = "")
    private Address shippingAddress;

    @XmlElement(name = "agentName", namespace = "")
    private String agentName;

    public PhysicalProductDetails() {

    }

    public PhysicalProductDetails(PhysicalSubType productSubType,
                                  String customerName,
                                  String purchasedItem,
                                  Address shippingAddress,
                                  String agentName) {
        this.productSubType = productSubType;
        this.customerName = customerName;
        this.purchasedItem = purchasedItem;
        this.shippingAddress = shippingAddress;
        this.agentName = agentName;
    }

    @Override
    public ProductType getProductType() {
        return ProductType.PHYSICAL;
    }

    @Override
    public PhysicalSubType getProductSubType() {
        return productSubType;
    }

    @Override
    public String getCustomerName() {
        return customerName;
    }

    @Override
    public String getPurchasedItem() {
        return purchasedItem;
    }

    @Override
    public Address getShippingAddress() {
        return shippingAddress;
    }

    @Override
    public String getAgentName() {
        return agentName;
    }

    public void setProductSubType(PhysicalSubType productSubType) {
        this.productSubType = productSubType;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setPurchasedItem(String purchasedItem) {
        this.purchasedItem = purchasedItem;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }
}
