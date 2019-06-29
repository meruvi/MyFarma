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
import java.util.List;

public class LimiteCreditoClienteController extends SQLiteOpenHelper {

    private Context context;

    String TAG = LimiteCreditoClienteController.class.getName();

    SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String DATA_BASE_NAME = "zeusMobil";
    private static final int DATA_BASE_VERSION = Util.DATA_BASE_VERSION_ZEUS;

    private static  String nombreTabla="limite_credito_cliente";

    String sqlCreateTable="create table "+nombreTabla+"(cod_cliente integer,monto_venta real,monto_limite_credito real)" ;




//    /**
//     * @param listado
//     */
//    public void registroLimiteCreditoCliente(Collection<LimiteCreditoCliente> listado){
//        Log.i(TAG, "INGRESANDO limite_credito_cliente");
//        SQLiteDatabase db = this.getWritableDatabase();
//        try
//        {
//            db.beginTransaction();
//            db.delete("limite_credito_cliente", null, null);
//            for(LimiteCreditoCliente c:listado){
//                ContentValues values = new ContentValues();
//                values.put("cod_cliente", c.getCodCliente());
//                //values.put("monto_venta", c.getMontoVenta());
//                values.put("cod_linea_venta", c.getMontoVenta());
//
//                values.put("monto_limite_credito", c.getMontoLimiteCredito());
//
//
//                db.insert("limite_credito_cliente", null,values);
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
    /**
     * @param context ESTE PARAMETRO SE ESPECIFICA EL CONTEXTO.
     */
    public LimiteCreditoClienteController(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);

    }
//
//
//
    public void crearColumnaTabla(String nombreColumna,String nombreTabla,String tipoColumna){
        if( !verificarColumnTabla(nombreColumna,nombreTabla) ){
            SQLiteDatabase db = this.getReadableDatabase();
            String sqlColumn=" ALTER TABLE "+nombreTabla+" ADD COLUMN "+nombreColumna+" "+tipoColumna+" ";
            db.execSQL(sqlColumn);
            //Log.i("CREANDO  TABLA: "+nombreTabla+" COLUMNA :"+nombreColumna, sqlColumn);
            db.close();

        }

    }
//
    public boolean verificarColumnTabla(String nombreColumnaVerificar,String nombreTabla){
        String selectQuery = "PRAGMA table_info("+nombreTabla+")";
        Log.i("INFO", selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {

                String nombreColumna =c.getString(c.getColumnIndex("name")  ) ;

                if( nombreColumnaVerificar.equals( nombreColumna  ) ){
                    Log.i(TAG, "VERIFICANDO TABLA: "+nombreTabla+" COLUMNA :"+nombreColumnaVerificar + "..............EXISTE");
                    return true;
                }
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        Log.i(TAG, "VERIFICANDO TABLA: "+nombreTabla+" COLUMNA :"+nombreColumnaVerificar + "..............NO EXISTE");
        return false;
    }
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

        // creandoTablaTipoVisita();
    }
//
//
//    public List<LimiteCreditoCliente> getLimiteCreditoCliente(int codCliente){
//
//
//        List<LimiteCreditoCliente>  limite=new ArrayList<LimiteCreditoCliente>();
//        StringBuilder selectQuery=new StringBuilder();
//        selectQuery.append( " select   monto_venta ,monto_limite_credito,cod_linea_venta  from limite_credito_cliente where cod_cliente="+codCliente );
//
//
//        Log.i("INFO", selectQuery.toString());
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery.toString(), null);
//        if (c.moveToFirst()) {
//            do {
//                LimiteCreditoCliente limiteCreditoCliente=new LimiteCreditoCliente();
//                limiteCreditoCliente.setMontoVenta( c.getDouble( c.getColumnIndex("cod_linea_venta"))  );
//                limiteCreditoCliente.setMontoLimiteCredito( c.getDouble( c.getColumnIndex("monto_limite_credito"))  );
//                limiteCreditoCliente.setCodCliente(codCliente );
//
//                Log.i("NADA", limiteCreditoCliente.getMontoLimiteCredito()+"");
//                limite.add(limiteCreditoCliente);
//            } while (c.moveToNext());
//            c.close();
//            db.close();
//        }
//
//        return limite;
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