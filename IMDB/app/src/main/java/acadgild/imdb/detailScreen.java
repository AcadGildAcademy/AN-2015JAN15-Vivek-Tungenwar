package acadgild.imdb;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Tungenwar on 10/05/2015.
 */
public class detailScreen extends ActionBarActivity {
    private static String url = "http://api.themoviedb.org/3/movie/";
    private static final String TAG_ID = "id";
    private static final String TAG_TITLE = "title";
    private static final String TAG_RELEASE_DATE = "release_date";
    private static final String TAG_BUDGET= "budget";
    private static final String TAG_VOTE_COUNT= "vote_count";
    private static final String TAG_VOTE_AVERAGE= "vote_average";
    private static final String TAG_POSTER_PATH="poster_path";
    private static final String TAG_REVENUE="revenue";
    private static final String TAG_DESCRIPTION="overview";
    private static final String TAG_TAG_LINE="tagline";
    private static final String TAG_STATUS="STATUS";
    TextView title=(TextView)findViewById(R.id.title);
    TextView tag_line=(TextView)findViewById(R.id.tag_line);
    TextView r_date=(TextView)findViewById(R.id.r_date);
    TextView budget=(TextView)findViewById(R.id.budget);
    TextView revenue=(TextView)findViewById(R.id.revenue);
    TextView status=(TextView)findViewById(R.id.status);
    TextView vote_average=(TextView)findViewById(R.id.vote_average);
    TextView description=(TextView)findViewById(R.id.description);

    JSONObject movies = null;

    List<HashMap<String, String>> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getIntent().getExtras();
        url=url+bundle.getString("id")+"?api_key=8496be0b2149805afa458ab8ec27560c";
        setContentView(R.layout.details_screen);
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
                    JSONObject c = movies.getJSONObject(url);

                    String id = c.getString(TAG_ID);
                    String title = c.getString(TAG_TITLE);
                    String release_date = c.getString(TAG_RELEASE_DATE);
                    String popularity = c.getString(TAG_BUDGET);
                    String vote_average = c.getString(TAG_VOTE_AVERAGE);
                    String vote_count = c.getString(TAG_VOTE_COUNT);
                    String description = c.getString(TAG_DESCRIPTION);
                    String revenue = c.getString(TAG_REVENUE);
                    String tag_line = c.getString(TAG_TAG_LINE);
                    String status = c.getString(TAG_STATUS);
                    String poster_path="http://image.tmdb.org/t/p/w45"+c.getString(TAG_POSTER_PATH);

                    HashMap<String, String> contact = new HashMap<String, String>();

                    contact.put(TAG_ID, id);
                    contact.put(TAG_TITLE, title);
                    contact.put(TAG_RELEASE_DATE, release_date);
                    contact.put(TAG_BUDGET, popularity);
                    contact.put(TAG_VOTE_AVERAGE, vote_average);
                    contact.put(TAG_VOTE_COUNT, vote_count);
                    contact.put(TAG_POSTER_PATH, poster_path);
                    contact.put(TAG_DESCRIPTION, description);
                    contact.put(TAG_REVENUE, revenue);
                    contact.put(TAG_TAG_LINE, tag_line);
                    contact.put(TAG_STATUS, status);


                    movieList.add(contact);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                catch (Exception e){
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            title.setText(movieList.get(0).get(TAG_TITLE));
            r_date.setText(movieList.get(0).get(TAG_RELEASE_DATE));
            tag_line.setText(movieList.get(0).get(TAG_TAG_LINE));
            budget.setText("Budget:"+movieList.get(0).get(TAG_BUDGET));
            revenue.setText("Revenue:"+movieList.get(0).get(TAG_REVENUE));
            status.setText("Status:"+movieList.get(0).get(TAG_STATUS));
            vote_average.setText("("+movieList.get(0).get(TAG_VOTE_AVERAGE+"/10)\n"+movieList.get(0).get(TAG_VOTE_COUNT)+"users"));
            description.setText(movieList.get(0).get(TAG_DESCRIPTION));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_screen_options,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.myFavorite:
                return true;
            case R.id.myWatchlist:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
