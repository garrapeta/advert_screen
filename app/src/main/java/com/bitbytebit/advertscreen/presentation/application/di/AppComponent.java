package com.bitbytebit.advertscreen.presentation.application.di;

import android.content.Context;
import android.content.res.AssetManager;

import com.bitbytebit.advertscreen.data.advert.AdvertRepo;
import com.bitbytebit.cleanframe.presentation.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class,
                AdvertRepoModule.class
        }
)
public interface AppComponent {

    AdvertRepo getAdvertRepo();

    SchedulerProvider getSchedulerProvider();

    Context getApplicationContext();

    AssetManager getAssetManager();

}
