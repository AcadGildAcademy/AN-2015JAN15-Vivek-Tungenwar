package acadgild.fragmentsexcercise;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Tungenwar on 28/03/2015.
 */
public class Main_Activity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragments);
        Button frag1=(Button)findViewById(R.id.button);
        Button frag2=(Button)findViewById(R.id.button2);
        Button frag3=(Button)findViewById(R.id.button3);
        frag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFrag(view);
            }
        });
        frag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFrag(view);
            }
        });
        frag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFrag(view);
            }
        });
    }

    public void selectFrag(View view) {
        Fragment fr;

        if(view == findViewById(R.id.button2)) {
            fr = new Fragment_two();

        }else if(view == findViewById(R.id.button3)) {
            fr = new Fragment_three();
        }
        else{
            fr=new Fragment_one();
        }

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_place, fr);
        fragmentTransaction.commit();
    }
}
