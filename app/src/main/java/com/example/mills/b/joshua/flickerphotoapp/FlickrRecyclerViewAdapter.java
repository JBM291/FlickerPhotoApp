package com.example.mills.b.joshua.flickerphotoapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Inferno on 3/25/2018.
 */

class FlickrRecyclerViewAdapter extends RecyclerView.Adapter<FlickrRecyclerViewAdapter.FlickerImageViewHolder>{
    private static final String TAG = "FlickrRecyclerViewAdapt";
    private List<Photo> photosList;
    private Context context;


    public FlickrRecyclerViewAdapter(Context context, List<Photo> photosList) {
        this.photosList = photosList;
        this.context = context;

    }

    @Override
    public FlickerImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.browse, parent,false);
        return new FlickerImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FlickerImageViewHolder holder, int position) {
        Photo photoItem = photosList.get(position);
        Picasso.get().load(photoItem.getImage())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(holder.thumbnail);
        holder.title.setText(photoItem.getTitle());

    }

    @Override
    public int getItemCount() {
        return ((photosList != null) && (photosList.size() != 0))? photosList.size():0;
    }

    void loadNewData(List<Photo> newPhotos){
        photosList = newPhotos;
        notifyDataSetChanged();
    }

    public Photo getPhoto(int position){
        return ((photosList != null) && (photosList.size() != 0)? photosList.get(position) : null);
    }

    static class FlickerImageViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.thumbnail) ImageView thumbnail = null;
        @BindView(R.id.title) TextView title = null;

        FlickerImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}


