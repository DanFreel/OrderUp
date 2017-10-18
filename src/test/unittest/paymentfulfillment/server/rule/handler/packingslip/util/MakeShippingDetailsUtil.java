package paymentfulfillment.server.rule.handler.packingslip.util;


import paymentfulfillment.server.core.Address;
import paymentfulfillment.server.core.producttype.PhysicalSubType;
import paymentfulfillment.server.core.producttype.ProductSubType;
import paymentfulfillment.server.core.producttype.ProductType;
import paymentfulfillment.server.service.message.purchasedetails.ShippingDetails;

public class MakeShippingDetailsUtil {
    public static ShippingDetails makeDetails(PhysicalSubType subType, String name, String itemName) {
        return new ShippingDetails() {
            @Override
            public ProductType getProductType() {
                return ProductType.PHYSICAL;
            }

            @Override
            public ProductSubType getProductSubType() {
                return subType;
            }

            @Override
            public String getCustomerName() {
                return name;
            }

            @Override
            public String getPurchasedItem() {
                return itemName;
            }

            @Override
            public Address getShippingAddress() {
                return new Address("", "", "", "", "");
            }
        };
    }
}
