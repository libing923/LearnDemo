package com.li.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import bean.DoubanMovieBean;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.Holder> {

    private DoubanMovieBean movieBean;
    private Context mContext;

    MovieAdapter(Context context) {
        mContext = context;
    }

    public void setData(DoubanMovieBean doubanMovieBean) {
        movieBean = doubanMovieBean;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.resetUi();
        if (i < movieBean.getSubjects().size()) {
            holder.setData(movieBean.getSubjects().get(i));
        }
    }

    @Override
    public int getItemCount() {
        return movieBean == null ? 0 : movieBean.getCount();
    }

    class Holder extends RecyclerView.ViewHolder {

        private ImageView icon;
        private TextView title, alias;

        public Holder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            alias = itemView.findViewById(R.id.alias);
        }

        public void setData(DoubanMovieBean.SubjectsBean subjectsBean) {
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .fitCenter();
            Glide.with(mContext).load(subjectsBean.getImages().getMedium()).apply(requestOptions).into(icon);
            title.setText(subjectsBean.getTitle());
            alias.setText(subjectsBean.getOriginal_title());
        }

        public void resetUi(){
            icon.setImageResource(R.drawable.ic_launcher_background);
            title.setText("");
            alias.setText("");
        }
    }
}
