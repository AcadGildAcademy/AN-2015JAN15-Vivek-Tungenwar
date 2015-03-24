package acadgild.actionbarwithtabs.ActionBar.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;
import android.widget.Switch;

import acadgild.actionbarwithtabs.Acadgild;
import acadgild.actionbarwithtabs.Apptor;
import acadgild.actionbarwithtabs.Others;

/**
 * Created by Tungenwar on 21/03/2015.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
        case 0:
            // Buttons fragment activity
            return new Acadgild();
        case 1:
            // Colors fragment activity
            return new Apptor();
        case 2:
            // Images fragment activity
            return new Others();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
