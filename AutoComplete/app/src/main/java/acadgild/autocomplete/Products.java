package acadgild.autocomplete;

/**
 * Created by Tungenwar on 11/03/2015.
 */
public class Products {

    int _id;
    String _name;

    // Empty constructor
    public Products(){

    }
    // constructor
    public Products(int id, String name){
        this._id = id;
        this._name = name;
    }

    // constructor
    public Products(String name){
        this._name = name;
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
}
