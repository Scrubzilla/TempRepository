/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

import Helpers.AreaHelper;
import Helpers.BattleLogicHelper;
import Helpers.CharacterHelper;
import Helpers.SpellsHelper;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
import javax.jws.WebMethod;
import javax.jws.WebService;
import jsrpg.Creature;
import jsrpg.Character;
import jsrpg.Spells;

/**
 *
 * @author User
 */
@Stateful
@WebService
public class BattleHandlerBean {

    CharacterHelper charHelp = new CharacterHelper();
    AreaHelper areaHelp = new AreaHelper();
    BattleLogicHelper batHelp = new BattleLogicHelper();
    SpellsHelper spellHelp = new SpellsHelper();

    List<Creature> creatureList = new ArrayList<>();
    List<Integer> creatureHPList = new ArrayList<>();
    List<Spells> spellsList = new ArrayList<>();
    Character character = null;
    int characterHP = 0;
    int characterMana = 0;
    int XP = 0;
    int money = 0;
    String zoneId;
    String areaId;

    /*<kslls på startingBattle när striden precis börjar, sen skicka in zoneId och areaId (1,2,3 på vardera)
     Sedan så kalla på getCreatureListName och getCharacterName för att få det du vill ha. Andra metoder kommer ränka ut HP:t som ska
     displaya etc.
     */
    @WebMethod
    public String startingBattle(String charName, String zoneId, String areaId) {
        resetAllStuff();
        this.zoneId = zoneId;
        this.areaId = areaId;

        character = charHelp.getCharacter(charName);
        creatureList = areaHelp.getCreaturesFromArea(zoneId, areaId);
        characterHP = batHelp.getHPfromVit(character.getVitality());
        characterMana = batHelp.getMPfromWisInt(character.getWisdom(), character.getIntelligence());
        creatureHPList = batHelp.getCreatureHP(creatureList);
        spellsList = spellHelp.getAllSpells();
        spellsList = batHelp.checkWhichSpellCharCanHave(spellsList, character.getIntelligence());

        return "Seuccesfully loaded all stuff for battle";
    }

    //Du får namnet på alla creatures i striden i en lista. Använd index positionen för att visa vilken fiende som blir targetad
    @WebMethod
    public List<String> getCreatureListName() {
        List<String> creatureNameList = new ArrayList<>();
        for (int i = 0; i < creatureList.size(); i++) {
            creatureNameList.add(creatureList.get(i).getName());
        }
        return creatureNameList;
    }

    @WebMethod
    public List<String> getspellsNameList() {
        List<String> spellsNameList = new ArrayList<>();
        for (int i = 0; i < spellsList.size(); i++) {
            spellsNameList.add(spellsList.get(i).getName());
        }
        return spellsNameList;
    }

    @WebMethod
    public String getCharacterName() {
        return character.getName();
    }

    @WebMethod
    public List<Integer> getCreatureHPList() {
        return creatureHPList;
    }

    @WebMethod
    public String getCharacterHP() {
        return characterHP + "";
    }

    @WebMethod
    public String getCharacterMana() {
        return characterMana + "";
    }

    @WebMethod
    public String attackCreature(String creaturePos) {
        
        int oldHP = creatureHPList.get(Integer.parseInt(creaturePos));
        creatureHPList.set(Integer.parseInt(creaturePos), batHelp.newCreatureHPafterAttack(creatureHPList.get(Integer.parseInt(creaturePos)), creatureList.get(Integer.parseInt(creaturePos)), character, null, false));
        
        int dmg = oldHP - creatureHPList.get(Integer.parseInt(creaturePos));
        
        
        if (creatureHPList.get(Integer.parseInt(creaturePos)) <= 0) {
            creatureHPList.remove(Integer.parseInt(creaturePos));
            creatureList.remove(Integer.parseInt(creaturePos));
            if (creatureList.size() == 0) {
                int xp = updateXP(character);
                resetAllStuff();
                return "You attack the creature with a mighty swing for " + dmg + " damage.\nAll creatures defeated! \nYou have recieved " + Integer.toString(xp) + " experience points!";
            } else {
                return "You attack the creature with a mighty swing for " + dmg + " damage. The creature is now dead, woo";
            }

        }

        return "You attack the creature with a mighty swing for " + dmg + " damage.";
    }

    @WebMethod
    public String creaturesAttackPlayer() {
        int amount = 0;
        for (int i = 0; i < creatureList.size(); i++) {

            amount += batHelp.creatureAttacksHero(character.getStrength(), character.getVitality(), creatureList.get(i).getAttack());
        }
        characterHP -= amount;

        if (characterHP <= 0) {
            resetAllStuff(); 
            return "The creatures killed you, tough luck";
            
        }

        return "Creatures attacked you for " + amount;
    }

    @WebMethod
    public String useSpellOnCreature(String spellPos, String creaturePos) {
        characterMana -= spellsList.get(Integer.parseInt(spellPos)).getManacost();
        if (characterMana <= 0) {
            return "Cannot use spell, not enough Mana";
        }

        int newHP = batHelp.newCreatureHPafterAttack(creatureHPList.get(Integer.parseInt(creaturePos)), creatureList.get(Integer.parseInt(creaturePos)), character, spellsList.get(Integer.parseInt(spellPos)), true);
        int dmg = creatureHPList.get(Integer.parseInt(creaturePos)) - newHP;
        
        creatureHPList.set(Integer.parseInt(creaturePos), newHP);

        if (creatureHPList.get(Integer.parseInt(creaturePos)) <= 0) {

            creatureHPList.remove(Integer.parseInt(creaturePos));
            creatureList.remove(Integer.parseInt(creaturePos));
            if (creatureList.size() == 0) {
                int xp = updateXP(character);
                resetAllStuff();
                return "You attack the creature with a powerful " + spellsList.get(Integer.parseInt(spellPos)).getName() + " for " + dmg + " damage.\nAll creatures defeated! \nYou have recieved " + Integer.toString(xp) + " experience points!";
            } else {
                return "You attack the creature with a powerful " + spellsList.get(Integer.parseInt(spellPos)).getName() + " for " + dmg + " damage. The creature is now dead, woo";
            }

        }
        return "You attack the creature with a powerful " + spellsList.get(Integer.parseInt(spellPos)).getName() + " for "  + dmg + " damage.";
    }
    
    @WebMethod
    public List<String> getCreaturesBeforeBattle(String zoneId, String areaId){
        creatureList = null;
        creatureList = areaHelp.getCreaturesFromArea(zoneId, areaId);
        List<String> creatureNameList = new ArrayList<>();
        for (int i = 0; i < creatureList.size(); i++) {
            creatureNameList.add(creatureList.get(i).getName());
        }
        return creatureNameList;
        
    }

    private int updateXP(Character chara) {
        int xp = batHelp.getXPAmountFromBattle(zoneId, areaId);
        charHelp.updateXP(xp, character.getName());
        
        return xp;
    }

    private void resetAllStuff() {
        creatureList = null;
        creatureHPList = null;
        spellsList = null;
        character = null;
        characterHP = 0;
        characterMana = 0;
        XP = 0;
        money = 0;
        zoneId = "";
        areaId = "";
    }
}
