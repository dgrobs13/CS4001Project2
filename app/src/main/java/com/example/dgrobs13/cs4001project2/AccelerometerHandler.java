package com.example.dgrobs13.cs4001project2;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.Observable;

/**
 * Created by dgrobs13 on 9/12/2016.
 */
public class AccelerometerHandler extends Observable implements SensorEventListener {

    SensorManager sensorManager = null;
    Sensor accelerometer = null;
    private long prev_time = 0;

    public AccelerometerHandler(Activity activ){
        sensorManager = (SensorManager) activ.getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        long curr_time = System.currentTimeMillis();
        if(curr_time - this.prev_time > 1000){
            this.prev_time = curr_time;
        }
        setChanged();
        notifyObservers(sensorEvent.values);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
