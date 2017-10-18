package paymentfulfillment.server.rule.handler;


import paymentfulfillment.server.core.Result;
import paymentfulfillment.server.externalstub.EmailNotificationSystemStub;
import paymentfulfillment.server.service.message.purchasedetails.EmailNotificationDetails;
import paymentfulfillment.server.service.message.purchasedetails.PurchaseDetails;

import java.util.logging.Level;
import java.util.logging.Logger;

public class EmailNotificationHandler extends PaymentFulfillmentHandler {
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());
    private EmailNotificationSystemStub emailSystem;

    public EmailNotificationHandler(EmailNotificationSystemStub emailSystem) {
        this.emailSystem = emailSystem;
    }

    @Override
    protected Result handleImpl(PurchaseDetails details) {
        EmailNotificationDetails emailNotiDetails = (EmailNotificationDetails) details;
        LOGGER.log(Level.INFO, "Sending notification email to " + emailNotiDetails.getEmailAddress() + ".");

        return emailSystem.sendEmail(emailNotiDetails.getEmailAddress(), emailNotiDetails.getEmailBody());
    }

    @Override
    protected void validateRequest(PurchaseDetails details) {
        if(!(details instanceof EmailNotificationDetails))
            throw new IllegalArgumentException(
                    "Expected EmailNotificationDetails, but received " + details);
    }
}
