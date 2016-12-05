package com.example.gyeongsuk.underthefield.main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gyeongsuk.underthefield.R;
import com.example.gyeongsuk.underthefield.main.Domestic.DomesticFragment;
import com.example.gyeongsuk.underthefield.main.Global.GlobalFragment;
import com.example.gyeongsuk.underthefield.main.History.HistoryFragment;
import com.example.gyeongsuk.underthefield.main.Recommendation.RecFragment;

public class MainActivity extends AppCompatActivity {

    ViewPager pager;
    TabLayout tab;
    DomesticFragment df;
    GlobalFragment gf;
    HistoryFragment hf;
    RecFragment rf;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        df = new DomesticFragment();
        gf = new GlobalFragment();
        hf = new HistoryFragment();
        rf = new RecFragment();

        tab = (TabLayout) findViewById(R.id.tab);
        tab.addTab(tab.newTab().setText("국내"));
        tab.addTab(tab.newTab().setText("국외"));
        tab.addTab(tab.newTab().setText("히스토리"));
        tab.addTab(tab.newTab().setText("추천"));

        pager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(4);

        tab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager));
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));

    }

    class PagerAdapter extends FragmentStatePagerAdapter {


        public PagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = df;
                    break;
                case 1:
                    fragment = gf;
                    break;
                case 2:
                    fragment = hf;
                    break;
                case 3:
                    fragment = rf;
            }
            return fragment;

        }

        @Override
        public int getCount() {
            return 4;
        }
    }

}