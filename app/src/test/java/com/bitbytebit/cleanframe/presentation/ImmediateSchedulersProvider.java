package com.bitbytebit.cleanframe.presentation;

import android.support.annotation.NonNull;

import rx.Scheduler;
import rx.schedulers.Schedulers;

public class ImmediateSchedulersProvider implements SchedulerProvider {
    @NonNull
    @Override
    public Scheduler computation() {
        return Schedulers.immediate();
    }

    @NonNull
    @Override
    public Scheduler io() {
        return Schedulers.immediate();
    }

    @NonNull
    @Override
    public Scheduler ui() {
        return Schedulers.immediate();
    }
}
