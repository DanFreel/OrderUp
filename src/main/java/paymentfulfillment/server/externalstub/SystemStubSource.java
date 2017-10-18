package paymentfulfillment.server.externalstub;


public class SystemStubSource {
    private PackingSlipSystemStub packingSlipSystemStub;
    private MembershipProcessingSystemStub membershipSystem;
    private EmailNotificationSystemStub emailNotificationSystemStub;
    private CommissionSystemStub commissionSystemStub;

    public SystemStubSource() {
        packingSlipSystemStub = new PackingSlipSystemStub();
        membershipSystem = new MembershipProcessingSystemStub();
        emailNotificationSystemStub = new EmailNotificationSystemStub();
        commissionSystemStub = new CommissionSystemStub();
    }

    public SystemStubSource(PackingSlipSystemStub psSystem,
                            MembershipProcessingSystemStub msSystem,
                            EmailNotificationSystemStub enSystem,
                            CommissionSystemStub commSystem) {
        packingSlipSystemStub = psSystem;
        membershipSystem = msSystem;
        emailNotificationSystemStub = enSystem;
        commissionSystemStub = commSystem;
    }

    public PackingSlipSystemStub getPackingSlipSystemStub() {
        return packingSlipSystemStub;
    }

    public MembershipProcessingSystemStub getMembershipSystem() {
        return membershipSystem;
    }

    public EmailNotificationSystemStub getEmailNotificationSystemStub() {
        return emailNotificationSystemStub;
    }

    public CommissionSystemStub getCommissionSystemStub() {
        return commissionSystemStub;
    }
}
