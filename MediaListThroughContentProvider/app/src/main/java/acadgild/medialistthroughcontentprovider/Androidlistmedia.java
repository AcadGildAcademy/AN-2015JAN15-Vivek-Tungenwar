package acadgild.medialistthroughcontentprovider;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Tungenwar on 28/03/2015.
 */
public class Androidlistmedia extends ActionBarActivity {
    ListView li;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.albumlist);
            li = (ListView) findViewById(R.id.listView);
            String[] proj = {MediaStore.Audio.Media._ID,
                    MediaStore.Audio.Media.ALBUM};
            Cursor cursor = managedQuery(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, proj, null, null, null);
            String[] displayFields = new String[]{MediaStore.Audio.Albums.ALBUM};
            for(int i=0;i<cursor.getCount();i++){
                displayFields[i]=cursor.getString(1);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, displayFields);
            li.setAdapter(adapter);
        }
        catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
        li.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Androidlistmedia.this,albumMusicList.class);
                startActivity(intent);
            }
        });
    }
}
