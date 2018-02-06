
package com.example.opilane.magic8ball;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.Random;

public class RaputaActivity extends AppCompatActivity {

    public static final int FADE_DURATION = 1500;
    public static final int START_OFFSET = 1000;
    public static final int VIBRATE_TIME = 250;
    public static final int THERESHOLD = 240;
    public static final int SHAKE_COUNT = 2;
    public static Random RANDOM = new Random();

	private Vibrator vibrator;
	private SensorManager sensorManager;
	private Sensor sensor;
	private float lastX, lastY, lastZ;
	int raputaloend = 0;
	TextView vastTekst;
	ImageView pall;
	Animation palliAnime;
	ArrayList<String>vastused;
	Button raputa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raputa);

		raputa = findViewById(R.id.raputa);
		pall = findViewById(R.id.pall);
		vastTekst = findViewById(R.id.vastTekst);
		vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		palliAnime = AnimationUtils.loadAnimation(this, R.anim.raputa);
		vastused = loadVastused();
		raputa.setOnClickListener(new View.OnClickListener(){

		@Override
		public void onClick(View v){
		showVastus (getVastus(),true);
		}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater =getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.esileht:
                startActivity(new Intent(this, MainActivity.class));
                return true;
        }
        return false;
    }
	@Override 
    public void onResume(){
	super.onResume();
	sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
	showVastus(getString(R.string.raputa), false);
	}
	@Override
	public void onPause(){
	super.onPause();
	sensorManager.unregisterListener(this);
	}
	@Override
	public void onSensorChanged(SensorEvent sensorEvent){if
		if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			if (isShakeEnough(sensorEvent.value[0], sensorEvent.value[1], sensorEvent.value[2])) {
				showVastus(getVastus(), false);
			}
		}
	}
	@Override
	public void onAccurancyChanged(Sensor sensor, int i){}
	private boolean isShakeEnough(float x, float y, float z){
		double force = 0d

		force += Math.pow((x-lastX) / SensorManager.GRAVITY_EARTH, 2.0);
		force += Math.pow((y-lastY) / SensorManager.GRAVITY_EARTH, 2.0);
		force += Math.pow((z-lastZ) / SensorManager.GRAVITY_EARTH, 2.0);




}
