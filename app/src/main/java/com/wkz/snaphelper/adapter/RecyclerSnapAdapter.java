package com.wkz.snaphelper.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.wkz.snaphelper.model.ImageModel;
import com.wkz.snaphelper.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerSnapAdapter extends RecyclerView.Adapter<RecyclerSnapAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_snap, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) holder.mIvImg.getLayoutParams();
//        if (position == 0) {
//            layoutParams.leftMargin = 20;
//            layoutParams.rightMargin = 20;
//        } else {
//            layoutParams.leftMargin = 0;
//            layoutParams.rightMargin = 20;
//        }
//        holder.mIvImg.setLayoutParams(layoutParams);

        Glide.with(holder.mIvImg)
                .load(ImageModel.IMAGES.get(position))
                .apply(new RequestOptions()
                        .transform(new CenterCrop(), new RoundedCorners(8))
                        .skipMemoryCache(false)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                )
                .into(holder.mIvImg);
    }

    @Override
    public int getItemCount() {
        return ImageModel.IMAGES.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivImg)
        ImageView mIvImg;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
