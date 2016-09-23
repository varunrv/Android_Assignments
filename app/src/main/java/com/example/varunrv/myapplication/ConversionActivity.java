package com.example.varunrv.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;

public class ConversionActivity extends AppCompatActivity {
    EditText address, latitude, longitude;
    Button geo_btn;
    Button reversegeo_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);
        GeoCoding();
        ReverseGeoCoding();
    }

    public void GeoCoding() {               //function when geocoding button is pressed
        address = (EditText) findViewById(R.id.inputAddress);
        geo_btn = (Button) findViewById(R.id.GeoCodingBtn);
        geo_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LatLng ex = GeoCoding.getcoordinates(ConversionActivity.this, address.getText().toString());
                        Log.d("val", "" + ex.latitude);
                        StringBuffer buffer3 = new StringBuffer();
                        buffer3.append("Latitude :" + ex.latitude + "\n");
                        buffer3.append("LOngitude :" + ex.longitude + "\n");

                        displayLocation("Corresponding coordinates", buffer3.toString());
                    }
                }
        );
    }

    public void ReverseGeoCoding() {                //function when Reverse GeoCoding button is presses
        reversegeo_btn = (Button) findViewById(R.id.RGoeCodingBtn);
        latitude = (EditText) findViewById(R.id.latitude_value);
        longitude = (EditText) findViewById(R.id.longitude_value);
        reversegeo_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s = GeoCoding.getCompleteAddressString
                                (Double.valueOf(latitude.getText().toString()), Double.valueOf(longitude.getText().toString()), ConversionActivity.this);
                        displayLocation("Corresponding address", s);
                    }

                }
        );
    }

    public void displayLocation(String title, String Message) {     //function to display current location in a dialog box
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                });
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
