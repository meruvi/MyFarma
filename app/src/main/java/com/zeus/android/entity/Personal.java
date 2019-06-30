package com.zeus.android.entity;

import java.io.Serializable;

public class Personal implements Serializable {



    int codPersonal;
    private int codCargo;
    public int getCodCargo() {
        return codCargo;
    }
    public void setCodCargo(int codCargo) {
        this.codCargo = codCargo;
    }
    public int getCodPersonal() {
        return codPersonal;
    }
    public void setCodPersonal(int codPersonal) {
        this.codPersonal = codPersonal;
    }
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public String getContraseniaUsuario() {
        return contraseniaUsuario;
    }
    public void setContraseniaUsuario(String contraseniaUsuario) {
        this.contraseniaUsuario = contraseniaUsuario;
    }
    public String getNombresPersonal() {
        nombresPersonal=(nombresPersonal==null)?"":nombresPersonal;
        return nombresPersonal;
    }
    public void setNombresPersonal(String nombresPersonal) {
        this.nombresPersonal = nombresPersonal;
    }
    public String getApPaternoPersonal() {
        apPaternoPersonal=(apPaternoPersonal==null)?"":apPaternoPersonal;
        return apPaternoPersonal;
    }
    public void setApPaternoPersonal(String apPaternoPersonal) {
        this.apPaternoPersonal = apPaternoPersonal;
    }
    public String getApMaternoPersonal() {
        apMaternoPersonal=(apMaternoPersonal==null)?"":apMaternoPersonal;
        return apMaternoPersonal;
    }
    public void setApMaternoPersonal(String apMaternoPersonal) {
        this.apMaternoPersonal = apMaternoPersonal;
    }
    public int getCodAreaEmpresa() {
        return codAreaEmpresa;
    }
    public void setCodAreaEmpresa(int codAreaEmpresa) {
        this.codAreaEmpresa = codAreaEmpresa;
    }
    public String getNombreAreaEmpresa() {
        return nombreAreaEmpresa;
    }
    public void setNombreAreaEmpresa(String nombreAreaEmpresa) {
        this.nombreAreaEmpresa = nombreAreaEmpresa;
    }
    String  nombreUsuario;
    String  contraseniaUsuario;
    String nombresPersonal;
    String apPaternoPersonal;
    String apMaternoPersonal;
    int codAreaEmpresa;
    String nombreAreaEmpresa;


}
