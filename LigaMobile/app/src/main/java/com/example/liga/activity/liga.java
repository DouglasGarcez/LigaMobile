package com.example.liga.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.liga.R;
import com.example.liga.banco.DadosOpenHelper;
import com.google.android.material.navigation.NavigationView;

public class liga extends AppCompatActivity {

    private TextView tv_liga_x;
    private Spinner s_n_times;
    private Button bt_equipe;
    private EditText et_liga_nome;
    private Button bt_atualizar;
    private Button bt_excluir;
    private Button bt_partidas;

    private String liga;
    private int control;

    DadosOpenHelper database;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liga);

        drawerLayout = findViewById(R.id.liga);
        navigationView = findViewById(R.id.nav_view);

        database = new DadosOpenHelper(this);

        Intent recebedora = getIntent();
        Bundle recebeDados = recebedora.getExtras();

        control = recebeDados.getInt("keyControl");

        bt_excluir = (Button) findViewById(R.id.bt_excluir);
        bt_equipe = (Button) findViewById(R.id.bt_equipes);
        et_liga_nome = (EditText) findViewById(R.id.et_liga_nome);
        tv_liga_x = (TextView) findViewById(R.id.tv_liga_x);
        bt_atualizar = (Button) findViewById(R.id.bt_atualizar);
        s_n_times = (Spinner) findViewById(R.id.s_n_times);
        bt_partidas = (Button) findViewById(R.id.bt_partidas);

        Intent intentReceiver = getIntent();
        //liga = intentReceiver.getStringExtra("keyLiga");
        control = intentReceiver.getIntExtra("keyControl",0);

        //et_liga_nome.setText(database.getLigaNome(control));
        tv_liga_x.setText(database.getLigaNome(control));

        s_n_times = (Spinner) findViewById(R.id.s_n_times);
        String[] items = new String[]{"2", "4", "8"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        s_n_times.setAdapter(adapter);

        if(Integer.parseInt(database.getLigaNumero(control)) == 2) {
            s_n_times.setSelection(0);
        }
        if(Integer.parseInt(database.getLigaNumero(control)) == 4) {
            s_n_times.setSelection(1);
        }
        if(Integer.parseInt(database.getLigaNumero(control)) == 8) {
            s_n_times.setSelection(2);
        }

        bt_atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String novoNome;
                String novoNumero;

                if(!et_liga_nome.getText().toString().matches("")) {
                    novoNome = String.valueOf(et_liga_nome.getText());
                    database.atualizar_liga_nome(control, novoNome);
                    et_liga_nome.setText(novoNome);
                    tv_liga_x.setText(novoNome);
                }

                novoNumero = s_n_times.getSelectedItem().toString();
                database.atualizar_liga_numero(control, Integer.parseInt(novoNumero));
                if(novoNumero=="2") {
                    s_n_times.setSelection(0);
                }
                if(novoNumero=="4") {
                    s_n_times.setSelection(1);
                }
                if(novoNumero=="8") {
                    s_n_times.setSelection(2);
                }
                openOrganizacaoLiga();
            }
        });


        bt_excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int j=0;

                if(control==1)
                {
                    j=0;
                }
                if(control==2)
                {
                    j=100;
                }
                if(control==3)
                {
                    j=200;
                }

                for(int i=0; i<=7; i++) {
                    database.apaga_registro(i+j, "PARTIDA", "PARTIDA_ID");
                }

                for(int i=0; i<=8; i++) {
                    database.apaga_registro(i+j, "TIME", "TIME_ID");
                }

                database.apaga_registro(control,"LIGA","LIGA_ID");

                openMain();
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

    public void openMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);;
        startActivity(intent);
    }

    public void openOrganizacaoLiga() {
        Intent intent = new Intent(getApplicationContext(), OrganizacaoLiga.class);
        intent.putExtra("keyControl", control);
        startActivity(intent);
    }
}