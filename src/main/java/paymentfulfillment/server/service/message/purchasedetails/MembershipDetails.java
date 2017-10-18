package paymentfulfillment.server.service.message.purchasedetails;


import paymentfulfillment.server.core.producttype.MembershipSubType;
import paymentfulfillment.server.core.producttype.ProductType;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "MembershipDetails", namespace = "http://purchasedetails.message.service.server.paymentfulfillment/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MembershipDetails", namespace = "http://purchasedetails.message.service.server.paymentfulfillment/")
public class MembershipDetails implements EmailNotificationDetails {

    private static final String EMAIL_BODY_TEMPLATE =
            "Dear %s, this is a notification to let you know the following operation was performed on " +
                    "membership [ID = %s]: %s. Thank you.";

    @XmlElement(name = "productSubType", namespace = "")
    private MembershipSubType productSubType;

    @XmlElement(name = "membershipId", namespace = "")
    private long membershipId;

    @XmlElement(name = "memberName", namespace = "")
    private String memberName;

    @XmlElement(name = "emailAddress", namespace = "")
    private String emailAddress;

    private String emailBody;

    public MembershipDetails() {

    }

    public MembershipDetails(MembershipSubType productSubType,
                             long membershipId,
                             String memberName,
                             String emailAddress) {
        this.productSubType = productSubType;
        this.membershipId = membershipId;
        this.memberName = memberName;
        this.emailAddress = emailAddress;
        emailBody = String.format(EMAIL_BODY_TEMPLATE, memberName, membershipId, productSubType.name());
    }

    @Override
    public ProductType getProductType() {
        return ProductType.MEMBERSHIP;
    }

    @Override
    public MembershipSubType getProductSubType() {
        return productSubType;
    }

    public long getMembershipId() {
        return membershipId;
    }

    public String getMemberName() {
        return memberName;
    }

    @Override
    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String getEmailBody() {
        return emailBody;
    }

    public void setProductSubType(MembershipSubType productSubType) {
        this.productSubType = productSubType;
    }

    public void setMembershipId(long membershipId) {
        this.membershipId = membershipId;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
