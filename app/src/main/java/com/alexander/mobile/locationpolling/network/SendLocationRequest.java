package com.alexander.mobile.locationpolling.network;

/**
 * Created by alexander on 12/7/15.
 */
public class SendLocationRequest {

    private int userId;
    private double latitude;
    private double longtitude;

    public int getUserId() {
        return userId;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public static RequestBuilder builder() {
        return new SendLocationRequest().new RequestBuilder();
    }

    @Override
    public String toString() {
        return "SendLocationRequest{" +
                "userId=" + userId +
                ", latitude=" + latitude +
                ", longtitude=" + longtitude +
                '}';
    }

    public class RequestBuilder {

        private RequestBuilder() {
            // private constructor
        }

        public RequestBuilder setUserId(int userId) {

            SendLocationRequest.this.userId = userId;
            return this;
        }

        public RequestBuilder setLatitude(double latitude) {

            SendLocationRequest.this.latitude = latitude;
            return this;
        }

        public RequestBuilder setLongtitude(double longtitude) {

            SendLocationRequest.this.longtitude = longtitude;
            return  this;
        }

        public SendLocationRequest build() {
            return SendLocationRequest.this;
        }

    }
}