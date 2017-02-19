package com.bitbytebit.advertscreen.data.advert.model;

import java.util.UUID;

/**
 * Model of a user (seller)
 */
public class User {

    public final UUID uuid;

    public final String firstName;

    public final Integer phoneNumber;

    public final String email;

    public User(UUID uuid, String firstName, Integer phoneNumber, String email) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }


    public static class Builder {
        private UUID mUuid;
        private String mFirstName;
        private Integer mPhoneNumber;
        private String mEmail;

        public Builder setUuid(UUID uuid) {
            mUuid = uuid;
            return this;
        }

        public Builder setFirstName(String firstName) {
            mFirstName = firstName;
            return this;
        }

        public Builder setPhoneNumber(Integer phoneNumber) {
            mPhoneNumber = phoneNumber;
            return this;
        }

        public Builder setEmail(String email) {
            mEmail = email;
            return this;
        }

        public User build() {
            return new User(mUuid, mFirstName, mPhoneNumber, mEmail);
        }
    }
}
