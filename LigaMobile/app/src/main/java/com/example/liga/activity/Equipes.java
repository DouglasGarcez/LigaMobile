package com.example.liga.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.liga.R;
import com.example.liga.banco.DadosOpenHelper;
import com.google.android.material.navigation.NavigationView;

public class Equipes extends AppCompatActivity {

    private Button bt_equipe_1;
    private Button bt_equipe_2;
    private Button bt_equipe_3;
    private Button bt_equipe_4;
    private Button bt_equipe_5;
    private Button bt_equipe_6;
    private Button bt_equipe_7;
    private Button bt_equipe_8;

    private int control;

    DadosOpenHelper database;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipes);

        drawerLayout = findViewById(R.id.equipes);
        navigationView = findViewById(R.id.nav_view);

        database = new DadosOpenHelper(this);

        Intent recebedora = getIntent();
        Bundle recebeDados = recebedora.getExtras();

        control = recebeDados.getInt("keyControl");

        bt_equipe_1 = (Button) findViewById(R.id.bt_equipe_1);
        bt_equipe_2 = (Button) findViewById(R.id.bt_equipe_2);
        bt_equipe_3 = (Button) findViewById(R.id.bt_equipe_3);
        bt_equipe_4 = (Button) findViewById(R.id.bt_equipe_4);
        bt_equipe_5 = (Button) findViewById(R.id.bt_equipe_5);
        bt_equipe_6 = (Button) findViewById(R.id.bt_equipe_6);
        bt_equipe_7 = (Button) findViewById(R.id.bt_equipe_7);
        bt_equipe_8 = (Button) findViewById(R.id.bt_equipe_8);

        bt_equipe_3.setVisibility(View.GONE);
        bt_equipe_4.setVisibility(View.GONE);
        bt_equipe_5.setVisibility(View.GONE);
        bt_equipe_6.setVisibility(View.GONE);
        bt_equipe_7.setVisibility(View.GONE);
        bt_equipe_8.setVisibility(View.GONE);

        int numero;

        numero = Integer.parseInt(database.getLigaNumero(control));

        if(numero>2) {
            bt_equipe_3.setVisibility(View.VISIBLE);
            bt_equipe_4.setVisibility(View.VISIBLE);
        }

        if(numero>4){
            bt_equipe_5.setVisibility(View.VISIBLE);
            bt_equipe_6.setVisibility(View.VISIBLE);
            bt_equipe_7.setVisibility(View.VISIBLE);
            bt_equipe_8.setVisibility(View.VISIBLE);
        }

        listarEquipes();

        bt_equipe_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comportamentoBotoes(1,101,201,"TIME A");
            }
        });

        bt_equipe_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comportamentoBotoes(2,102,202,"TIME B");
            }
        });

        bt_equipe_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comportamentoBotoes(3,103,203,"TIME C");
            }
        });

        bt_equipe_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comportamentoBotoes(4,104,204,"TIME D");
            }
        });

        bt_equipe_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comportamentoBotoes(5,105,205,"TIME E");
            }
        });

        bt_equipe_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comportamentoBotoes(6,106,206,"TIME F");
            }
        });

        bt_equipe_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comportamentoBotoes(7,107,207,"TIME G");
            }
        });

        bt_equipe_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comportamentoBotoes(8,108,208,"TIME H");
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

    public void onResume(){
        super.onResume();
        listarEquipes();
    }

    private void listarEquipes(){

        if(control==1) {
            listarEquipesBotoes(1,2,3,4,5,6,7,8);
        }

        if(control==2) {
            listarEquipesBotoes(101,102,103,104,105,106,107,108);
        }

        if(control==3) {
            listarEquipesBotoes(201,202,203,204,205,206,207,208);
        }
    }

    private void listarEquipesBotoes(int timeA, int timeB, int timeC, int timeD, int timeE, int timeF, int timeG, int timeH){
        bt_equipe_1.setText(database.getTimeNome(timeA));
        bt_equipe_2.setText(database.getTimeNome(timeB));
        bt_equipe_3.setText(database.getTimeNome(timeC));
        bt_equipe_4.setText(database.getTimeNome(timeD));
        bt_equipe_5.setText(database.getTimeNome(timeE));
        bt_equipe_6.setText(database.getTimeNome(timeF));
        bt_equipe_7.setText(database.getTimeNome(timeG));
        bt_equipe_8.setText(database.getTimeNome(timeH));
    }

    private void comportamentoBotoes(int partida1, int partida2, int partida3, String time) {
        if(control==1) {
            comportamentoBotoesControl(partida1,time);
        }

        if(control==2) {
            comportamentoBotoesControl(partida2,time);
        }

        if(control==3) {
            comportamentoBotoesControl(partida3,time);
        }
    }

    private void comportamentoBotoesControl(int partida, String time){
        if (database.getTimeCod(partida) == "") {
            database.insert_time(partida, null,time);
        }
        openEquipe(partida);
    }

    public void openEquipe(int controlTime) {
        Intent intent = new Intent(getApplicationContext(), Equipe.class);
        intent.putExtra("keyControl", control);
        intent.putExtra("keyControlTime", controlTime);
        startActivity(intent);
    }
}