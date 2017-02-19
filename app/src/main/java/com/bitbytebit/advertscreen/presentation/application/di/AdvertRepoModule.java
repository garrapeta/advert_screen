package com.bitbytebit.advertscreen.presentation.application.di;


import com.bitbytebit.advertscreen.data.advert.AdvertDataSource;
import com.bitbytebit.advertscreen.data.advert.FakeAdvertDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AdvertRepoModule {

    @Provides
    @Singleton
    AdvertDataSource providesAdvertDataSource() {
        return new FakeAdvertDataSource();
    }
}
