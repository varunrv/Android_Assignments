package com.example.varunrv.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static Button bunLogin;
    private static EditText empid;
    private static EditText empname;
    private static Button button_cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoginButton();
        onClickButtonListener();
        //Toast.makeText(MainActivity.this,"Enter only number", Toast.LENGTH_SHORT).show();

    }
   /* public void FirstTime(View v){
        Toast.makeText(MainActivity.this,"Hi varun", Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"HI",Toast.LENGTH_LONG).show();
        Toast.makeText(this,"WelCome to Broadsoft",Toast.LENGTH_LONG).show();

    }*/

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

}
