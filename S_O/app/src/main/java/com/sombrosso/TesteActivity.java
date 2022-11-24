package com.sombrosso;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

public class TesteActivity extends AppCompatActivity implements SensorEventListener {
    ImageButton btnMenu9;
    ImageView imgPoder;



    // As duas linhas abaixo: variaveis necessárias para gerenciar o sensor e definir qual sensor é respectivamente.
    SensorManager sensorManager;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);
        btnMenu9 = findViewById(R.id.btnMenu9);
        imgPoder = findViewById(R.id.imgPoder);


        // Permite o uso dos serviços de sensor
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Define o tipo do sensor.
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // "Liga o sensor" (faz com que o sensor reaja as mudanças na variação e passe essas informações.)
        sensorManager.registerListener( this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        btnMenu9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onSensorChanged(SensorEvent event){
        // A funcionalidade do sensor vai aqui
        float direção = event.values[0];
        if(direção < -0.5)
            imgPoder.setImageResource(R.drawable.sol_poder);
        else if(direção > 0.5)
            imgPoder.setImageResource(R.drawable.escuro_poder);
        else
            imgPoder.setImageResource(R.drawable.no_poder);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}