package com.fphoenixcorneae.recyclerview.snaphelper.widget;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;


/**
 * 滑动停靠左对齐
 *
 * @author wkz
 */
public class StartSnapHelper extends LinearSnapHelper {

    @Nullable
    private OrientationHelper mVerticalHelper;
    @Nullable
    private OrientationHelper mHorizontalHelper;

    public StartSnapHelper() {

    }

    @Override
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View targetView) {
        return calculateDisOnStart(layoutManager, targetView);
    }

    @Override
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        return findStartSnapView(layoutManager);
    }

    /**
     * find the start view
     *
     * @param layoutManager
     * @return
     */
    private View findStartSnapView(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager.canScrollVertically()) {
            return findStartView(layoutManager, getVerticalHelper(layoutManager));
        } else if (layoutManager.canScrollHorizontally()) {
            return findStartView(layoutManager, getHorizontalHelper(layoutManager));
        }
        return null;
    }

    private int[] calculateDisOnStart(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View targetView) {
        int[] out = new int[2];
        if (layoutManager.canScrollHorizontally()) {
            out[0] = distanceToStart(layoutManager, targetView,
                    getHorizontalHelper(layoutManager));
        } else {
            out[0] = 0;
        }

        if (layoutManager.canScrollVertically()) {
            out[1] = distanceToStart(layoutManager, targetView,
                    getVerticalHelper(layoutManager));
        } else {
            out[1] = 0;
        }
        return out;
    }

    /**
     * calculate distance to start
     *
     * @param layoutManager
     * @param targetView
     * @param helper
     * @return
     */
    private int distanceToStart(@NonNull RecyclerView.LayoutManager layoutManager,
                                @NonNull View targetView, OrientationHelper helper) {
        return helper.getDecoratedStart(targetView) - helper.getStartAfterPadding();
    }


    /**
     * 注意判断最后一个item时，应通过判断距离右侧的位置
     *
     * @param layoutManager
     * @param helper
     * @return
     */
    private View findStartView(RecyclerView.LayoutManager layoutManager, OrientationHelper helper) {
        if (!(layoutManager instanceof LinearLayoutManager)) {
            // only for LinearLayoutManager
            return null;
        }
        int childCount = layoutManager.getChildCount();
        if (childCount == 0) {
            return null;
        }

        View closestChild = null;
        final int start = helper.getStartAfterPadding();

        int absClosest = Integer.MAX_VALUE;
        for (int i = 0; i < childCount; i++) {
            final View child = layoutManager.getChildAt(i);
            int childStart = helper.getDecoratedStart(child);
            int absDistance = Math.abs(childStart - start);

            if (absDistance < absClosest) {
                absClosest = absDistance;
                closestChild = child;
            }
        }

        View firstVisibleChild = layoutManager.getChildAt(0);

        if (firstVisibleChild != closestChild) {
            return closestChild;
        }

        int firstChildStart = helper.getDecoratedStart(firstVisibleChild);

        int lastChildPos = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        View lastChild = layoutManager.getChildAt(childCount - 1);
        int lastChildCenter = helper.getDecoratedStart(lastChild) + (helper.getDecoratedMeasurement(lastChild) / 2);
        boolean isEndItem = lastChildPos == layoutManager.getItemCount() - 1;
        if (isEndItem && firstChildStart < 0 && lastChildCenter < helper.getEnd()) {
            return lastChild;
        }

        return closestChild;
    }

    @NonNull
    private OrientationHelper getVerticalHelper(@NonNull RecyclerView.LayoutManager layoutManager) {
        if (mVerticalHelper == null) {
            mVerticalHelper = OrientationHelper.createVerticalHelper(layoutManager);
        }
        return mVerticalHelper;
    }

    @NonNull
    private OrientationHelper getHorizontalHelper(
            @NonNull RecyclerView.LayoutManager layoutManager) {
        if (mHorizontalHelper == null) {
            mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return mHorizontalHelper;
    }
}