package acadgild.downloadimage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.os.Handler;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Tungenwar on 09/03/2015.
 */
public class imagedownloader extends Activity {
    Button download;
    EditText texturl;
    static Bitmap bit=null;
    static ProgressBar progress;
    static ImageView i;
    static android.os.Handler update=new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    i.setImageBitmap(bit);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        i=(ImageView)this.findViewById(R.id.imageView);
        progress=(ProgressBar)this.findViewById(R.id.progressBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagedownloader);
        download=(Button)findViewById(R.id.button);
        texturl=(EditText)findViewById(R.id.editText);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(texturl.toString().endsWith(".png"))
                {
                    async a=new async();
                    a.execute("started");
                }
            }
        });
    }

    class async extends AsyncTask<String,Integer,Bitmap>
    {
        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                URL url=new URL(texturl.getText().toString());
                HttpURLConnection urlconn=(HttpURLConnection)url.openConnection();
                InputStream in = new BufferedInputStream(urlconn.getInputStream());
                bit= BitmapFactory.decodeStream(in);
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bit;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            update.sendMessage(update.obtainMessage(1));
        }

        @Override
        protected void onPostExecute(Bitmap s) {
            super.onPostExecute(s);
            update.sendMessage(update.obtainMessage(2));
        }
    }
}
