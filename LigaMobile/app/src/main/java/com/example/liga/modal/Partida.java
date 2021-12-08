package com.example.liga.modal;

public class Partida {
    private int partida_id;
    private int liga;
    private int mandante_id;
    private int visitante_id;
    private int mandante_placar;
    private int visitante_placar;
    private int mandante_placar_p;

    public int getPartida_id() {
        return partida_id;
    }

    public void setPartida_id(int partida_id) {
        this.partida_id = partida_id;
    }

    public int getLiga() {
        return liga;
    }

    public void setLiga(int liga) {
        this.liga = liga;
    }

    public int getMandante_id() {
        return mandante_id;
    }

    public void setMandante_id(int mandante_id) {
        this.mandante_id = mandante_id;
    }

    public int getVisitante_id() {
        return visitante_id;
    }

    public void setVisitante_id(int visitante_id) {
        this.visitante_id = visitante_id;
    }

    public int getMandante_placar() {
        return mandante_placar;
    }

    public void setMandante_placar(int mandante_placar) {
        this.mandante_placar = mandante_placar;
    }

    public int getVisitante_placar() {
        return visitante_placar;
    }

    public void setVisitante_placar(int visitante_placar) {
        this.visitante_placar = visitante_placar;
    }

    public int getMandante_placar_p() {
        return mandante_placar_p;
    }

    public void setMandante_placar_p(int mandante_placar_p) {
        this.mandante_placar_p = mandante_placar_p;
    }

    public float getLocal_lat() {
        return local_lat;
    }

    public void setLocal_lat(float local_lat) {
        this.local_lat = local_lat;
    }

    public int getLocal_long() {
        return local_long;
    }

    public void setLocal_long(int local_long) {
        this.local_long = local_long;
    }

    private float local_lat;
    private int local_long;


}
