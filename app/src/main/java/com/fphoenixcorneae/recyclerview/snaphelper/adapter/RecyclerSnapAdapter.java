package com.fphoenixcorneae.recyclerview.snaphelper.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.fphoenixcorneae.recyclerview.snaphelper.R;
import com.fphoenixcorneae.recyclerview.snaphelper.model.ImageModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wkz
 */
public class RecyclerSnapAdapter extends RecyclerView.Adapter<RecyclerSnapAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_snap, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) holder.mIvImg.getLayoutParams();
        if (position == 0) {
            layoutParams.leftMargin = 30;
            layoutParams.rightMargin = 15;
        } else if (position == getItemCount() - 1) {
            layoutParams.leftMargin = 15;
            layoutParams.rightMargin = 30;
        } else {
            layoutParams.leftMargin = 15;
            layoutParams.rightMargin = 15;
        }
        holder.mIvImg.setLayoutParams(layoutParams);

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
