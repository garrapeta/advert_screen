package com.bitbytebit.advertscreen.presentation.application;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.bitbytebit.advertscreen.presentation.application.di.AppComponent;
import com.bitbytebit.advertscreen.presentation.application.di.AppModule;
import com.bitbytebit.advertscreen.presentation.application.di.DaggerAppComponent;

/**
 * Application context of the App
 */
public class AdvertScreenApplication extends MultiDexApplication {

    public static AdvertScreenApplication get(Context context) {
        return (AdvertScreenApplication) context.getApplicationContext();
    }

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        setupDependencyInjection();
    }

    private void setupDependencyInjection() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }



}
