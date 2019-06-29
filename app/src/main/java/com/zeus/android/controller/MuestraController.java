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

public class MuestraController extends SQLiteOpenHelper {




    SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String DATA_BASE_NAME = "zeusMobil";
    private static final int DATA_BASE_VERSION = Util.DATA_BASE_VERSION_ZEUS;

    private static final String nombreTabla="muestras";
    String sqlCreateTable="create table muestras(codigo_muestra text,nombre_muestra text) ";



    public MuestraController(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);

    }

    public void crearTablaMuestra(){

        creandoTabla(nombreTabla,sqlCreateTable);


    }
//
    public void creandoTabla(String nombreTabla,String sqlCreateTable){

        Log.i("---INICIANDO---", "-----VERIFICANDO TABLA----:"+nombreTabla);
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
//    public void registroMuestra(Collection<Muestra> listado){
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        try
//        {
//            db.beginTransaction();
//            db.delete(nombreTabla, null, null);
//
//
//
//            for(Muestra c:listado){
//                ContentValues values = new ContentValues();
//                values.put("codigo_muestra", c.getCodigoMuestra());
//                values.put("nombre_muestra", c.getNombreMuestra());
//                db.insert(nombreTabla, null,values);
//            }
//            db.setTransactionSuccessful();
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally{
//            db.endTransaction();
//        }
//        db.close();
//    }
//
//
//    public ArrayList<Muestra> getListadoMuestras(String str, ArrayList<BoletaVisitaDetalleMuestra> detalle) {
//
//        //boletaVisitaBean
//        String codigoMuestraNobuscar="";
//        for(BoletaVisitaDetalleMuestra bean:detalle){
//
//            codigoMuestraNobuscar+="'"+bean.getCodigoMuestra()+"',";
//        }
//        codigoMuestraNobuscar=codigoMuestraNobuscar.substring(0,codigoMuestraNobuscar.length()-1);
//
//
//        ArrayList<Muestra> listado=new ArrayList<Muestra>();
//        String selectQuery = "select codigo_muestra,nombre_muestra from muestras where nombre_muestra like '"+str+"%' and codigo_muestra not in ("+codigoMuestraNobuscar+") order by nombre_muestra";
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//                Muestra td = new Muestra();
//                td.setCodigoMuestra( c.getString( c.getColumnIndex("codigo_muestra")));
//                td.setNombreMuestra(  c.getString( c.getColumnIndex("nombre_muestra")));
//                listado.add(td);
//            } while (c.moveToNext());
//            c.close();
//            db.close();
//        }
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