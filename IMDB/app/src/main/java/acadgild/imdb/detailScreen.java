package acadgild.imdb;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import acadgild.imdb.Model.Casts;
import acadgild.imdb.Utils.ImageLoader;

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
    private static final String TAG_CAST="cast";
    private static final String TAG_CAST_ID="cast_id";
    private static final String TAG_CHARACTER="character";
    private static final String TAG_NAME="name";
    private static final String TAG_PROFILE_PATH="profile_path";
    TextView title;
    TextView tag_line;
    TextView r_date;
    TextView budget;
    TextView revenue;
    TextView status;
    TextView vote_average;
    TextView description;
    ImageView poster;
    ImageLoader imageLoader;
    JSONArray movies = null;

    ArrayList<HashMap<String, String>> castList;
    List<HashMap<String, String>> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getIntent().getExtras();
        url=url+bundle.getString("id")+"?api_key=8496be0b2149805afa458ab8ec27560c";
        title=(TextView)findViewById(R.id.title);
        tag_line=(TextView)findViewById(R.id.tag_line);
        r_date=(TextView)findViewById(R.id.r_date);
        budget=(TextView)findViewById(R.id.budget);
        revenue=(TextView)findViewById(R.id.revenue);
        status=(TextView)findViewById(R.id.status);
        vote_average=(TextView)findViewById(R.id.vote_average);
        description=(TextView)findViewById(R.id.description);
        poster=(ImageView)findViewById(R.id.imageView2);
        imageLoader = new ImageLoader(this.getApplicationContext());
        setContentView(R.layout.details_screen);
        new GetDetails().execute();
        new GetCast().execute();
    }

    private void showCasts(ArrayList<HashMap<String, String>> cast) {
        LinearLayout castsSection = (LinearLayout) findViewById(R.id.casts_section);
        List<Casts> casts = new ArrayList<Casts>();


        for(int i=0;i<cast.size();i++){


            Casts c=new Casts();
            c.setId(cast.get(i).get(TAG_CAST_ID));
            c.setName(cast.get(i).get(TAG_NAME));
            c.setCharacter(cast.get(i).get(TAG_CHARACTER));
            c.setProfilePath(cast.get(i).get(TAG_PROFILE_PATH));

            casts.add(c);
        }


        if (casts != null && !casts.isEmpty()) {
            castsSection.setVisibility(View.VISIBLE);
            setCasts(casts);
        } else {
            castsSection.setVisibility(View.GONE);
        }
    }

    // Add multiple casts in the casts container
    private void setCasts(List<Casts> casts) {
        LinearLayout castsContainer = (LinearLayout) findViewById(R.id.casts_container);

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        ImageLoader imageLoader = new ImageLoader(this.getApplicationContext());

        int size = casts.size();
        for (int i = 0; i < size; i++) {
            Casts cast = casts.get(i);

            if (cast != null) {
                LinearLayout clickableColumn = (LinearLayout) inflater.inflate(
                        R.layout.clickable_column, null);
                ImageView thumbnailImage = (ImageView) clickableColumn
                        .findViewById(R.id.thumbnail_image);
                TextView titleView = (TextView) clickableColumn
                        .findViewById(R.id.title_view);
                TextView subTitleView = (TextView) clickableColumn
                        .findViewById(R.id.subtitle_view);

                imageLoader.DisplayImage(movieList.get(i).get("poster_path"), thumbnailImage);
                titleView.setText(cast.getName());
                subTitleView.setText(cast.getCharacter());

                clickableColumn.setTag(cast);
                clickableColumn.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Casts cast = (Casts) v.getTag();

                        Toast.makeText(getApplicationContext(), cast.getName(), Toast.LENGTH_SHORT).show();
                    }
                });

                castsContainer.addView(clickableColumn);

                if (i != size - 1) {
                    castsContainer.addView(inflater.inflate(
                            R.layout.horizontal_divider, null));
                }
            }
        }
    }

    private class GetCast extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... arg0) {

            movieList=new ArrayList<HashMap<String, String>>();

            ServiceHandler sh = new ServiceHandler();

            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    movies = jsonObj.getJSONArray(TAG_CAST);
                    for (int i = 0; i < movies.length(); i++) {
                        JSONObject c = movies.getJSONObject(i);

                        String cast_id = c.getString(TAG_CAST_ID);
                        String character = c.getString(TAG_CHARACTER);
                        String name = c.getString(TAG_NAME);
                        String profile_path="http://image.tmdb.org/t/p/w45"+c.getString(TAG_PROFILE_PATH);

                        HashMap<String, String> contact = new HashMap<String, String>();

                        contact.put(TAG_CAST_ID, cast_id);
                        contact.put(TAG_CHARACTER, character);
                        contact.put(TAG_NAME, name);
                        contact.put(TAG_PROFILE_PATH, profile_path);

                        castList.add(contact);
                    }
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
            showCasts(castList);
        }

    }

    private class GetDetails extends AsyncTask<Void, Void, Void> {

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
                    JSONObject c = new JSONObject(jsonStr);

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
            imageLoader.DisplayImage(movieList.get(0).get(TAG_POSTER_PATH),poster);
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
