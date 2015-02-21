package acadgild.hideandseek;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Tungenwar on 21/02/2015.
 */
public class hideseek extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hide_seek);
        final Button hide=(Button)findViewById(R.id.bt_hide_seek);
        final TextView text=(TextView)findViewById(R.id.text_hide_seek);
        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text.getVisibility()==View.VISIBLE)
                {
                    text.setVisibility(View.INVISIBLE);
                    hide.setText(R.string.bt_show);
                }
                else
                {
                    text.setVisibility(View.VISIBLE);
                    hide.setText(R.string.bt_hide);
                }
            }
        });
    }
}
