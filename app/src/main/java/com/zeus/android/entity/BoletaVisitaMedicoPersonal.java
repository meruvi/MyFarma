package com.zeus.android.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class BoletaVisitaMedicoPersonal implements Serializable {

    @SerializedName("id_boleta")
    private int codBoleta;

    @SerializedName("firma")
    private byte[] firma;

    @SerializedName("latitud")
    private double latitud ;


    @SerializedName("longitud")
    private double longitud ;



    private java.util.Date fecha_visita ;

    @SerializedName("fechavisita")
    private String fechaVisitaFormato ;




    @SerializedName("cod_personal")
    private int codPersonal;

    @SerializedName("observacion")
    private String observacion;

    @SerializedName("detalle")
    private ArrayList<BoletaVisitaMedicoPersonalDetalle> detalle=new ArrayList<BoletaVisitaMedicoPersonalDetalle>();



    public int getCodBoleta() {
        return codBoleta;
    }

    public void setCodBoleta(int codBoleta) {
        this.codBoleta = codBoleta;
    }

    public byte[] getFirma() {
        return firma;
    }

    public void setFirma(byte[] firma) {
        this.firma = firma;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public java.util.Date getFecha_visita() {
        return fecha_visita;
    }

    public void setFecha_visita(java.util.Date fecha_visita) {
        this.fecha_visita = fecha_visita;
    }

    public int getCodPersonal() {
        return codPersonal;
    }

    public void setCodPersonal(int codPersonal) {
        this.codPersonal = codPersonal;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getFechaVisitaFormato() {
        return fechaVisitaFormato;
    }

    public void setFechaVisitaFormato(String fechaVisitaFormato) {
        this.fechaVisitaFormato = fechaVisitaFormato;
    }

    public ArrayList<BoletaVisitaMedicoPersonalDetalle> getDetalle() {
        return detalle;
    }

    public void setDetalle(ArrayList<BoletaVisitaMedicoPersonalDetalle> detalle) {
        this.detalle = detalle;
    }
}
