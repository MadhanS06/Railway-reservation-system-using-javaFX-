/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package train;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
/**
 *
 * @author Madhan Kumar S
 */
public class record {
    private final StringProperty tnum;
    private final StringProperty tname;
    private final StringProperty destination;
    private final StringProperty arrivaltime;
    private final StringProperty departuretime;
    
    public record(){
        tnum=new SimpleStringProperty(this,"tnum");
        tname=new SimpleStringProperty(this,"tname");
        destination= new SimpleStringProperty(this,"destination");
        arrivaltime=new SimpleStringProperty(this,"arrivaltime");
        departuretime=new SimpleStringProperty(this,"departuretime");
    }
    
    public StringProperty num(){ return tnum;}
    public String getnum(){ return tnum.get();}
    public void setnum(String s){  tnum.set(s); }
    
    public StringProperty name(){ return tname;}
    public String getname(){ return tname.get();}
    public void setname(String s){  tname.set(s); }
    
    public StringProperty dest(){ return destination;}
    public String getdest(){ return destination.get();}
    public void setdest(String s){  destination.set(s); }
    
    public StringProperty arr(){ return arrivaltime;}
    public String getarr(){ return arrivaltime.get();}
    public void setarr(String s){  arrivaltime.set(s); }
    
    public StringProperty dept(){ return departuretime;}
    public String getdept(){ return departuretime.get();}
    public void setdept(String s){  departuretime.set(s); }
    
    @Override
    public String toString(){
        return String.format("%s[id=%s,name=%s]", getnum(),getname(),getdest(),getarr(),getdept());
        
    }
   
}
