package com.example.mymoviememoir.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.mymoviememoir.R;
import com.example.mymoviememoir.database.SearchMovieBean;
import com.example.mymoviememoir.utils.StarBar;

import java.util.ArrayList;
import java.util.List;

public class SearchMovieAdapter extends RecyclerView.Adapter<SearchMovieAdapter.HomeMovieHolder> {

    List<SearchMovieBean.ResultBean> mList;

    private Context mContext;
    private final RequestOptions options;

    public SearchMovieAdapter(Context mContext) {
        this.mList = new ArrayList<>();
        this.mContext = mContext;
        options = new RequestOptions()
                .transforms(new CenterCrop(),new RoundedCorners(8));
    }

    public void changeData(List<SearchMovieBean.ResultBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeMovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeMovieHolder(LayoutInflater.from(mContext).inflate(R.layout.home_movie_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeMovieHolder holder, int position) {

        Glide.with(mContext).load(mList.get(position).getPoster()).apply(options).into(holder.iv_img);

        holder.tv_title.setText(mList.get(position).getTitle() + "");

        String rating = mList.get(position).getRating();

        if (rating != null && !"".equals(rating)) {
            holder.rs_view.setStarMark((float) (Double.parseDouble(rating) / 2.0f));
            holder.tv_star.setText(mList.get(position).getRating());
        }else {
            holder.rs_view.setStarMark(0);
            holder.tv_star.setText("0");
        }

        holder.tv_content.setText("actors："+mList.get(position).getActors());

        holder.tv_write.setText("directors："+mList.get(position).getDirectors());

        String mainland_pubdate = mList.get(position).getRelease_date();

        if (mainland_pubdate == null || "".equals(mainland_pubdate)) {
            mainland_pubdate = "nothing";
        }

        holder.tv_publish_time.setText("Release time："+mainland_pubdate);

        holder.tv_time.setText("Running time："+mList.get(position).getRuntime());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iOnClick != null) {
                    iOnClick.iOnClickCheck(mList.get(position));
                }
            }
        });
    }

    IOnClick iOnClick;

    public void setiOnClick(IOnClick iOnClick) {
        this.iOnClick = iOnClick;
    }

    public interface IOnClick {
        void iOnClickCheck(SearchMovieBean.ResultBean movieId);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HomeMovieHolder extends RecyclerView.ViewHolder {
        ImageView iv_img;
        TextView tv_title;
        TextView tv_star;
        StarBar rs_view;
        TextView tv_content;
        TextView tv_publish_time;
        TextView tv_time;
        TextView tv_g;
        TextView tv_write;
        public HomeMovieHolder(@NonNull View itemView) {
            super(itemView);
            tv_g = itemView.findViewById(R.id.tv_g);
            iv_img = itemView.findViewById(R.id.iv_img);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_star = itemView.findViewById(R.id.tv_star);
            rs_view = itemView.findViewById(R.id.rs_view);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_publish_time = itemView.findViewById(R.id.tv_publish_time);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_write = itemView.findViewById(R.id.tv_write);
        }
    }
}
