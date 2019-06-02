package com.wkz.snaphelper.widget;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 滑动停靠右对齐
 *
 * @author wkz
 */
public class EndSnapHelper extends LinearSnapHelper {

    @Nullable
    private OrientationHelper mVerticalHelper;
    @Nullable
    private OrientationHelper mHorizontalHelper;

    public EndSnapHelper() {

    }

    @Override
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View targetView) {
        return calculateDisOnEnd(layoutManager, targetView);
    }

    @Override
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        return findEndSnapView(layoutManager);
    }

    private int[] calculateDisOnEnd(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View targetView) {
        int[] out = new int[2];
        if (layoutManager.canScrollHorizontally()) {
            out[0] = distanceToEnd(layoutManager, targetView,
                    getHorizontalHelper(layoutManager));
        } else {
            out[0] = 0;
        }

        if (layoutManager.canScrollVertically()) {
            out[1] = distanceToEnd(layoutManager, targetView,
                    getVerticalHelper(layoutManager));
        } else {
            out[1] = 0;
        }
        return out;
    }

    /**
     * calculate distance to end
     *
     * @param layoutManager
     * @param targetView
     * @param helper
     * @return
     */
    private int distanceToEnd(@NonNull RecyclerView.LayoutManager layoutManager,
                              @NonNull View targetView, OrientationHelper helper) {
        return helper.getDecoratedEnd(targetView) - helper.getEndAfterPadding();
    }

    /**
     * find the end view
     *
     * @param layoutManager
     * @return
     */
    private View findEndSnapView(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager.canScrollVertically()) {
            return findEndView(layoutManager, getVerticalHelper(layoutManager));
        } else if (layoutManager.canScrollHorizontally()) {
            return findEndView(layoutManager, getHorizontalHelper(layoutManager));
        }
        return null;
    }

    private View findEndView(RecyclerView.LayoutManager layoutManager, OrientationHelper helper) {
        if (!(layoutManager instanceof LinearLayoutManager)) {
            // only for LinearLayoutManager
            return null;
        }
        int childCount = layoutManager.getChildCount();
        if (childCount == 0) {
            return null;
        }

        if (((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition() == 0) {
            return null;
        }

        View closestChild = null;
        final int end = helper.getEndAfterPadding();

        int absClosest = Integer.MAX_VALUE;
        for (int i = 0; i < childCount; i++) {
            final View child = layoutManager.getChildAt(i);
            int childStart = helper.getDecoratedEnd(child);
            int absDistance = Math.abs(childStart - end);

            if (absDistance < absClosest) {
                absClosest = absDistance;
                closestChild = child;
            }
        }

        View lastVisibleChild = layoutManager.getChildAt(childCount - 1);

        if (lastVisibleChild != closestChild) {
            return closestChild;
        }

        if (closestChild != null
                && layoutManager.getPosition(closestChild) == ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition()) {
            return closestChild;
        }

        View firstChild = layoutManager.getChildAt(0);
        int firstChildStart = helper.getDecoratedStart(firstChild);

        int firstChildPos = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        boolean isFirstItem = firstChildPos == 0;


        int firstChildCenter = helper.getDecoratedStart(firstChild) + (helper.getDecoratedMeasurement(firstChild) / 2);
        if (isFirstItem && firstChildStart < 0 && firstChildCenter > helper.getStartAfterPadding()) {
            return firstChild;
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