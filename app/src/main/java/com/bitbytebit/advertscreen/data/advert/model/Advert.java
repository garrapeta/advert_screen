package com.bitbytebit.advertscreen.data.advert.model;

import java.util.Date;
import java.util.UUID;

/**
 * An advert
 */
public class Advert {

    public final UUID uuid;

    public final User seller;

    public final String title;

    public final String description;

    public final Coord location;

    public final String locationDesc;

    public final Date datePosted;


    public Advert(UUID uuid, User seller, String title, String description, Coord location, String locationDesc, Date datePosted) {
        this.uuid = uuid;
        this.seller = seller;
        this.title = title;
        this.description = description;
        this.location = location;
        this.locationDesc = locationDesc;
        this.datePosted = datePosted;
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
