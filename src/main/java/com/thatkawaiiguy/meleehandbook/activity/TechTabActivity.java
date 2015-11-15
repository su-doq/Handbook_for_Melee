package com.thatkawaiiguy.meleehandbook.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrConfig;
import com.thatkawaiiguy.meleehandbook.R;
import com.thatkawaiiguy.meleehandbook.adapter.fragment.DIFragmentAdapter;
import com.thatkawaiiguy.meleehandbook.adapter.fragment.GrappleFragmentAdapter;
import com.thatkawaiiguy.meleehandbook.adapter.fragment.SWDFragmentAdapter;
import com.thatkawaiiguy.meleehandbook.adapter.fragment.WallJumpFragmentAdapter;
import com.thatkawaiiguy.meleehandbook.other.Preferences;

import pl.droidsonroids.gif.GifImageView;

public class TechTabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getIntent().hasExtra("bundle") && savedInstanceState == null)
            savedInstanceState = getIntent().getExtras().getBundle("bundle");
        Preferences.applyTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collapsing_tab_gif_layout);
        SlidrConfig config = new SlidrConfig.Builder().sensitivity(0.3f).build();
        Slidr.attach(this, config);

        if (getIntent().getExtras() == null)
            return;
        String techPicked = getIntent().getExtras().getString("option");

        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(
                R.id.collapse_toolbar);
        collapsingToolbar.setTitleEnabled(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            getWindow().setStatusBarColor(0x00000000);
        final AppBarLayout appBar = (AppBarLayout) findViewById(R.id.app_bar);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle(techPicked);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final GifImageView tabImage = (GifImageView) findViewById(R.id.infoGif);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        if(techPicked.equals("Wall jump")) {
            viewPager.setAdapter(new WallJumpFragmentAdapter(getSupportFragmentManager()));
            tabLayout.setupWithViewPager(viewPager);
            tabImage.setImageResource(R.drawable.walljump);
            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                    appBar.setExpanded(true);
                    switch (tab.getPosition()) {
                        case 0:
                            tabImage.setImageResource(R.drawable.walljump);
                            break;
                        case 1:
                            tabImage.setImageResource(R.drawable.ledgewalljump);
                            break;
                        case 2:
                            tabImage.setImageResource(R.drawable.reversewalljump);
                            break;
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });
        } else if (techPicked.equals("Directional Influence")) {
            viewPager.setAdapter(new DIFragmentAdapter(getSupportFragmentManager()));
            tabLayout.setupWithViewPager(viewPager);
            tabImage.setImageResource(R.drawable.di);
            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                    appBar.setExpanded(true);
                    switch (tab.getPosition()) {
                        case 0:
                            tabImage.setImageResource(R.drawable.di);
                            break;
                        case 1:
                            tabImage.setImageResource(R.drawable.sdi);
                            break;
                        case 2:
                            tabImage.setImageResource(R.drawable.diangles);
                            break;
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {}

                @Override
                public void onTabReselected(TabLayout.Tab tab) {}
            });
        } else if (techPicked.equals("Super wavedash & SDWD")) {
            viewPager.setAdapter(new SWDFragmentAdapter(getSupportFragmentManager()));
            tabLayout.setupWithViewPager(viewPager);
            tabImage.setImageResource(R.drawable.swd);
            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                    appBar.setExpanded(true);
                    switch (tab.getPosition()) {
                        case 0:
                            tabImage.setImageResource(R.drawable.swd);
                            break;
                        case 1:
                            tabImage.setImageResource(R.drawable.sdwd);
                            break;
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {}

                @Override
                public void onTabReselected(TabLayout.Tab tab) {}
            });
        } else if (techPicked.equals("Extended & homing grapple")) {
            viewPager.setAdapter(new GrappleFragmentAdapter(getSupportFragmentManager()));
            tabLayout.setupWithViewPager(viewPager);
            tabImage.setImageResource(R.drawable.egrapple);
            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                    appBar.setExpanded(true);
                    switch (tab.getPosition()) {
                        case 0:
                            tabImage.setImageResource(R.drawable.egrapple);
                            break;
                        case 1:
                            tabImage.setImageResource(R.drawable.hominggrapple);
                            break;
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {}

                @Override
                public void onTabReselected(TabLayout.Tab tab) {}
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
}