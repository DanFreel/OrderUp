package paymentfulfillment.server.rule.handler;


import paymentfulfillment.server.core.Result;
import paymentfulfillment.server.core.producttype.MembershipSubType;
import paymentfulfillment.server.externalstub.MembershipProcessingSystemStub;
import paymentfulfillment.server.service.message.purchasedetails.MembershipDetails;
import paymentfulfillment.server.service.message.purchasedetails.PurchaseDetails;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MembershipHandler extends PaymentFulfillmentHandler {
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());
    private MembershipProcessingSystemStub membershipSystem;

    public MembershipHandler(MembershipProcessingSystemStub membershipSystem) {
        this.membershipSystem = membershipSystem;
    }

    @Override
    protected Result handleImpl(PurchaseDetails details) {
        MembershipDetails membershipDetails = (MembershipDetails) details;

        if(MembershipSubType.CREATE.equals(membershipDetails.getProductSubType())) {
            LOGGER.log(Level.INFO, "Creating membership: " + membershipDetails.getMembershipId());

            return membershipSystem.createMembership(membershipDetails.getMembershipId(),
                                                     membershipDetails.getMemberName());
        }
        else if (MembershipSubType.UPGRADE.equals(membershipDetails.getProductSubType())) {
            LOGGER.log(Level.INFO, "Upgrading membership: " + membershipDetails.getMembershipId());
            return membershipSystem.upgradeMembership(membershipDetails.getMembershipId(),
                                                      membershipDetails.getMemberName());
        }

        String errorMsg = "Unsupported Membership Sub Type: " + membershipDetails.getProductSubType();
        LOGGER.log(Level.SEVERE, errorMsg);
        throw new UnsupportedOperationException(errorMsg);
    }

    @Override
    protected void validateRequest(PurchaseDetails details) {
        if(!(details instanceof MembershipDetails))
            throw new IllegalArgumentException("Expected MembershipDetails, but received " + details);
    }
}
