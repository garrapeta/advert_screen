package com.bitbytebit.advertscreen.data.common;

import rx.Observable;

public class MaybeUnwrapperTransformer<T> implements Observable.Transformer<Maybe<T>, T> {
    @Override
    public Observable<T> call(Observable<Maybe<T>> maybeObservable) {
        return maybeObservable.flatMap(maybe -> {
            if (maybe.isError()) {
                return Observable.error(maybe.getError());
            } else {
                return Observable.just(maybe.getValue());
            }
        });
    }
}
