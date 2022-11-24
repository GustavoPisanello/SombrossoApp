package com.sombrosso;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;


public class LocalizacaoActivity extends AppCompatActivity {

    TextView view_distancia;
    ImageButton btnMenu10;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizacao);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        view_distancia = findViewById(R.id.view_distancia);
        btnMenu10 = findViewById(R.id.btnMenu10);


        btnMenu10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }
        });


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)   != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(LocalizacaoActivity.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(LocalizacaoActivity.this, new String[] {Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            ActivityCompat.requestPermissions(LocalizacaoActivity.this, new String[] {Manifest.permission.ACCESS_NETWORK_STATE}, 1);
        }
        LocationManager  locationManager  = (LocationManager) getSystemService(LOCATION_SERVICE);
        LocationListener locationListener = new local();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            double latitude = local.latitude;
            double longitude = local.longitude;
            double distancia = Math.round(Math.sqrt(Math.pow(latitude -(47.497913),2) + Math.pow(longitude -(19.040236),2))* 111139/1000);
            String texto = "Você está a uma distância de:\n" + Double.toString(distancia) + " Km";
            view_distancia.setText(texto);
        }


    }

    public void btnPeste(View view) {
        String mapa = "geo:" + 47.497913 + "," + 19.040236;
        Uri urlMapa = Uri.parse(mapa);
        Intent intentMapa = new Intent(Intent.ACTION_VIEW, urlMapa);
        startActivity(intentMapa);
    }
}
