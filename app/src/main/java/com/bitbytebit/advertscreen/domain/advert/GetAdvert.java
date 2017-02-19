package com.bitbytebit.advertscreen.domain.advert;

import com.bitbytebit.advertscreen.data.advert.model.Advert;
import com.bitbytebit.advertscreen.data.advert.AdvertRepo;
import com.bitbytebit.advertscreen.data.common.MaybeUnwrapperTransformer;
import com.bitbytebit.cleanframe.domain.RxUseCase;

import java.util.UUID;

import javax.inject.Inject;

import rx.Observable;

/**
 * Use case to get one {@link Advert} by id
 */
public class GetAdvert implements RxUseCase<UUID, Advert> {

    private final AdvertRepo mAdvertRepo;

    @Inject
    public GetAdvert(AdvertRepo advertRepo) {
        mAdvertRepo = advertRepo;
    }

    @Override
    public Observable<Advert> build(UUID adUuid) {
        return mAdvertRepo.getAdvertObs(adUuid)
                .compose(new MaybeUnwrapperTransformer<>());
    }
}
