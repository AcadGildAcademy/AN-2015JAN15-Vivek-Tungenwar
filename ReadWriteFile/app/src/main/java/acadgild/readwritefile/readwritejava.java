package acadgild.readwritefile;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Tungenwar on 08/03/2015.
 */
public class readwritejava extends Activity {
    Button addData,Delete;
    EditText txtData;
    TextView showData=(TextView)findViewById(R.id.textView2);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.readwritefile);
        addData=(Button)findViewById(R.id.addData);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                async a=new async();
                a.execute("started");
            }
        });
    }

    class async extends AsyncTask<String, String, String>
    {
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                File myFile = new File(String.valueOf(Environment.getExternalStorageDirectory().getAbsolutePath())+File.separator+"test.txt");
                FileInputStream fIn = new FileInputStream(myFile);
                BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
                String aDataRow = "";
                String aBuffer = "";
                while ((aDataRow = myReader.readLine()) != null) {
                    aBuffer += aDataRow + "\n";
                }
                showData.setText(aBuffer);
                myReader.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(String... params) {
            txtData=(EditText)findViewById(R.id.txtData);
            File txtFile=new File(String.valueOf(Environment.getExternalStorageDirectory().getAbsolutePath())+File.separator+"test.txt");
            try
            {
                txtFile.createNewFile();
                FileOutputStream fout=new FileOutputStream(txtFile);
                OutputStreamWriter output=new OutputStreamWriter(fout);
                output.append(txtData.getText());
                output.close();
                fout.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
