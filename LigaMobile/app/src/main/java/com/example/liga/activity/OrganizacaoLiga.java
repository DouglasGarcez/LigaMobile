package com.example.liga.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.liga.R;
import com.example.liga.activity.Equipes;
import com.example.liga.activity.Partidas;
import com.example.liga.banco.DadosOpenHelper;

public class OrganizacaoLiga extends AppCompatActivity {
    private Button bt_partidas;
    private Button bt_equipe;
    private TextView tv_liga_x;
    private int control;
    DadosOpenHelper database;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizacao_liga);

        database = new DadosOpenHelper(this);
        tv_liga_x = (TextView) findViewById(R.id.tv_organizacaoLiga_x);
        bt_equipe = (Button) findViewById(R.id.bt_equipes);
        bt_partidas = (Button) findViewById(R.id.bt_partidas);

        Intent recebedora = getIntent();
        Bundle recebeDados = recebedora.getExtras();
        control = recebeDados.getInt("keyControl");

        tv_liga_x.setText(database.getLigaNome(control));

        bt_equipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEquipes();
            }
        });

        bt_partidas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openPartidas();
            }
        });
    }

    public void openEquipes() {
        Intent intent = new Intent(getApplicationContext(), Equipes.class);
        intent.putExtra("keyControl", control);
        startActivity(intent);
    }

    public void openPartidas() {
        Intent intent = new Intent(getApplicationContext(), Partidas.class);
        intent.putExtra("keyControl", control);
        startActivity(intent);
    }
}
