package acadgild.asynctask;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

/**
 * Created by Tungenwar on 06/03/2015.
 */
public class ssa extends Activity {
    ProgressBar pb1,pb2,pb3,pb4;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sss);
        pb1=(ProgressBar)findViewById(R.id.pb_1);
        pb2=(ProgressBar)findViewById(R.id.pb_2);
        pb3=(ProgressBar)findViewById(R.id.pb_3);
        pb4=(ProgressBar)findViewById(R.id.pb_4);
        bt=(Button)findViewById(R.id.bt_download);
        bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    async1 task1=new async1();
                    task1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    async2 task2=new async2();
                    task2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    async3 task3=new async3();
                    task3.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    async4 task4=new async4();
                    task4.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }
            });
    }

    class async1 extends AsyncTask<String, String, String>
        {
            @Override
            protected String doInBackground(String... params) {
                for(int i=1;i<=pb1.getMax();i++)
                {
                    publishProgress(Integer.toString(i));
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(String... values) {
                super.onProgressUpdate(values);
                pb1.setProgress(Integer.parseInt(values[0]));
            }
        }

    class async2 extends AsyncTask<String, String, String>
        {
            @Override
            protected String doInBackground(String... params) {
                for(int i=1;i<=pb2.getMax();i++)
                {
                    publishProgress(Integer.toString(i));
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(String... values) {
                super.onProgressUpdate(values);
                pb2.setProgress(Integer.parseInt(values[0]));
            }
    }

    class async3 extends AsyncTask<String, String, String>
    {
            @Override
            protected String doInBackground(String... params) {
                for(int i=1;i<=pb3.getMax();i++)
                {
                    publishProgress(Integer.toString(i));
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(String... values) {
                super.onProgressUpdate(values);
                pb3.setProgress(Integer.parseInt(values[0]));
            }
    }

    class async4 extends AsyncTask<String, String, String>
        {
            @Override
            protected String doInBackground(String... params) {
                for(int i=1;i<=pb4.getMax();i++)
                {
                    publishProgress(Integer.toString(i));
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(String... values) {
                super.onProgressUpdate(values);
                pb4.setProgress(Integer.parseInt(values[0]));
            }
        }
}
