package paymentfulfillment.server.service.message.purchasedetails;


public interface EmailNotificationDetails extends PurchaseDetails {
    String getEmailAddress();
    String getEmailBody();
}
