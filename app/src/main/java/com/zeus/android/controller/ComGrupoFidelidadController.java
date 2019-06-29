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

public class ComGrupoFidelidadController extends SQLiteOpenHelper {
    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String DATA_BASE_NAME = "zeusMobil";
    private static final int DATA_BASE_VERSION = Util.DATA_BASE_VERSION_ZEUS;
    private static final String nombreTablaComGrupoFidelidad = "com_grupo_fidelidad";
    String sqlCreateTable = "create table com_grupo_fidelidad(ID integer, ID_GRUPO_POLITICA_PRECIO integer, COD_LINEA_VENTA integer, COD_LINEA_MKT integer)";

    public ComGrupoFidelidadController(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }


    public void crearTablaComGrupoFidelidad() {
        Log.i("---INICIANDO---", "-----VERIFICANDO TABLA COM GRUPO FIDELIDAD ----");
        String selectQuery = " select count(*) as cantidad from sqlite_master where name='" + nombreTablaComGrupoFidelidad + "' and type='table' ";
        Log.i("INFO", selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                int cantidad = c.getInt(c.getColumnIndex("cantidad"));
                if (cantidad == 0) {
                    Log.i("LA TABLA GRUPOFIDE:", "NO EXISTE");
                    Log.i("CREANDO TABLA GRUPOFIDE", sqlCreateTable);
                    db.execSQL(sqlCreateTable);
                    Log.i("CREANDO TABLA GRUPOFIDE", "TABLA CREADA....OK");
                } else {
                    Log.i("LA TABLA GRUPOFIDE :", "------ EXISTE");
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
//    public int actualizarComGrupoFidelidad(Collection<ComGrupoFidelidad> listComGrupoFidelidad) {
//
//        int cantidadInsertada = 0;
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        try {
//            db.beginTransaction();
//
//            String codigos[] = new String[0];
//            db.delete("com_grupo_fidelidad", "", codigos);
//
//            for (ComGrupoFidelidad bean : listComGrupoFidelidad) {
//                ContentValues values = new ContentValues();
//
//                values.put("ID", bean.getId());
//                values.put("ID_GRUPO_POLITICA_PRECIO",bean.getIdGrupoPoliticaPrecio());
//                values.put("COD_LINEA_VENTA", bean.getCodLineaVenta());
//                values.put("COD_LINEA_MKT", bean.getCodLineaMkt());
//
//                db.insert("com_grupo_fidelidad", null, values);
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
//
//    public ComGrupoFidelidad obtenerCodGrupoFide(int codLineaMkt) {
//        String sql = " select * from com_grupo_fidelidad where cod_linea_mkt = " + codLineaMkt;
//        Log.i("INFO", sql);
//        SQLiteDatabase db = this.getReadableDatabase();
//        ComGrupoFidelidad comGrupoFidelidad = new ComGrupoFidelidad();
//        Cursor c = db.rawQuery(sql, null);
//        if (c.moveToFirst()) {
//            comGrupoFidelidad.setId(c.getInt(c.getColumnIndex("ID")));
//            comGrupoFidelidad.setIdGrupoPoliticaPrecio(c.getInt(c.getColumnIndex("ID_GRUPO_POLITICA_PRECIO")));
//            comGrupoFidelidad.setCodLineaVenta(c.getInt(c.getColumnIndex("COD_LINEA_VENTA")));
//            comGrupoFidelidad.setCodLineaMkt(c.getInt(c.getColumnIndex("COD_LINEA_MKT")));
//            c.close();
//        }
//        db.close();
//
//        Log.e("GRUPO_G: ", "" + comGrupoFidelidad);
//        Log.e("GRUPO_G: ", "" + comGrupoFidelidad.getId());
//        Log.e("GRUPO_G: ", "" + comGrupoFidelidad.getIdGrupoPoliticaPrecio());
//        Log.e("GRUPO_G: ", "" + comGrupoFidelidad.getCodLineaVenta());
//        Log.e("GRUPO_G: ", "" + comGrupoFidelidad.getCodLineaMkt());
//
//        return comGrupoFidelidad;
//    }
//
//    public ComGrupoFidelidad obtenerCodLineaVenta(int igpp) {
//        String sql = " select * from com_grupo_fidelidad where id_grupo_politica_precio = " + igpp;
//        Log.i("INFO", sql);
//        SQLiteDatabase db = this.getReadableDatabase();
//        ComGrupoFidelidad comGrupoFidelidad = new ComGrupoFidelidad();
//        Cursor c = db.rawQuery(sql, null);
//        if (c.moveToFirst()) {
//            comGrupoFidelidad.setId(c.getInt(c.getColumnIndex("ID")));
//            comGrupoFidelidad.setIdGrupoPoliticaPrecio(c.getInt(c.getColumnIndex("ID_GRUPO_POLITICA_PRECIO")));
//            comGrupoFidelidad.setCodLineaVenta(c.getInt(c.getColumnIndex("COD_LINEA_VENTA")));
//            comGrupoFidelidad.setCodLineaMkt(c.getInt(c.getColumnIndex("COD_LINEA_MKT")));
//            c.close();
//        }
//        db.close();
//
//        Log.e("GRUPO_G: ", "" + comGrupoFidelidad);
//        Log.e("GRUPO_G: ", "" + comGrupoFidelidad.getId());
//        Log.e("GRUPO_G: ", "" + comGrupoFidelidad.getIdGrupoPoliticaPrecio());
//        Log.e("GRUPO_G: ", "" + comGrupoFidelidad.getCodLineaVenta());
//        Log.e("GRUPO_G: ", "" + comGrupoFidelidad.getCodLineaMkt());
//
//        return comGrupoFidelidad;
//    }

}