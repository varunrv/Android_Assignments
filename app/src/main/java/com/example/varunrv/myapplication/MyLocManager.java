package com.example.varunrv.myapplication;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by vvadiraj on 9/21/2016.
 */
public class MyLocManager implements android.location.LocationListener {
    private Double lat;
    private Double lon;


    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            Log.e("Latitude:", "" + location.getLatitude());
            lat = location.getLatitude();
            Log.d("val", "" + lat.toString());
            Log.e("Longitude:", "" + location.getLongitude());
            lon = location.getLongitude();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public String getLatitude() {
        return lat.toString();
    }

    public String getLongitude() {
        return lon.toString();
    }
}

