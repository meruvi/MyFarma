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

public class DaoVentasCliente extends SQLiteOpenHelper {




    String TAG=DaoVentasCliente.class.getName();

    SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String DATA_BASE_NAME = "zeusMobil";
    private static final int DATA_BASE_VERSION = Util.DATA_BASE_VERSION_ZEUS;

    private static  String nombreTabla="VENTAS_CLIENTE";
    String sqlCreateTable="create table "+nombreTabla+"(anio integer ,mes integer,cod_cliente integer,monto_venta real)" ;


    public DaoVentasCliente(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);

    }
//
//
//
//    public void guardarVentasCliente(Collection<VentasCliente> ventasClienteList){
//
//        SQLiteDatabase dbWrite = this.getWritableDatabase();
//
//
//        try
//        {
//
//
//            dbWrite.beginTransaction();
//            ContentValues values = new ContentValues();
//
//            for(VentasCliente vc :ventasClienteList){
//                values.put("anio", vc.getAnio());
//                values.put("mes", vc.getMes());
//                values.put("cod_cliente", vc.getCodCliente());
//                values.put("monto_venta",  vc.getMontoVenta() );
//                dbWrite.insert(nombreTabla, null,values);
//            }
//            dbWrite.setTransactionSuccessful();
//
//        }catch(Exception e){
//            e.printStackTrace();
//
//        }finally{
//            dbWrite.endTransaction();
//        }
//        dbWrite.close();
//    }
//
//
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
//
//
//
//    public ArrayList<Cliente> getListadoClienteBuscar(String str){
//
//
//        StringBuilder selectQuery=new StringBuilder();
//        selectQuery.append( " select cod_cliente , nombre_cliente from clientes c where nombre_cliente like '"+str+"%' " );
//
//
//        Log.i(TAG, selectQuery.toString());
//        SQLiteDatabase db = this.getReadableDatabase();
//        ArrayList<Cliente>  listado=new ArrayList<Cliente>();
//        Cursor c = db.rawQuery(selectQuery.toString(), null);
//        if (c.moveToFirst()) {
//            do {
//
//
//
//                Cliente cliente=new Cliente();
//
//
//                cliente.setNombreCliente( c.getString( c.getColumnIndex("nombre_cliente"))  );
//                cliente.setCodCliente( c.getInt( c.getColumnIndex("cod_cliente"))  );
//
//
//
//                listado.add(cliente);
//
//
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
