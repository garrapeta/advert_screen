package com.bitbytebit.cleanframe.presentation;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.OutsideLifecycleException;
import com.trello.rxlifecycle.RxLifecycle;

import rx.Scheduler;
import rx.functions.Func1;
import rx.subjects.BehaviorSubject;

import static com.trello.rxlifecycle.RxLifecycle.bind;

/**
 * Presenter for implementations based on rxJava.
 */
public abstract class RxPresenter implements Presenter {

    private final BehaviorSubject<PresenterEvent> mLifecycleSubject = BehaviorSubject.create();

    private final SchedulerProvider mSchedulerProvider;

    protected RxPresenter(SchedulerProvider schedulerProvider) {
        mSchedulerProvider = schedulerProvider;
    }

    // Lifecycle events ----------------------------------------------------------------------------

    @Override
    public final void onReady() {
        mLifecycleSubject.onNext(PresenterEvent.READY);
        onViewReady();
    }

    protected void onViewReady() {
    }

    @Override
    public final void onStart() {
        mLifecycleSubject.onNext(PresenterEvent.START);
        onViewStart();
    }

    protected void onViewStart() {}

    @Override
    public final void onResume() {
        mLifecycleSubject.onNext(PresenterEvent.RESUME);
        onViewResume();
    }

    protected void onViewResume() {}

    @Override
    public final void onPause() {
        mLifecycleSubject.onNext(PresenterEvent.PAUSE);
        onViewPause();
    }

    protected void onViewPause() {}

    @Override
    public final void onStop() {
        mLifecycleSubject.onNext(PresenterEvent.STOP);
        onViewStop();
    }

    protected void onViewStop() {}

    @Override
    public final void onDestroyView() {
        mLifecycleSubject.onNext(PresenterEvent.DESTROY_VIEW);
        onViewDestroyView();
    }

    protected void onViewDestroyView() {}

    // ---------------------------------------------------------------------------------------------

    @NonNull
    @CheckResult
    public <T> LifecycleTransformer<T> bindToLifecycle() {
        return bind(mLifecycleSubject, PRESENTER_LIFECYCLE);
    }

    @NonNull
    @CheckResult
    public <T> LifecycleTransformer<T> bindPresenterUntil(PresenterEvent untilEvent) {
        return RxLifecycle.bindUntilEvent(mLifecycleSubject, untilEvent);
    }

    protected final Scheduler uiScheduler() {
        return mSchedulerProvider.ui();
    }

    protected final Scheduler ioScheduler() {
        return mSchedulerProvider.io();
    }

    protected final Scheduler compScheduler() {
        return mSchedulerProvider.computation();
    }

    /**
     * Lifecycle events that can be emitted by Presenters.
     */
    public enum PresenterEvent {
        READY,
        START,
        RESUME,
        PAUSE,
        STOP,
        DESTROY_VIEW
    }

    // Figures out which corresponding next lifecycle event in which to unsubscribe, for Events
    private static final Func1<PresenterEvent, PresenterEvent> PRESENTER_LIFECYCLE =
            lastEvent -> {
                switch (lastEvent) {
                    case READY:
                        return PresenterEvent.DESTROY_VIEW;
                    case START:
                        return PresenterEvent.STOP;
                    case RESUME:
                        return PresenterEvent.PAUSE;
                    case PAUSE:
                        return PresenterEvent.STOP;
                    case STOP:
                        return PresenterEvent.DESTROY_VIEW;
                    case DESTROY_VIEW:
                        throw new OutsideLifecycleException("Cannot bind to Fragment lifecycle when outside of it.");
                    default:
                        throw new UnsupportedOperationException("Binding to " + lastEvent + " not yet implemented");
                }
            };

}
