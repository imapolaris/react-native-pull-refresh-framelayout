package com.mayew.pullrefreshframelayout;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.mayew.pullrefreshframelayout.view.RefreshHeaderViewManager;
import com.mayew.pullrefreshframelayout.view.RefreshLayoutViewManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sunhui on 12/09/2018.
 */

public class RefreshLayoutPackage implements ReactPackage {

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }

    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        List<ViewManager> managers = new ArrayList<>();
        managers.add(new RefreshLayoutViewManager());
        managers.add(new RefreshHeaderViewManager());
        return managers;
    }
}
