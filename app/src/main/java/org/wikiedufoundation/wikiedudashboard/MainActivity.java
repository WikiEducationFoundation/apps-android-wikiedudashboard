package org.wikiedufoundation.wikiedudashboard;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_explore:
                    replaceFragment(new ExploreFragment());
                    return true;
                case R.id.navigation_dashboard:
                    replaceFragment(new MyDashboardFragment());
                    return true;
                case R.id.navigation_training:
                    replaceFragment(new TrainingFragment());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        addFragment(new ExploreFragment());
    }

    public void addFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.home_layout, fragment);
//            fragmentTransaction.hide(fragmentManager.getFragments().get(fragmentManager.getBackStackEntryCount() - 1));
            fragmentTransaction.add(R.id.home_container, fragment);
            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
            fragmentTransaction.commit();
        }
    }

    public void clearStack() {
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 1) {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(1);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    public void replaceFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.home_container, fragment);
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
            fragmentTransaction.commit();
        }
    }

}
