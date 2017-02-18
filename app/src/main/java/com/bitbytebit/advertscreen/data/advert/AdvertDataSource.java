package com.bitbytebit.advertscreen.data.advert;

import com.bitbytebit.advertscreen.data.advert.model.Advert;

import java.util.UUID;

import rx.Observable;

/*
 * Data source user to acquire {@link Advert}s
 */
public interface AdvertDataSource {

    /**
     * @param uuid
     * @return observable for the {@link Advert} with the requested id
     */
    Observable<Advert> getById(UUID uuid);

    /**
     * Sets one {@link Advert} as favourite (starred)
     *
     * @param userId id of the user who is setting the ad as favourite
     * @param adId id of the {@link Advert}
     * @param isFavourite
     * @return
     */
    Observable<Advert> setFavourite(UUID userId, UUID adId, boolean isFavourite);
}
