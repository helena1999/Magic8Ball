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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raputa);
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
}
