package com.mayew.pullrefreshframelayout.ptrframelayout;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ScrollView;

public abstract class PtrDefaultHandler implements PtrHandler {

    public static boolean canChildScrollUp(View view) {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (view instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) view;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                        .getTop() < absListView.getPaddingTop());
            } else {
                return view.getScrollY() > 0;
            }
        } else {
            if (!(view instanceof ScrollView || view instanceof AbsListView)) {
                // 判断ViewGroup中每个子节点是否可以向上滚动
                if (view instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup)view;
                    for (int i = 0; i < viewGroup.getChildCount(); ++i) {
                        View view1 = viewGroup.getChildAt(i);
                        if (view1.canScrollVertically(-1)) {
                            return true;
                        }
                    }

                    return false;
                }
            }

            return view.canScrollVertically(-1);
        }
    }

    /**
     * Default implement for check can perform pull to refresh
     *
     * @param frame
     * @param content
     * @param header
     * @return
     */
    public static boolean checkContentCanBePulledDown(PtrFrameLayout frame, View content, View header) {
        return !canChildScrollUp(content);
    }

    @Override
    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        return checkContentCanBePulledDown(frame, content, header);
    }
}