package com.bitbytebit.advertscreen.presentation.advert_detail;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bitbytebit.advertscreen.R;
import com.bitbytebit.advertscreen.data.advert.model.Advert;
import com.bitbytebit.cleanframe.presentation.ViewFragment;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AdvertItemDetailFragment extends ViewFragment implements AdvertDetailContract.View {

    public static AdvertItemDetailFragment newInstance() {
        return new AdvertItemDetailFragment();
    }

    public static final String FRAGMENT_TAG = AdvertItemDetailFragment.class.getSimpleName();

    private AdvertDetailContract.Presenter mPresenter;

    @BindView(R.id.images_list)
    RecyclerView mImagesRecyclerView;

    @BindView(R.id.title_textview)
    TextView mTitle;

    @BindView(R.id.description_textview)
    TextView mDescription;

    @BindView(R.id.location_textview)
    TextView mLocationTextView;

    @BindView(R.id.price_textview)
    TextView mPriceTextView;

    @BindView(R.id.date_textview)
    TextView mDateTextView;

    private Boolean mIsFavourited;

    private final AdvertImagesAdapter mImagesAdapter = new AdvertImagesAdapter();

    private Unbinder mUnbinder;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_advert_detail, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
        setupImagesRecyclerView();
    }

    private void setupImagesRecyclerView() {
        final LinearLayoutManager recyclerViewLayout = new LinearLayoutManager(getActivity());
        recyclerViewLayout.setOrientation(LinearLayoutManager.HORIZONTAL);
        mImagesRecyclerView.setLayoutManager(recyclerViewLayout);
        mImagesRecyclerView.setAdapter(mImagesAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_advert_detail, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (mIsFavourited != null) {
            menu.findItem(R.id.action_favourite).setIcon(mIsFavourited ?
                    R.drawable.ic_star_white_24dp :
                    R.drawable.ic_star_border_white_24dp);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favourite:
                mPresenter.onFavouriteClicked(!mIsFavourited);
                break;
            case R.id.action_share:
                mPresenter.onShareClicked();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPresenter(AdvertDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showAdvert(Advert advert) {
        getActivity().setTitle(advert.title);

        populateTitle(advert);
        populateDescription(advert);
        populatePrice(advert);
        populateDate(advert);
        populateLocation(advert);
        populateFavourited(advert);
        populateImages(advert);
    }

    private void populateFavourited(Advert advert) {
        mIsFavourited = advert.isFavourite;
        // invalidate options to populate item in onPrepareOptionsMenu
        getActivity().invalidateOptionsMenu();
    }

    private void populateTitle(Advert advert) {
        mTitle.setText(advert.title);
    }

    private void populateDescription(Advert advert) {
        mDescription.setText(advert.description);
    }

    private void populatePrice(Advert advert) {
        mPriceTextView.setText(String.format(getString(R.string.price), String.valueOf(advert.price)));
    }

    private void populateLocation(Advert advert) {
        mLocationTextView.setText(advert.locationDesc);
    }

    private void populateDate(Advert advert) {
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/YYYY", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        final String nextDateStr = sdf.format(advert.datePosted);
        mDateTextView.setText(nextDateStr);
    }

    private void populateImages(Advert advert) {
        if (mImagesAdapter.getImagesUrls().isEmpty()) {
            mImagesAdapter.setImagesUrls(advert.imagesUris);
            mImagesAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), R.string.error_generic, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.message_button)
    public void onMessageClick(View view) {
        mPresenter.onMessageClicked();
    }


}
