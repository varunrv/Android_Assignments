package com.example.varunrv.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static Button bunLogin;
    private static EditText empid;
    private static EditText empname;
    private static Button button_cancel;
    DBHelp mydb;
    String address;
    double latitude, longitude;
    LocationManager locationManager;
    MyLocManager loc = new MyLocManager();
    Location myLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoginButton();              // clicking login button
        onClickButtonListener();        // alert window function

        mydb = new DBHelp(MainActivity.this);   //creating database
        Log.d("val", "after sqladapter");

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        MyLocManager loc;
        loc = new MyLocManager();
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
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, loc);
        myLocation = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);    //checking with GPS is ON or OFF
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        myLocation = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "GPS is Enabled in your devide", Toast.LENGTH_SHORT).show();
            OnTasks();    // function call to perform operations when GPS is on
        } else {
            Toast.makeText(this, "GPS is not Enabled in your devide", Toast.LENGTH_SHORT).show();
            OffTasks();         // function call to perform operations when GPS is OFF
        }

    }

    public void LoginButton() {
        empid = (EditText) findViewById(R.id.emp_id);
        empname = (EditText) findViewById(R.id.emp_name);
        bunLogin = (Button) findViewById(R.id.login_btn);

        bunLogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (empid.getText().toString().equals("100") && empname.getText().toString().equals("A")) {
                            Toast.makeText(MainActivity.this, "Emp id and password is correct", Toast.LENGTH_SHORT).show();
                            Intent in = new Intent("com.example.varunrv.myapplication.UserActivity");
                            startActivity(in);
                        } else {
                            if (empid.getText().toString().equals("100") && empname.getText().toString().equals("A"))
                                Toast.makeText(MainActivity.this, "Both wrong input", Toast.LENGTH_SHORT).show();
                            else if (!empid.getText().toString().equals("100"))
                                Toast.makeText(MainActivity.this, "Invalid emp id", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(MainActivity.this, "Invalid emp name", Toast.LENGTH_SHORT).show();
                        }

                    }

                }
        );
    }

    public void onClickButtonListener() {
        button_cancel = (Button) findViewById(R.id.close_btn);
        button_cancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                        a_builder.setMessage("Do you want to close the application").setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert = a_builder.create();
                        alert.setTitle("Alert....!!!!");
                        alert.show();
                    }

                }
        );
    }

    public void OnTasks() {
        insert();
        StringBuffer buffer1 = new StringBuffer();
        buffer1.append("Address :" + address + "\n");
        buffer1.append("Latitude :" + latitude + "\n");
        buffer1.append("Longitude :" + longitude + "\n\n");
        displayLocation("Current Location", buffer1.toString());
    }

    public void insert() {
        latitude = myLocation.getLatitude();
        longitude = myLocation.getLongitude();
        address = GeoCoding.getCompleteAddressString(latitude, longitude, this);
        long numberOfRows = mydb.insert(address, String.valueOf(latitude), String.valueOf(longitude));
        String s = String.valueOf(numberOfRows);

    }

    public void OffTasks() {
        Cursor res = mydb.getdata();
        int count = res.getCount();
        StringBuffer buffer2 = new StringBuffer(count);
        if (res.moveToLast()) {
            //buffer.append("Id :" + res.getString(0) + "\n");
            buffer2.append("Address :" + res.getString(0) + "\n");
            buffer2.append("LAtitude :" + res.getString(1) + "\n");
            buffer2.append("Longitude :" + res.getString(2) + "\n\n");
        }
        displayLocation("Current Location", buffer2.toString());
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
