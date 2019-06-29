package com.zeus.android.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.zeus.android.util.Util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

public class RuteroMaestroController extends SQLiteOpenHelper {


    String TAG=LimiteCreditoClienteController.class.getName();

    SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String DATA_BASE_NAME = "zeusMobil";
    private static final int DATA_BASE_VERSION = Util.DATA_BASE_VERSION_ZEUS;

    private static  String nombreTabla="rutero_maestro_detalle";

    String sqlCreateTable="create table "+nombreTabla+"(rutero_nro_semana integer,rutero_nro_dia integer,rutero_turno integer,nro_orden_rutero integer,cod_cliente integer,nombre_cliente text)" ;


    /**
     * @param context ESTE PARAMETRO SE ESPECIFICA EL CONTEXTO.
     */
    public RuteroMaestroController(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);

    }



    public void creandoTabla(){

        Log.i("---INICIANDO---", "-----VERIFICANDO TABLA----");


        String selectQuery = " select count(*) as cantidad from sqlite_master where name='"+nombreTabla+"' and type='table' " ;
        Log.i("INFO", selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                int cantidad=c.getInt( c.getColumnIndex("cantidad"));
                if( cantidad==0 ){
                    Log.i("LA TABLA  :", "NO EXISTE");
                    Log.i("CREANDO TABLA "+nombreTabla, sqlCreateTable);
                    db.execSQL(sqlCreateTable);

                }else {
                    Log.i("LA TABLA "+nombreTabla+" :", "------ EXISTE");
                }


            } while (c.moveToNext());
            c.close();
            db.close();
        }


    }
//
//    public void registroLimiteCreditoCliente(Collection<RuteroMaestroDetalle> listado){
//        Log.i(TAG, "INGRESANDO RuteroMaestroController");
//        SQLiteDatabase db = this.getWritableDatabase();
//        try
//        {
//            db.beginTransaction();
//            db.delete("rutero_maestro_detalle", null, null);
//            for(RuteroMaestroDetalle bean:listado){
//                ContentValues values = new ContentValues();
//                values.put("rutero_nro_semana", bean.getCodRuteroSemana());
//                values.put("rutero_nro_dia", bean.getCodDiaSemana());
//                values.put("rutero_turno", bean.getCodRuteroTurno());
//                values.put("nro_orden_rutero", bean.getNroOrden());
//                values.put("cod_cliente", bean.getCodCliente());
//                values.put("nombre_cliente", bean.getNombreCliente());
//
//                db.insert("rutero_maestro_detalle", null,values);
//
//            }
//            db.setTransactionSuccessful();
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally{
//            db.endTransaction();
//        }
//    }
//
//
//    public ArrayList<RuteroMaestroDetalle> getRuteroDiaDia(int codRuteroSemana, int codRuteroDia){
//        ArrayList<RuteroMaestroDetalle>  listado=new ArrayList<RuteroMaestroDetalle>();
//
//        StringBuilder selectQuery=new StringBuilder();
//        selectQuery.append( " select r.rutero_turno,r.nro_orden_rutero,r.cod_cliente,nombre_cliente from rutero_maestro_detalle r where r.rutero_nro_semana="+codRuteroSemana+" and r.rutero_nro_dia="+codRuteroDia );
//
//
//        Log.i("INFO", selectQuery.toString());
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor c = db.rawQuery(selectQuery.toString(), null);
//        if (c.moveToFirst()) {
//            do {
//
//                RuteroMaestroDetalle   ruteroMaestroDetalle=new RuteroMaestroDetalle();
//                ruteroMaestroDetalle.setCodRuteroTurno(  c.getInt(c.getColumnIndex("rutero_turno")) );
//                ruteroMaestroDetalle.setNroOrden(c.getInt(c.getColumnIndex("nro_orden_rutero")));
//                ruteroMaestroDetalle.setCodCliente( c.getInt( c.getColumnIndex("cod_cliente")) );
//                ruteroMaestroDetalle.setNombreCliente(c.getString(c.getColumnIndex("nombre_cliente")));
//
//                if( ruteroMaestroDetalle.getCodRuteroTurno()== RuteroTurno.AM.codRuteroTurno ){
//                    ruteroMaestroDetalle.setNombreRuteroTurno( RuteroTurno.AM.nombreRuteroTurno );
//                }else{
//                    ruteroMaestroDetalle.setNombreRuteroTurno( RuteroTurno.PM.nombreRuteroTurno );
//                }
//
//
//                listado.add(ruteroMaestroDetalle);
//
//            } while (c.moveToNext());
//            c.close();
//            db.close();
//        }
//
//        return listado;
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