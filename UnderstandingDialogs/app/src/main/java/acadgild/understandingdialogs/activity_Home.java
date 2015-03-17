package acadgild.understandingdialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Tungenwar on 17/03/2015.
 */
public class activity_Home extends Activity {
    Context context=this;
    public ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.dialog, null);
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setView(promptsView);
                final AlertDialog alertDialog=alertDialogBuilder.create();
                final EditText username = (EditText) promptsView .findViewById(R.id.editText);
                Button bt_ok=(Button)promptsView.findViewById(R.id.bt_ok);
                Button bt_cancel=(Button)promptsView.findViewById(R.id.bt_cancel);
                alertDialog.setCancelable(false);
                alertDialog.setTitle("Title");
                bt_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        async a = new async();
                        a.execute(Integer.parseInt(username.getText().toString()));
                    }
                });
                bt_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });
    }

    class async extends AsyncTask<Integer,Integer,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setCancelable(true);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Integer[] params) {
            try{
                progressDialog.setMax(params[0]);
                for (int i=1;i<=params[0];i++) {
                    Thread.sleep(1000);
                    publishProgress(i);
                }
            } catch (Exception e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String o) {
            super.onPostExecute(o);
            progressDialog.dismiss();
            new AlertDialog.Builder(activity_Home.this)
                    .setTitle("Succesful")
                    .setMessage("Completed")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();
        }

        @Override
        protected void onProgressUpdate(Integer[] values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }
    }
}