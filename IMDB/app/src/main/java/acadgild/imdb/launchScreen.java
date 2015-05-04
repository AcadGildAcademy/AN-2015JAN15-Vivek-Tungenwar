package acadgild.imdb;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Tungenwar on 25/04/2015.
 */
public class launchScreen extends ActionBarActivity {

    private static String url = "http://api.themoviedb.org/3/movie/latest?api_key=8496be0b2149805afa458ab8ec27560c";

    private static final String TAG_MOVIES = "8496be0b2149805afa458ab8ec27560c";
    private static final String TAG_ID = "id";
    private static final String TAG_TITLE = "title";
    private static final String TAG_RELEASE_DATE = "release_date";
    private static final String TAG_VOTE_AVERAGE = "vote_average";
    private static final String TAG_VOTE_COUNT= "vote_count";

    JSONArray contacts = null;

    ArrayList<HashMap<String, String>> contactList;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_screen);
        contactList = new ArrayList<HashMap<String, String>>();
        lv=(ListView)findViewById(R.id.listView);
    }

    private class GetMovies extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... arg0) {

            ServiceHandler sh = new ServiceHandler();

            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    contacts = jsonObj.(TAG_MOVIES);

                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String id = c.getString(TAG_ID);
                        String title = c.getString(TAG_TITLE);
                        String release_date = c.getString(TAG_RELEASE_DATE);
                        String vote_count = c.getString(TAG_VOTE_COUNT);

                        HashMap<String, String> contact = new HashMap<String, String>();

                        contact.put(TAG_ID, id);
                        contact.put(TAG_TITLE, title);
                        contact.put(TAG_RELEASE_DATE, release_date);
                        contact.put(TAG_VOTE_COUNT, vote_count);

                        // adding contact to contact list
                        contactList.add(contact);
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            ListAdapter adapter = new SimpleAdapter(
                    launchScreen.this, contactList,
                    R.layout.movielist, new String[] { TAG_TITLE, TAG_RELEASE_DATE,
                    TAG_VOTE_COUNT }, new int[] { R.id.textView,
                    R.id.textView2, R.id.textView3 });

            setListAdapter(adapter);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuoptions,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
