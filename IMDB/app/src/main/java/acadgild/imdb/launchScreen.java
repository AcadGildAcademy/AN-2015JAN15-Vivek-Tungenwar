package acadgild.imdb;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Tungenwar on 25/04/2015.
 */
public class launchScreen extends ActionBarActivity {

    private static String url = "http://api.themoviedb.org/3/movie/upcoming?api_key=8496be0b2149805afa458ab8ec27560c";
    private static final String TAG_MOVIES = "results";
    private static final String TAG_ID = "id";
    private static final String TAG_TITLE = "title";
    private static final String TAG_RELEASE_DATE = "release_date";
    private static final String TAG_POPULARITY= "popularity";
    private static final String TAG_VOTE_COUNT= "vote_count";
    private static final String TAG_VOTE_AVERAGE= "vote_average";
    private static final String TAG_POSTER_PATH="poster_path";
    private String mStrings[]=null;

    JSONArray movies = null;

    ArrayList<HashMap<String, String>> movieList;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_screen);
        movieList = new ArrayList<HashMap<String,String>>();
        lv=(ListView)findViewById(R.id.listView);
        new GetMovies().execute();
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

                    movies = jsonObj.getJSONArray(TAG_MOVIES);

                    for (int i = 0; i < movies.length(); i++) {
                        JSONObject c = movies.getJSONObject(i);

                        String id = c.getString(TAG_ID);
                        String title = c.getString(TAG_TITLE);
                        String release_date = c.getString(TAG_RELEASE_DATE);
                        String popularity = c.getString(TAG_POPULARITY);
                        String vote_average = c.getString(TAG_VOTE_AVERAGE);
                        String vote_count = c.getString(TAG_VOTE_COUNT);
                        String poster_path=c.getString(TAG_POSTER_PATH);
                        mStrings[i]="http://image.tmdb.org/t/p/w45"+poster_path;

                        HashMap<String, String> contact = new HashMap<String, String>();

                        contact.put(TAG_ID, id);
                        contact.put(TAG_TITLE, title);
                        contact.put(TAG_RELEASE_DATE, release_date);
                        contact.put(TAG_POPULARITY, popularity);
                        contact.put(TAG_VOTE_AVERAGE, vote_average);
                        contact.put(TAG_VOTE_COUNT, vote_count);

                        movieList.add(contact);
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(launchScreen.this,e.getMessage(),Toast.LENGTH_LONG).show();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            MyAdapter adapter=new MyAdapter(launchScreen.this,mStrings,movieList);
            lv.setAdapter(adapter);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuoptions,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
