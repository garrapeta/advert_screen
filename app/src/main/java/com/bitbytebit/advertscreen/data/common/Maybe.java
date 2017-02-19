package com.bitbytebit.advertscreen.data.common;

public class Maybe<T> {
    private final T mValue;
    private final Throwable mError;

    public Maybe(T value) {
        this(value, null);
    }

    public Maybe(Throwable error) {
        this(null, error);
    }

    private Maybe(T value, Throwable error) {
        mValue = value;
        mError = error;
    }

    public boolean isError() {
        return mError != null;
    }

    public T getValue() {
        return mValue;
    }

    public Throwable getError() {
        return mError;
    }

    @Override
    public String toString() {
        return "Maybe{" +
                "mValue=" + mValue +
                ", mError=" + mError +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Maybe<?> maybe = (Maybe<?>) o;

        if (mValue != null ? !mValue.equals(maybe.mValue) : maybe.mValue != null) return false;
        return mError != null ? mError.equals(maybe.mError) : maybe.mError == null;

    }

    @Override
    public int hashCode() {
        int result = mValue != null ? mValue.hashCode() : 0;
        result = 31 * result + (mError != null ? mError.hashCode() : 0);
        return result;
    }
}
