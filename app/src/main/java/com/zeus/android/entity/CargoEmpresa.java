package com.zeus.android.entity;

public enum CargoEmpresa {

    PROMOTOR_VENTA(1124), DESPACHADOR(1126),COBRADOR(1255),VISITADOR(1123),JEFE_REGIONAL(1120),VISITADOR_FARMACIAS(1248);

    public int codCargo;

    CargoEmpresa(int codCargp){
        this.codCargo = codCargp;
    }
}
