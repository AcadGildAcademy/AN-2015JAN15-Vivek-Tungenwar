package acadgild.assg_dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Tungenwar on 17/03/2015.
 */
public class assgDialog extends ActionBarActivity {
    ListView listView;
    MyAdapter adapter;
    Context context=this;
    ArrayList<Contacts> items= new ArrayList<Contacts>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        items.add(new Contacts("Pushpa","5326436346"));
        items.add(new Contacts("Acadgild","5326445365"));
        listView = (ListView) findViewById(R.id.listView);
        adapter = new MyAdapter(this, items);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                LayoutInflater li = LayoutInflater.from(this);
                View promptsView = li.inflate(R.layout.dialog, null);
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setView(promptsView);
                final EditText Name = (EditText) promptsView .findViewById(R.id.editText);
                final EditText Number=(EditText)promptsView.findViewById(R.id.editText2);
                Button add=(Button)promptsView.findViewById(R.id.bt_dialog1);
                Button close=(Button)promptsView.findViewById(R.id.bt_dialog2);
                final AlertDialog d=alertDialogBuilder.create();
                d.setCancelable(false);
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Name.getText().equals("")||Number.getText().equals("")){
                            Toast.makeText(context,"Enter Name and Number",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            items.add(new Contacts(Name.getText().toString(), Number.getText().toString()));
                            adapter.notifyDataSetChanged();
                            listView.setAdapter(adapter);
                            Name.setText("");
                            Number.setText("");
                        }
                    }
                });
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        d.dismiss();
                    }
                });
                d.show();
                return true;
        }
        return true;
    }
}