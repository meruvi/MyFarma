package com.zeus.android.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.zeus.android.util.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SalidaEntregaController extends SQLiteOpenHelper {

    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String DATA_BASE_NAME = "zeusMobil";
    private static final int DATA_BASE_VERSION = Util.DATA_BASE_VERSION_ZEUS;

    private static final String nombreTablaSalida = "salida_entrega";
    String sqlCreateTable = "CREATE TABLE salida_entrega " +
            "  ( " +
            "     cod_salida               INTEGER, " +
            "     cod_estado_entregasalida INTEGER, " +
            "     obs_entrega_salida       TEXT, " +
            "     fecha_entrega_salida     DATETIME, " +
            "     cod_area                 INTEGER, " +
            "     cod_cliente_salida       INTEGER, " +
            "     nro_documento_venta      INTEGER, " +
            "     fecha_salida             DATETIME, " +
            "     monto_total_venta        REAL " +
            "  ) ";
//
    public SalidaEntregaController(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }
//
    public void crearColumnaTabla(String nombreColumna, String nombreTabla, String tipoColumna) {
        if (!verificarColumnTabla(nombreColumna, nombreTabla)) {
            SQLiteDatabase db = this.getReadableDatabase();
            String sqlColumn = " ALTER TABLE " + nombreTabla + " ADD COLUMN " + nombreColumna + " " + tipoColumna + " ";
            db.execSQL(sqlColumn);
            db.close();
        }
    }
//
    public boolean verificarColumnTabla(String nombreColumnaVerificar, String nombreTabla) {
        String selectQuery = "PRAGMA table_info(" + nombreTabla + ")";
        Log.i("INFO", selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                String nombreColumna = c.getString(c.getColumnIndex("name"));
                if (nombreColumnaVerificar.equals(nombreColumna)) {
                    return true;
                }
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return false;
    }
//
//    public void registroSalidaEntrega(List<SalidaEntrega> listado) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        try {
//            db.beginTransaction();
//            for (SalidaEntrega salidaVenta : listado) {
//                ContentValues values = new ContentValues();
//                values.put("cod_salida", salidaVenta.getCodSalida());
//                values.put("cod_estado_entregasalida", salidaVenta.getCodEstadoEntregaSalida());
//                values.put("obs_entrega_salida", salidaVenta.getObsEntregaSalida());
//                values.put("fecha_entrega_salida", f.format(new java.util.Date()));
//                values.put("cod_area", salidaVenta.getCodAreaEmpresa());
//                values.put("cod_cliente_salida", salidaVenta.getCodCliente());
//                values.put("nro_documento_venta", salidaVenta.getNroDocumento());
//                values.put("fecha_salida", f.format(salidaVenta.getFechaSalidaVenta()));
//                values.put("monto_total_venta", salidaVenta.getMontoTotalVenta());
//                db.insert("salida_entrega", null, values);
//            }
//            db.setTransactionSuccessful();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            db.endTransaction();
//            db.close();
//        }
//    }
//
//    public void actualizarSalidaEntrega(Collection<SalidaEntrega> listado) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        try {
//            db.beginTransaction();
//            for (SalidaEntrega salidaVenta : listado) {
//                ContentValues values = new ContentValues();
//                values.put("cod_estado_entregasalida", EstadoEntregaSalida.SINCRONIZADO.codEstadoEntrega);
//                db.update("salida_entrega", values, "cod_salida=" + salidaVenta.getCodSalida(), null);
//            }
//            db.setTransactionSuccessful();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            db.endTransaction();
//            db.close();
//        }
//    }
//
//    public void registroSalidaEntrega(SalidaEntrega objSalidaEntrega) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        try {
//            db.beginTransaction();
//            ContentValues values = new ContentValues();
//
//            values.put("cod_salida", objSalidaEntrega.getCodSalida());
//            values.put("cod_estado_entregasalida", objSalidaEntrega.getCodEstadoEntregaSalida());
//            values.put("obs_entrega_salida", objSalidaEntrega.getObsEntregaSalida());
//            values.put("fecha_entrega_salida", f.format(new java.util.Date()));
//            values.put("cod_area", objSalidaEntrega.getCodAreaEmpresa());
//            values.put("cod_cliente_salida", objSalidaEntrega.getCodCliente());
//            values.put("nro_documento_venta", objSalidaEntrega.getNroDocumento());
//            values.put("fecha_salida", f.format(objSalidaEntrega.getFechaSalidaVenta()));
//            values.put("monto_total_venta", objSalidaEntrega.getMontoTotalVenta());
//            values.put("cod_personal", objSalidaEntrega.getCodPersonal());
//            values.put("cod_estado_entregado_ultimo", objSalidaEntrega.getCodEstadoEntregadoUltimo());
//
//            db.insert("salida_entrega", null, values);
//            db.setTransactionSuccessful();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            db.endTransaction();
//            db.close();
//        }
//    }
//
//    public void registroActualizacionSalidaEntrega(SalidaEntrega objSalidaEntrega) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        try {
//            db.beginTransaction();
//            ContentValues values = new ContentValues();
//
//            String selectQuery = "SELECT 1 " +
//                    "FROM   salida_entrega " +
//                    "WHERE  cod_salida = "  + objSalidaEntrega.getCodSalida() ;
//            Log.i("INFO", selectQuery);
//
//            values.put("cod_salida", objSalidaEntrega.getCodSalida());
//            values.put("cod_estado_entregasalida", objSalidaEntrega.getCodEstadoEntregaSalida());
//            values.put("obs_entrega_salida", objSalidaEntrega.getObsEntregaSalida());
//            values.put("fecha_entrega_salida", f.format(new java.util.Date()));
//            values.put("cod_area", objSalidaEntrega.getCodAreaEmpresa());
//            values.put("cod_cliente_salida", objSalidaEntrega.getCodCliente());
//            values.put("nro_documento_venta", objSalidaEntrega.getNroDocumento());
//            values.put("fecha_salida", f.format(objSalidaEntrega.getFechaSalidaVenta()));
//            values.put("monto_total_venta", objSalidaEntrega.getMontoTotalVenta());
//            values.put("cod_personal", objSalidaEntrega.getCodPersonal());
//            values.put("cod_estado_entregado_ultimo", objSalidaEntrega.getCodEstadoEntregadoUltimo());
//
//            Cursor c = db.rawQuery(selectQuery, null);
//            if (c.getCount() > 0){
//                // Realizamos un UPDATE
//                db.update("salida_entrega", values, "cod_salida=" + objSalidaEntrega.getCodSalida(), null);
//            } else{
//                //Relizamos un INSERT
//                db.insert("salida_entrega", null, values);
//            }
//            db.setTransactionSuccessful();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            db.endTransaction();
//            db.close();
//        }
//    }
    public void creandoTablaSalida() {
        Log.i("---INICIANDO---", "-----VERIFICANDO TABLA SALIDA ENTREGA----");

        String selectQuery = " select count(*) as cantidad from sqlite_master where name='" + nombreTablaSalida + "' and type='table' ";
        Log.i("INFO", selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                int cantidad = c.getInt(c.getColumnIndex("cantidad"));
                if (cantidad == 0) {
                    db.execSQL(sqlCreateTable);
                } else {
                }
            } while (c.moveToNext());
            c.close();
            db.close();
        }
    }
//
//    public ArrayList<SalidaEntrega> getListadoSalidaEntrega() {
//        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        ArrayList<SalidaEntrega> lista = new ArrayList<SalidaEntrega>();
//        String selectQuery = "SELECT cod_salida, " +
//                "       cod_estado_entregasalida, " +
//                "       obs_entrega_salida, " +
//                "       fecha_entrega_salida, " +
//                "       cod_area, " +
//                "       cod_cliente_salida, " +
//                "       nro_documento_venta, " +
//                "       cod_personal, " +
//                "       cod_estado_entregado_ultimo " +
//                "FROM   salida_entrega " +
//                "WHERE  cod_estado_entregasalida = 1 ";
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//                SalidaEntrega s = new SalidaEntrega();
//                s.setCodPersonal(c.getInt(c.getColumnIndex("cod_personal")));
//                s.setCodSalida(c.getInt(c.getColumnIndex("cod_salida")));
//                s.setCodEstadoEntregaSalida(c.getInt(c.getColumnIndex("cod_estado_entregasalida")));
//                s.setObsEntregaSalida(c.getString(c.getColumnIndex("obs_entrega_salida")));
//                try {
//                    s.setFechaEntregaSalida(f.parse(c.getString(c.getColumnIndex("fecha_entrega_salida"))));
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                s.setCodAreaEmpresa(c.getInt(c.getColumnIndex("cod_area")));
//                s.setCodCliente(c.getInt(c.getColumnIndex("cod_cliente_salida")));
//                s.setNroDocumento(c.getInt(c.getColumnIndex("nro_documento_venta")));
//                s.setCodEstadoEntregadoUltimo(c.getInt(c.getColumnIndex("cod_estado_entregado_ultimo")));
//                lista.add(s);
//            } while (c.moveToNext());
//            c.close();
//            db.close();
//        }
//        return lista;
//    }
//
//    public ArrayList<SalidaEntrega> getListadoSalidaDespacho() {
//        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        ArrayList<SalidaEntrega> lista = new ArrayList<SalidaEntrega>();
//        String selectQuery = " SELECT cod_salida,cod_estado_entregasalida,obs_entrega_salida,fecha_entrega_salida,cod_area,cod_cliente_salida,nro_documento_venta,(select nombre_cliente from clientes c where c.cod_cliente=s.cod_cliente_salida)  as nombre_cliente" +
//                "  FROM salida_entrega s ";
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//                SalidaEntrega s = new SalidaEntrega();
//                s.setCodSalida(c.getInt(c.getColumnIndex("cod_salida")));
//                s.setCodEstadoEntregaSalida(c.getInt(c.getColumnIndex("cod_estado_entregasalida")));
//                s.setObsEntregaSalida(c.getString(c.getColumnIndex("obs_entrega_salida")));
//                try {
//                    s.setFechaEntregaSalida(f.parse(c.getString(c.getColumnIndex("fecha_entrega_salida"))));
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                s.setCodAreaEmpresa(c.getInt(c.getColumnIndex("cod_area")));
//                s.setCodCliente(c.getInt(c.getColumnIndex("cod_cliente_salida")));
//                s.setNroDocumento(c.getInt(c.getColumnIndex("nro_documento_venta")));
//                s.setNombreCliente(c.getString(c.getColumnIndex("nombre_cliente")));
//                lista.add(s);
//            } while (c.moveToNext());
//            c.close();
//            db.close();
//        }
//        return lista;
//    }

    @Override
    public void onCreate(SQLiteDatabase arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
    }
}
