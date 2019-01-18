package com.li.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

public abstract class LoadMoreWrapAdapter extends RecyclerView.Adapter {

    private boolean isFooterVisiable;

    public final static int LOADED = 0;
    public final static int LOADING = 1;
    public final static int LOADEND = 2;

    public final int FOOTER = -1;

    private int mLoadingState = LOADED;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View footer = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.loadmore_footer, viewGroup, false);
        return new FooterHolder(footer);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof FooterHolder) {
            FooterHolder footerHolder = (FooterHolder) viewHolder;
            if (isFooterVisiable) {
                footerHolder.itemView.setVisibility(View.VISIBLE);
                if (mLoadingState == LOADING) {
                    footerHolder.mProgressBar.setVisibility(View.VISIBLE);
                    footerHolder.mTextView.setText(R.string.loading);
                } else {
                    footerHolder.mProgressBar.setVisibility(View.GONE);
                    if (mLoadingState == LOADED) {
                        footerHolder.mTextView.setText(R.string.loaded);
                    } else {
                        footerHolder.mTextView.setText(R.string.loadend);
                    }
                }
            } else {
                footerHolder.itemView.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return FOOTER;
        }
        return super.getItemViewType(position);
    }

    public int getLoadingState() {
        return mLoadingState;
    }

    public void setLoadingState(int state) {
        mLoadingState = state;
        notifyItemRangeChanged(getItemCount() - 1, 1);
    }

    public void setFooterVisiable(boolean visiable) {
        isFooterVisiable = visiable;
        notifyItemRangeChanged(getItemCount() - 1, 1);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            final int spanCount = gridLayoutManager.getSpanCount();
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int i) {
                    return getItemViewType(i) == FOOTER ? spanCount : 1;
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            if (holder.getLayoutPosition() == getItemCount() - 1) {
                ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
            }
        }
    }

    public class FooterHolder extends RecyclerView.ViewHolder {
        ProgressBar mProgressBar;
        TextView mTextView;

        public FooterHolder(View itemView) {
            super(itemView);
            mProgressBar = itemView.findViewById(R.id.progressBar);
            mTextView = itemView.findViewById(R.id.footer);
        }
    }
}
