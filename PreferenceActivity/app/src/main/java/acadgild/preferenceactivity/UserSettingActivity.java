package acadgild.preferenceactivity;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Tungenwar on 23/02/2015.
 */
public class UserSettingActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.user_settings);
    }
}
