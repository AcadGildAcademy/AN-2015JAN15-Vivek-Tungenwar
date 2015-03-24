package acadgild.actionbarwithtabs;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import acadgild.actionbarwithtabs.ActionBar.adapter.TabsPagerAdapter;

/**
 * Created by Tungenwar on 21/03/2015.
 */
public class Main_Activity extends FragmentActivity implements ActionBar.TabListener, ViewPager.OnPageChangeListener{

    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main_activity);

            // Initilization
            viewPager = (ViewPager) findViewById(R.id.pager);
            actionBar = getActionBar();
            mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
            String[] tabs = {"Acadgild", "Apptor", "Others"};

            viewPager.setAdapter(mAdapter);
            actionBar.setHomeButtonEnabled(false);
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

            // Adding Tabs
            for (String tab_name : tabs) {
                actionBar.addTab(actionBar.newTab().setText(tab_name)
                        .setTabListener(this));
            }

            /**
             * on swiping the viewpager make respective tab selected
             * */
            viewPager.setOnPageChangeListener(this);
        }
        catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int i) {
        actionBar.setSelectedNavigationItem(i);

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
