/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Singletons;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class ZoneStorage {
    
    private static ZoneStorage zoneStorage;
    private List<String> zone1 = new ArrayList<>();
    private List<String> zone2 = new ArrayList<>(); 
    private List<String> zone3 = new ArrayList<>(); 
    
    private List<String> zone1Chat = new ArrayList<>();
    private List<String> zone2Chat = new ArrayList<>();
    private List<String> zone3Chat = new ArrayList<>();
    
    private ZoneStorage(){}
    
    public static ZoneStorage getInstance(){
        if(zoneStorage == null){
            zoneStorage = new ZoneStorage();
        }
        return zoneStorage;
    }

    public List<String> getZone1() {
        return zone1;
    }

    public List<String> getZone2() {
        return zone2;
    }

    public List<String> getZone3() {
        return zone3;
    }
    
    public void addAccountToZone(String zone, String accName){
        if(zone.equalsIgnoreCase("1")){
            zone1.add(accName);
        }else if(zone.equalsIgnoreCase("2")){
            zone2.add(accName);
        }else if(zone.equalsIgnoreCase("3")){
            zone3.add(accName);
        }
    }
    
    public void moveAccoutFromZone(String accName){
        for(int i = 0; i < zone1.size(); i++){
            if(zone1.get(i).equalsIgnoreCase(accName)){
                zone1.remove(i);
            }
        }
        
        for(int i = 0; i < zone2.size(); i++){
            if(zone2.get(i).equalsIgnoreCase(accName)){
                zone2.remove(i);
            }
        }
        
        for(int i = 0; i < zone3.size(); i++){
            if(zone3.get(i).equalsIgnoreCase(accName)){
                zone3.remove(i);
            }
        }
    }

    public List<String> getZone1Chat() {
        return zone1Chat;
    }

    public List<String> getZone2Chat() {
        return zone2Chat;
    }

    public List<String> getZone3Chat() {
        return zone3Chat;
    }
    
    public void addMessageToChat1(String message){
        zone1Chat.add(message);
        
        if(zone1Chat.size() > 300){
            zone1Chat.remove(0);
        }
    }
    public void addMessageToChat2(String message){
        zone2Chat.add(message);
        
        if(zone2Chat.size() > 300){
            zone2Chat.remove(0);
        }
    }
    public void addMessageToChat3(String message){
        zone3Chat.add(message);
        
        if(zone3Chat.size() > 300){
            zone3Chat.remove(0);
        }
    }
    
}
