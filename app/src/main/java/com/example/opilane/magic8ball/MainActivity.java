package com.example.opilane.magic8ball;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView vasText;

    private SensorManager sensorManager;
    private float acelValue;
    private float acelLast;
    private float raputa;
    MediaPlayer music;

    private String [] answer;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.magic1:
                startActivity(new Intent(this, RaputaActivity.class));
                return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vasText = findViewById(R.id.vasText);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(sensorListener,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);

        acelLast = SensorManager.GRAVITY_EARTH;
        acelValue = SensorManager.GRAVITY_EARTH;
        raputa = 0.00f;
        answer = getResources().getStringArray(R.array.vastused);


    }
    private final SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];


            acelLast = acelValue;
            acelValue = (float)Math.sqrt((double)(x*x+y*y+z*z));
            float delta = acelValue - acelLast;
            raputa = raputa * 0.9f + delta;

            if (raputa > 12)
            {

                int randomInt = new Random().nextInt(answer.length);
                String randomVastus = answer[randomInt];
                vasText.setText(randomVastus);
                if (music == null){
                    music = MediaPlayer.create(MainActivity.this, R.raw.music);
            }
            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };
}
