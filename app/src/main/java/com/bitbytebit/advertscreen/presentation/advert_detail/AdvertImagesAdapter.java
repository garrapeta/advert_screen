package com.bitbytebit.advertscreen.presentation.advert_detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bitbytebit.advertscreen.R;
import com.bumptech.glide.Glide;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


class AdvertImagesAdapter extends RecyclerView.Adapter<AdvertImagesAdapter.ImageViewHolder> {

    private List<URL> mImagesUrls = new ArrayList<>();

    public void setImagesUrls(List<URL> urls) {
        mImagesUrls.clear();
        mImagesUrls.addAll(urls);
    }

    public List<URL> getImagesUrls() {
        return mImagesUrls;
    }

    @Override
    public int getItemCount() {
        return mImagesUrls.size();
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ImageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        holder.bind(mImagesUrls.get(position));
    }


    class ImageViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mImageView;


        ImageViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.image_imageView);
        }

        void bind(final URL url) {
            Glide
                    .with(mImageView.getContext())
                    .load(url.toString())
                    .into(mImageView);
        }
    }
}
