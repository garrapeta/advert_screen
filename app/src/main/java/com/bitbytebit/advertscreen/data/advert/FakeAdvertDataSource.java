package com.bitbytebit.advertscreen.data.advert;


import com.bitbytebit.advertscreen.data.advert.model.Advert;
import com.bitbytebit.advertscreen.data.advert.model.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import rx.Observable;

/**
 * Implementation od {@link AdvertDataSource} that returns hardcoded adds
 */
public class FakeAdvertDataSource implements AdvertDataSource {

    private final Map<UUID, Advert> mAdverts;
    private final Random mRandom;

    private static final long MILLIS_IN_DAYS = 24 * 60 * 60 * 1000;

    public FakeAdvertDataSource() {
        mAdverts = new HashMap<>();
        mRandom = new Random();
    }

    @Override
    public synchronized Observable<Advert> getById(UUID uuid) {
        Advert advert = mAdverts.get(uuid);

        if (advert == null) {
            advert = generateRandom(uuid);
            mAdverts.put(uuid, advert);
        }

        return Observable.just(advert);
    }

    @Override
    public Observable<Advert> setFavourite(UUID userId, UUID addId, boolean isFavourite) {
        final Advert updatedAdvert = new Advert.Builder()
                .from(mAdverts.get(addId))
                .setFavourite(isFavourite)
                .build();

        synchronized (this) {
            mAdverts.put(addId, updatedAdvert);
        }

        return Observable.just(updatedAdvert);
    }


    private Advert generateRandom(UUID uuid) {
        switch (mRandom.nextInt(3)) {
            case 0:
                return generateTurntablesAd(uuid);
            case 1:
                return generateOculusAd(uuid);
            default:
                return generateLaptopAd(uuid);
        }
    }

    private Advert generateTurntablesAd(UUID uuid) {
        return appendFields(uuid, new Advert.Builder()
                .setTitle("Technichs  turntable 1210 mks")
                .addImageUrl("https://i.ebayimg.com/00/s/NTc0WDEwMjQ=/z/rzEAAOSwax5Ypwxu/$_86.JPG")
                .addImageUrl("https://i.ebayimg.com/00/s/NTc0WDEwMjQ=/z/Q4AAAOSw4CFYpwyC/$_86.JPG")
                .addImageUrl("https://i.ebayimg.com/00/s/MTAyNFg1NzQ=/z/6gEAAOSwCU1YpwyQ/$_86.JPG")
                .addImageUrl("https://i.ebayimg.com/00/s/NTc0WDEwMjQ=/z/pvAAAOSwB-1Ypwx0/$_86.JPG"))
                .setPrice(200 + mRandom.nextInt(300))
                .build();

    }

    private Advert generateOculusAd(UUID uuid) {
        return appendFields(uuid, new Advert.Builder()
                .setTitle("Oculus rift with controllers")
                .addImageUrl("https://i.ebayimg.com/00/s/NzY4WDEwMjQ=/z/qXcAAOSwx6pYpJoU/$_86.JPG")
                .addImageUrl("https://i.ebayimg.com/00/s/NzY4WDEwMjQ=/z/kFQAAOSw4CFYpJoZ/$_86.JPG")
                .addImageUrl("https://i.ebayimg.com/00/s/MTAyNFg3Njg=/z/kOoAAOSw4CFYpJod/$_86.JPG")
                .addImageUrl("https://i.ebayimg.com/00/s/MTAyNFg3Njg=/z/Va0AAOSwOgdYpJog/$_86.JPG"))
                .addImageUrl("https://i.ebayimg.com/00/s/MTAyNFg3Njg=/z/-PsAAOSw4A5YpJoj/$_86.JPG")
                .setPrice(300 + mRandom.nextInt(300))
                .build();

    }

    private Advert generateLaptopAd(UUID uuid) {
        return appendFields(uuid, new Advert.Builder()
                .setTitle("Dell XPS Ubuntu edition")
                .addImageUrl("https://i.ebayimg.com/00/s/NTcwWDk2NQ==/z/NEkAAOSwdGFYqK74/$_86.JPG")
                .addImageUrl("https://i.ebayimg.com/00/s/NjAxWDEwMjQ=/z/GYcAAOSwr~lYqK7y/$_86.JPG")
                .addImageUrl("https://i.ebayimg.com/00/s/MzMyWDU5MA==/z/PFgAAOSw32lYqK71/$_86.JPG")
                .addImageUrl("https://i.ebayimg.com/00/s/MzgwWDk2NQ==/z/~ooAAOSwOgdYqK76/$_86.JPG"))
                .setPrice(700 + mRandom.nextInt(700))
                .build();

    }


    private Advert.Builder appendFields(UUID uuid, Advert.Builder builder) {
        return builder
                .setUuid(uuid)
                .setDatePosted(new Date(System.currentTimeMillis() - (1 + mRandom.nextInt(6) * MILLIS_IN_DAYS)))
                .setSeller(generateRandomSeller())
                .setLocationDesc(generateRandomLocation())
                .setDescription(generateRandomDescription())
                .setFavourite(false);

    }

    private String generateRandomDescription() {
        switch (mRandom.nextInt(3)) {
            case 0:
                return "Like new. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur";
            case 1:
                return "A bit broken. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur";
            case 2:
                return "Unwanted present. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur";
            default:
                return "Boxed and with warranty. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur";
        }
    }

    private String generateRandomLocation() {
        switch (mRandom.nextInt(3)) {
            case 0:
                return "London";
            case 1:
                return "Plymouth";
            case 2:
                return "Richmond";
            default:
                return "Madrid";
        }
    }

    private User generateRandomSeller() {
        switch (mRandom.nextInt(3)) {
            case 0:
                return new User.Builder()
                        .setUuid(UUID.randomUUID())
                        .setFirstName("John")
                        .setEmail("john@hotmail.com")
                        .build();
            case 1:
                return new User.Builder()
                        .setUuid(UUID.randomUUID())
                        .setFirstName("Sergio")
                        .setEmail("sergiotorresdev@gmail.com")
                        .build();
            default:
                return new User.Builder()
                    .setUuid(UUID.randomUUID())
                    .setFirstName("Roger Waters")
                    .setEmail("roger@thewall.com")
                    .build();
        }
    }

}
