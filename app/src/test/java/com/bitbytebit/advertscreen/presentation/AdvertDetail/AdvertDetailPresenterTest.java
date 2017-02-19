package com.bitbytebit.advertscreen.presentation.AdvertDetail;

import com.bitbytebit.advertscreen.data.advert.model.Advert;
import com.bitbytebit.advertscreen.domain.advert.FavouriteAdvert;
import com.bitbytebit.advertscreen.domain.advert.GetAdvert;
import com.bitbytebit.advertscreen.domain.advert.MessageAdvert;
import com.bitbytebit.advertscreen.domain.advert.ShareAdvert;
import com.bitbytebit.cleanframe.presentation.ImmediateSchedulersProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;

import java.util.Random;
import java.util.UUID;

import rx.schedulers.TestScheduler;
import rx.subjects.BehaviorSubject;
import rx.subjects.TestSubject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AdvertDetailPresenterTest {

    private AdvertDetailPresenter mAdvertDetailPresenter;

    @Mock
    private AdvertDetailContract.View mView;

    @Mock
    private GetAdvert mGetAdvert;

    @Mock
    private FavouriteAdvert mFavouriteAdvert;

    @Mock
    private ShareAdvert mShareAdvert;

    private UUID mAdUuid;

    @Mock
    private MessageAdvert mMessageAdvert;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mAdUuid = UUID.randomUUID();

        mAdvertDetailPresenter = new AdvertDetailPresenter(new ImmediateSchedulersProvider(), mView, mGetAdvert, mFavouriteAdvert, mShareAdvert, mMessageAdvert, mAdUuid);
    }


    @Test
    public void onStart_subscribesToAdvert() {
        // GIVEN and observable returned by GetAdvert
        final TestSubject<Advert> mAdvertSubject = TestSubject.create(new TestScheduler());
        when(mGetAdvert.build(any())).thenReturn(mAdvertSubject);

        // WHEN the screen is started
        mAdvertDetailPresenter.onStart();

        // THEN the presenter queries and ad from the repo
        verify(mGetAdvert, VerificationModeFactory.times(1)).build(eq(mAdUuid));
        assertTrue(mAdvertSubject.hasObservers());
    }

    @Test
    public void onStop_UnSubscribesFromAdvert() {
        // GIVEN and observable returned by GetAdvert
        final TestSubject<Advert> mAdvertSubject = TestSubject.create(new TestScheduler());
        when(mGetAdvert.build(any())).thenReturn(mAdvertSubject);

        // WHEN the screen is stopped
        mAdvertDetailPresenter.onStop();

        // THEN the presenter unsubscribes
        assertFalse(mAdvertSubject.hasObservers());
    }

    @Test
    public void onNextAdvert_asksViewToShowAdvert() {
        // GIVEN and observable returned by GetAdvert
        final Advert expectedAdvert = mock(Advert.class);
        final BehaviorSubject<Advert> mAdvertSubject = BehaviorSubject.create();
        when(mGetAdvert.build(any())).thenReturn(mAdvertSubject);

        // AND the presenter started
        mAdvertDetailPresenter.onStart();

        // WHEN a new event for the Advert is triggerd
        mAdvertSubject.onNext(expectedAdvert);

        // THEN the presenter calls the view
        verify(mView, VerificationModeFactory.times(1)).showAdvert(eq(expectedAdvert));
    }

    @Test
    public void onErrorAdvert_asksViewToShowError() {
        // GIVEN and observable returned by GetAdvert
        final BehaviorSubject<Advert> mAdvertSubject = BehaviorSubject.create();
        when(mGetAdvert.build(any())).thenReturn(mAdvertSubject);

        // AND the presenter started
        mAdvertDetailPresenter.onStart();

        // WHEN a new event for the Advert is triggered
        mAdvertSubject.onError(new Exception());

        // THEN the presenter calls the view
        verify(mView, VerificationModeFactory.times(1)).showError();
    }


    @Test
    public void onFavourite_executedUseCase() {
        final Boolean expectedFavValue = new Random().nextBoolean();
        final FavouriteAdvert.RequestModel expectedRequestModel = new FavouriteAdvert.RequestModel(mAdUuid, expectedFavValue);
        final TestSubject<Advert> mFavSubject = TestSubject.create(new TestScheduler());
        when(mFavouriteAdvert.build(any()))
                .thenReturn(mFavSubject);

        // WHEN favouriting
        mAdvertDetailPresenter.onFavouriteClicked(expectedFavValue);

        // THEN the use case is executed
        verify(mFavouriteAdvert, VerificationModeFactory.times(1)).build(expectedRequestModel);
        assertTrue(mFavSubject.hasObservers());
    }

    @Test
    public void onShareClicked_executesUseCase() {
        mAdvertDetailPresenter.onShareClicked();

        verify(mShareAdvert, VerificationModeFactory.times(1)).execute(any());
    }

    @Test
    public void onMessageClicked_executesUseCase() {
        mAdvertDetailPresenter.onMessageClicked();

        verify(mMessageAdvert, VerificationModeFactory.times(1)).execute(any());
    }
}