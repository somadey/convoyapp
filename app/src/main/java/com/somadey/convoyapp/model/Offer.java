package com.somadey.convoyapp.model;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Offer {

    private int miles;
    private float offer;
    private Origin origin;
    private Destination destination;

    private static final String TAG = "Offer";

    public Offer(int miles, float offer, Origin origin, Destination destination) {
        this.miles = miles;
        this.offer = offer;
        this.origin = origin;
        this.destination = destination;
    }

    public int getMiles() {
        return miles;
    }

    public float getOffer() {
        return offer;
    }

    public Origin getOrigin() {
        return origin;
    }

    public Destination getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "miles=" + miles +
                ", offer=" + offer +
                ", origin=" + origin +
                ", destination=" + destination +
                '}';
    }

    public void log() {
        Log.d(TAG, this.toString());
    }

    public class Origin {
        private String city;
        private String state;
        private Timeslot pickup;
        public Origin(String city, String state, Timeslot pickup) {
            this.city = city;
            this.state = state;
            this.pickup = pickup;
        }

        @Override
        public String toString() {
            return "Origin{" +
                    "city='" + city + '\'' +
                    ", state='" + state + '\'' +
                    ", pickup=" + pickup +
                    '}';
        }

        public String getCity() {
            return city;
        }

        public String getState() {
            return state;
        }

        public Timeslot getPickup() {
            return pickup;
        }
    }

    public class Destination {
        private String city;
        private String state;
        private Timeslot dropoff;

        @Override
        public String toString() {
            return "Destination{" +
                    "city='" + city + '\'' +
                    ", state='" + state + '\'' +
                    ", dropoff=" + dropoff +
                    '}';
        }

        public String getCity() {
            return city;
        }

        public String getState() {
            return state;
        }

        public Timeslot getDropoff() {
            return dropoff;
        }

        public Destination(String city, String state, Timeslot dropoff) {
            this.city = city;
            this.state = state;
            this.dropoff = dropoff;
        }

    }

    public class Timeslot {
        public String getStart() {
            return start;
        }

        public String getEnd() {
            return end;
        }

        private String start;
        private String end;
        public Timeslot(String start, String end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Timeslot{" +
                    "start='" + start + '\'' +
                    ", end='" + end + '\'' +
                    '}';
        }

        public String getTimeDescription(){
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            SimpleDateFormat startOutputDateFormat = new SimpleDateFormat("EEE MM/dd h:mm a");
            SimpleDateFormat endOutputDateFormat = new SimpleDateFormat("- h:mm a");
            try {
                Date startDate = inputDateFormat.parse(start);
                Date endDate = inputDateFormat.parse(end);
                return startOutputDateFormat.format(startDate)+endOutputDateFormat.format(endDate);
            }catch (ParseException e){
                return "Parsing Error in Time";
            }
        }
    }

}
