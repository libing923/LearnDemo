package com.li.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;
import java.util.List;

import bean.GankResp;

public class PicAdapter extends LoadMoreWrapAdapter {

    private Context mContext;
    private List<GankResp.GankEntry> list = new ArrayList<>();

    PicAdapter(Context context) {
        mContext = context;
    }

    public void clear() {
        list.clear();
    }

    public void setData(GankResp gankResp) {
        this.list = new ArrayList<>(gankResp.results);
        notifyDataSetChanged();
    }

    public void addData(GankResp gankResp) {
        list.addAll(gankResp.results);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == FOOTER) return super.onCreateViewHolder(viewGroup, i);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pic_item, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        if (viewHolder instanceof Holder) {
            final Holder holder = (Holder) viewHolder;
            holder.resetUi();
            if (i < list.size()) {
                final GankResp.GankEntry entry = list.get(i);
                holder.setData(entry);
                holder.icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (holder.loadFinish && holder.bitmap != null) {
                            DisplayActivity.bitmap = holder.bitmap.copy(Bitmap.Config.RGB_565, true);
                            Intent intent = new Intent(mContext, DisplayActivity.class);
                            mContext.startActivity(intent);
                        }
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        int count = list.size();
        return count + 1;
    }

    class Holder extends RecyclerView.ViewHolder {

        private ImageView icon;
        private Bitmap bitmap;
        private boolean loadFinish;

        public Holder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.card_view);
        }

        public void setData(final GankResp.GankEntry entry) {
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_error)
                    .fitCenter();
            Glide.with(mContext).asBitmap().load(entry.url).apply(requestOptions).into(new SimpleTarget<Bitmap>() {

                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                private void getErrorBitmap(Drawable errorDrawable) {
                    if (errorDrawable instanceof VectorDrawable) {
                        VectorDrawable bd = (VectorDrawable) errorDrawable;
                        bitmap = Bitmap.createBitmap(bd.getIntrinsicWidth(), bd.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                    }
                }

                @Override
                public void onLoadFailed(@Nullable Drawable errorDrawable) {
                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getErrorBitmap(errorDrawable);
                    }
                    bitmap = null;
                    icon.setImageDrawable(errorDrawable);
                    loadFinish = true;
                }

                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    bitmap = resource;
                    icon.setImageBitmap(bitmap);
                    loadFinish = true;
                }

                @Override
                public void onLoadStarted(@Nullable Drawable placeholder) {
                    loadFinish = false;
                }
            });
        }

        public void resetUi() {
            icon.setImageResource(R.drawable.ic_launcher_background);
        }
    }
}
