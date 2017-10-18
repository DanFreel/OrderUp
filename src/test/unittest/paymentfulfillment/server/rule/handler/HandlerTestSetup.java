package paymentfulfillment.server.rule.handler;

import paymentfulfillment.server.core.producttype.PhysicalSubType;
import paymentfulfillment.server.core.producttype.ProductSubType;
import paymentfulfillment.server.core.producttype.ProductType;
import paymentfulfillment.server.service.message.purchasedetails.PurchaseDetails;


public class HandlerTestSetup {
    PurchaseDetails makeIncompatibleDetails() {
        return new PurchaseDetails() {
            @Override
            public ProductType getProductType() {
                return ProductType.PHYSICAL;
            }

            @Override
            public ProductSubType getProductSubType() {
                return PhysicalSubType.BOOK;
            }
        };
    }
}
