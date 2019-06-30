package com.zeus.android.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BoletaVisitaMedicoPersonalDetalle implements Serializable {


    @SerializedName("id_boleta")
    private int codBoleta;


    @SerializedName("codigo_muestra")
    private String codigoMuestra;

    @SerializedName("muestra")
    private String nombreMuestra;


    @SerializedName("cantidad_muestra")
    private int cantidadMuestra;

    @SerializedName("material")
    private String nombreMaterial;

    @SerializedName("cantidad_material")
    private int cantidadMaterial;
    @SerializedName("tipo")
    private int tipoMaterial;


    public int getCodBoleta() {
        return codBoleta;
    }

    public void setCodBoleta(int codBoleta) {
        this.codBoleta = codBoleta;
    }

    public String getCodigoMuestra() {
        return codigoMuestra;
    }

    public void setCodigoMuestra(String codigoMuestra) {
        this.codigoMuestra = codigoMuestra;
    }

    public String getNombreMuestra() {
        return nombreMuestra;
    }

    public void setNombreMuestra(String nombreMuestra) {
        this.nombreMuestra = nombreMuestra;
    }

    public int getCantidadMuestra() {
        return cantidadMuestra;
    }

    public void setCantidadMuestra(int cantidadMuestra) {
        this.cantidadMuestra = cantidadMuestra;
    }

    public String getNombreMaterial() {
        return nombreMaterial;
    }

    public void setNombreMaterial(String nombreMaterial) {
        this.nombreMaterial = nombreMaterial;
    }

    public int getCantidadMaterial() {
        return cantidadMaterial;
    }

    public void setCantidadMaterial(int cantidadMaterial) {
        this.cantidadMaterial = cantidadMaterial;
    }

    public int getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(int tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }
}
