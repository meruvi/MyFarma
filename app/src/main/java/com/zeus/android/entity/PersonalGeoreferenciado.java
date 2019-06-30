package com.zeus.android.entity;

import java.io.Serializable;
import java.util.Date;

public class PersonalGeoreferenciado implements Serializable {


    int codAreaEmpresa;
    int codPersonal;
    java.util.Date fechaGeoreferencia;
    double latitud;
    double longitud;

    private String correoPersonal;
    private String deviceId;


    private int versionGoogle;
    private int versionZeus;
    //device_id

    public int getCodAreaEmpresa() {
        return codAreaEmpresa;
    }

    public void setCodAreaEmpresa(int codAreaEmpresa) {
        this.codAreaEmpresa = codAreaEmpresa;
    }

    public int getCodPersonal() {
        return codPersonal;
    }

    public void setCodPersonal(int codPersonal) {
        this.codPersonal = codPersonal;
    }

    public Date getFechaGeoreferencia() {
        return fechaGeoreferencia;
    }

    public void setFechaGeoreferencia(Date fechaGeoreferencia) {
        this.fechaGeoreferencia = fechaGeoreferencia;
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


    public String getCorreoPersonal() {
        return correoPersonal;
    }

    public void setCorreoPersonal(String correoPersonal) {
        this.correoPersonal = correoPersonal;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * @return the versionGoogle
     */
    public int getVersionGoogle() {
        return versionGoogle;
    }

    /**
     * @param versionGoogle the versionGoogle to set
     */
    public void setVersionGoogle(int versionGoogle) {
        this.versionGoogle = versionGoogle;
    }

    /**
     * @return the versionZeus
     */
    public int getVersionZeus() {
        return versionZeus;
    }

    /**
     * @param versionZeus the versionZeus to set
     */
    public void setVersionZeus(int versionZeus) {
        this.versionZeus = versionZeus;
    }



}
