package acadgild.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

/**
 * Created by Tungenwar on 19/02/2015.
 */
public class settings extends Activity {
    Button wifi,bluetooth,account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        wifi=(Button)findViewById(R.id.bt_wifi);
        wifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Settings.ACTION_WIFI_SETTINGS);
                startActivity(intent);
            }
        });
        bluetooth=(Button)findViewById(R.id.bt_bluetooth);
        bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
                startActivity(intent);
            }
        });
        account=(Button)findViewById(R.id.bt_addaccount);
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Settings.ACTION_ADD_ACCOUNT);
                startActivity(intent);
            }
        });
    }
}
