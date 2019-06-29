package com.zeus.android.entity;

public class RegionalClienteUbicacion {
    double latitud;

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

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public int getCodAreaEmpresa() {
        return codAreaEmpresa;
    }

    public void setCodAreaEmpresa(int codAreaEmpresa) {
        this.codAreaEmpresa = codAreaEmpresa;
    }

    double longitud;
    int codCliente;
    int codAreaEmpresa;

    public RegionalClienteUbicacion(double latitud, double longitud,
                                    int codCliente, int codAreaEmpresa) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.codCliente = codCliente;
        this.codAreaEmpresa = codAreaEmpresa;

    }

    public RegionalClienteUbicacion(int codCliente, int codAreaEmpresa) {
        this.codCliente = codCliente;
        this.codAreaEmpresa = codAreaEmpresa;

    }
}
