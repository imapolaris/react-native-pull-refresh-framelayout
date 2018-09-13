package com.mayew.pullrefreshframelayout.view;

import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import java.util.Map;

import javax.annotation.Nullable;

/**
 * Created by sunhui on 12/09/2018.
 */

public class RefreshHeaderViewManager extends ViewGroupManager<RefreshHeader> {
    private static final String CLASS_NAME = "RCTRefreshHeader";

    @Override
    public String getName() {
        return CLASS_NAME;
    }

    public enum Events { //这里是一些点击事件
        ON_PUSHING_STATE("onPushingState");

        private final String mName;
        Events(final String name) {mName = name;}
        @Override
        public String toString() {
            return mName;
        }
    }

    @Override
    protected RefreshHeader createViewInstance(ThemedReactContext reactContext) {
        final RefreshHeader refreshHeader = new RefreshHeader(reactContext);
        final RCTEventEmitter mEventEmitter = reactContext.getJSModule(RCTEventEmitter.class);
        refreshHeader.setPullStateChangeListener(new RefreshHeader.PullStateChangeListener() {
            @Override
            public void onStateChange(boolean isUnderTouch, int state, int moveHeight) {
                //进行下拉触摸的回调
                WritableMap map = Arguments.createMap();
                map.putInt("moveHeight", moveHeight);
                map.putInt("state", state);
                mEventEmitter.receiveEvent(refreshHeader.getId(), Events.ON_PUSHING_STATE.toString(), map);
            }
        });
        return refreshHeader;
    }

    @Nullable
    @Override
    public Map getExportedCustomDirectEventTypeConstants() {
        MapBuilder.Builder builder = MapBuilder.builder();
        for (Events event : Events.values()) {
            builder.put(event.toString(), MapBuilder.of("registrationName", event.toString()));
        }
        return builder.build();
    }

    @ReactProp(name = "viewHeight")
    public void setViewHeight(RefreshHeader view, int viewHeight) {
        view.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, viewHeight));
    }
}
