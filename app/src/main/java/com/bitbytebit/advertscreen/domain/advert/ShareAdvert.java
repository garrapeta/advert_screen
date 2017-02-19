package com.bitbytebit.advertscreen.domain.advert;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.bitbytebit.advertscreen.data.advert.model.Advert;
import com.bitbytebit.cleanframe.domain.UseCase;

public class ShareAdvert implements UseCase<Advert,Void> {

    private final Context mContext;

    public ShareAdvert(Context context) {
        mContext = context;
    }

    @Override
    public Void execute(Advert advert) {
        final Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, buildUrl(advert));
        sendIntent.setType("text/plain");

        mContext.startActivity(sendIntent);

        return null;
    }

    private Uri buildUrl(Advert advert) {
        return new Uri.Builder()
                .scheme("https")
                .authority("gumclassfields.com")
                .appendPath("p")
                .appendPath("for-sale")
                .appendPath(advert.title.replace(" ", "-"))
                .appendPath(advert.uuid.toString())
                .build();
    }
}
