package jsrpg;
// Generated Jan 8, 2017 5:32:40 PM by Hibernate Tools 4.3.1



/**
 * Items generated by hbm2java
 */
public class Items  implements java.io.Serializable {


     private Integer id;
     private String type;
     private Integer maxammount;
     private Integer sellvalue;
     private String comment;

    public Items() {
    }

    public Items(String type, Integer maxammount, Integer sellvalue, String comment) {
       this.type = type;
       this.maxammount = maxammount;
       this.sellvalue = sellvalue;
       this.comment = comment;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    public Integer getMaxammount() {
        return this.maxammount;
    }
    
    public void setMaxammount(Integer maxammount) {
        this.maxammount = maxammount;
    }
    public Integer getSellvalue() {
        return this.sellvalue;
    }
    
    public void setSellvalue(Integer sellvalue) {
        this.sellvalue = sellvalue;
    }
    public String getComment() {
        return this.comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }




}


