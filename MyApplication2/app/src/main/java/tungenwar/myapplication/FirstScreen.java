package tungenwar.myapplication;

import android.app.Activity;
import android.os.Bundle;
/**
 * Created by Tungenwar on 13/02/2015.
 */
public class FirstScreen extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //Blank Window
        setContentView(R.layout.rainbow);
    }
}
