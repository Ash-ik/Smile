package com.askme.dreamhackathon.smile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.askme.dreamhackathon.smile.DatabaseBuilder.DBadapter;
import com.mxn.soul.flowingdrawer_core.FlowingView;
import com.mxn.soul.flowingdrawer_core.LeftDrawerLayout;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;


public class MainActivity extends AppCompatActivity {

    MyMenuFragment mMenuFragment;
    private RecyclerView rvFeed;
    LeftDrawerLayout mLeftDrawerLayout;
    View animatedView;
    String userName,userEmail,userPassword;
    int[] imgList = {R.drawable.home1, R.drawable.home2, R.drawable.home3, R.drawable.home4,
            R.drawable.home5};

    String[] nameList = {"Mother and Children Health", "Health care in time of Pregnancy", "Baby Health", "Newborn Baby Vaccine", "Child Health care"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBadapter dBadapter=new DBadapter(getApplicationContext());

        setupToolbar();

        SharedPreferences prefer = getSharedPreferences("askme.dreamhackathon.smile", Context.MODE_PRIVATE);
        userName=prefer.getString("askme.dreamhackathon.smile.userName","noUser");
        userPassword=prefer.getString("askme.dreamhackathon.smile.userPassword","noUser");
        userEmail=prefer.getString("askme.dreamhackathon.smile.userEmail","noUser");
        mLeftDrawerLayout = (LeftDrawerLayout) findViewById(R.id.id_drawerlayout);
        rvFeed = (RecyclerView) findViewById(R.id.rvFeed);

        FragmentManager fm = getSupportFragmentManager();
        mMenuFragment = (MyMenuFragment) fm.findFragmentById(R.id.id_container_menu);
        FlowingView mFlowingView = (FlowingView) findViewById(R.id.sv);

        if (mMenuFragment == null) {
            fm.beginTransaction().add(R.id.id_container_menu, mMenuFragment = new MyMenuFragment()).commit();
        }
        mMenuFragment.setUser(userName,userEmail,userPassword);

        mLeftDrawerLayout.setFluidView(mFlowingView);
        mLeftDrawerLayout.setMenuFragment(mMenuFragment);

        mMenuFragment.leftDrawerLayout=mLeftDrawerLayout;

        setupFeed();

    }


    protected void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_white);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLeftDrawerLayout.toggle();
                mMenuFragment.vNavigation.setNavigationItemSelectedListener(navlistner);
            }
        });
    }


    private void setupFeed() {
        rvFeed.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        MasonryAdapter adapter = new MasonryAdapter(this);
        rvFeed.setAdapter(adapter);

        SpacesItemDecoration decoration = new SpacesItemDecoration(5);
        rvFeed.addItemDecoration(decoration);
        adapter.setOnItemClickListener(mItemClickListener);


    }
    MasonryAdapter.OnItemClickListener mItemClickListener= new MasonryAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {

            int[] screenLocation = new int[2];
            view.getLocationOnScreen(screenLocation);

            Intent i=new Intent(MainActivity.this,DetailsHomeItemActivity.class);

            i.putExtra("title",nameList[position])
                    .putExtra("photo", imgList[position])
                    .putExtra("left", screenLocation[0])
                    .putExtra("top", screenLocation[1])
                    .putExtra("width", view.getWidth())
                    .putExtra("height", view.getHeight());
            startActivity(i);
            // Override transitions: we don't want the normal window animation in addition to our
            // custom one
            overridePendingTransition(0, 0);

            // The detail activity handles the enter and exit animations. Both animations involve a
            // ghost view animating into its final or initial position respectively. Since the detail
            // activity starts translucent, the clicked view needs to be invisible in order for the
            // animation to look correct.
            animatedView=view;
            ViewPropertyAnimator.animate(animatedView).alpha(0.0f);
        }
    };

    @Override
    public void onBackPressed() {
        if (mLeftDrawerLayout.isShownMenu()) {
            mLeftDrawerLayout.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            // The activity transition animates the clicked image alpha to zero, reset that value when
            // you come back to this activity
            if(animatedView!=null)
            ViewHelper.setAlpha(animatedView, 1.0f);
        }
    }
    NavigationView.OnNavigationItemSelectedListener navlistner=new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            int id = menuItem.getItemId();
            Intent i;
            //noinspection SimplifiableIfStatement
            mLeftDrawerLayout.closeDrawer();
            switch (id)
            {
                case R.id.menu_timeline:
/*                    i = new Intent(MainActivity.this, nomaratmanfi.class);
                    startActivity(i);*/
                    break;
                case R.id.menu_dailyTips:
/*                    i = new Intent(MainActivity.this, Setting.class);
                    startActivity(i);*/
                    break;
                case R.id.menu_healthChart:
/*                    i = new Intent(MainActivity.this, Payment.class);
                    startActivity(i);*/
                    break;
                case R.id.menu_nearbyHospital:
/*                    i = new Intent(MainActivity.this, Aboutus.class);
                    startActivity(i);*/
                    break;
                case R.id.menu_settings:
/*                    i = new Intent(MainActivity.this, Contactus.class);
                    startActivity(i);*/
                    break;
                case R.id.menu_about:
/*
                    i = new Intent(MainActivity.this, Contactus.class);
                    startActivity(i);
*/
                    break;
            }

            return false;
        }
    };
}
