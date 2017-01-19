/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojo;

import java.util.ArrayList;
import java.util.List;
import jsrpg.Spells;

/**
 *
 * @author User
 */
public class PvPCharInfoHolder {
    private String username;
    private String charName;
    private int hp;
    private int mana;
    private int str;
    private int dex;
    private int vit;
    private int wis;
    private int intell;
    private int chr;
    private int armor;
    private List<Spells> spellList;
    private int battleId;
    private String opponentUsername;

    public PvPCharInfoHolder(String username, String charName, int hp, int mana, int str, int dex, int vit, int wis, int intell, int chr, int armor ,List<Spells> spellList, int battleId, String opponentUsername) {
        this.username = username;
        this.charName = charName;
        this.hp = hp;
        this.mana = mana;
        this.str = str;
        this.dex = dex;
        this.vit = vit;
        this.wis = wis;
        this.intell = intell;
        this.chr = chr;
        this.armor = armor;
        this.spellList = spellList;
        this.battleId = battleId;
        this.opponentUsername = opponentUsername;
    }
    
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getStr() {
        return str;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public int getDex() {
        return dex;
    }

    public void setDex(int dex) {
        this.dex = dex;
    }

    public int getVit() {
        return vit;
    }

    public void setVit(int vit) {
        this.vit = vit;
    }

    public int getWis() {
        return wis;
    }

    public void setWis(int wis) {
        this.wis = wis;
    }

    public int getIntell() {
        return intell;
    }

    public void setIntell(int intell) {
        this.intell = intell;
    }

    public int getChr() {
        return chr;
    }

    public void setChr(int chr) {
        this.chr = chr;
    }

    public List<Spells> getSpellList() {
        return spellList;
    }

    public void setSpellList(List<Spells> spellList) {
        this.spellList = spellList;
    }

    public int getBattleId() {
        return battleId;
    }

    public void setBattleId(int battleId) {
        this.battleId = battleId;
    }

    public String getOpponentUsername() {
        return opponentUsername;
    }

    public void setOpponentUsername(String opponentUsername) {
        this.opponentUsername = opponentUsername;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }
    
    
    
    
    
    
}
