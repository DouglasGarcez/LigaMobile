package com.example.liga.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.liga.R;
import com.example.liga.banco.DadosOpenHelper;
import com.google.android.material.navigation.NavigationView;
import com.santalu.maskedittext.MaskEditText;

public class Partida extends AppCompatActivity {

    DadosOpenHelper database;

    private int control;
    private int controlPartida;
    private double log;
    private double lat;

    private TextView tv_mandante;
    private TextView tv_visitante;
    private TextView tv_mandanteP;
    private TextView tv_visitanteP;
    private MaskEditText et_mandante_placar;
    private MaskEditText et_visitante_placar;
    private MaskEditText et_mandante_placarP;
    private MaskEditText et_visitante_placarP;
    private TextView tv_mapa;
    private Button bt_local;
    private Button bt_atualizar_partida;

    int PLACE_PICKER_REQUEST=1;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);

        drawerLayout = findViewById(R.id.partida);
        navigationView = findViewById(R.id.nav_view);

        database = new DadosOpenHelper(this);

        Intent recebedora = getIntent();
        Bundle recebeDados = recebedora.getExtras();

        control = recebeDados.getInt("keyControl");
        controlPartida = recebeDados.getInt("keyControlTime");
        lat = recebeDados.getDouble("lat");
        log = recebeDados.getDouble("log");

        FragmentManager fragmentManager = getSupportFragmentManager();
        final androidx.fragment.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MapViewFragment fragobj = new MapViewFragment();
        Bundle args = new Bundle();
        args.putDouble("log", log);
        args.putDouble("lat", lat);
        fragobj.setArguments(args);
        fragmentTransaction.replace(R.id.fragment, fragobj);
        fragmentTransaction.commit();

        tv_mandante = findViewById(R.id.tv_mandante);
        tv_visitante = findViewById(R.id.tv_visitante);
        //tv_mandanteP = findViewById(R.id.tv_mandanteP);
        //tv_visitanteP = findViewById(R.id.tv_visitanteP);
        et_mandante_placar = findViewById(R.id.et_mandante_placar);
        et_visitante_placar = findViewById(R.id.et_visitante_placar);
        et_mandante_placarP = findViewById(R.id.et_mandante_placarP);
        et_visitante_placarP = findViewById(R.id.et_visitante_placarP);
        bt_local = findViewById(R.id.bt_local);
        bt_atualizar_partida = findViewById(R.id.bt_atualizar_partida);

        tv_mandante.setText(database.getPartidaMan(controlPartida));
        tv_visitante.setText(database.getPartidaVis(controlPartida));
       //tv_mandanteP.setText(tv_mandante.getText());
        //tv_visitanteP.setText(tv_visitante.getText());

        et_mandante_placar.setText(database.getPartidaScore(controlPartida,"MANDANTE_PLACAR"));
        et_visitante_placar.setText(database.getPartidaScore(controlPartida,"VISITANTE_PLACAR"));

        et_mandante_placarP.setText(database.getPartidaScore(controlPartida, "MANDANTE_PLACAR_P"));
        et_visitante_placarP.setText(database.getPartidaScore(controlPartida,"VISITANTE_PLACAR_"));

        bt_local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMapa();
            }
        });

        bt_atualizar_partida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String novoPlacarMan;
                String novoPlacarVis;
                String novoPlacarManP;
                String novoPlacarVisP;

                if(!et_mandante_placar.getText().toString().matches("")) {
                    novoPlacarMan = String.valueOf(et_mandante_placar.getText());
                    database.atualizar_partida_placarMan(controlPartida, novoPlacarMan);
                    et_mandante_placar.setText(novoPlacarMan);
                }

                if(!et_visitante_placar.getText().toString().matches("")) {
                    novoPlacarVis = String.valueOf(et_visitante_placar.getText());
                    database.atualizar_partida_placarVis(controlPartida, novoPlacarVis);
                    et_visitante_placar.setText(novoPlacarVis);
                }

                if(!et_mandante_placarP.getText().toString().matches("")) {
                    novoPlacarManP = String.valueOf(et_mandante_placarP.getText());
                    database.atualizar_partida_placarManP(controlPartida, novoPlacarManP);
                    et_mandante_placarP.setText(novoPlacarManP);
                }

                if(!et_visitante_placarP.getText().toString().matches("")) {
                    novoPlacarVisP = String.valueOf(et_visitante_placarP.getText());
                    database.atualizar_partida_placarVisQ(controlPartida, novoPlacarVisP);
                    et_visitante_placarP.setText(novoPlacarVisP);
                }
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.nav_home: {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        break;
                    }

                    case R.id.nav_liga: {
                        Intent intent = new Intent(getApplicationContext(), liga.class);
                        intent.putExtra("keyControl", control);
                        startActivity(intent);
                        break;
                    }

                    case R.id.nav_equipes: {
                        Intent intent = new Intent(getApplicationContext(), Equipes.class);
                        intent.putExtra("keyControl", control);
                        startActivity(intent);
                        break;
                    }

                    case R.id.nav_partidas: {
                        Intent intent = new Intent(getApplicationContext(), Partidas.class);
                        intent.putExtra("keyControl", control);
                        startActivity(intent);
                        break;
                    }
                }

                return true;
            }
        });
    }

    public void openMapa() {
        Intent intent = new Intent(getApplicationContext(), PlacePicker.class);
        intent.putExtra("keyControl", control);
        intent.putExtra("keyControlTime", controlPartida);
        //intent.putExtra("lat", -51.176275731362146);
        //intent.putExtra("log", -29.1610913363014);
        startActivity(intent);
    }
}