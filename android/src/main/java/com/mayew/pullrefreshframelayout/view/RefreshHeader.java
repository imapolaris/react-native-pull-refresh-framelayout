package com.mayew.pullrefreshframelayout.view;

import android.content.Context;

import com.facebook.react.views.view.ReactViewGroup;
import com.mayew.pullrefreshframelayout.ptrframelayout.PtrFrameLayout;
import com.mayew.pullrefreshframelayout.ptrframelayout.PtrIndicator;
import com.mayew.pullrefreshframelayout.ptrframelayout.PtrUIHandler;

import javax.annotation.Nonnull;

/**
 * Created by sunhui on 12/09/2018.
 */

public class RefreshHeader extends ReactViewGroup implements PtrUIHandler {

    private PullStateChangeListener listener;

    public RefreshHeader(@Nonnull Context context) {
        super(context);
    }

    public void setPullStateChangeListener(PullStateChangeListener listener) {
        this.listener = listener;
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {

    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        if (listener != null) {
            listener.onStateChange(isUnderTouch, status, ptrIndicator.getCurrentPosY());
        }
    }

    public interface PullStateChangeListener {
        void onStateChange(boolean isUnderTouch, int state, int moveHeight);
    }
}
