package com.zeus.android.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

import com.zeus.android.util.Util;

public class ConfiguracionController extends SQLiteOpenHelper {

    private static final String DATA_BASE_NAME = "zeusMobil";
    private static final int DATA_BASE_VERSION = Util.DATA_BASE_VERSION_ZEUS;
    Context context;

    // private static final String
    // CREATE_TABLE_CLIENTE="create table clientes(cod_cliente integer primary key ,nombre_cliente text,cod_area_empresa numeric,nit_cliente text)";
    public ConfiguracionController(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
        this.context=context;
        //
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // db.execSQL(CREATE_TABLE_CLIENTE);

    }

    public long actualizarConfiguracion(String ipRegional, String ipExterno,int codAreaEmpresa) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("configuracion_aplicacion_ip", null, null);

        long cantidadInsertada = 0;

        ContentValues values = new ContentValues();
        values.put("ip_regional", ipRegional);
        values.put("ip_externo", ipExterno);
        values.put("cod_area_empresa", codAreaEmpresa);
        cantidadInsertada = db.insert("configuracion_aplicacion_ip", null,values);

        return cantidadInsertada;
    }

    public String[] getIpConfiguracion() {





        String selectQuery = "SELECT  ip_regional,  ip_externo,cod_area_empresa FROM configuracion_aplicacion_ip ";
        String[] data= {"","",""};


        Log.i("INFO", selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                //Cliente td = new Cliente();
                //td.setCodCliente(c.getInt(c.getColumnIndex("cod_cliente")));
                //td.setNombreCliente(c.getString(c.getColumnIndex("nombre_cliente")));

                data[0]=c.getString(c.getColumnIndex("ip_regional"));
                data[1]=c.getString(c.getColumnIndex("ip_externo"));
                data[2]=c.getString(c.getColumnIndex("cod_area_empresa"));

            } while (c.moveToNext());
            c.close();
        }
        Log.i("IP_REGIONAL", data[0]);
        Log.i("IP_EXTERIOR", data[1]);
        db.close();



        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);

        //For 3G check
        boolean is3g = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .isConnectedOrConnecting();
        //For WiFi Check
        boolean isWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .isConnectedOrConnecting();

        System.out.println("3g:"+is3g + " wifi: " + isWifi);

        if (!is3g && !isWifi)
        {
            Toast.makeText(context,"Please make sure your Network Connection is ON ",Toast.LENGTH_LONG).show();
        }
        else
        {
            // " Your method what you want to do ";
        }



        if(is3g){
            data[0]=data[0];
        }

        if(isWifi){
            data[0]=data[1];
        }


        Log.i("IP connect:  ", data[0]);


        return data;
    }


    public String[] getModificacion() {





        String selectQuery = "SELECT  ip_regional,  ip_externo,cod_area_empresa FROM configuracion_aplicacion_ip ";
        String[] data= {"","",""};


        Log.i("INFO", selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                //Cliente td = new Cliente();
                //td.setCodCliente(c.getInt(c.getColumnIndex("cod_cliente")));
                //td.setNombreCliente(c.getString(c.getColumnIndex("nombre_cliente")));

                data[0]=c.getString(c.getColumnIndex("ip_regional"));
                data[1]=c.getString(c.getColumnIndex("ip_externo"));
                data[2]=c.getString(c.getColumnIndex("cod_area_empresa"));

            } while (c.moveToNext());
        }
        Log.i("IP_REGIONAL", data[0]);
        Log.i("IP_EXTERIOR", data[1]);
        db.close();





        return data;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {

    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

}
