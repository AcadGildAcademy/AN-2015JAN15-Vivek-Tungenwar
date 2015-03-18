package acadgild.autocomplete;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

/**
 * Created by Tungenwar on 11/03/2015.
 */
public class Database extends SQLiteOpenHelper {

    private static final String KEY_ID = "id";
    private static final String Product_NAME = "name";
    private static final String Table_Name="Product";
    private static final String DATABASE_NAME = "productsManager";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +Table_Name + " (" + KEY_ID + " INTEGER PRIMARY KEY," + Product_NAME +" TEXT)";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS Products");

        // Create tables again
        onCreate(db);
    }

    void addProducts(Products product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Product_NAME, product.getName()); // Contact Name

        // Inserting Row
        db.insert(Table_Name, null, values);
        db.close(); // Closing database connection
    }

    public String[] getAllProducts() {
        String selectQuery = "SELECT  * FROM " + Table_Name;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.getCount() > 0) {
            String[] str = new String[cursor.getCount()];
            int i = 0;

            while (cursor.moveToNext()) {
                str[i] = cursor.getString(cursor.getColumnIndex(Product_NAME));
                i++;
            }
            return str;

        } else {
            return new String[]{};

        }
    }
}
