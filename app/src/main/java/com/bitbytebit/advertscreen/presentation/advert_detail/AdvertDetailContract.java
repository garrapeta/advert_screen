package com.bitbytebit.advertscreen.presentation.advert_detail;

import com.bitbytebit.advertscreen.data.advert.model.Advert;

/**
 * MVP contract for one screen showing the details of one {@link com.bitbytebit.advertscreen.data.advert.model.Advert}
 */
public class AdvertDetailContract {

    public interface View extends  com.bitbytebit.cleanframe.presentation.View {

        void setPresenter(AdvertDetailContract.Presenter presenter);

        void showAdvert(Advert advert);

        void showError();
    }

    interface Presenter extends com.bitbytebit.cleanframe.presentation.Presenter  {
        void onFavouriteClicked(boolean favourite);

        void onShareClicked();

        void onMessageClicked();
    }

}
