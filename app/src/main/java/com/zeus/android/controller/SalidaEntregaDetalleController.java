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

public class SalidaEntregaDetalleController extends SQLiteOpenHelper {

    String TAG = SalidaEntregaDetalleController.class.getName();
    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private static final String DATA_BASE_NAME = "zeusMobil";
    private static final int DATA_BASE_VERSION = Util.DATA_BASE_VERSION_ZEUS;
    private static String nombreTabla = "salida_entrega_detalle";
    String sqlCreateTable = "CREATE TABLE "  + nombreTabla +
            " (" +
            "        cod_salidaventa           INTEGER,"+
            "        fecha_registro            DATETIME,"+
            "        cod_tipo_accion           INTEGER,"+
            "        cod_tipo_motivo           INTEGER,"+
            "        observacion               TEXT,"+
            "        cod_personal              INTEGER,"+
            "        PRIMARY KEY (cod_salidaventa, fecha_registro)"+
            ")";

    public SalidaEntregaDetalleController(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }

//    public void registroSalidaEntregaDetalle(SalidaEntregaDetalle objSalidaEntregaDetalle) {
//        Log.i(TAG, "registro salida_entrega_detalle:" + objSalidaEntregaDetalle);
//        SQLiteDatabase dbWrite = this.getWritableDatabase();
//        try {
//            dbWrite.beginTransaction();
//            ContentValues values = new ContentValues();
//            values.put("cod_salidaventa", objSalidaEntregaDetalle.getCodSalidaVenta());
//            values.put("fecha_registro", f.format(objSalidaEntregaDetalle.getFechaRegisto()));
//            values.put("cod_tipo_accion", objSalidaEntregaDetalle.getCodTipoAccion());
//            values.put("cod_tipo_motivo", objSalidaEntregaDetalle.getCodTipoMotivo());
//            values.put("observacion", objSalidaEntregaDetalle.getObservacion());
//            values.put("cod_personal", objSalidaEntregaDetalle.getCodPersonal());
//            dbWrite.insert(nombreTabla, null, values);
//            dbWrite.setTransactionSuccessful();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            dbWrite.endTransaction();
//        }
//        dbWrite.close();
//    }
//
    public void creandoTabla() {
        Log.i("---INICIANDO---", "-----VERIFICANDO TABLA----");
        String selectQuery = " select count(*) as cantidad from sqlite_master where name='" + nombreTabla + "' and type='table' ";
        Log.i("INFO", selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                int cantidad = c.getInt(c.getColumnIndex("cantidad"));
                if (cantidad == 0) {
                    Log.i(TAG, "LA TABLA  :.............NO EXISTE");
                    Log.i(TAG,"CREANDO TABLA " + nombreTabla + ".........." + sqlCreateTable);
                    db.execSQL(sqlCreateTable);
                } else {
                    Log.i(TAG, "LA TABLA " + nombreTabla + " :............EXISTE");
                }
            } while (c.moveToNext());
            c.close();
            db.close();
        }
    }
//
//    public ArrayList<SalidaEntregaDetalle> obtenerListaSalidaEntregaDetalle() {
//        String sql = " select * from " + nombreTabla;
//        Log.i("INFO", sql);
//        SQLiteDatabase db = this.getReadableDatabase();
//        ArrayList<SalidaEntregaDetalle> lstSalidaEntregaDetalle = new ArrayList<SalidaEntregaDetalle>();
//        Cursor c = db.rawQuery(sql, null);
//        if (c.moveToFirst()) {
//            do {
//                SalidaEntregaDetalle objSalidaEntregaDetalle = new SalidaEntregaDetalle();
//                objSalidaEntregaDetalle.setCodSalidaVenta(c.getInt(c.getColumnIndex("cod_salidaventa")));
//                objSalidaEntregaDetalle.setCodTipoAccion(c.getInt(c.getColumnIndex("cod_tipo_accion")));
//                objSalidaEntregaDetalle.setCodTipoMotivo(c.getInt(c.getColumnIndex("cod_tipo_motivo")));
//                objSalidaEntregaDetalle.setObservacion(c.getString(c.getColumnIndex("observacion")));
//                objSalidaEntregaDetalle.setCodPersonal(c.getInt(c.getColumnIndex("cod_personal")));
//                try {
//                    objSalidaEntregaDetalle.setFechaRegisto(f.parse(c.getString(c.getColumnIndex("fecha_registro"))));
//                } catch (ParseException e1) {
//                    e1.printStackTrace();
//                }
//                lstSalidaEntregaDetalle.add(objSalidaEntregaDetalle);
//            } while (c.moveToNext());
//            c.close();
//        }
//        db.close();
//        return lstSalidaEntregaDetalle;
//    }
//
///*    public ArrayList<SalidaEntregaDetalle> getListadoDesviacion(int codSalidaVenta) {
//        String sql = " select * from " + nombreTabla;
//        if (codSalidaVenta != 0) {
//            sql += " and  cod_salidaventa=" + codSalidaVenta;
//        }
//        Log.i("INFO", sql);
//        SQLiteDatabase db = this.getReadableDatabase();
//        ArrayList<SalidaEntregaDetalle> listado = new ArrayList<SalidaEntregaDetalle>();
//        Cursor c = db.rawQuery(sql, null);
//        if (c.moveToFirst()) {
//            do {
//                SalidaEntregaDetalle objSalidaEntregaDetalle = new SalidaEntregaDetalle();
//                objSalidaEntregaDetalle.setCodSalidaVenta(c.getInt(c.getColumnIndex("cod_salidaventa")));
//                objSalidaEntregaDetalle.setCodTipoAccion(c.getInt(c.getColumnIndex("cod_tipo_accion")));
//                objSalidaEntregaDetalle.setObservacion(c.getString(c.getColumnIndex("observacion")));
//                objSalidaEntregaDetalle.setCodPersonal(c.getInt(c.getColumnIndex("cod_personal")));
//                try {
//                    objSalidaEntregaDetalle.setFechaRegisto(f.parse(c.getString(c.getColumnIndex("fecha_registro"))));
//                } catch (ParseException e1) {
//                    e1.printStackTrace();
//                }
//                listado.add(objSalidaEntregaDetalle);
//            } while (c.moveToNext());
//            c.close();
//        }
//        db.close();
//        return listado;
//    }*/
//
//    public void eliminarRegistros() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        try {
//            db.beginTransaction();
//            db.delete(nombreTabla, null, null);
//            db.setTransactionSuccessful();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            db.endTransaction();
//            db.close();
//        }
//    }

    @Override
    public void onCreate(SQLiteDatabase arg0) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
    }


}