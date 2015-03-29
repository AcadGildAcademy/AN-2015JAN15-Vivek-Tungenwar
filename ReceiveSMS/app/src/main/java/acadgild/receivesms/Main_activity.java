package acadgild.receivesms;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by Tungenwar on 29/03/2015.
 */
public class Main_activity extends ActionBarActivity {
    Context context;
    Main_activity()
    {
        context=this;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }
}
