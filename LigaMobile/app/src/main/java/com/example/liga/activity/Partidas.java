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

public class Partidas extends AppCompatActivity {

    private Button bt_partida_1_1;
    private Button bt_partida_1_2;
    private Button bt_partida_1_3;
    private Button bt_partida_1_4;
    private Button bt_partida_2_1;
    private Button bt_partida_2_2;
    private Button bt_partida_3_1;

    private int control;

    private  double lat = -51.176275731362146;
    private  double log = -29.1610913363014;

    DadosOpenHelper database;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partidas);



        drawerLayout = findViewById(R.id.partidas);
        navigationView = findViewById(R.id.nav_view);

        database = new DadosOpenHelper(this);

        Intent recebedora = getIntent();
        Bundle recebeDados = recebedora.getExtras();

        control = recebeDados.getInt("keyControl");

        int numero;

        numero = Integer.parseInt(database.getLigaNumero(control));

        bt_partida_1_1 = findViewById(R.id.bt_partida_1_1);
        bt_partida_1_2 = findViewById(R.id.bt_partida_1_2);
        bt_partida_1_3 = findViewById(R.id.bt_partida_1_3);
        bt_partida_1_4 = findViewById(R.id.bt_partida_1_4);
        bt_partida_2_1 = findViewById(R.id.bt_partida_2_1);
        bt_partida_2_2 = findViewById(R.id.bt_partida_2_2);
        bt_partida_3_1 = findViewById(R.id.bt_partida_3_1);

        bt_partida_1_2.setVisibility(View.GONE);
        bt_partida_1_3.setVisibility(View.GONE);
        bt_partida_1_4.setVisibility(View.GONE);
        bt_partida_2_1.setVisibility(View.GONE);
        bt_partida_2_2.setVisibility(View.GONE);
        bt_partida_3_1.setVisibility(View.GONE);

        if(numero>2){
            bt_partida_1_2.setVisibility(View.VISIBLE);
            bt_partida_2_1.setVisibility(View.VISIBLE);

        }
        if(numero>4){
            bt_partida_1_3.setVisibility(View.VISIBLE);
            bt_partida_1_4.setVisibility(View.VISIBLE);
            bt_partida_2_2.setVisibility(View.VISIBLE);
            bt_partida_3_1.setVisibility(View.VISIBLE);
        }

        listarPartidas();

        bt_partida_1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(control==1) {
                    primeiraFase(1,1,2);
                }

                if(control==2) {
                    primeiraFase(101,101,102);
                }

                if(control==3) {
                    primeiraFase(201,201,202);
                }

            }
        });

        bt_partida_1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(control==1) {
                    primeiraFase(2,3,4);
                }

                if(control==2) {
                    primeiraFase(102,103,104);
                }

                if(control==3) {
                    primeiraFase(202,203,204);
                }
                log = -29.1610913363014;
                lat = -31.176275731362146;
            }

        });

        bt_partida_1_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(control==1) {
                    primeiraFase(3,5,6);
                }

                if(control==2) {
                    primeiraFase(103,105,106);
                }

                if(control==3) {
                    primeiraFase(203,205,206);
                }
                log = -29.1610913363014;
                lat = -41.176275731362146;
            }
        });

        bt_partida_1_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(control==1) {
                    primeiraFase(4,7,8);
                }

                if(control==2) {
                    primeiraFase(104,107,108);
                }

                if(control==3) {
                    primeiraFase(204,207,208);
                }
                log = -19.1610913363014;
                lat = -31.176275731362146;
            }
        });

        bt_partida_2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(control==1)
                {
                    segundaFase(5,1,2,1,2,3,4);
                }

                if(control==2)
                {
                    segundaFase(105,101,102,101,102,103,104);
                }

                if(control==3)
                {
                    segundaFase(205,201,202,201,202,203,204);
                }
                log = -29.1610913363014;
                lat = -31.176275731362146;
            }

        });

        bt_partida_2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(control==1)
                {
                    segundaFase(6,3,4,5,6,7,8);
                }

                if(control==2)
                {
                    segundaFase(106,103,104,105,106,107,108);
                }

                if(control==3)
                {
                    segundaFase(206,203,204,205,206,207,208);
                }
                log = -29.1610913363014;
                lat = -31.176275731362146;
            }
        });

        bt_partida_3_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(control==1)
                {
                    terceiraFase(7,5,6);
                }

                if(control==2)
                {
                    terceiraFase(107,105,106);
                }

                if(control==3)
                {
                    terceiraFase(207,205,206);
                }
                log = -29.1610913363014;
                lat = -31.176275731362146;
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
                }

                return true;
            }
        });
    }

    public void onResume(){
        super.onResume();
        listarPartidas();
    }

    public void openPartida(int controlPartida) {
        Intent intent = new Intent(getApplicationContext(), Partida.class);
        intent.putExtra("keyControl", control);
        intent.putExtra("keyControlTime", controlPartida);
        intent.putExtra("lat", lat);
        intent.putExtra("log", log);
        startActivity(intent);
    }

    private void novaFase(int jogoA, int jogoB, int timeA, int timeB, int timeC, int timeD, int novoJogo){
        if(!vencedor(jogoA,timeA,timeB).toString().equals("")){
            if(!vencedor(jogoB,timeC,timeD).toString().equals("")){
                database.insert_partida(novoJogo,control,0,0,0,0,lat,log);
            }
        }
    }

    private StringBuilder vencedor(int jogo, int timeA, int timeB) {
        StringBuilder botao = new StringBuilder();

        if(Integer.parseInt(database.getPartidaScore(jogo,"MANDANTE_PLACAR")) > Integer.parseInt(database.getPartidaScore(jogo,"VISITANTE_PLACAR"))) {
            botao.append(database.getTimeNome(timeA));
        }
        else if(Integer.parseInt(database.getPartidaScore(jogo,"MANDANTE_PLACAR")) < Integer.parseInt(database.getPartidaScore(jogo,"VISITANTE_PLACAR"))) {
            botao.append(database.getTimeNome(timeB));
        }
        else {
            if(Integer.parseInt(database.getPartidaScore(jogo,"MANDANTE_PLACAR_P")) < Integer.parseInt(database.getPartidaScore(jogo,"VISITANTE_PLACAR_"))){
                botao.append(database.getTimeNome(timeB));
            }
            else{
                botao.append(database.getTimeNome(timeA));
            }
        }

        return botao;
    }

    private void listarPartidas(){
        if(control==1)
        {
            listarLiga(1,2,3,4,5,6,7,8,1,2,3,4,5,6,7);
        }
        if(control==2)
        {
            listarLiga(101,102,103,104,105,106,107,108,101,102,103,104,105,106,107);
        }
        if(control==3)
        {
            listarLiga(201,202,203,204,205,206,207,208,201,202,203,204,205,206,207);
        }
    }

    private void avancar(int jogoA, int jogoB, int timeA, int timeB, int timeC, int timeD, int jogoNovo, Button button){

        StringBuilder botao = new StringBuilder();

        botao.append(vencedor(jogoA,timeA,timeB));

        botao.append(' ' + database.getPartidaScore(jogoNovo,"MANDANTE_PLACAR") + " X ");
        botao.append(database.getPartidaScore(jogoNovo,"VISITANTE_PLACAR") + ' ');

        botao.append(vencedor(jogoB,timeC,timeD));

        button.setText(botao);
    }

    private void primeiraFase(int partidaID, int timeA, int timeB) {
        if (database.getPartidaCod(partidaID) == "") {
            database.insert_partida(partidaID,control,0,0,0,0,lat,log);
        }

        if(!database.getTimeCod(timeA).equals("")) {
            database.atualizar_partida_mandante(partidaID, Integer.parseInt(database.getTimeCod(timeA)));
        }
        if(!database.getTimeCod(timeB).equals("")) {
            database.atualizar_partida_visitante(partidaID, Integer.parseInt(database.getTimeCod(timeB)));
        }

        openPartida(partidaID);
    }

    private void segundaFase(int partidaNova, int partidaA, int partidaB, int timeA, int timeB, int timeC, int timeD) {
        if (database.getPartidaCod(partidaNova) == "" && database.getPartidaCod(partidaA) != "" && database.getPartidaCod(partidaB) != "") {
            novaFase(partidaA, partidaB, timeA, timeB, timeC, timeD, partidaNova);
        }

        if(database.getPartidaCod(partidaNova) != "") {
            int crazyA = Integer.parseInt(database.getTimeCodByNome(vencedor(partidaA, timeA, timeB).toString()));
            int crazyB = Integer.parseInt(database.getTimeCodByNome(vencedor(partidaB, timeC, timeD).toString()));

            if (!database.getTimeCod(crazyA).equals("")) {
                database.atualizar_partida_mandante(partidaNova, Integer.parseInt(database.getTimeCod(crazyA)));
            }
            if (!database.getTimeCod(crazyB).equals("")) {
                database.atualizar_partida_visitante(partidaNova, Integer.parseInt(database.getTimeCod(crazyB)));
            }

            openPartida(partidaNova);
        }
    }

    private void terceiraFase(int partidaNova, int partidaA, int partidaB){
        if (database.getPartidaCod(partidaNova) == "" && database.getPartidaCod(partidaA) != "" && database.getPartidaCod(partidaB) != "") {

            int madA = Integer.parseInt(database.getTimeCodByNome(database.getPartidaMan(partidaA)));
            int madB = Integer.parseInt(database.getTimeCodByNome(database.getPartidaVis(partidaA)));
            int madC = Integer.parseInt(database.getTimeCodByNome(database.getPartidaMan(partidaB)));
            int madD = Integer.parseInt(database.getTimeCodByNome(database.getPartidaVis(partidaB)));

            novaFase(partidaA, partidaB, madA, madB, madC, madD, partidaNova);
        }

        if(database.getPartidaCod(partidaNova) != "") {
            int crazyA = Integer.parseInt(database.getTimeCodByNome(vencedor(partidaA, Integer.parseInt(database.getTimeCodByNome(database.getPartidaMan(partidaA))), Integer.parseInt(database.getTimeCodByNome(database.getPartidaMan(partidaA)))).toString()));
            int crazyB = Integer.parseInt(database.getTimeCodByNome(vencedor(partidaB, Integer.parseInt(database.getTimeCodByNome(database.getPartidaMan(partidaB))), Integer.parseInt(database.getTimeCodByNome(database.getPartidaMan(partidaB)))).toString()));

            if (!database.getTimeCod(crazyA).equals("")) {
                database.atualizar_partida_mandante(partidaNova, Integer.parseInt(database.getTimeCod(crazyA)));
            }
            if (!database.getTimeCod(crazyB).equals("")) {
                database.atualizar_partida_visitante(partidaNova, Integer.parseInt(database.getTimeCod(crazyB)));
            }

            openPartida(partidaNova);
        }
    }

    private void listarLiga(int timeA, int timeB, int timeC, int timeD, int timeE, int timeF, int timeG, int timeH, int partidaA, int partidaB, int partidaC, int partidaD, int partidaE, int partidaF, int partidaG){
        bt_partida_1_1.setText(database.getTimeNome(timeA) + ' ' + database.getPartidaScore(partidaA,"MANDANTE_PLACAR") + " X " + database.getPartidaScore(partidaA,"VISITANTE_PLACAR") + ' ' + database.getTimeNome(timeB));
        bt_partida_1_2.setText(database.getTimeNome(timeC) + ' ' + database.getPartidaScore(partidaB,"MANDANTE_PLACAR") + " X " + database.getPartidaScore(partidaB,"VISITANTE_PLACAR") + ' ' + database.getTimeNome(timeD));
        bt_partida_1_3.setText(database.getTimeNome(timeE) + ' ' + database.getPartidaScore(partidaC,"MANDANTE_PLACAR") + " X " + database.getPartidaScore(partidaC,"VISITANTE_PLACAR") + ' ' + database.getTimeNome(timeF));
        bt_partida_1_4.setText(database.getTimeNome(timeG) + ' ' + database.getPartidaScore(partidaD,"MANDANTE_PLACAR") + " X " + database.getPartidaScore(partidaD,"VISITANTE_PLACAR") + ' ' + database.getTimeNome(timeH));

        if(database.getPartidaCod(partidaA)!="" && database.getPartidaCod(partidaB)!="") {
            avancar(partidaA, partidaB, timeA, timeB, timeC, timeD, partidaE, bt_partida_2_1);
        }

        if(database.getPartidaCod(partidaC)!="" && database.getPartidaCod(partidaD)!="") {
            avancar(partidaC, partidaD, timeE, timeF, timeG, timeH, partidaF, bt_partida_2_2);
        }

        if(database.getPartidaCod(partidaE)!="" && database.getPartidaCod(partidaF)!="") {
            int crazyA = Integer.parseInt(database.getTimeCodByNome(database.getPartidaMan(partidaE)));
            int crazyB = Integer.parseInt(database.getTimeCodByNome(database.getPartidaVis(partidaE)));
            int crazyC = Integer.parseInt(database.getTimeCodByNome(database.getPartidaMan(partidaF)));
            int crazyD = Integer.parseInt(database.getTimeCodByNome(database.getPartidaVis(partidaF)));

            avancar(partidaE,partidaF,crazyA,crazyB,crazyC,crazyD,partidaG,bt_partida_3_1);
        }
    }
}