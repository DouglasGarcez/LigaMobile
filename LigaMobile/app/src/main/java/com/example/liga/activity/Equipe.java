package com.example.liga.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liga.R;
import com.example.liga.activity.Camera;
import com.example.liga.banco.DadosOpenHelper;

public class Equipe extends AppCompatActivity {

    private Button bt_foto;
    private TextView tv_time_x;
    private EditText et_time_nome;
    private Button bt_atualizar_time;
    Bitmap imageBitmap;
    private ImageView iv_picture;
    private int control;
    private int controlTime;

    DadosOpenHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipe);

        database = new DadosOpenHelper(this);
        Intent recebedora = getIntent();
        Bundle recebeDados = recebedora.getExtras();
        control = recebeDados.getInt("keyControl");
        controlTime = recebeDados.getInt("keyControlTime");
        tv_time_x = findViewById(R.id.tv_time_x);
        et_time_nome = findViewById(R.id.et_time_nome);
        bt_atualizar_time = findViewById(R.id.bt_atualizar_time);
        bt_foto = findViewById(R.id.bt_foto);
        tv_time_x.setText(database.getTimeNome(controlTime));
        //et_time_nome.setText(database.getTimeNome(controlTime));

        iv_picture = (ImageView) findViewById(R.id.iv_picture_equipe);

        String imagem = database.getImagemTime(controlTime);

        if(imagem != null)
        {
            byte imagemEmByte [];
            imagemEmByte = Base64.decode(imagem, Base64.DEFAULT);
            Bitmap imagemDecodificada = BitmapFactory.decodeByteArray(imagemEmByte,0,imagemEmByte.length);
            iv_picture.setImageBitmap(imagemDecodificada);
        }

        bt_atualizar_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String novoNome;

                if(!et_time_nome.getText().toString().matches("")) {
                    novoNome = String.valueOf(et_time_nome.getText());
                    database.atualizar_time_nome(controlTime, novoNome);
                    et_time_nome.setText(novoNome);
                    tv_time_x.setText(novoNome);
                }
            }
        });

        bt_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFoto();
            }
        });
    }

    public void openFoto() {
        Intent intent = new Intent(getApplicationContext(), Camera.class);
        intent.putExtra("keyControl", control);
        intent.putExtra("keyControlTime", controlTime);
        startActivity(intent);
    }
}