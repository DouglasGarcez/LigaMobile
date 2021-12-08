package com.example.liga.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.liga.R;
import com.example.liga.activity.liga;
import com.example.liga.banco.DadosOpenHelper;

public class MainActivity extends AppCompatActivity {

    private Button bt_liga_1;
    private Button bt_liga_2;
    private Button bt_liga_3;

    DadosOpenHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new DadosOpenHelper(this);

        database.createGoddamnTables();

        bt_liga_1 = (Button) findViewById(R.id.bt_liga_1);
        bt_liga_2 = (Button) findViewById(R.id.bt_liga_2);
        bt_liga_3 = (Button) findViewById(R.id.bt_liga_3);

        listarLiga();

        bt_liga_1 = (Button) findViewById(R.id.bt_liga_1);
        bt_liga_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comportamentoBotoes(1,"Liga A");
            }
        });

        bt_liga_2 = (Button) findViewById(R.id.bt_liga_2);
        bt_liga_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comportamentoBotoes(2,"Liga B");
            }
        });

        bt_liga_3 = (Button) findViewById(R.id.bt_liga_3);
        bt_liga_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comportamentoBotoes(3,"Liga C");
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        listarLiga();
    }

    private void listarLiga(){
        bt_liga_1.setText(database.getLigaNome(1));
        bt_liga_2.setText(database.getLigaNome(2));
        bt_liga_3.setText(database.getLigaNome(3));
    }

    private void comportamentoBotoes(int id, String liga){
        if(database.getLigaCod(id)=="") {
            database.insert_liga(id, 2,liga);
        }
        openLiga(id);
    }

    public void openLiga(int control) {
        Intent intent = new Intent(getApplicationContext(), liga.class);
        //intent.putExtra("keyLiga",codigo);
        intent.putExtra("keyControl", control);
        startActivity(intent);
    }
}