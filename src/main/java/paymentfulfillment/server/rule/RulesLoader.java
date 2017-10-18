package paymentfulfillment.server.rule;


import paymentfulfillment.server.core.producttype.PhysicalSubType;
import paymentfulfillment.server.core.producttype.ProductType;
import paymentfulfillment.server.externalstub.*;
import paymentfulfillment.server.rule.handler.CommissionHandler;
import paymentfulfillment.server.rule.handler.EmailNotificationHandler;
import paymentfulfillment.server.rule.handler.MembershipHandler;
import paymentfulfillment.server.rule.handler.PackingSlipHandler;
import paymentfulfillment.server.rule.handler.PackingSlipHandler.PackingSlipIntent;
import paymentfulfillment.server.rule.handler.packingslip.FirstAidVideoPackingSlipEnrichmentDelegate;
import paymentfulfillment.server.rule.handler.packingslip.SupplementaryPackingSlipEnrichmentDelegate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class RulesLoader {
    private SystemStubSource systems;
    private List<Rule> rules;

    public RulesLoader(SystemStubSource systems) {
        this.systems = systems;
        rules = createRules();
    }

    public RulesLoader(List<Rule> rules) {
        this.rules = new ArrayList<>(rules);
    }

    List<Rule> loadRules() {
        return rules;
    }

    private List<Rule> createRules() {
         return Arrays.asList(
            new Rule(
                "Packing Slip Rule",
                det -> ProductType.PHYSICAL.equals(det.getProductType()),
                new PackingSlipHandler(Arrays.asList(new FirstAidVideoPackingSlipEnrichmentDelegate(),
                                                     new SupplementaryPackingSlipEnrichmentDelegate(
                                                         det -> PhysicalSubType.BOOK.equals(det.getProductSubType()),
                                                         PackingSlipIntent.ROYALTY_DEP)),
                                       systems.getPackingSlipSystemStub(),
                                       PackingSlipIntent.SHIPPING)),
            new Rule(
                "Commission Generation Rule",
                det -> ProductType.PHYSICAL.equals(det.getProductType()),
                new CommissionHandler(systems.getCommissionSystemStub())),
            new Rule(
                "Membership Rule",
                det -> ProductType.MEMBERSHIP.equals(det.getProductType()),
                new MembershipHandler(systems.getMembershipSystem())),
            new Rule(
                "Email Notification Rule",
                det -> ProductType.MEMBERSHIP.equals(det.getProductType()),
                new EmailNotificationHandler(systems.getEmailNotificationSystemStub()))
        );
    }
}
