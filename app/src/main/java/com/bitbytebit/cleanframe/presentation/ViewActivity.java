package com.bitbytebit.cleanframe.presentation;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Activity that bounds a collection of {@link Presenter} to the lifecycle
 * of the fragment
 * <p/>
 * Yo need to call {@link ViewActivity#registerPresenter(Presenter)} for
 * every {@link Presenter} that is attached to the fragment.
 */
public abstract class ViewActivity extends AppCompatActivity {

    private final Set<Presenter> mPresenters;

    public ViewActivity() {
        mPresenters = new LinkedHashSet<>();
    }

    /**
     * Register a presenter to ensure it's lifecycle methods are called.¬
     */
    public final void registerPresenter(@NonNull Presenter presenter) {
        Log.v(View.TAG, "Registered " + presenter + " to " + this);
        mPresenters.add(presenter);
    }


    // Lifecycle events ----------------------------------------------------------------------------


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (Presenter presenter : mPresenters) {
            presenter.onReady();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        for (Presenter presenter : mPresenters) {
            presenter.onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        for (Presenter presenter : mPresenters) {
            presenter.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        for (Presenter presenter : mPresenters) {
            presenter.onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        for (Presenter presenter : mPresenters) {
            presenter.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (Presenter presenter : mPresenters) {
            presenter.onDestroyView();
        }
    }



}
