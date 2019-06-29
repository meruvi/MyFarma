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

public class SalidaMaterialPromocionalEntregaController extends SQLiteOpenHelper {

    Context context;
    SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String DATA_BASE_NAME = "zeusMobil";
    private static final int DATA_BASE_VERSION = Util.DATA_BASE_VERSION_ZEUS;

    private static final String nombreTablaSalida="salida_material_entrega";
    //String sqlCreateTable="create table salida_material_entrega_(cod_salida integer,cod_estado_entregasalida integer,obs_entrega_salida text,fecha_entrega_salida datetime,cod_area integer" +
//			" , cod_cliente_salida integer, nro_documento_venta integer,fecha_salida datetime ,monto_total_venta real )";

    String sqlCreateTable="create table "+nombreTablaSalida+"(cod_salida_matpromocional integer,cod_cliente integer,nro_salida_matpromocional integer,cod_mat_promocional integer,fecha_salida_material datetime, cantidad_salida_matpromocional integer,obs_salida_oferta text,obs_salida_venta text,cod_estado_entregasalida integer,obs_entrega_salida text,fecha_entrega_salida datetime)" ;

    public SalidaMaterialPromocionalEntregaController(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
        this.context = context;
    }
//	/*public void registroSalidaEntrega(List<SalidaEntrega> listado){
//
//
//		SQLiteDatabase db = this.getWritableDatabase();
//
//		try{
//
//
//			db.beginTransaction();
//
//			for(SalidaEntrega salidaVenta:listado){
//				ContentValues values = new ContentValues();
//
//				values.put("cod_salida", salidaVenta.getCodSalida());
//				values.put("cod_estado_entregasalida", salidaVenta.getCodEstadoEntregaSalida());
//				values.put("obs_entrega_salida", salidaVenta.getObsEntregaSalida());
//				values.put("fecha_entrega_salida", f.format( new java.util.Date() ));
//				values.put("cod_area", salidaVenta.getCodAreaEmpresa());
//				values.put("cod_cliente_salida", salidaVenta.getCodCliente());
//				values.put("nro_documento_venta", salidaVenta.getNroDocumento());
//				values.put("fecha_salida", f.format( salidaVenta.getFechaSalidaVenta()));
//				values.put("monto_total_venta", salidaVenta.getMontoTotalVenta());
//				db.insert("salida_entrega", null,values);
//
//
//			}
//			db.setTransactionSuccessful();
//
//
//		}catch(Exception e){
//			e.printStackTrace();
//		}	finally{
//			db.endTransaction();
//			db.close();
//
//		}
//
//
//
//
//
//
//
//
//	}*/
//
//
//
//    public void actualizarSalidaEntrega(Collection<SalidaMaterialPromocionalEntrega> listado){
//
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        try{
//
//
//            db.beginTransaction();
//            for(SalidaMaterialPromocionalEntrega salidaVenta:listado){
//                ContentValues values = new ContentValues();
//                values.put("cod_estado_entregasalida", EstadoEntregaSalida.SINCRONIZADO.codEstadoEntrega);
//                db.update(nombreTablaSalida, values, "cod_salida_matpromocional="+salidaVenta.getCodSalidaMaterialPromocional(), null);
//            }
//            db.setTransactionSuccessful();
//        }catch(Exception e){
//            e.printStackTrace();
//        }	finally{
//            db.endTransaction();
//            db.close();
//
//        }
//
//
//
//
//
//
//
//
//    }
//
//    public void registrandoSalidaEntregaMaterial(SalidaMaterialPromocionalEntrega salidaVenta){
//
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        try{
//
//
//            db.beginTransaction();
//
//
//
//            ContentValues values = new ContentValues();
//
//            values.put("cod_salida_matpromocional", salidaVenta.getCodSalidaMaterialPromocional());
//            values.put("cod_cliente", salidaVenta.getCodCliente());
//            values.put("nro_salida_matpromocional", salidaVenta.getNroSalidaMaterialPromocional());
//            values.put("cod_mat_promocional", salidaVenta.getCodMaterialPromocional());
//            values.put("fecha_salida_material", f.format( salidaVenta.getFechaSalidaMaterialPromocional() ));
//            values.put("cantidad_salida_matpromocional", salidaVenta.getCantidadSalidaMaterialPromocional());
//            values.put("obs_salida_oferta", salidaVenta.getObsMaterialOferta());
//            values.put("obs_salida_venta", salidaVenta.getObsSalidaventa());
//            values.put("cod_estado_entregasalida", salidaVenta.getCodEstadoEntregaSalidaMaterial());
//            values.put("obs_entrega_salida", salidaVenta.getObsEntregaSalidaMaterial());
//            values.put("fecha_entrega_salida", f.format( salidaVenta.getFechaEntregaSalidaMaterial() ));
//
//
//            db.insert(nombreTablaSalida, null,values);
//
//
//
//            db.setTransactionSuccessful();
//
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }	finally{
//            db.endTransaction();
//            db.close();
//
//        }
//
//
//
//
//
//
//
//
//    }
//
//
    public void creandoTablaSalida(){

        Log.i("---INICIANDO---", "-----VERIFICANDO TABLA SALIDA "+nombreTablaSalida+"----");


        String selectQuery = " select count(*) as cantidad from sqlite_master where name='"+nombreTablaSalida+"' and type='table' " ;
        Log.i("INFO", selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                int cantidad=c.getInt( c.getColumnIndex("cantidad"));
                if( cantidad==0 ){
                    Log.i(context.getClass().getSimpleName(),"LA TABLA SALIDA "+nombreTablaSalida+":.............NO EXISTE");
                    Log.i(context.getClass().getSimpleName(),"CREANDO TABLA "+nombreTablaSalida + "............" + sqlCreateTable);
                    db.execSQL(sqlCreateTable);

                }else {
                    Log.i(context.getClass().getSimpleName(),"LA TABLA "+nombreTablaSalida+":................EXISTE");
                }


            } while (c.moveToNext());
            c.close();
            db.close();
        }
    }
//
//    public ArrayList<SalidaEntrega> getListadoSalidaEntrega(){
//
//        SimpleDateFormat  f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//
//        ArrayList<SalidaEntrega>   lista=new ArrayList<SalidaEntrega>();
//        String selectQuery = " SELECT cod_salida,cod_estado_entregasalida,obs_entrega_salida,fecha_entrega_salida,cod_area,cod_cliente_salida,nro_documento_venta " +
//                "  FROM salida_entrega where  cod_estado_entregasalida=1 " ;
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//
//
//
//                SalidaEntrega s=new SalidaEntrega();
//                s.setCodSalida( c.getInt( c.getColumnIndex("cod_salida"))  );
//                s.setCodEstadoEntregaSalida( c.getInt( c.getColumnIndex("cod_estado_entregasalida"))  );
//                s.setObsEntregaSalida( c.getString( c.getColumnIndex("obs_entrega_salida"))  );
//                try {
//                    s.setFechaEntregaSalida(  f.parse(  c.getString( c.getColumnIndex("fecha_entrega_salida")) ) );
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                s.setCodAreaEmpresa( c.getInt( c.getColumnIndex("cod_area"))  );
//                s.setCodCliente( c.getInt( c.getColumnIndex("cod_cliente_salida"))  );
//                s.setNroDocumento( c.getInt( c.getColumnIndex("nro_documento_venta"))  );
//                lista.add(s);
//            } while (c.moveToNext());
//            c.close();
//            db.close();
//        }
//        return lista;
//    }
//
///*public String obtenerSalidaEntrega(int codSalida){
//
//
//
//		   String obsSalidaEntrega="";
//		   String selectQuery = " select s.obs_entrega_salida from salida_entrega s where s.cod_salida="+codSalida ;
//		   Log.i("INFO", selectQuery);
//		   SQLiteDatabase db = this.getReadableDatabase();
//		    Cursor c = db.rawQuery(selectQuery, null);
//		    if (c.moveToFirst()) {
//		        do {
//
//
//		        } while (c.moveToNext());
//		        c.close();
//		        db.close();
//		    }
//	}*/


    @Override
    public void onCreate(SQLiteDatabase arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }

}
