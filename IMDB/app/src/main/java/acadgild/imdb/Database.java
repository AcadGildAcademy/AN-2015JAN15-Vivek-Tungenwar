package acadgild.imdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Tungenwar on 20/05/2015.
 */
public class Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "TO-DO_Database";

    // Contacts table name
    private static final String TABLE_MOVIES = "movie_details";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_RELEASE_DATE = "date";
    private static final String KEY_POSTER_PATH = "poster_path";
    private static final String KEY_POPULARITY = "popularity";
    private static final String KEY_VOTE_AVERAGE = "vote_average";
    private static final String KEY_VOTE_COUNT = "vote_count";
    private static final String KEY_IS_FAVORITE = "is_favorite";
    private static final String KEY_IS_WATCHLIST = "is_watchlist";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_MOVIES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT," + KEY_POPULARITY + " TEXT"
                + KEY_RELEASE_DATE + " TEXT," + KEY_POSTER_PATH + " TEXT," + KEY_VOTE_AVERAGE + " TEXT" +
                KEY_VOTE_COUNT + " INTEGER"+ KEY_IS_FAVORITE + " TEXT"+ KEY_IS_WATCHLIST + " TEXT"+ ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
        onCreate(db);
    }

    // Adding new movies
    void addMovies(Movies movie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, movie.getID());
        values.put(KEY_TITLE, movie.getTitle());
        values.put(KEY_RELEASE_DATE, movie.getDate());
        values.put(KEY_POPULARITY, movie.getPopularity());
        values.put(KEY_POSTER_PATH, movie.getPosterPath());
        values.put(KEY_VOTE_AVERAGE, movie.getVoteAverage());
        values.put(KEY_VOTE_COUNT, movie.getVoteCount());
        values.put(KEY_IS_FAVORITE, movie.getIsFavorite());
        values.put(KEY_IS_WATCHLIST, movie.getIsWatchlist());

        // Inserting Row
        db.insert(TABLE_MOVIES, null, values);
        db.close(); // Closing database connection
    }

    // Getting single movie
    Movies getMovies(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_MOVIES, new String[] { KEY_ID,
                        KEY_TITLE}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
            Movies reminders = new Movies(cursor.getString(0),
                    cursor.getString(7), cursor.getString(8));
            return reminders;
    }

    // Getting All Movies
    public ArrayList<HashMap<String, String>> getFavoritesMovies() {
        ArrayList<HashMap<String, String>> moviesList = new ArrayList<HashMap<String, String>>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MOVIES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> movies = new HashMap<String, String>();
                if(cursor.getString(7)=="1") {
                    movies.put("id", cursor.getString(0));
                    movies.put("title", cursor.getString(1));
                    movies.put("release_date", cursor.getString(2));
                    movies.put("popularity", cursor.getString(3));
                    movies.put("poster_path", cursor.getString(4));
                    movies.put("vote_average", cursor.getString(5));
                    movies.put("vote_count", cursor.getString(6));
                }
                // Adding contact to list
                moviesList.add(movies);
            } while (cursor.moveToNext());
        }

        // return reminders list
        return moviesList;
    }

    // Getting All Movies
    public ArrayList<HashMap<String, String>> getWatchlistMovies() {
        ArrayList<HashMap<String, String>> moviesList = new ArrayList<HashMap<String, String>>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MOVIES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> movies = new HashMap<String, String>();
                if(cursor.getString(8)=="1") {
                    movies.put("id", cursor.getString(0));
                    movies.put("title", cursor.getString(1));
                    movies.put("release_date", cursor.getString(2));
                    movies.put("popularity", cursor.getString(3));
                    movies.put("vote_average", cursor.getString(4));
                    movies.put("vote_count", cursor.getString(5));
                    movies.put("poster_path", cursor.getString(6));
                }
                // Adding contact to list
                moviesList.add(movies);
            } while (cursor.moveToNext());
        }

        // return reminders list
        return moviesList;
    }

    // Updating Status
    public int updateFavorite(Movies movie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        if(movie._is_favorite.equals("0")) {
            values.put(KEY_IS_FAVORITE, 1);
        }
        else{
            values.put(KEY_IS_FAVORITE, 0);
        }

        // updating row
        return db.update(TABLE_MOVIES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(movie.getID()) });
    }

        // Updating Status
    public int updateWatchlist(Movies movie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        if(movie._is_watchlist.equals("0")) {
            values.put(KEY_IS_WATCHLIST, 1);
        }
        else{
            values.put(KEY_IS_WATCHLIST, 0);
        }

        // updating row
        return db.update(TABLE_MOVIES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(movie.getID()) });
    }

     public void deleteMovies(Movies movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MOVIES, KEY_ID + " = ?",
                new String[] { movie.getID() });
        db.close();
    }
}
