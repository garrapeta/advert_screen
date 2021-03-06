package com.bitbytebit.cleanframe.presentation;

import android.support.annotation.NonNull;

import rx.Scheduler;

/**
 * Allow providing different types of {@link Scheduler}s.
 */
public interface SchedulerProvider {

    @NonNull
    Scheduler computation();

    @NonNull
    Scheduler io();

    @NonNull
    Scheduler ui();
}
