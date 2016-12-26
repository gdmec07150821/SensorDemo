package cn.edu.gdmec.s07150821.sensordemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {


    private SensorManager sensorManager;
    private Sensor mAccelerometer;
    private Sensor mOrientation;
    private Sensor mLight;
    private TextView tAccelerometer;
    private TextView tOrientation;
    private TextView tLight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tAccelerometer= (TextView) this.findViewById(R.id.acceleromete);
        tOrientation= (TextView) this.findViewById(R.id.orientation);
        tLight= (TextView) this.findViewById(R.id.light);
        sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mOrientation=sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        mLight=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,mAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this,mOrientation,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this,mLight,SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x=event.values[SensorManager.DATA_X];
        float y=event.values[SensorManager.AXIS_MINUS_Y];
        float z=event.values[SensorManager.AXIS_MINUS_Z];
        if (event.sensor.getType()==Sensor.TYPE_ORIENTATION){
            tOrientation.setText("方位:"+x+","+y+","+z);
        }
        else if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            tAccelerometer.setText("加速度:"+x+","+y+","+z);
        }else if (event.sensor.getType()==Sensor.TYPE_LIGHT){
            tLight.setText("光线:"+event.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
