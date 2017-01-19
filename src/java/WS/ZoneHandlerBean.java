/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

import Helpers.CharacterHelper;
import Singletons.ZoneStorage;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import jsrpg.Character; 


/**
 *
 * @author User
 */
@Stateless
@WebService
public class ZoneHandlerBean {

    @WebMethod
    public String addAccountToZone(String accName, String zoneId) {
        ZoneStorage.getInstance().addAccountToZone(zoneId, accName);
        return "";
    }
    @WebMethod
    public String moveAccountFromZone(String accName) {
        ZoneStorage.getInstance().moveAccoutFromZone(accName);
        return "";
    }
    @WebMethod
    public List<String> getZone(String zoneId){
        List<String> zone = null;
        if(zoneId.equalsIgnoreCase("1")){
            zone = ZoneStorage.getInstance().getZone1();
        }else if(zoneId.equalsIgnoreCase("2")){
            zone = ZoneStorage.getInstance().getZone2();
        }else if(zoneId.equalsIgnoreCase("3")){
            zone = ZoneStorage.getInstance().getZone3();
        }
        
        return zone;
    }
    
    @WebMethod
    public String changeCharLocationInDB(String charName, String zoneId){
        
        CharacterHelper charHelp = new CharacterHelper();
        //Character chara = charHelp.getCharacterFromAccountName(accName);
        charHelp.changeLocationOfChar(charName, zoneId);
        
        
        return "";
    }
    
    
    @WebMethod
    public List<String> getzone1Chat(){
        return ZoneStorage.getInstance().getZone1Chat();
    }
    
    @WebMethod
    public List<String> getzone2Chat(){
        return ZoneStorage.getInstance().getZone2Chat();
    }
    
    @WebMethod
    public List<String> getzone3Chat(){
        return ZoneStorage.getInstance().getZone3Chat();
    }
    
    @WebMethod
    public String addToZone1Chat(String message, String username){
        ZoneStorage.getInstance().addMessageToChat1(username + " - " + message);
        return "successfully added the meesage to chat";
    }
    
    @WebMethod
    public String addToZone2Chat(String message, String username){
        ZoneStorage.getInstance().addMessageToChat2(username + " - " + message);
        return "successfully added the meesage to chat";
    }
    
    @WebMethod
    public String addToZone3Chat(String message, String username){
        ZoneStorage.getInstance().addMessageToChat3(username + " - " + message);
        return "successfully added the meesage to chat";
    }
}
