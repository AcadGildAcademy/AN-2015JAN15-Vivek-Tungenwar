package acadgild.employeerecord;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by Tungenwar on 19/03/2015.
 */
public class empRecord extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record);
        Database db = new Database(this);
        Records records=new Records("Vivek","22",getBytes(BitmapFactory.decodeResource(	getResources(), R.drawable.images)));
        db.addRecord(records);
        TextView Name=(TextView)findViewById(R.id.textView);
        TextView Age = (TextView)findViewById(R.id.textView2);
        ImageView image=(ImageView)findViewById(R.id.imageView);
        records=db.getRecord(1);
        Name.setText(records.getName());
        Age.setText(records.getAge());
        image.setImageBitmap(getPhoto(records.getPhoto()));
}

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();

    }

    public static Bitmap getPhoto(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

}
