package com.bitbytebit.advertscreen.data.advert.model;

import java.util.Date;
import java.util.UUID;

/**
 * An advert
 */
public class Advert {

    private final UUID mUUID;

    private final User mSeller;

    private final String mTitle;

    private final String mDescription;

    private final Coord mLocation;

    private final String mLocationDesc;

    private final Date mDatePosted;


    public Advert(UUID uuid, User seller, String title, String description, Coord location, String locationDesc, Date datePosted) {
        mUUID = uuid;
        mSeller = seller;
        mTitle = title;
        mDescription = description;
        mLocation = location;
        mLocationDesc = locationDesc;
        mDatePosted = datePosted;
    }


    public static class Builder {
        private UUID mUuid;
        private User mSeller;
        private String mTitle;
        private String mDescription;
        private Coord mLocation;
        private String mLocationDesc;
        private Date mDatePosted;

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

        public Advert build() {
            return new Advert(mUuid, mSeller, mTitle, mDescription, mLocation, mLocationDesc, mDatePosted);
        }
    }
}
