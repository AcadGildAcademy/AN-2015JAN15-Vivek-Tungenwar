package acadgild.to_do;

/**
 * Created by Tungenwar on 12/03/2015.
 */
public class Reminders {

    //private variables
    int _id;
    String _title;
    String _description;
    String _date;
    int _status;

    // Empty constructor
    public Reminders(){

    }
    // constructor
    public Reminders(int id, String _title, String _description, String _date, int _status){
        this._id = id;
        this._title = _title;
        this._description = _description;
        this._date=_date;
        this._status=_status;
    }

    // constructor
    public Reminders(String _title, String _description, String _date, int _status){
        this._title = _title;
        this._description = _description;
        this._date=_date;
        this._status=_status;
    }
    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting name
    public String getTitle(){
        return this._title;
    }

    // setting name
    public void setTitle(String title){
        this._title = title;
    }

    public String getDescription(){
        return this._description;
    }

    public void setDescription(String description){
        this._description = description;
    }
    // getting phone number
    public String getDate(){
        return this._date;
    }

    // setting phone number
    public void setDate(String date){
        this._date = date;
    }

    public int getStatus(){
        return this._status;
    }

    // setting id
    public void setStatus(int status){
        this._status = status;
    }
}
