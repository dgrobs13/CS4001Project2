package com.example.dgrobs13.cs4001project2;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import java.util.Observable;

/**
 * Created by dgrobs13 on 9/13/2016.
 */
public class LocationHandler extends Observable implements LocationListener {

    private LocationManager lm = null;
    private Location loc = null;
    private final static int Update_time = 1000;

    double latitude;
    double longitude;

    public LocationHandler(Activity act) {
        lm = (LocationManager) act.getSystemService(Context.LOCATION_SERVICE);
        if(lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, Update_time, 0, this);
            loc= lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);


        }

        latitude = loc.getLatitude();
        longitude = loc.getLongitude();

    }


    @Override
    public void onLocationChanged(Location location) {
        double[] doub = new double[2];
        doub[0]=location.getLatitude();
        doub[1]=location.getLongitude();
        setChanged();
        notifyObservers(doub);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
