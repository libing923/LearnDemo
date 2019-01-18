package com.li.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;

public class LRecyclerView extends RecyclerView {

    //是否是向上滑动
    private boolean mIsSlowUp = false;

    public LRecyclerView(@NonNull Context context) {
        super(context);
    }

    public LRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    private OnLoadMoreListener mLoadMoreListener;

    public void setLoadMoreListener(OnLoadMoreListener loadMore) {
        if (loadMore != null) {
            mLoadMoreListener = loadMore;
            addOnScrollListener(new OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);

                    int lastItemPosition = -1;
                    int itemCount = 0;
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        LayoutManager manager = recyclerView.getLayoutManager();
                        if (manager instanceof LinearLayoutManager) {
                            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) manager;

                            lastItemPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                            itemCount = linearLayoutManager.getItemCount();

                        } else if (manager instanceof StaggeredGridLayoutManager) {
                            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) manager;
                            int[] into = new int[staggeredGridLayoutManager.getSpanCount()];
                            staggeredGridLayoutManager.findLastCompletelyVisibleItemPositions(into);
                            lastItemPosition = findMax(into);
                            itemCount = staggeredGridLayoutManager.getItemCount();
                        }

                        if (getAdapter() instanceof LoadMoreWrapAdapter) {
                            LoadMoreWrapAdapter adapter = (LoadMoreWrapAdapter) getAdapter();
                            if (lastItemPosition > (itemCount - 2)) {
                                if (mIsSlowUp) {
                                    adapter.setFooterVisiable(true);
                                    if (adapter.getLoadingState() != LoadMoreWrapAdapter.LOADING) {
                                        adapter.setLoadingState(LoadMoreWrapAdapter.LOADING);
                                        if (mLoadMoreListener != null)
                                            mLoadMoreListener.onLoadMore();
                                    }
                                }
                            } else {
                                adapter.setFooterVisiable(false);
                            }
                        }
                    }
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    mIsSlowUp = dy > 0;
                }
            });
        }
    }

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

}
