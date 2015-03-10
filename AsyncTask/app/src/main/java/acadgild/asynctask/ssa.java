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
                    async task1=new async(pb1);
                    task1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    async task2=new async(pb2);
                    task2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    async task3=new async(pb3);
                    task3.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    async task4=new async(pb4);
                    task4.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }
            });
    }

    class async extends AsyncTask<String, Integer, String>
    {
        ProgressBar asyncprogress;
        async(ProgressBar pb)
        {
            asyncprogress=pb;
        }
            @Override
            protected String doInBackground(String... params) {
                for(int i=1;i<=pb1.getMax();i++)
                {
                    publishProgress(i);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                asyncprogress.setProgress(values[0]);
            }
    }
}
