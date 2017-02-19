package com.bitbytebit.advertscreen.domain.advert;

import com.bitbytebit.advertscreen.data.advert.model.Advert;
import com.bitbytebit.advertscreen.data.advert.model.AdvertRepo;
import com.bitbytebit.cleanframe.domain.RxUseCase;

import java.util.UUID;

import rx.Observable;


public class FavouriteAdvert implements RxUseCase<FavouriteAdvert.RequestModel, Advert> {

    private final AdvertRepo mAdvertRepo;

    public FavouriteAdvert(AdvertRepo advertRepo) {
        mAdvertRepo = advertRepo;
    }

    @Override
    public Observable<Advert> build(RequestModel requestValues) {
        // TODO: obtain User UUID from User Repository
        return mAdvertRepo.setFavourite(null, requestValues.mUUID, requestValues.mFavourite);
    }

    public static class RequestModel {
        final UUID mUUID;
        final Boolean mFavourite;

        public RequestModel(UUID uuid, Boolean favourite) {
            mUUID = uuid;
            mFavourite = favourite;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            final RequestModel that = (RequestModel) o;

            if (mUUID != null ? !mUUID.equals(that.mUUID) : that.mUUID != null) return false;
            return mFavourite != null ? mFavourite.equals(that.mFavourite) : that.mFavourite == null;

        }

        @Override
        public int hashCode() {
            int result = mUUID != null ? mUUID.hashCode() : 0;
            result = 31 * result + (mFavourite != null ? mFavourite.hashCode() : 0);
            return result;
        }
    }
}
