package com.zeus.android.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.zeus.android.util.Util;

import java.text.SimpleDateFormat;
import java.util.Collection;

public class PoliticaPreciosController extends SQLiteOpenHelper {

    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String DATA_BASE_NAME = "zeusMobil";
    private static final int DATA_BASE_VERSION = Util.DATA_BASE_VERSION_ZEUS;
    private static final String nombreTablaPolPriceFide = "com_politica_precio_corte";
    String sqlCreateTable = "create table com_politica_precio_corte(ID integer, COD_REGIONAL integer, COD_PROMOTOR_VENTA integer, COD_CLIENTE integer, ID_GRUPO_POLITICA_PRECIO integer, VENTA_BOLIVIANOS real, DESCUENTO_COMERCIAL real, DESCUENTO real, MONTO_ACCESO real, COD_MES integer, COD_GESTION integer, ESTADO integer, FECHA_SISTEMA string)";

    public PoliticaPreciosController(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }

    public void crearTablaPoliticaPrecios() {
        Log.i("---INICIANDO---", "-----VERIFICANDO TABLA POLITICA DE PRECIOS----");
        String selectQuery = " select count(*) as cantidad from sqlite_master where name='" + nombreTablaPolPriceFide + "' and type='table' ";
        Log.i("INFO", selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                int cantidad = c.getInt(c.getColumnIndex("cantidad"));
                if (cantidad == 0) {
                    Log.i("LA TABLA POLITICA :", "NO EXISTE");
                    Log.i("CREANDO TABLA POLITICA", sqlCreateTable);
                    db.execSQL(sqlCreateTable);
                    Log.i("CREANDO TABLA POLITICA", "TABLA CREADA....OK");
                } else {
                    Log.i("LA TABLA POLITICA :", "------ EXISTE");
                }
            } while (c.moveToNext());
            c.close();
            db.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*try{
            Log.e("GONZALO-CREAR", "CREANDO LA TABLA POLITICA DE PRECIOS");
            db.execSQL(sqlCreateTable);
            Log.e("GONZALO-CREADO", "CREANDO LA TABLA POLITICA DE PRECIOS");
        }catch (Exception e){
            Log.e("GONZALO-ERROR", ""+e.toString());
        }*/

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
//
//    public int actualizarPoliticaPrecios(Collection<PoliticaPrecio> listPolPrice) {
//
//        int cantidadInsertada = 0;
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        try {
//            db.beginTransaction();
//
//            String codigos[] = new String[0];
//            db.delete("com_politica_precio_corte", "", codigos);
//
//            for (PoliticaPrecio bean : listPolPrice) {
//                ContentValues values = new ContentValues();
//
//                values.put("ID", bean.getId());
//                values.put("COD_REGIONAL", bean.getCodRegional());
//                values.put("COD_PROMOTOR_VENTA",bean.getCodPromotorVenta());
//                values.put("COD_CLIENTE",bean.getCodCliente());
//                values.put("ID_GRUPO_POLITICA_PRECIO", bean.getIdgrupoPoliticaPrecio());
//                values.put("VENTA_BOLIVIANOS", bean.getVentaBolivianos());
//                values.put("DESCUENTO_COMERCIAL", bean.getDescuentoComercial());
//                values.put("DESCUENTO", bean.getDescuento());
//                values.put("MONTO_ACCESO", bean.getMontoAcceso());
//                values.put("COD_MES", bean.getCodMes());
//                values.put("COD_GESTION", bean.getCodGestion());
//                values.put("ESTADO", bean.getEstado());
//                values.put("FECHA_SISTEMA", bean.getFechaSistema());
//
//
//                db.insert("com_politica_precio_corte", null, values);
//
//                cantidadInsertada++;
//            }
//            db.setTransactionSuccessful();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//
//            db.endTransaction();
//            db.close();
//        }
//
//        return cantidadInsertada;
//
//    }
}