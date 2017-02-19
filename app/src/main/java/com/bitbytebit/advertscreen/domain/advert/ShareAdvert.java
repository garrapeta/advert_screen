package com.bitbytebit.advertscreen.domain.advert;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.bitbytebit.advertscreen.data.advert.model.Advert;
import com.bitbytebit.cleanframe.domain.UseCase;

import javax.inject.Inject;

/**
 * Use case to share one {@link Advert}
 */
public class ShareAdvert implements UseCase<Advert,Void> {

    private final Context mContext;




    @Inject
    public ShareAdvert(Context context) {
        mContext = context;
    }

    @Override
    public Void execute(Advert advert) {
        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, advert.seller.email);
        intent.putExtra(Intent.EXTRA_SUBJECT, advert.title);

        mContext.startActivity(intent);
        return null;
    }
}
