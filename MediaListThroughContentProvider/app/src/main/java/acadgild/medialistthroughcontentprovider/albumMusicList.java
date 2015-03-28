package acadgild.medialistthroughcontentprovider;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Tungenwar on 28/03/2015.
 */
public class albumMusicList extends ActionBarActivity {
    ListView li;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.albumlist);
        String[] whereVal = { getIntent().getStringExtra("key") };

        String[] columns = { MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media._ID,

                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.MIME_TYPE, };

        String where = android.provider.MediaStore.Audio.Media.ALBUM + "=?";

        String orderBy = android.provider.MediaStore.Audio.Media.TITLE;

        Cursor cursor = managedQuery(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                columns, where, whereVal, orderBy);
        String[] displayfields=null;
        for (int i = 0; i < cursor.getCount(); i++) {
            displayfields[i]=cursor.getString(i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, displayfields);
        li=(ListView)findViewById(R.id.listView);
        li.setAdapter(adapter);

    }
}
