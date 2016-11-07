package com.example.soul.swipecardsview;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.facebook.react.LifecycleState;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;


/**
 * * @author soul
 *
 * @项目名:SwipeCardsView
 * @包名: com.example.soul.swipecardsview
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2016/10/11 21:54
 */
public class ReactNatiiveActivity extends Activity implements DefaultHardwareBackBtnHandler {
    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        mReactRootView = new ReactRootView(this);
        mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setBundleAssetName("index.android.bundle")
                .setJSMainModuleName("index.android")
                .addPackage(new MainReactPackage())
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                //.setUseOldBridge(true) // uncomment this line if your app crashes
                .build();
        mReactRootView.startReactApplication(mReactInstanceManager, "HelloWorld", null);

        setContentView(mReactRootView);

    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();

    }
    @Override
    protected void onPause() {
        super.onPause();

        if (mReactInstanceManager != null) {
//            mReactInstanceManager.onPause();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (mReactInstanceManager != null) {
//            mReactInstanceManager.onResume(this, this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mReactInstanceManager != null) {
//            mReactInstanceManager.onDestroy();
        }
    }

    @Override
    public void onBackPressed() {
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }
}
