package paymentfulfillment.client;


import paymentfulfillment.server.core.Address;
import paymentfulfillment.server.core.producttype.MembershipSubType;
import paymentfulfillment.server.core.producttype.PhysicalSubType;
import paymentfulfillment.server.service.PaymentFulfillment;
import paymentfulfillment.server.service.message.PaymentFulfillmentRequest;
import paymentfulfillment.server.service.message.purchasedetails.MembershipDetails;
import paymentfulfillment.server.service.message.purchasedetails.PhysicalProductDetails;
import paymentfulfillment.server.service.message.purchasedetails.PurchaseDetails;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public class SimpleClient {
    private PaymentFulfillment payFulfillment;

    private SimpleClient(URL url) {
        QName qname = new QName("http://service.server.paymentfulfillment/", "PaymentFulfillmentImplService");
        Service service = Service.create(url, qname);

        payFulfillment = service.getPort(PaymentFulfillment.class);
    }

    private void runMembershipScenario() {
        MembershipDetails details =
                new MembershipDetails(MembershipSubType.CREATE, 123456, "James Bond", "007@mi6.com");

        runAndOutput(details);
    }

    private  void runPhysicalProductScenario(PhysicalSubType subType, String itemName) {
        Address dummyAddress = new Address("Some Address",
                                           "That is Not Validated",
                                           "Some Postal Code",
                                           "Some City",
                                           "Some Country");

        PhysicalProductDetails details =
                new PhysicalProductDetails(subType, "Random Shopper", itemName, dummyAddress, "Some Agent");

        runAndOutput(details);
    }

    private void runAndOutput(PurchaseDetails details) {
        System.out.println(payFulfillment.service(new PaymentFulfillmentRequest(details)).getResult());
    }


    public static void main(String[] args) throws Exception {
        SimpleClient client = new SimpleClient(new URL("http://localhost:9999/ws/orderup?wsdl"));

        client.runMembershipScenario();
        client.runPhysicalProductScenario(PhysicalSubType.GAME, "Random Game");
        client.runPhysicalProductScenario(PhysicalSubType.BOOK, "Random Book");
        client.runPhysicalProductScenario(PhysicalSubType.VIDEO, "Random Video");
        client.runPhysicalProductScenario(PhysicalSubType.VIDEO, "Learning to Ski");
    }
}
