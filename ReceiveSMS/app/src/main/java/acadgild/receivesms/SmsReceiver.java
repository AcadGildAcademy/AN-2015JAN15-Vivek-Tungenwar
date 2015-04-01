package acadgild.receivesms;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by Tungenwar on 29/03/2015.
 */
public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle pudsBundle = intent.getExtras();
        Object[] pdus = (Object[]) pudsBundle.get("pdus");
        SmsMessage messages =SmsMessage.createFromPdu((byte[]) pdus[0]);
        Main_activity m=new Main_activity();
        Toast.makeText(m.getApplicationContext(),messages.getDisplayMessageBody()+messages.getDisplayOriginatingAddress(),Toast.LENGTH_LONG).show();
    }
}
