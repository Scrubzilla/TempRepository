package Beans;

import javax.ejb.Stateless;
import javax.xml.ws.WebServiceRef;
//import ws.JspRPGWebService_Service;

/**
 *
 * @author User
 */
@Stateless
public class BankHandler {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    //Lägg till en web Service method från den där bankappen, strulade så fick ta bort lite i denna klassen, du ser det utkommenterade i metoden nedanför
    public String makeThePayment(String creditcard, String amount) {
        String response = ""; //makePayments(creditcard, amount);
        return response;
    }

}
