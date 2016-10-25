package com.fairphone.checkup.tests.gyroscope;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.fairphone.checkup.R;
import com.fairphone.checkup.tests.Test;

import java.text.DecimalFormat;

public class GyroscopeTest extends Test {

    private static final String TAG = GyroscopeTest.class.getSimpleName();

    SensorEventListener mSensorEventListener;
    SensorManager mSensorManager;
    Sensor mGyroscope;
    View mTestView;

    public GyroscopeTest(Context context) {
        super(context);
    }

    @Override
    protected int getTestTitleID() {
        return R.string.gyroscope_test_title;
    }

    @Override
    protected int getTestDescriptionID() {
        return R.string.gyroscope_test_description;
    }

    private void replaceView() {
        mTestView = LayoutInflater.from(getContext()).inflate(R.layout.view_gyroscope_test, null);
        setTestView(mTestView);
    }

    @Override
    protected void runTest() {
        replaceView();
        getGyroscope();
        setupSensorListener();
    }

    @Override
    protected void onCleanUp() {
        mSensorManager.unregisterListener(mSensorEventListener);
        mSensorEventListener = null;
        super.onCleanUp();
    }

    private void getGyroscope() {
        mSensorManager = (SensorManager)
                getContext().getSystemService(Context.SENSOR_SERVICE);
        mGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (mGyroscope == null) {
            onTestFailure();
            return;
        }
    }

    private void setupSensorListener() {
        mSensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                    onSensorChange(event);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        mSensorManager.registerListener(mSensorEventListener, mGyroscope, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void onSensorChange(SensorEvent event) {
        ((TextView)findViewById(R.id.x_value)).setText(event.values[0] + " " + getResources().getString(R.string.gyroscope_unit));
        ((TextView)findViewById(R.id.y_value)).setText(event.values[1] + " " + getResources().getString(R.string.gyroscope_unit));
        ((TextView)findViewById(R.id.z_value)).setText(event.values[2] + " " + getResources().getString(R.string.gyroscope_unit));
    }

    private String format(float value) {
        DecimalFormat df = new DecimalFormat("#.##");
        String ret = "";
        if(!(value < 0.0)) {
            ret = " ";
        }
        ret += df.format(value);
        return ret;
    }
}