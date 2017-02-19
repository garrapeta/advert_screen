package com.bitbytebit.advertscreen.presentation.application.di;


import android.content.Context;
import android.content.res.AssetManager;

import com.bitbytebit.advertscreen.data.advert.AdvertDataSource;
import com.bitbytebit.advertscreen.data.advert.AdvertRepo;
import com.bitbytebit.advertscreen.presentation.application.AdvertScreenApplication;
import com.bitbytebit.cleanframe.presentation.AndroidSchedulerProvider;
import com.bitbytebit.cleanframe.presentation.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final AdvertScreenApplication mApp;

    public AppModule(AdvertScreenApplication app) {
        mApp = app;
    }

    @Provides
    @Singleton
    public AdvertScreenApplication providesApplication() {
        return mApp;
    }

    @Provides
    @Singleton
    public Context providesApppplicationContext() {
        return mApp;
    }

    @Provides
    @Singleton
    public SchedulerProvider providesSchedulerProvider() {
        return new AndroidSchedulerProvider();
    }

    @Provides
    @Singleton
    AdvertRepo providesAdvertRepo(AdvertDataSource dataSource) {
        return new AdvertRepo(dataSource);
    }

    @Provides
    @Singleton
    public AssetManager providesAssetManager() {
        return mApp.getAssets();
    }
}
