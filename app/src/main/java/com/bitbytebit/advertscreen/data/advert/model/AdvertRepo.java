package com.bitbytebit.advertscreen.data.advert.model;

import com.bitbytebit.advertscreen.data.advert.AdvertDataSource;
import com.bitbytebit.advertscreen.data.common.Maybe;

import java.util.UUID;

import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Repository to fetch / update {@link Advert}.
 * It acquires the resources using the {@link AdvertDataSource} provided.
 */
public class AdvertRepo {

    final AdvertDataSource mDataSource;

    final BehaviorSubject<Maybe<Advert>> mAdvertSubject = BehaviorSubject.create();

    public AdvertRepo(AdvertDataSource datasource) {
        mDataSource = datasource;
    }

    /**
     * Gets and observable for one {@link Advert}.
     * The observable will NOT terminate after the first onNext, and changes (such as favouriting it)
     * will be notified to the subscriber
     *
     * @param uuid
     * @return
     */
    public Observable<Maybe<Advert>> getAdvertObs(UUID uuid) {
        return null;
    }

    /**
     * Sets one {@link Advert} as favourite.
     * If there were any observers subscribed to the advert with that id, they will be notified with the
     * change.
     *
     * @param userId
     * @param uuid
     * @return
     */
    public Observable<Void> setFavourite(UUID userId, UUID uuid, boolean favourite) {
        return null;
    }

}
