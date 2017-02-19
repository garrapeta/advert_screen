package com.bitbytebit.advertscreen.presentation.advert_detail;

import android.content.ActivityNotFoundException;

import com.bitbytebit.advertscreen.data.advert.model.Advert;
import com.bitbytebit.advertscreen.domain.advert.FavouriteAdvert;
import com.bitbytebit.advertscreen.domain.advert.GetAdvert;
import com.bitbytebit.advertscreen.domain.advert.MessageSeller;
import com.bitbytebit.advertscreen.domain.advert.ShareAdvert;
import com.bitbytebit.cleanframe.presentation.RxPresenter;
import com.bitbytebit.cleanframe.presentation.SchedulerProvider;

import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;

public class AdvertDetailPresenter extends RxPresenter implements AdvertDetailContract.Presenter {

    private final AdvertDetailContract.View mView;
    private final GetAdvert mGetAdvert;
    private final FavouriteAdvert mFavouriteAdvert;
    private final ShareAdvert mShareAdvert;
    private final MessageSeller mMessageSeller;

    private final UUID mAdvertUUID;
    private Advert mAdvert;

    @Inject
    public AdvertDetailPresenter(SchedulerProvider schedulerProvider,
                                 AdvertDetailContract.View view,
                                 GetAdvert getAdvert,
                                 FavouriteAdvert favouriteAdvert,
                                 ShareAdvert shareAdvert,
                                 MessageSeller messageSeller,
                                 @Named("advertUUID") UUID advertUUID) {
        super(schedulerProvider);

        mView = view;
        mGetAdvert = getAdvert;
        mFavouriteAdvert = favouriteAdvert;
        mShareAdvert = shareAdvert;
        mMessageSeller = messageSeller;
        mAdvertUUID = advertUUID;
    }

    @Override
    protected void onViewStart() {
        super.onViewStart();

        mGetAdvert.build(mAdvertUUID)
                .subscribeOn(ioScheduler())
                .observeOn(uiScheduler())
                .compose(bindPresenterUntil(PresenterEvent.STOP))
                .subscribe(advert -> {
                    mAdvert = advert;
                    mView.showAdvert(advert);
                }, throwable -> mView.showError());
    }

    @Override
    public void onFavouriteClicked(boolean favourite) {
        mFavouriteAdvert.build(new FavouriteAdvert.RequestModel(mAdvertUUID, favourite))
                .subscribeOn(ioScheduler())
                .observeOn(uiScheduler())
                .compose(bindPresenterUntil(PresenterEvent.DESTROY_VIEW))
                .subscribe();
    }

    @Override
    public void onShareClicked() {
        try {
            mShareAdvert.execute(mAdvert);
        } catch (ActivityNotFoundException e) {
            mView.showError();
        }
    }

    @Override
    public void onMessageClicked() {
        try {
            mMessageSeller.execute(mAdvert);
        } catch (ActivityNotFoundException e) {
            mView.showError();
        }
    }

}
