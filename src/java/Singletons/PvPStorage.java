/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Singletons;

import Helpers.CharacterHelper;
import Helpers.SpellsHelper;
import Pojo.PvPCharInfoHolder;
import java.util.ArrayList;
import java.util.List;
import jsrpg.Spells;
import jsrpg.Character;

/**
 *
 * @author User
 */
public class PvPStorage {
    private CharacterHelper charHelp = new CharacterHelper();
    
    private static PvPStorage pvpStorage;
    private List<PvPCharInfoHolder> charInfoHolder = new ArrayList<>();
    
    private PvPStorage(){}
    
    public static PvPStorage getInstance(){
        if(pvpStorage == null){
            pvpStorage = new PvPStorage();
        }
        return pvpStorage;
    }
    
    public void createBothChars(String p1Username, String p2Username){
        Character P1Char = charHelp.getCharacterFromAccountName(p1Username);
        Character P2Char = charHelp.getCharacterFromAccountName(p2Username);
        
        
        int hpP1 = calcHP(P1Char.getVitality());
        int hpP2 = calcHP(P2Char.getVitality());
        
        int armorP1 = calcArmor(P1Char.getVitality(), P1Char.getStrength());
        int armorP2 = calcArmor(P2Char.getVitality(), P2Char.getStrength());
        
        int manaP1 = calcMana(P1Char.getWisdom(), P1Char.getIntelligence());
        int manaP2 = calcMana(P2Char.getWisdom(), P2Char.getIntelligence());
        
        List<Spells> spellListP1 = getSpells(P1Char.getIntelligence());
        List<Spells> spellListP2 = getSpells(P2Char.getIntelligence());
        
        int battleId = charInfoHolder.size()/2;
        
        
        
        createChar(p1Username, P1Char.getName(), hpP1, manaP1, P1Char.getStrength(), P1Char.getDexterity(), P1Char.getVitality(), P1Char.getWisdom(),
                   P1Char.getIntelligence(), P1Char.getCharisma(), armorP1 ,spellListP1, battleId, p2Username);
        createChar(p2Username, P2Char.getName(), hpP2, manaP2, P2Char.getStrength(), P2Char.getDexterity(), P2Char.getVitality(), P2Char.getWisdom(),
                   P2Char.getIntelligence(), P2Char.getCharisma(), armorP2 ,spellListP2, battleId, p1Username);
        
    }
    
    public Integer getHP(String username){
        int hp = 0;
        
        for(PvPCharInfoHolder chara : charInfoHolder){
            if(chara.getUsername().equalsIgnoreCase(username)){
                hp = chara.getHp();
            }
        }
        return hp; 
    }
    
    public Integer getMana(String username){
        int mana = 0;
        
        for(PvPCharInfoHolder chara : charInfoHolder){
            if(chara.getUsername().equalsIgnoreCase(username)){
                mana = chara.getMana();
            }
        }
        return mana; 
    }
    
    public String getOpponentName(String username){
        String name = "";
        
        for(PvPCharInfoHolder chara : charInfoHolder){
            if(chara.getUsername().equalsIgnoreCase(username)){
                name = chara.getOpponentUsername();
            }
        }
        return name;
    }
    
    private void createChar(String username ,String charName, int hp, int mana, int str, int dex, int vit, 
            int wis, int intell, int chr, int armor ,List<Spells> spellList, int battleId, String opponentUsername){
        
        PvPCharInfoHolder charHolder = new PvPCharInfoHolder(username, charName, hp, mana, str, dex, vit, wis, intell, chr, armor, spellList, battleId, opponentUsername);
        charInfoHolder.add(charHolder);
    
    }
    
    private Integer calcHP(int vit){
        return vit * 10;
    }
    
    private Integer calcArmor(int vit, int str){
        return vit + str;
    }
    
    private Integer calcMana(int wis, int intell){
        return wis + intell;
    }
    
    private List<Spells> getSpells(int intell){
        SpellsHelper spelHelp = new SpellsHelper();
        return checkWhichSpellCharCanHave(spelHelp.getAllSpells(), intell);
    }
    
    public List<Spells> checkWhichSpellCharCanHave(List<Spells> spellList, int intell){
        List<Spells> useableSpellsList = null;
        if(intell >= 10){
            useableSpellsList.add(spellList.get(1));
        }else if(intell >= 12){
            useableSpellsList.add(spellList.get(2));
        }else if(intell >= 14){
            useableSpellsList.add(spellList.get(0));
        }
        return useableSpellsList;
    }
    
    private PvPCharInfoHolder findUser(String name, String oppOrNot){
        PvPCharInfoHolder PvPChar = null;
        
        for(PvPCharInfoHolder chara : charInfoHolder){
            if(oppOrNot.equalsIgnoreCase("0")){
                if(chara.getUsername().equalsIgnoreCase(name)){
                    PvPChar = chara;
                }
            }else if(oppOrNot.equalsIgnoreCase("1")){
                if(chara.getOpponentUsername().equalsIgnoreCase(name)){
                    PvPChar = chara;
                }
            }
        }
        
        return PvPChar;
    }
    
    
    //------- The battle logic ------------->
    
    public String attackOpp(String username){
        PvPCharInfoHolder attackChar = findUser(username, "0");
        PvPCharInfoHolder oppChar = findUser(username, "1");
        int dmg = calcAttackAmount(oppChar.getArmor(), attackChar.getStr(), attackChar.getDex());
        updateHP(oppChar.getUsername(), dmg);
        
        return oppChar.getCharName() + " has been attacked for " + dmg;
    }
    
    public String spellAttOpp(String username, String spellId){
        PvPCharInfoHolder attackChar = findUser(username, "0");
        PvPCharInfoHolder oppChar = findUser(username, "1");
        int spellDmg = calcSpellDmg(attackChar.getSpellList().get(Integer.parseInt(spellId)), attackChar.getIntell());
        updateHP(oppChar.getUsername(), spellDmg);
        updateMana(attackChar.getUsername(), attackChar.getSpellList().get(Integer.parseInt(spellId)).getManacost());
        
        
        return oppChar.getCharName() + " has been attacked for " + spellDmg;
    }
    
    
    private Integer calcAttackAmount(int armor, int str, int dex){
        double attack = (str + dex) * (armor/100); 
        return (int) attack;
    }
    
    private void updateHP(String username, int dmg){
        for(PvPCharInfoHolder chara : charInfoHolder){
            if(chara.getUsername().equalsIgnoreCase(username)){
                chara.setHp( chara.getHp() - dmg  );
            }
        } 
    }
    
    private Integer calcSpellDmg(Spells spell, int intell){
        double amount = 0;
        amount = spell.getDamage() * intell;
        
        return (int) amount;
    }
    
    private void updateMana(String username, int mana){
        for(PvPCharInfoHolder chara : charInfoHolder){
            if(chara.getUsername().equalsIgnoreCase(username)){
                chara.setMana( chara.getMana() - mana);
            }
        } 
    }
    
    
}
