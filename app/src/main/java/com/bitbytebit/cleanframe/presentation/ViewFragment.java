package com.bitbytebit.cleanframe.presentation;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.pariti.pariti.presentation.webview.WebViewActivity;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Fragment that bounds a collection of {@link Presenter} to the lifecycle
 * of the fragment
 * <p/>
 * Yo need to call {@link ViewFragment#registerPresenter(Presenter)} for
 * every {@link Presenter} that is attached to the fragment.
 */
public abstract class ViewFragment extends Fragment {

    private final Set<Presenter> mPresenters;

    public ViewFragment() {
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
    public void onDestroyView() {
        super.onDestroyView();
        for (Presenter presenter : mPresenters) {
            presenter.onDestroyView();
        }
    }


    public final void navigateToInternalBrowser(String url, String title) {
        startActivity(WebViewActivity.newIntent(getContext(), Uri.parse(url), title));
    }
}
