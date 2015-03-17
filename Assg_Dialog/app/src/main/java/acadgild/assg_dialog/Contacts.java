package acadgild.assg_dialog;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tungenwar on 17/03/2015.
 */
public class Contacts {
    //private variables
    String _Name,_number;

    // Empty constructor
    public Contacts(){

    }
    // constructor
    public Contacts(String _Name, String _number){
        this._Name = _Name;
        this._number = _number;
    }
    // getting Name
    public String getName(){
        return this._Name;
    }

    // setting Name
    public void setName(String name){
        this._number = name;
    }

    // getting number
    public String getNumber(){
        return this._number;
    }

    // setting number
    public void setNumber(String number){
        this._number = number;
    }
}
