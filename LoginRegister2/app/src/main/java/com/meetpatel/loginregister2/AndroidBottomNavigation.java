package com.meetpatel.loginregister2;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.meetpatel.loginregister2.Dashboard.SelectMail;


public class AndroidBottomNavigation extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener,
        PriceFragment.OnFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener,
        DashboardFragment.OnFragmentInteractionListener,
        BotHistoryFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_bottom_navigation);
       // loadFragment(new PriceFragment());
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Fragment fragment = new PriceFragment();
        loadFragment(fragment);
    }
//    public void onClick(View view) {
//        Intent intent = new Intent(getApplicationContext(), Login.class);
//        startActivity(intent);
   // }



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        Fragment fragment = null;

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.nav_price:
                    fragment = new PriceFragment();
                    break;
                case R.id.nav_bothistory:
                    fragment = new BotHistoryFragment();
                    break;
                case R.id.nav_buysell:
                    fragment = new DashboardFragment();

                    break;
                case R.id.nav_settings:
                    fragment = new SettingsFragment();
                    break;
            }
             return loadFragment(fragment);
        }
    };

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        for(Fragment fragment_delete:getSupportFragmentManager().getFragments()){

            getSupportFragmentManager().beginTransaction().remove(fragment_delete).commit();
        }

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.bottom_nav, fragment).commit();
            Log.i("user clicked", "test");
            return true;
        }
        Log.i("user  didnt clicked", "test");
        return false;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
