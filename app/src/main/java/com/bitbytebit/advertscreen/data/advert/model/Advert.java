package com.bitbytebit.advertscreen.data.advert.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * An advert
 */
public class Advert {

    public final UUID uuid;

    public final User seller;

    public final String title;

    public final String description;

    public final Integer price;

    public final Coord location;

    public final String locationDesc;

    public final Date datePosted;

    public final List<URL> imagesUris;

    public final boolean isFavourite;


    public Advert(UUID uuid, User seller, String title, String description, Integer price, Coord location, String locationDesc, Date datePosted, List<URL> imagesUris, boolean isFavourite) {
        this.uuid = uuid;
        this.seller = seller;
        this.title = title;
        this.description = description;
        this.price = price;
        this.location = location;
        this.locationDesc = locationDesc;
        this.datePosted = datePosted;
        this.imagesUris = imagesUris;
        this.isFavourite = isFavourite;
    }


    public static class Builder {
        private UUID mUuid;
        private User mSeller;
        private String mTitle;
        private String mDescription;
        private Coord mLocation;
        private String mLocationDesc;
        private Date mDatePosted;
        private List<URL> mImagesUris = new ArrayList<>();
        private boolean mIsFavourite;
        private Integer mPrice;

        public Builder setUuid(UUID uuid) {
            mUuid = uuid;
            return this;
        }

        public Builder setTitle(String title) {
            mTitle = title;
            return this;
        }

        public Builder setSeller(User seller) {
            mSeller = seller;
            return this;
        }

        public Builder setPrice(int price) {
            mPrice = price;
            return this;
        }

        public Builder setDescription(String description) {
            mDescription = description;
            return this;
        }

        public Builder setLocation(Coord location) {
            mLocation = location;
            return this;
        }

        public Builder setLocationDesc(String locationDesc) {
            mLocationDesc = locationDesc;
            return this;
        }

        public Builder setDatePosted(Date datePosted) {
            mDatePosted = datePosted;
            return this;
        }

        public Builder addImageUrl(String imageUrl) {
            try {
                mImagesUris.add(new URL(imageUrl));
                return this;
            } catch (MalformedURLException e) {
                throw new IllegalArgumentException(e);
            }
        }

        public Builder setImageUris(List<URL> imageUris) {
            mImagesUris.addAll(imageUris);
            return this;
        }

        public Builder setFavourite(boolean favourite) {
            mIsFavourite = favourite;
            return this;
        }


        public Builder from(Advert advert) {
            return new Builder()
                    .setUuid(advert.uuid)
                    .setSeller(advert.seller)
                    .setPrice(advert.price)
                    .setTitle(advert.title)
                    .setDescription(advert.description)
                    .setLocation(advert.location)
                    .setLocationDesc(advert.locationDesc)
                    .setDatePosted(advert.datePosted)
                    .setFavourite(advert.isFavourite)
                    .setImageUris(advert.imagesUris);

        }

        public Advert build() {
            return new Advert(mUuid, mSeller, mTitle, mDescription, mPrice, mLocation, mLocationDesc, mDatePosted, mImagesUris, mIsFavourite);
        }


    }
}
