package com.bitbytebit.advertscreen.data.advert.model;


import com.bitbytebit.advertscreen.data.advert.AdvertDataSource;
import com.bitbytebit.advertscreen.data.common.Maybe;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;

import java.util.Random;
import java.util.UUID;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AdvertRepoTest {

    private AdvertRepo mAdvertRepo;

    @Mock
    private AdvertDataSource mAdvertDataSource;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mAdvertRepo = new AdvertRepo(mAdvertDataSource);
    }


    @Test
    public void getAdvert_delegatesIntoDataSource_whenAdvertWasNotCached() {
        // GIVEN a data source that returns one specific resource
        final UUID expectedUUid = UUID.randomUUID();
        final Advert expectedAdvert = mock(Advert.class);

        when(mAdvertDataSource
                .getById(expectedUUid))
                .thenReturn(Observable.just(expectedAdvert));

        // WHEN subscribing to the observable of the advert
        final TestSubscriber<Maybe<Advert>> subscriber = new TestSubscriber<>();
        mAdvertRepo.getAdvertObs(expectedUUid).subscribe(subscriber);

        // THEN:

        // - the repo delegates into the datasource
        verify(mAdvertDataSource, VerificationModeFactory.times(1))
                .getById(eq(expectedUUid));

        // - the subscriber is notified
        subscriber.assertValue(new Maybe<>(expectedAdvert));
    }

    @Test
    public void getAdvert_doesNotCallDataSource_whenAdvertWasCached() {
        // GIVEN a data source that returns one specific resource
        final UUID expectedUUid = UUID.randomUUID();
        final Advert expectedAdvert = mock(Advert.class);

        when(mAdvertDataSource
                .getById(expectedUUid))
                .thenReturn(Observable.just(expectedAdvert));

        // ... AND a previous request for one advert
        mAdvertRepo.getAdvertObs(expectedUUid).subscribe(new TestSubscriber<>());


        // WHEN subscribing to observable of the same advert
        final TestSubscriber<Maybe<Advert>> subscriber = new TestSubscriber<>();
        mAdvertRepo.getAdvertObs(expectedUUid).subscribe(subscriber);

        // THEN:

        // - the repo has delegated into the datasource ony once, as the first request cached the resource
        verify(mAdvertDataSource, VerificationModeFactory.times(1))
                .getById(eq(expectedUUid));

        // - the subscriber is notified
        subscriber.assertValue(new Maybe<>(expectedAdvert));
    }


    @Test
    public void favouriteAdvert_delegatesIntoDataSource_andNotifiesSubscribers() {
        when(mAdvertDataSource.getById(any())).thenReturn(Observable.just(mock(Advert.class)));

        // GIVEN a data source that returns one ad when setting it as favourite
        final UUID expectedUserUuid = UUID.randomUUID();
        final UUID expectedAdUUid = UUID.randomUUID();
        final boolean expectedFavValue = new Random().nextBoolean();
        final Advert expectedFavouriteAdvert = mock(Advert.class);

        when(mAdvertDataSource
                .setFavourite(eq(expectedUserUuid), eq(expectedAdUUid), eq(expectedFavValue)))
                .thenReturn(Observable.just(expectedFavouriteAdvert));

        // ... AND a subscriber to the add with that id
        final TestSubscriber<Maybe<Advert>> adSubscriber = new TestSubscriber<>();
        mAdvertRepo.getAdvertObs(expectedAdUUid).subscribe(adSubscriber);


        // WHEN favouriting the advert
        final TestSubscriber<Void> favSubscriber = new TestSubscriber<>();
        mAdvertRepo.setFavourite(expectedUserUuid, expectedAdUUid, expectedFavValue).subscribe(favSubscriber);

        // THEN:

        // - the repo has delegated into the datasource
        verify(mAdvertDataSource, VerificationModeFactory.times(1))
                .setFavourite(eq(expectedUserUuid), eq(expectedAdUUid), eq(expectedFavValue));

        favSubscriber.assertValueCount(1);

        // - the subscriber is notified (one for when subscribed, another for the change)
        adSubscriber.assertValueCount(2);
        // - and the second notification is the updated ad
        Assert.assertSame(expectedFavouriteAdvert, adSubscriber.getOnNextEvents().get(1).getValue());
    }

}