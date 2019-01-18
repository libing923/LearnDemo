package com.li.myapplication;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;

import bean.DoubanMovieBean;
import bean.GankResp;
import subscribe.MovieSubscribe;
import utils.MyApplication;

public class MyFragment extends Fragment {

    private int type, page, start;
    private SwipeRefreshLayout swipe;
    private boolean isRefreshing;
    private MovieSubscribe movieSubscribe;
    private MovieAdapter movieAdapter;
    private PicAdapter picAdapter;
    private Observer observer;
    private final int T = 13;

    public final static MyFragment newInstance(int type) {
        MyFragment fragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getInt("type");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipe = view.findViewById(R.id.swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefreshing = true;
                if (type == 2) {
                    initPage();
                    picAdapter.clear();
                }
                getData(false);
            }
        });
        final LRecyclerView recyclerView = view.findViewById(R.id.recyleview);

        if (type == 2) {
            initPage();
            picAdapter = new PicAdapter(getContext());
            RecyclerView.LayoutManager sGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(sGridLayoutManager);
            recyclerView.setLoadMoreListener(new LRecyclerView.OnLoadMoreListener() {
                @Override
                public void onLoadMore() {
                    if (type == 2 && ++page % T == start) {
                        page--;
                        picAdapter.setLoadingState(LoadMoreWrapAdapter.LOADEND);
                    } else {
                        view.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getData(false);
                            }
                        }, 1000);
                    }
                }
            });
            recyclerView.setAdapter(picAdapter);
            if (observer == null) {
                observer = new Observer<GankResp>() {
                    @Override
                    public void onChanged(@Nullable GankResp gankResp) {
                        picAdapter.addData(gankResp);
                        if (page != start) picAdapter.setLoadingState(LoadMoreWrapAdapter.LOADED);
                        if (isRefreshing) {
                            isRefreshing = false;
                            swipe.setRefreshing(false);
                        }
                    }
                };
                getMovieSubscribe().getPicData().observe(this, observer);
            }
        } else {
            movieAdapter = new MovieAdapter(getContext());
            RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(movieAdapter);
            if (observer == null) {
                observer = new Observer<DoubanMovieBean>() {
                    @Override
                    public void onChanged(@Nullable DoubanMovieBean doubanMovieBean) {
                        movieAdapter.setData(doubanMovieBean);
                        if (isRefreshing) {
                            isRefreshing = false;
                            swipe.setRefreshing(false);
                        }
                    }
                };
            }
            getMovieSubscribe().getMovieBean().observe(this, observer);
        }


    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    getData(true);
                }
            });
        }
    }

    private void initPage() {
        start = new Random().nextInt(T);
        page = start + 1;
    }

    private void getData(boolean showProgress) {
        if (type == 0) {
            getMovieSubscribe().getLast(0, 30, getContext());
        } else if (type == 1) {
            getMovieSubscribe().getTop(0, 30);
        } else {
            getMovieSubscribe().getPic(page, getContext(), showProgress);
        }
    }

    private MovieSubscribe getMovieSubscribe() {
        if (movieSubscribe == null) {
            // ViewModelProvider.Factory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication());
            // HERE IS THE ISSUE: Seems not good practice to have to create a new ViewModelProvider every time this Fragment is created.
            // Perhaps I should just create a singleton ViewModelProvider in the Activity or Application,
            // so here could call getActivity().getViewModelProvider(this, factory).
            ViewModelProvider viewModelProvider = new ViewModelProvider(this, ((MyApplication) getActivity().getApplication()).gettViewModelFactory());
            movieSubscribe = viewModelProvider.get(MovieSubscribe.class);
        }
        return movieSubscribe;
    }
}
