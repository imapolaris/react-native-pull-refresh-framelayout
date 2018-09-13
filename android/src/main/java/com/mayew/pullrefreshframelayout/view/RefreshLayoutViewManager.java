package com.mayew.pullrefreshframelayout.view;

import android.view.View;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.mayew.pullrefreshframelayout.ptrframelayout.PtrDefaultHandler;
import com.mayew.pullrefreshframelayout.ptrframelayout.PtrFrameLayout;

import java.util.Map;

import javax.annotation.Nullable;

/**
 * Created by sunhui on 12/09/2018.
 */

public class RefreshLayoutViewManager extends ViewGroupManager<PtrFrameLayout> {

    private static final String CLASS_NAME = "RCTRefreshLayout";
    private static final int START_REFRESH = 1;
    private static final int FINISH_REFRESH = 2;

    @Override
    public String getName() {
        return CLASS_NAME;
    }

    public enum Events {
        ON_RELEASE("onPullRelease");

        private final String mName;
        Events(final String name) {mName = name;}

        @Override
        public String toString() {
            return mName;
        }
    }

    @Override
    protected PtrFrameLayout createViewInstance(ThemedReactContext reactContext) {
        final PtrFrameLayout ptrFrameLayout = new PtrFrameLayout(reactContext);
        final RCTEventEmitter mEventEmitter = reactContext.getJSModule(RCTEventEmitter.class);
        ptrFrameLayout.setRatioOfHeaderHeightToRefresh(1.0f);
        ptrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mEventEmitter.receiveEvent(ptrFrameLayout.getId(), Events.ON_RELEASE.toString(), Arguments.createMap());
            }
        });
        return ptrFrameLayout;
    }

    @Override
    public void receiveCommand(final PtrFrameLayout root, int commandId, @Nullable ReadableArray args) {
        switch (commandId) {
            case START_REFRESH:
                root.post(new Runnable() {
                    @Override
                    public void run() {
                        root.autoRefresh();
                    }
                });
                break;
            case FINISH_REFRESH:
                root.refreshComplete();
                break;
        }
    }

    @Nullable
    @Override
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("startRefresh", START_REFRESH, "finishRefresh", FINISH_REFRESH);
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

    @ReactProp(name = "isContentScroll")
    public void setContentScroll(PtrFrameLayout view, boolean isContentScroll) {
        view.setPinContent(!isContentScroll);
    }

    @ReactProp(name = "refreshable")
    public void setRefreshable(PtrFrameLayout view, boolean refreshable) {
        view.setEnabled(refreshable);
    }
}
