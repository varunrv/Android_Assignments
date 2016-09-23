package com.example.varunrv.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.R.attr.start;

/**
 * Created by vvadiraj on 9/15/2016.
 */
public class GeoCoding {
    public static String getCompleteAddressString(double LATITUDE, double LONGITUDE, Context context) {         //reverse GeoCoding
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

    public static LatLng getcoordinates(Context context, String locationname) {             //Geocoding
        if (!Geocoder.isPresent()) {

        }
        Geocoder geocoder = new Geocoder(context);
        try {
            List<Address> list = geocoder.getFromLocationName(locationname, 1);
            Address location =list.get(0);
            double latitude= location.getLatitude();
            double longitude=location.getLongitude();
            return new LatLng(latitude,longitude);

        } catch (IOException e) {
            Log.d(Geocoder.class.getName(), "not able to find LtLng");
        }
        return null;
    }

}
