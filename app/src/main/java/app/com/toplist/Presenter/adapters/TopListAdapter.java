package app.com.toplist.Presenter.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;

import app.com.toplist.DTO.responses.TopListDTO;
import app.com.toplist.R;

/**
 * Created by Balwinder on 27/07/2017.
 * Copyright (c) 2017 M800 inc. All rights reserved.
 */
public class TopListAdapter extends RecyclerView.Adapter<TopListAdapter.MyViewHolder> {

    private Context mContext;
    private List<TopListDTO> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvSong, tvArtistName;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            tvSong = (TextView) view.findViewById(R.id.tvSong);
            tvArtistName = (TextView) view.findViewById(R.id.tvArtistName);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }


    public TopListAdapter(Context mContext, List<TopListDTO> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false);

        return new MyViewHolder(itemView);
    }

    public void updateData(List<TopListDTO> albumList)
    {
        this.albumList.clear();
        this.albumList.addAll(albumList);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        TopListDTO album = albumList.get(position);
        holder.tvSong.setText(album.getSongName());
        holder.tvArtistName.setText(album.getArtist());

        // loading album cover using Glide library
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);


    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}