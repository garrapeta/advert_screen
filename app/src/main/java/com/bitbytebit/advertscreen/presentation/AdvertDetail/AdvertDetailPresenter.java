package com.bitbytebit.advertscreen.presentation.AdvertDetail;

import com.bitbytebit.advertscreen.data.advert.model.Advert;
import com.bitbytebit.advertscreen.domain.advert.FavouriteAdvert;
import com.bitbytebit.advertscreen.domain.advert.GetAdvert;
import com.bitbytebit.advertscreen.domain.advert.MessageAdvert;
import com.bitbytebit.advertscreen.domain.advert.ShareAdvert;
import com.bitbytebit.cleanframe.presentation.RxPresenter;
import com.bitbytebit.cleanframe.presentation.SchedulerProvider;

import java.util.UUID;

public class AdvertDetailPresenter extends RxPresenter implements AdvertDetailContract.Presenter {

    private final AdvertDetailContract.View mView;
    private final GetAdvert mGetAdvert;
    private final FavouriteAdvert mFavouriteAdvert;
    private final ShareAdvert mShareAdvert;
    private final MessageAdvert mMessageAdvert;

    private final UUID mAdvertUUID;
    private Advert mAdvert;

    public AdvertDetailPresenter(SchedulerProvider schedulerProvider,
                                 AdvertDetailContract.View view,
                                 GetAdvert getAdvert,
                                 FavouriteAdvert favouriteAdvert,
                                 ShareAdvert shareAdvert,
                                 MessageAdvert messageAdvert,
                                 UUID advertUUID) {
        super(schedulerProvider);

        mView = view;
        mGetAdvert = getAdvert;
        mFavouriteAdvert = favouriteAdvert;
        mShareAdvert = shareAdvert;
        mMessageAdvert = messageAdvert;
        mAdvertUUID = advertUUID;
    }

    @Override
    protected void onViewStart() {
        super.onViewStart();

        mGetAdvert.build(mAdvertUUID)
                .subscribeOn(ioScheduler())
                .observeOn(uiScheduler())
                .compose(bindPresenterUntil(PresenterEvent.STOP))
                .subscribe(advert -> mView.showAdvert(advert), throwable -> mView.showError());
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
        mShareAdvert.execute(mAdvert);
    }

    @Override
    public void onMessageClicked() {
        mMessageAdvert.execute(mAdvert);
    }

}
