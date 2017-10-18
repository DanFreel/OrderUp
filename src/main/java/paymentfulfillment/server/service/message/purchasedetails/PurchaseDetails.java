package paymentfulfillment.server.service.message.purchasedetails;


import paymentfulfillment.server.core.producttype.ProductSubType;
import paymentfulfillment.server.core.producttype.ProductType;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
@XmlSeeAlso({MembershipDetails.class, PhysicalProductDetails.class})
public interface PurchaseDetails {
    ProductType getProductType();
    ProductSubType getProductSubType();
}
