package com.example.cgvapplication;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cgvapplication.adapter.MoviePersonAdapter;
import com.example.cgvapplication.adapter.MovieStillCutAdapter;
import com.example.cgvapplication.model.movie.MoviePerson;
import com.example.cgvapplication.model.movie.StillCut;

import java.util.ArrayList;
import java.util.List;

public class FragMovieDetailInfo extends Fragment {

    private static final String TAG = "FragMovieDetailInfo";
    private final FragMovieDetailInfo fragMovieDetailInfo = this;
    private RecyclerView mRvMoviePerson;
    private MoviePersonAdapter mMoviePersonAdapter;
    private List<MoviePerson> mMoviePeople;
    private List<StillCut> mStillCuts;
    private MovieStillCutAdapter mMovieStillCutAdapter;
    private GridView mGvMovieDetail;
    private Handler mHandler;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_movie_detail_info, container, false);
        init(view);

        mMoviePeople = new ArrayList<>();
        mMoviePeople.add(new MoviePerson(1, "데이미언셔젤", R.drawable.ic_person, true));
        mMoviePeople.add(new MoviePerson(2, "라이언 고슬링", R.drawable.ic_person, false));
        mMoviePeople.add(new MoviePerson(3, "엠마 스톤", R.drawable.ic_person, false));
        mMoviePeople.add(new MoviePerson(4, "존 레전드", R.drawable.ic_person, false));
        mMoviePeople.add(new MoviePerson(5, "로즈마리드윗", R.drawable.ic_person, false));
        mMoviePeople.add(new MoviePerson(6, "J.K. 시몬스", R.drawable.ic_person, false));
        mMoviePeople.add(new MoviePerson(7, "소노야 미즈노", R.drawable.ic_person, false));
        mMoviePeople.add(new MoviePerson(8, "제시카 로테", R.drawable.ic_person, false));

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false);
        mRvMoviePerson.setLayoutManager(mLinearLayoutManager);
        mMoviePersonAdapter = new MoviePersonAdapter(mMoviePeople);
        mRvMoviePerson.setAdapter(mMoviePersonAdapter);


        mMovieStillCutAdapter = new MovieStillCutAdapter(mStillCuts, fragMovieDetailInfo.getContext());
        mGvMovieDetail.setAdapter(mMovieStillCutAdapter);
        return view;
    }

    private void init(View view) {
        mRvMoviePerson = view.findViewById(R.id.rv_movie_person);
        mGvMovieDetail = view.findViewById(R.id.gv_movie_detail);

        mHandler = new Handler();
        mStillCuts = new ArrayList<>();
        download();
    }

    private void download() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                mStillCuts.add(new StillCut(1, R.drawable.lalaland));
                mStillCuts.add(new StillCut(2, R.drawable.lalaland_poster));
                mStillCuts.add(new StillCut(3, R.drawable.lalaland));
                mStillCuts.add(new StillCut(4, R.drawable.lalaland));
                mStillCuts.add(new StillCut(5, R.drawable.lalaland));
                mStillCuts.add(new StillCut(6, R.drawable.lalaland));
                Log.d(TAG, "run: "+mStillCuts.size());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mMovieStillCutAdapter.notifyDataSetChanged();
                    }
                });

            }
        }).start();
    }
    private void runOnUiThread(Runnable runnable) {
        mHandler.post(runnable);
    }
}
