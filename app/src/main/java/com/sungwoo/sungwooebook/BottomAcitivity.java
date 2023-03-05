package com.sungwoo.sungwooebook;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sungwoo.sungwooebook.Fragment.EventFragment;
import com.sungwoo.sungwooebook.Fragment.FavoriteFragment;
import com.sungwoo.sungwooebook.Fragment.HomeFragment;
import com.sungwoo.sungwooebook.Fragment.MyPageFragment;
import com.sungwoo.sungwooebook.Fragment.SearchFragment;
import com.sungwoo.sungwooebook.Utils.UtilsLog;

public class BottomAcitivity extends AppCompatActivity {

    HomeFragment mHomeFragment;
    SearchFragment mSearchFragment;
    FavoriteFragment mFavoriteFragment;
    MyPageFragment mMyPageFragment;
    EventFragment mEventFragment;

    Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UtilsLog.d("BottomAcitivity onCreate");
        mContext = this;
        setContentView(R.layout.activity_bottom);

        final HomeFragment mHomeFragment = new HomeFragment(mContext);
        /*Fragment mSearchFragment = new Fragment();
        Fragment mFavoriteFragment = new Fragment();
        Fragment mMyPageFragment = new Fragment();
        Fragment mEventFragment = new Fragment();*/

        getSupportFragmentManager().beginTransaction().replace(R.id.bottom_container, mHomeFragment).commit();

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.bottom_container, mHomeFragment).commit();
                        return true;
                    /*case R.id.tab2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.bottom_container, fragment2).commit();
                        return true;
                    case R.id.tab3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.bottom_container, fragment3).commit();
                        return true;*/
                }
                return false;
            }
        });
    }
}
