package paymentfulfillment.server.service.message.purchasedetails;


import paymentfulfillment.server.core.Address;

public interface ShippingDetails extends PurchaseDetails {
    String getCustomerName();
    String getPurchasedItem();
    Address getShippingAddress();
}
