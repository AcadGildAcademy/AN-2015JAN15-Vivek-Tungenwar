package acadgild.autocomplete;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

/**
 * Created by Tungenwar on 11/03/2015.
 */
public class auto_complete extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autocomplete);
        Database db = new Database(this);
        String[] products=db.getAllProducts();
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,products);
        AutoCompleteTextView autocomplete=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
        autocomplete.setAdapter(adapter);
    }
}
