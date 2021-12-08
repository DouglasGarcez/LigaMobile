package com.example.liga.banco;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;

import com.example.liga.modal.Liga;
import com.example.liga.modal.Partida;
import com.example.liga.modal.Time;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;

public class DadosOpenHelper extends SQLiteOpenHelper{

    private Object Cursor;
    private Liga liga;
    private Partida partida;
    private Time time;

    public DadosOpenHelper (Context context)
    {
        super(context, "DADOS", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(cria_liga());
        db.execSQL(cria_time());
        db.execSQL(cria_partida());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        apaga();
        db.execSQL(cria_liga());
        db.execSQL(cria_time());
        db.execSQL(cria_partida());
    }

    public void apaga_registro(int id, String tabela, String campo){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + tabela + " WHERE " + campo + " = " + id);
        db.close();
    }

    public void createGoddamnTables()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(cria_liga());
        db.execSQL(cria_time());
        db.execSQL(cria_partida());
    }

    public void apaga () {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS LIGA");
        db.execSQL("DROP TABLE IF EXISTS PARTIDA");
        db.execSQL("DROP TABLE IF EXISTS TIME");

        /*StringBuilder sql2 = new StringBuilder();
        sql2.append("DROP TABLE IF EXISTS LIGA;");
        sql2.append("DROP TABLE IF EXISTS PARTIDA;");
        sql2.append("DROP TABLE IF EXISTS TIME;");
        return sql2.toString();*/
    }

    public static String cria_liga ()
    {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS LIGA( ");
        sql.append("LIGA_ID INTEGER PRIMARY KEY,");
        sql.append("LIGA_NUMERO INTEGER,");
        sql.append("LIGA_NOME VARCHAR(255) )");
        return sql.toString();
    }

    public static String cria_partida ()
    {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS PARTIDA(  ");
        sql.append("PARTIDA_ID INTEGER PRIMARY KEY,");
        sql.append("LIGA INTEGER,");
        sql.append("MANDANTE_ID INTEGER,");
        sql.append("VISITANTE_ID INTEGER,");
        sql.append("MANDANTE_PLACAR INTEGER,");
        sql.append("VISITANTE_PLACAR INTEGER,");
        sql.append("MANDANTE_PLACAR_P INTEGER,");
        sql.append("VISITANTE_PLACAR_ INTEGER,");
        sql.append("LOCAL_LAT FLOAT,");
        sql.append("LOCAL_LONG FLOAT,");
        sql.append("FOREIGN KEY(LIGA) REFERENCES LIGA(LIGA_ID),");
        sql.append("FOREIGN KEY(MANDANTE_ID) REFERENCES TIME(TIME_ID),");
        sql.append("FOREIGN KEY(VISITANTE_ID) REFERENCES TIME(TIME_ID)  ) ");
        return sql.toString();
    }

    public static String cria_time ()
    {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS TIME( ");
        sql.append("TIME_ID INTEGER PRIMARY KEY,");
        sql.append("TIME_IMAGEM VARCHAR(10000),");
        sql.append("TIME_NOME VARCHAR(255) ) ");
        return sql.toString();
    }

    public void insert_liga(int id, int numero, String nome)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        //db.execSQL("INSERT INTO LIGA(LIGA_ID,LIGA_NOME) VALUES("+ id +","+ nome +")");

        ContentValues contentValues = new ContentValues();
        contentValues.put("LIGA_ID",id);
        contentValues.put("LIGA_NUMERO",numero);
        contentValues.put("LIGA_NOME",nome);
        db.insert("LIGA",null,contentValues);

        /*StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO LIGA(LIGA_ID,LIGA_NOME)");
        sql.append("VALUES("+ id +","+ nome +")");
        return sql.toString();*/
    }

    public void insert_time(int id, Image imagem, String nome) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("TIME_ID",id);
        contentValues.put("TIME_IMAGEM", String.valueOf(imagem));
        contentValues.put("TIME_NOME",nome);
        db.insert("TIME",null,contentValues);

        /*StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TIME(TIME_ID,TIME_IMAGEM,TIME_NOME)");
        sql.append("VALUES("+ id +","+ imagem +","+ nome +")");
        return sql.toString();*/
    }

    public void insert_partida(int id, int liga, int mandanteP, int visitanteP, int mandantePP, int visitantePP, double lat, double longi) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("PARTIDA_ID",id);
        contentValues.put("LIGA", liga);
        //contentValues.put("MANDANTE_ID",mandante);
        //contentValues.put("VISITANTE_ID",visitante);
        contentValues.put("MANDANTE_PLACAR",mandanteP);
        contentValues.put("VISITANTE_PLACAR",visitanteP);
        contentValues.put("MANDANTE_PLACAR_P",mandantePP);
        contentValues.put("VISITANTE_PLACAR_",visitantePP);
        contentValues.put("LOCAL_LAT",lat);
        contentValues.put("LOCAL_LAT",longi);
        //contentValues.put("LOCAL_LAT",lat);
        //contentValues.put("LOCAL_LONG",longi);
        db.insert("PARTIDA",null,contentValues);
        db.close();
    }

    public void atualizar_liga_numero(int id, int numero){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("UPDATE LIGA SET LIGA_NUMERO = " + numero + " WHERE LIGA_ID = " + id);
        db.close();
    }

    public void atualizar_liga_nome(int id, String nome){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("UPDATE LIGA SET LIGA_NOME = '" + nome + "' WHERE LIGA_ID = " + id);
        db.close();
    }

    public void atualizar_time_nome(int id, String nome){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("UPDATE TIME SET TIME_NOME = '" + nome + "' WHERE TIME_ID = " + id);
        db.close();
    }
    public void atualizar_time_foto(int id, String imagemString)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("UPDATE TIME SET TIME_IMAGEM = '" + imagemString + "' WHERE TIME_ID = " + id);
        db.close();
    }

    public void atualizar_partida_mandante(int id, int mandante){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("UPDATE PARTIDA SET MANDANTE_ID = '" + mandante + "' WHERE PARTIDA_ID = " + id);
        db.close();
    }

    public void atualizar_partida_visitante(int id, int visitante){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("UPDATE PARTIDA SET VISITANTE_ID = '" + visitante + "' WHERE PARTIDA_ID = " + id);
        db.close();
    }

    public void atualizar_partida_placarMan(int id, String placar){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("UPDATE PARTIDA SET MANDANTE_PLACAR = '" + placar + "' WHERE PARTIDA_ID = " + id);
        db.close();
    }

    public void atualizar_partida_placarVis(int id, String placar){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("UPDATE PARTIDA SET VISITANTE_PLACAR = '" + placar + "' WHERE PARTIDA_ID = " + id);
        db.close();
    }

    public void atualizar_partida_placarManP(int id, String placar){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("UPDATE PARTIDA SET MANDANTE_PLACAR_P = '" + placar + "' WHERE PARTIDA_ID = " + id);
        db.close();
    }

    public void atualizar_partida_placarVisQ(int id, String placar){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("UPDATE PARTIDA SET VISITANTE_PLACAR_ = '" + placar + "' WHERE PARTIDA_ID = " + id);
        db.close();
    }

    public String getLigaNome(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        StringBuilder sql = new StringBuilder();
        String Code = "";
        sql.append("SELECT LIGA_NOME ");
        sql.append("FROM LIGA ");
        sql.append("WHERE LIGA_ID = " + id);
        sql.append(";");
        String ids = String.valueOf(id);
        Cursor cursor = db.rawQuery(sql.toString(), null);

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();
            Code = cursor.getString(cursor.getColumnIndex("LIGA_NOME"));
        }

        cursor.close();
        return Code;
    }

    public String getLigaCod(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        StringBuilder sql = new StringBuilder();
        //Cursor cursor = null;
        String Code = "";
        sql.append("SELECT LIGA_ID ");
        sql.append("FROM LIGA ");
        sql.append("WHERE LIGA_ID = " + id);
        sql.append(";");
        String ids = String.valueOf(id);
        Cursor cursor = db.rawQuery(sql.toString(), null);

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();
            Code = cursor.getString(cursor.getColumnIndex("LIGA_ID"));
        }

        cursor.close();
        return Code;
    }

    public String getImagemTime(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        StringBuilder sql = new StringBuilder();
        //Cursor cursor = null;
        String Code = "";
        sql.append("SELECT TIME_IMAGEM ");
        sql.append("FROM TIME ");
        sql.append("WHERE TIME_ID = " + id);
        sql.append(";");
        String ids = String.valueOf(id);
        Cursor cursor = db.rawQuery(sql.toString(), null);

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();
            Code = cursor.getString(cursor.getColumnIndex("TIME_IMAGEM"));
        }

        cursor.close();
        return Code;
    }

    public String getLigaNumero(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        StringBuilder sql = new StringBuilder();
        //Cursor cursor = null;
        String Code = "";
        sql.append("SELECT LIGA_NUMERO ");
        sql.append("FROM LIGA ");
        sql.append("WHERE LIGA_ID = " + id);
        sql.append(";");
        String ids = String.valueOf(id);
        Cursor cursor = db.rawQuery(sql.toString(), null);

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();
            Code = cursor.getString(cursor.getColumnIndex("LIGA_NUMERO"));
        }

        cursor.close();
        return Code;
    }

    public String getTimeNome(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        StringBuilder sql = new StringBuilder();
        //Cursor cursor = null;
        String Code = "";
        sql.append("SELECT TIME_NOME ");
        sql.append("FROM TIME ");
        sql.append("WHERE TIME_ID = " + id);
        sql.append(";");
        String ids = String.valueOf(id);
        Cursor cursor = db.rawQuery(sql.toString(), null);

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();
            Code = cursor.getString(cursor.getColumnIndex("TIME_NOME"));
        }

        cursor.close();
        return Code;
    }

    public String getTimeCodByNome(String nome){
        SQLiteDatabase db = this.getReadableDatabase();
        StringBuilder sql = new StringBuilder();
        //Cursor cursor = null;
        String Code = "";
        sql.append("SELECT TIME_ID ");
        sql.append("FROM TIME ");
        sql.append("WHERE TIME_NOME = " + "\"" + nome + "\"");
        sql.append(";");
        String ids = String.valueOf(nome);
        Cursor cursor = db.rawQuery(sql.toString(), null);

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();
            Code = cursor.getString(cursor.getColumnIndex("TIME_ID"));
        }

        cursor.close();
        return Code;
    }

    public String getTimeCod(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        StringBuilder sql = new StringBuilder();
        //Cursor cursor = null;
        String Code = "";
        sql.append("SELECT TIME_ID ");
        sql.append("FROM TIME ");
        sql.append("WHERE TIME_ID = " + id);
        sql.append(";");
        String ids = String.valueOf(id);
        Cursor cursor = db.rawQuery(sql.toString(), null);

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();
            Code = cursor.getString(cursor.getColumnIndex("TIME_ID"));
        }

        cursor.close();
        return Code;
    }

    public String getPartidaCod(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        StringBuilder sql = new StringBuilder();
        //Cursor cursor = null;
        String Code = "";
        sql.append("SELECT PARTIDA_ID ");
        sql.append("FROM PARTIDA ");
        sql.append("WHERE PARTIDA_ID = " + id);
        sql.append(";");
        String ids = String.valueOf(id);
        Cursor cursor = db.rawQuery(sql.toString(), null);

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();
            Code = cursor.getString(cursor.getColumnIndex("PARTIDA_ID"));
        }

        cursor.close();
        return Code;
    }

    public String getPartidaMan(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        StringBuilder sql = new StringBuilder();
        //Cursor cursor = null;
        String Code = "";
        sql.append("SELECT TIME_NOME ");
        sql.append("FROM TIME ");
        sql.append("JOIN PARTIDA ON MANDANTE_ID = TIME_ID ");
        sql.append("WHERE PARTIDA_ID = " + id);
        sql.append(";");
        String ids = String.valueOf(id);
        Cursor cursor = db.rawQuery(sql.toString(), null);

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();
            Code = cursor.getString(cursor.getColumnIndex("TIME_NOME"));
        }

        cursor.close();
        return Code;
    }

    public String getPartidaVis(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        StringBuilder sql = new StringBuilder();
        //Cursor cursor = null;
        String Code = "";
        sql.append("SELECT T.TIME_NOME ");
        sql.append("FROM TIME T ");
        sql.append("JOIN PARTIDA P ON P.VISITANTE_ID = T.TIME_ID ");
        sql.append("WHERE P.PARTIDA_ID = " + id);
        sql.append(";");
        String ids = String.valueOf(id);
        Cursor cursor = db.rawQuery(sql.toString(), null);

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();
            Code = cursor.getString(cursor.getColumnIndex("TIME_NOME"));
        }

        cursor.close();
        return Code;
    }

    public String getPartidaScore(int id, String coluna) {
        SQLiteDatabase db = this.getReadableDatabase();
        StringBuilder sql = new StringBuilder();
        //Cursor cursor = null;
        String Code = "";
        sql.append("SELECT " + coluna);
        sql.append(" FROM PARTIDA ");
        sql.append("WHERE PARTIDA_ID = " + id);
        sql.append(";");
        String ids = String.valueOf(id);
        Cursor cursor = db.rawQuery(sql.toString(), null);

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();
            Code = cursor.getString(cursor.getColumnIndex(coluna));
        }

        cursor.close();
        return Code;
    }

}

