package jsrpg;
// Generated Jan 8, 2017 5:32:40 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Character generated by hbm2java
 */
public class Character  implements java.io.Serializable {


     private CharacterId id;
     private Account account;
     private String name;
     private int portrait;
     private Date lastloggedin;
     private Integer level;
     private Integer xp;
     private Integer location;
     private Boolean isonline;
     private Integer money;
     private Integer strength;
     private Integer dexterity;
     private Integer vitality;
     private Integer intelligence;
     private Integer wisdom;
     private Integer charisma;
     private Integer equiphelm;
     private Integer equiparmor;
     private Integer equipweapon;
     private Integer equipoffhand;
     private Integer struse;
     private Integer dexuse;
     private Integer fureuse;
     private Integer iceuse;
     private Integer arcaneuse;
     private Integer lightuse;
     private Integer darkuse;
     private Integer magoffuse;
     private Integer magdefuse;

    public Character() {
    }

	
    public Character(CharacterId id, Account account, String name, int portrait) {
        this.id = id;
        this.account = account;
        this.name = name;
        this.portrait = portrait;
    }
    public Character(CharacterId id, Account account, String name, int portrait, Date lastloggedin, Integer level, Integer xp, Integer location, Boolean isonline, Integer money, Integer strength, Integer dexterity, Integer vitality, Integer intelligence, Integer wisdom, Integer charisma, Integer equiphelm, Integer equiparmor, Integer equipweapon, Integer equipoffhand, Integer struse, Integer dexuse, Integer fureuse, Integer iceuse, Integer arcaneuse, Integer lightuse, Integer darkuse, Integer magoffuse, Integer magdefuse) {
       this.id = id;
       this.account = account;
       this.name = name;
       this.portrait = portrait;
       this.lastloggedin = lastloggedin;
       this.level = level;
       this.xp = xp;
       this.location = location;
       this.isonline = isonline;
       this.money = money;
       this.strength = strength;
       this.dexterity = dexterity;
       this.vitality = vitality;
       this.intelligence = intelligence;
       this.wisdom = wisdom;
       this.charisma = charisma;
       this.equiphelm = equiphelm;
       this.equiparmor = equiparmor;
       this.equipweapon = equipweapon;
       this.equipoffhand = equipoffhand;
       this.struse = struse;
       this.dexuse = dexuse;
       this.fureuse = fureuse;
       this.iceuse = iceuse;
       this.arcaneuse = arcaneuse;
       this.lightuse = lightuse;
       this.darkuse = darkuse;
       this.magoffuse = magoffuse;
       this.magdefuse = magdefuse;
    }
   
    public CharacterId getId() {
        return this.id;
    }
    
    public void setId(CharacterId id) {
        this.id = id;
    }
    public Account getAccount() {
        return this.account;
    }
    
    public void setAccount(Account account) {
        this.account = account;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public int getPortrait() {
        return this.portrait;
    }
    
    public void setPortrait(int portrait) {
        this.portrait = portrait;
    }
    public Date getLastloggedin() {
        return this.lastloggedin;
    }
    
    public void setLastloggedin(Date lastloggedin) {
        this.lastloggedin = lastloggedin;
    }
    public Integer getLevel() {
        return this.level;
    }
    
    public void setLevel(Integer level) {
        this.level = level;
    }
    public Integer getXp() {
        return this.xp;
    }
    
    public void setXp(Integer xp) {
        this.xp = xp;
    }
    public Integer getLocation() {
        return this.location;
    }
    
    public void setLocation(Integer location) {
        this.location = location;
    }
    public Boolean getIsonline() {
        return this.isonline;
    }
    
    public void setIsonline(Boolean isonline) {
        this.isonline = isonline;
    }
    public Integer getMoney() {
        return this.money;
    }
    
    public void setMoney(Integer money) {
        this.money = money;
    }
    public Integer getStrength() {
        return this.strength;
    }
    
    public void setStrength(Integer strength) {
        this.strength = strength;
    }
    public Integer getDexterity() {
        return this.dexterity;
    }
    
    public void setDexterity(Integer dexterity) {
        this.dexterity = dexterity;
    }
    public Integer getVitality() {
        return this.vitality;
    }
    
    public void setVitality(Integer vitality) {
        this.vitality = vitality;
    }
    public Integer getIntelligence() {
        return this.intelligence;
    }
    
    public void setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
    }
    public Integer getWisdom() {
        return this.wisdom;
    }
    
    public void setWisdom(Integer wisdom) {
        this.wisdom = wisdom;
    }
    public Integer getCharisma() {
        return this.charisma;
    }
    
    public void setCharisma(Integer charisma) {
        this.charisma = charisma;
    }
    public Integer getEquiphelm() {
        return this.equiphelm;
    }
    
    public void setEquiphelm(Integer equiphelm) {
        this.equiphelm = equiphelm;
    }
    public Integer getEquiparmor() {
        return this.equiparmor;
    }
    
    public void setEquiparmor(Integer equiparmor) {
        this.equiparmor = equiparmor;
    }
    public Integer getEquipweapon() {
        return this.equipweapon;
    }
    
    public void setEquipweapon(Integer equipweapon) {
        this.equipweapon = equipweapon;
    }
    public Integer getEquipoffhand() {
        return this.equipoffhand;
    }
    
    public void setEquipoffhand(Integer equipoffhand) {
        this.equipoffhand = equipoffhand;
    }
    public Integer getStruse() {
        return this.struse;
    }
    
    public void setStruse(Integer struse) {
        this.struse = struse;
    }
    public Integer getDexuse() {
        return this.dexuse;
    }
    
    public void setDexuse(Integer dexuse) {
        this.dexuse = dexuse;
    }
    public Integer getFureuse() {
        return this.fureuse;
    }
    
    public void setFureuse(Integer fureuse) {
        this.fureuse = fureuse;
    }
    public Integer getIceuse() {
        return this.iceuse;
    }
    
    public void setIceuse(Integer iceuse) {
        this.iceuse = iceuse;
    }
    public Integer getArcaneuse() {
        return this.arcaneuse;
    }
    
    public void setArcaneuse(Integer arcaneuse) {
        this.arcaneuse = arcaneuse;
    }
    public Integer getLightuse() {
        return this.lightuse;
    }
    
    public void setLightuse(Integer lightuse) {
        this.lightuse = lightuse;
    }
    public Integer getDarkuse() {
        return this.darkuse;
    }
    
    public void setDarkuse(Integer darkuse) {
        this.darkuse = darkuse;
    }
    public Integer getMagoffuse() {
        return this.magoffuse;
    }
    
    public void setMagoffuse(Integer magoffuse) {
        this.magoffuse = magoffuse;
    }
    public Integer getMagdefuse() {
        return this.magdefuse;
    }
    
    public void setMagdefuse(Integer magdefuse) {
        this.magdefuse = magdefuse;
    }




}


