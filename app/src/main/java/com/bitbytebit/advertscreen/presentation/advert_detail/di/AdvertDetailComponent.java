package com.bitbytebit.advertscreen.presentation.advert_detail.di;


import com.bitbytebit.advertscreen.presentation.advert_detail.AdvertDetailPresenter;
import com.bitbytebit.advertscreen.presentation.application.di.AppComponent;
import com.bitbytebit.cleanframe.presentation.di.FragmentScope;

import dagger.Component;

@FragmentScope
@Component (
        modules = AdvertDetailPresenterModule.class,
        dependencies = AppComponent.class
)
public interface AdvertDetailComponent {

    AdvertDetailPresenter getPresenter();

}
