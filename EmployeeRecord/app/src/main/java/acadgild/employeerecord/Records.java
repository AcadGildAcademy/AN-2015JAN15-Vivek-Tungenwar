package acadgild.employeerecord;

/**
 * Created by Tungenwar on 27/03/2015.
 */
public class Records {
    //private variables
    int _id;
    String _name;
    String _age;
    byte[] _photo;

    // Empty constructor
    public Records(){

    }
    // constructor
    public Records(String name, String _age, byte[] _photo){
        this._name = name;
        this._age = _age;
        this._photo=_photo;
    }

    // constructor
    public Records(int _id, String _name, String _age, byte[] _photo){
        this._id = _id;
        this._name = _name;
        this._age = _age;
        this._photo=_photo;
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
    public String getName(){
        return this._name;
    }

    // setting name
    public void setName(String name){
        this._name = name;
    }

    // getting phone number
    public String getAge(){
        return this._age;
    }

    // setting phone number
    public void setAge(String _age){
        this._age = _age;
    }

    //getting photo
    public byte[] getPhoto(){
        return this._photo;
    }

    //setting photo
    public void setPhoto(byte[] _photo){
        this._photo=_photo;
    }
}
