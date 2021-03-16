package com.example.cgvapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cgvapplication.adapter.MovieDetailFragMentPagerAdapter;
import com.example.cgvapplication.adapter.MoviePersonAdapter;
import com.example.cgvapplication.helper.MyNavigationHelper;
import com.example.cgvapplication.model.movie.MoviePerson;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String TAG = "MovieDetailActivity";
    private MovieDetailFragMentPagerAdapter mMovieDetailFragMentPagerAdapter;
    private ViewPager mVpMovieDetailContainer;
    private Toolbar mToolbarDefault;
    private TextView mTvToolbarTitle;
    private TabLayout mTabsMovieDetail;
    private MyNavigationHelper mMyNavigationHelper;
    private LinearLayout mLinearNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        init();
        setSupportActionBar(mToolbarDefault);
        mMyNavigationHelper.enable(mLinearNavigation);






    }

    private void init() {
        mLinearNavigation = findViewById(R.id.linear_navigation);
        mToolbarDefault = findViewById(R.id.toolbar_default);
        mTvToolbarTitle = findViewById(R.id.tv_toolbar_title);
        mTabsMovieDetail = findViewById(R.id.tabs_movie_detail);

        mTvToolbarTitle.setText("영화제목");
        mVpMovieDetailContainer = findViewById(R.id.vp_movie_detail_container);
        mMovieDetailFragMentPagerAdapter = new MovieDetailFragMentPagerAdapter(getSupportFragmentManager(), 1);
        mMovieDetailFragMentPagerAdapter.addFragment(new FragMovieDetailInfo());
        mMovieDetailFragMentPagerAdapter.addFragment(new FragMovieDetailCommand());
        mVpMovieDetailContainer.setAdapter(mMovieDetailFragMentPagerAdapter);
        mTabsMovieDetail.setupWithViewPager(mVpMovieDetailContainer);

        mTabsMovieDetail.getTabAt(0).setText("상세정보");
        mTabsMovieDetail.getTabAt(1).setText("실관람평");

        mMyNavigationHelper = new MyNavigationHelper(MovieDetailActivity.this);




    }
}