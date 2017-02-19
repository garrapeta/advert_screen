package com.bitbytebit.advertscreen.presentation.advert_detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bitbytebit.advertscreen.R;
import com.bitbytebit.advertscreen.presentation.advert_detail.di.AdvertDetailPresenterModule;
import com.bitbytebit.advertscreen.presentation.advert_detail.di.DaggerAdvertDetailComponent;
import com.bitbytebit.advertscreen.presentation.application.AdvertScreenApplication;

import java.util.UUID;

public class AdvertDetailActivity extends AppCompatActivity {

    private final static String AD_UUID_EXTRA = "uuid";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!getIntent().hasExtra(AD_UUID_EXTRA)) {
            // TODO: in a real app, this parameter would come as an extra in the Bundle
            final UUID fakeUuid = UUID.randomUUID();
            getIntent().putExtra(AD_UUID_EXTRA, fakeUuid);
        }

        setContentView(R.layout.activity_content_frame);

        // Obtain the Fragment

        AdvertItemDetailFragment fragment =
                (AdvertItemDetailFragment) getSupportFragmentManager().findFragmentByTag(AdvertItemDetailFragment.FRAGMENT_TAG);

        if (fragment == null) {
            // Create the fragment
            fragment = AdvertItemDetailFragment.newInstance();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentFrame, fragment, AdvertItemDetailFragment.FRAGMENT_TAG)
                    .commit();
        }

        final UUID uuid = (UUID) getIntent().getSerializableExtra(AD_UUID_EXTRA);
        final AdvertDetailContract.Presenter loginPresenter = DaggerAdvertDetailComponent.builder()
                .appComponent(AdvertScreenApplication.get(this).getAppComponent())
                .advertDetailPresenterModule(new AdvertDetailPresenterModule(fragment, uuid))
                .build()
                .getPresenter();

        fragment.setPresenter(loginPresenter);
        fragment.registerPresenter(loginPresenter);
    }

}
