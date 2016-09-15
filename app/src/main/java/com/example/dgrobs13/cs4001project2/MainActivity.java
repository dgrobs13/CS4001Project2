package com.example.dgrobs13.cs4001project2;

import android.Manifest;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer{

    TextView X = null;
    TextView Y = null;
    TextView Z = null;
    TextView Lat = null;
    TextView Long = null;
    AccelerometerHandler ah = null;
    LocationHandler lh = null;
    private static final int MY_PERMISSIONS_ACCESS_FINE_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},MY_PERMISSIONS_ACCESS_FINE_LOCATION);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        X = (TextView) findViewById(R.id.X);
        Y = (TextView) findViewById(R.id.Y);
        Z = (TextView) findViewById(R.id.Z);
        Lat = (TextView) findViewById(R.id.Lat);
        Long = (TextView) findViewById(R.id.Long);



    }

    @Override
    protected void onResume(){
        super.onResume();
        ah = new AccelerometerHandler(this);
        ah.addObserver(this);
        lh = new LocationHandler(this);
        lh.addObserver(this);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Lat.setText(Float.toString(getPreferences(MODE_PRIVATE).getFloat("LAT", (float) 0.0)));
        Long.setText(Float.toString(getPreferences(MODE_PRIVATE).getFloat("LONG", (float) 0.0)));
    }

    @Override
    protected void onPause(){
        super.onPause();
        getPreferences(MODE_PRIVATE).edit().putFloat("LAT",Float.parseFloat(Lat.getText().toString())).apply();
        getPreferences(MODE_PRIVATE).edit().putFloat("LONG",Float.parseFloat(Long.getText().toString())).apply();

    }

    @Override
    public void update(Observable observable, Object o) {
        if(observable instanceof AccelerometerHandler){
            float [] xyz = (float []) o;
            X.setText(Float.toString(xyz[0]));
            Y.setText(Float.toString(xyz[1]));
            Z.setText(Float.toString(xyz[2]));
        }

        if(observable instanceof LocationHandler){
            double [] doub = (double [])o;
            Lat.setText(Double.toString(doub[0]));
            Long.setText(Double.toString(doub[1]));
        }

    }
}
