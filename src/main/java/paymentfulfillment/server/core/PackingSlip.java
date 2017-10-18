package paymentfulfillment.server.core;


import paymentfulfillment.server.rule.handler.PackingSlipHandler.PackingSlipIntent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PackingSlip {

    private String customerName;
    private List<String> orderItems;
    private Address shippingAddress;
    private PackingSlipIntent packingSlipIntent;

    public PackingSlip(String customerName,
                       String purchasedItem,
                       Address shippingAddress,
                       PackingSlipIntent packingSlipIntent) {
        this.customerName = customerName;
        this.shippingAddress = shippingAddress;
        this.packingSlipIntent = packingSlipIntent;
        orderItems = new ArrayList<>(Collections.singletonList(purchasedItem));
    }

    public String getCustomerName() {
        return customerName;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public PackingSlipIntent getPackingSlipIntent() {
        return packingSlipIntent;
    }

    public List<String> getOrderItems() {
        return new ArrayList<>(orderItems);
    }

    public void addOrderItem(String item) {
        orderItems.add(item);
    }

    @Override
    public String toString() {
        return "Customer Name: " + customerName
            + " - Ordered Items: " + orderItems
            + " - packingSlipIntent: " + packingSlipIntent;
    }
}
