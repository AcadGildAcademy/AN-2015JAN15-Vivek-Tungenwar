package acadgild.to_do;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Tungenwar on 12/03/2015.
 */
public class todoscreen extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todolist);

        Database db=new Database(this);
        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Reminders> reminder = db.getAllReminders();

        for (Reminders cn : reminder) {
            String log = "Id: "+cn.getID()+" ,Title: " + cn.getTitle() + " ,Description: " + cn.getDescription() + " ,Date: " + cn.getDate() + " ,Status: " + cn.getStatus();
            // Writing Contacts to log
            Log.d("Title: ", log);

        }

        final ListView listview = (ListView) findViewById(R.id.listView);

        final StableArrayAdapter adapter = new StableArrayAdapter(this,R.layout.listview,reminder);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {

            }

        });
    }

    private class StableArrayAdapter extends ArrayAdapter<Reminders> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
        List<Reminders> reminders;

        public StableArrayAdapter(Context context, int textViewResourceId, List<Reminders> reminders) {
            super(context, textViewResourceId,reminders);
            this.reminders=reminders;

        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menubar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bt_dialog1:
                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.dialog);
                dialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
