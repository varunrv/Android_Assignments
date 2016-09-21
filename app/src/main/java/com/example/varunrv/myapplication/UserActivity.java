package com.example.varunrv.myapplication;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class UserActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private EditText latitude, longitude;
    private EditText latitude2, longitude2;
    Button mark_button;
    String a, b, c, d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        buttonClick();


    }

    public void buttonClick() {
        latitude = (EditText) findViewById(R.id.la_value);
        longitude = (EditText) findViewById(R.id.lo_value);
        latitude2 = (EditText) findViewById(R.id.la_value2);
        longitude2 = (EditText) findViewById(R.id.lo_value2);

        mark_button = (Button) findViewById(R.id.search_button);
        mark_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a = latitude.getText().toString();
                b = longitude.getText().toString();
                c = latitude2.getText().toString();
                d = longitude2.getText().toString();
                Log.d("values", a);
                Log.d("values", b);
                Log.d("values", c);
                Log.d("values", d);
                LatLng userEntry1 = new LatLng(Double.valueOf(a), Double.valueOf(b));
                LatLng userEntry2 = new LatLng(Double.valueOf(c), Double.valueOf(d));

                Marker m1 = mMap.addMarker(new MarkerOptions()
                        .position((userEntry1)));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(userEntry1));
                Marker m2 = mMap.addMarker(new MarkerOptions()
                        .position((userEntry2)));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(userEntry2));
                String z = GeoCoding.getCompleteAddressString(userEntry1.latitude, userEntry1.longitude, UserActivity.this);
                String abc = "bangalore";
                LatLng user3 = GeoCoding.getcoordinates(UserActivity.this, abc);
                String l = String.valueOf(user3.latitude);
                String ll = String.valueOf(user3.longitude);
                Log.d("val", l);
                Log.d("val", ll);
                calculateDistance(userEntry1, userEntry2);

            }

            void calculateDistance(LatLng userEntry1, LatLng userEntry2) {
                float x[] = new float[1];
                Location.distanceBetween(userEntry1.latitude, userEntry1.longitude, userEntry2.latitude, userEntry2.longitude, x);
                String f = Float.toString(x[0] / 1000);
                Log.d("values", f);
                AlertDialog.Builder a_builder = new AlertDialog.Builder(UserActivity.this);
                a_builder.setMessage("Distance between coordinates  is: " + f + "kms").setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();

                            }
                        });
                AlertDialog alert = a_builder.create();
                alert.setTitle("Info....!!!!");
                alert.show();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng defaultMarker = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(defaultMarker).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(defaultMarker));
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

    }
}
