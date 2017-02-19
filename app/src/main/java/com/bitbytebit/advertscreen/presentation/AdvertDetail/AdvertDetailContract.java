package com.bitbytebit.advertscreen.presentation.AdvertDetail;

import com.bitbytebit.advertscreen.data.advert.model.Advert;

/**
 * MVP contract for one screen showing the details of one {@link com.bitbytebit.advertscreen.data.advert.model.Advert}
 */
public class AdvertDetailContract {

    interface View extends  com.bitbytebit.cleanframe.presentation.View {

        void setPresenter(AdvertDetailContract.Presenter presenter);

        void showAdvert(Advert advert);
        
    }

    interface Presenter extends com.bitbytebit.cleanframe.presentation.Presenter  {
        void onFavouriteClicked(boolean favourite);

        void onShareClicked();

        void onPicturesClicked();

        void onLocationClicked();

        void onCallClicked();

        void onSmsClicked();

        void onMessageClicked();
    }

}
