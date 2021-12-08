package com.example.liga.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.liga.R;
import com.example.liga.banco.DadosOpenHelper;

import java.io.ByteArrayOutputStream;

public class Camera extends AppCompatActivity {
    private Button bt_camera;
    private ImageView iv_picture;
    private static final int REQUEST_IMAGE_CAPTURE = 101;
    private static final int CAMERA_REQUEST = 1888;
    private final int PERMISSAO_REQUEST = 2;
    private final int  GALERIA_IMAGENS = 2;
    private final int CAMERA = 1;
    private Context mContext;
    private int control;
    private int controlTime;
    Bitmap imageBitmap;
    DadosOpenHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = new DadosOpenHelper(this);
        Intent recebedora = getIntent();
        Bundle recebeDados = recebedora.getExtras();

        control = recebeDados.getInt("keyControl");
        controlTime = recebeDados.getInt("keyControlTime");
        setContentView(R.layout.activity_camera);
        iv_picture = (ImageView) findViewById(R.id.iv_picture);

        String imagem = database.getImagemTime(controlTime);

        if(imagem != null)
        {
            byte imagemEmByte [];
            imagemEmByte = Base64.decode(imagem, Base64.DEFAULT);
            Bitmap imagemDecodificada = BitmapFactory.decodeByteArray(imagemEmByte,0,imagemEmByte.length);
            iv_picture.setImageBitmap(imagemDecodificada);
        }

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String [] {Manifest.permission.CAMERA}, 0);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            }
            else
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSAO_REQUEST);
            }
        }

        findViewById(R.id.bt_camera).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                tirarFoto();
            }
        });

        findViewById(R.id.bt_galeria).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                abreGaleria();
            }
        });

        findViewById(R.id.bt_cadastrar_foto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                byte imagemBytes [] = outputStream.toByteArray();
                String fotoEmString = Base64.encodeToString(imagemBytes, Base64.DEFAULT);
                database.atualizar_time_foto(controlTime, fotoEmString);
                Toast.makeText(getApplicationContext(), "Imagem cadastrada", Toast.LENGTH_SHORT).show();
                openEquipe();
            }
        });
    }

    private void openEquipe(){
        Intent intent = new Intent(getApplicationContext(), Equipe.class);
        intent.putExtra("keyControl", control);
        intent.putExtra("keyControlTime", controlTime);
        startActivity(intent);
    }

    private void tirarFoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    private void abreGaleria(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALERIA_IMAGENS);
    }

    private void mostraFoto(String caminho)
    {
        Bitmap bitmap = (BitmapFactory.decodeFile(caminho));
        iv_picture.setImageBitmap(bitmap);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode == RESULT_OK && requestCode == CAMERA) {
           Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            iv_picture.setImageBitmap(imageBitmap);
        }
        if(resultCode == RESULT_OK && requestCode == GALERIA_IMAGENS)
        {
            Uri selectedImage = data.getData();
            String [] filePath  = { MediaStore.Images.Media.DATA };
            Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePath[0]);
            String picturePath = c.getString(columnIndex);
            mostraFoto(picturePath);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == PERMISSAO_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // A permissão foi concedida. Pode continuar
            }
            else
            {
                // A permissão foi negada. Precisa ver o que deve ser desabilitado
            }
            return;

        }
    }
}