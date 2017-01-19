/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

import Singletons.PvPStorage;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author User
 */
@Stateless
@WebService
public class PvPHandlerBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @WebMethod
    public String startPvPbattle(String p1Username, String p2Username){
        PvPStorage.getInstance().createBothChars(p1Username, p2Username);
        return "PvP battle started";
    }
    @WebMethod
    public String attackOpp(String username){
        String response = PvPStorage.getInstance().attackOpp(username);
        return response;
    }
    @WebMethod
    public String useSpell(String username, String spellId){
        String response = PvPStorage.getInstance().spellAttOpp(username, spellId);
        return response;
    }
    
   
    
    
}
