package acadgild.intent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Tungenwar on 18/02/2015.
 */
public class Homescreen extends Activity {
    Button app,setting,vibrate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        app=(Button)findViewById(R.id.bt_app);
        app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Homescreen.this,appscreen.class);
                startActivity(intent);
            }
        });
        setting=(Button)findViewById(R.id.bt_setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Homescreen.this,settings.class);
                startActivity(intent);
            }
        });
        vibrate=(Button)findViewById(R.id.bt_vibrate);
        vibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator a=(Vibrator)getSystemService(VIBRATOR_SERVICE);
                a.vibrate(2000);
            }
        });
    }
}
