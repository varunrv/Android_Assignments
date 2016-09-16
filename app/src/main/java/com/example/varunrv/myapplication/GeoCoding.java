package com.example.varunrv.myapplication;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by vvadiraj on 9/15/2016.
 */
public class GeoCoding {
    public static String getCompleteAddressString(double LATITUDE, double LONGITUDE, Context context) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.w("My Current address", "" + strReturnedAddress.toString());
            } else {
                Log.w("My Current Address", "No Address returned!");
            }
        } catch (Exception e) {


            e.printStackTrace();
            Log.w("My Current address", "Canont get Address!");
        }
        return strAdd;
    }

    public static LatLng getcoordinates(Context context, String locationname) {
        if (!Geocoder.isPresent()) {

        }
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocationName(locationname, 1);
            int tentatives = 0;
            while (addresses.size() > 0 && (tentatives < 10)) {
                addresses = geocoder.getFromLocationName("<address goes here>", 1);
                tentatives++;
            }
            if (addresses.size() > 0) {
                Log.d("zebia", "reverse geocoding:locationname" + locationname + "Latitude" + addresses.get(0).getLatitude());
                return new LatLng(addresses.get(0).getLatitude(), addresses.get(0).getLongitude());
            }
        } catch (IOException e) {
            Log.d(Geocoder.class.getName(), "not able to find LtLng");
        }
        return null;
    }

}
