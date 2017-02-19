package com.bitbytebit.advertscreen.data.common;


import rx.Observable.Operator;
import rx.Subscriber;


public final class MaybeWrapperOperator<T> implements Operator<Maybe<T>, T> {


    @Override
    public Subscriber<? super T> call(final Subscriber<? super Maybe<T>> subscriber) {
        return new Subscriber<T>() {
            @Override
            public void onCompleted() {
                subscriber.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                subscriber.onNext(new Maybe<T>(e));
            }

            @Override
            public void onNext(T t) {
                subscriber.onNext(new Maybe<>(t));
            }
        };
    }
}
