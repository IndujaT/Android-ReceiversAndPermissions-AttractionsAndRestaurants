package com.example.induja.attractionsandrestaurants_app01;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import android.Manifest;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mButton;

    public static final String TOAST_INTENT_ATTRACTION = "edu.uic.cs478.sp18.Attraction";
    public static final String TOAST_INTENT_RESTAURANT = "edu.uic.cs478.sp18.Restaurant";
    public static final String INDUJAS_PERMISSION = "edu.uic.cs478.IndujasPermission";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermissionandBroadcast();
        Button Attraction_Btn = (Button)findViewById(R.id.btn_attractions);
        Button Restaurant_Btn = (Button)findViewById(R.id.btn_restaurants);

        Attraction_Btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int result = ContextCompat.checkSelfPermission(MainActivity.this, INDUJAS_PERMISSION);
                if (result == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getBaseContext(), "PERMISSION GRANTED", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent();
                    intent.setAction(TOAST_INTENT_ATTRACTION);
                    intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                    sendBroadcast(intent);
                }
                else{
                    checkPermissionandBroadcast();
                }
            }
        });
        Restaurant_Btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int result = ContextCompat.checkSelfPermission(MainActivity.this, INDUJAS_PERMISSION);
                if (result == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getBaseContext(), "PERMISSION GRANTED", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent();
                    intent.setAction(TOAST_INTENT_RESTAURANT);
                    intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                    sendBroadcast(intent);
                }
                else{
                    checkPermissionandBroadcast();
                }
            }
        });
    }

    private void checkPermissionandBroadcast(){
        if(ContextCompat.checkSelfPermission(this, INDUJAS_PERMISSION) == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getBaseContext(), "PERMISSION GRANTED", Toast.LENGTH_LONG).show();
        }
        else{
            ActivityCompat.requestPermissions(this,new String[]{INDUJAS_PERMISSION},0);
        }

    }


}