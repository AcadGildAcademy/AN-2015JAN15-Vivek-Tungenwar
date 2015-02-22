package acadgild.intent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Tungenwar on 19/02/2015.
 */
public class appscreen extends Activity {
    EditText search;
    Button search1,developer,spapp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apps_activity);
        search=(EditText)findViewById(R.id.et_search);
        search1 = (Button)findViewById(R.id.bt_search);
        developer=(Button)findViewById(R.id.bt_developer);
        spapp=(Button)findViewById(R.id.bt_spapp);
        spapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=ranjith.naidu.filetransfer.gui&hl=en"));
                startActivity(intent);
            }
        });
        search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/search?q="+search.getText()+"&c=apps&hl=en"));
                startActivity(intent);
            }
        });
        developer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/search?q=pub:Ranjith naidu&c=apps&hl=en"));
                startActivity(intent);
            }
        });
    }
}
