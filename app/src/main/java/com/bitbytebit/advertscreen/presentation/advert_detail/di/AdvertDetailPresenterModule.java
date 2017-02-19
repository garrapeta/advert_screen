package com.bitbytebit.advertscreen.presentation.advert_detail.di;


import com.bitbytebit.advertscreen.presentation.advert_detail.AdvertDetailContract;
import com.bitbytebit.cleanframe.presentation.di.FragmentScope;

import java.util.UUID;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class AdvertDetailPresenterModule {

    private final AdvertDetailContract.View mView;
    private final UUID mAdvertUUID;

    public AdvertDetailPresenterModule(AdvertDetailContract.View view, UUID advertUUID) {
        mView = view;
        mAdvertUUID = advertUUID;
    }

    @Provides
    @FragmentScope
    public AdvertDetailContract.View providesView() {
        return mView;
    }

    @Provides @Named("advertUUID")
    @FragmentScope
    public UUID providesAdvertUUID() {
        return mAdvertUUID;
    }
}
