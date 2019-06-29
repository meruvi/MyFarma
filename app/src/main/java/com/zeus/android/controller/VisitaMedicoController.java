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

public class VisitaMedicoController extends SQLiteOpenHelper {



    SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String DATA_BASE_NAME = "zeusMobil";
    private static final int DATA_BASE_VERSION = Util.DATA_BASE_VERSION_ZEUS;

    private static final String nombreTabla="visita_medico";
    String sqlCreateTable="create table visita_medico(cod_visita_medico integer,cod_medico integer,cod_motivo integer,obs text,fecha_registro datetime,cod_personal integer,cod_boleta integer,estado_boleta integer) ";


    private static final String nombreTablaTipoBajaVisita="tipo_motivo_visita";
    String sqlCreateTableTipoBajaVisita="create table tipo_motivo_visita(cod_motivo integer,nombre_motivo text) ";


    public VisitaMedicoController(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);

    }
//
    public void crearTabla(){

        creandoTabla(nombreTabla,sqlCreateTable);
        creandoTabla(nombreTablaTipoBajaVisita,sqlCreateTableTipoBajaVisita);

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
//    public void registroTipoMotivoNoVisita(Collection<TipoBajaVisita> listado){
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        try
//        {
//            db.beginTransaction();
//            db.delete(nombreTablaTipoBajaVisita, null, null);
//
//
//
//            for(TipoBajaVisita c:listado){
//                ContentValues values = new ContentValues();
//                values.put("cod_motivo", c.getCodMotivo());
//                values.put("nombre_motivo", c.getNombreMotivo());
//                db.insert(nombreTablaTipoBajaVisita, null,values);
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
//    public void registroMotivoNoVisita(VisitaMedico visitaMedico){
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        try
//        {
//            db.beginTransaction();
//
//            //ContentValues dataEliminar = new ContentValues();
//            db.delete(nombreTabla, "cod_boleta in("+visitaMedico.getCodBoleta()+")",null);
//
//
//
//            ContentValues values = new ContentValues();
//            values.put("cod_boleta", visitaMedico.getCodBoleta());
//            values.put("estado_boleta", 0);
//            values.put("cod_medico", visitaMedico.getCodMedico());
//            values.put("cod_motivo", visitaMedico.getCodMotivo());
//
//            values.put("obs", visitaMedico.getObsRegistro());
//            values.put("fecha_registro", f.format( visitaMedico.getFechaRegistro() ));
//            values.put("cod_personal", visitaMedico.getCodPersonal());
//
//
//            db.insert(nombreTabla, null,values);
//
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
//    public ArrayList<VisitaMedico> getListadoNoVisita() {
//        ArrayList<VisitaMedico> listado=new ArrayList<VisitaMedico>();
//        String selectQuery = "select cod_visita_medico,cod_medico,cod_motivo,obs,fecha_registro,cod_personal,cod_boleta   from visita_medico";
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//                VisitaMedico td = new VisitaMedico();
//                //td.setCodigoMuestra( c.getString( c.getColumnIndex("codigo_muestra")));
//                //td.setNombreMuestra(      c.getString( c.getColumnIndex("nombre_muestra")));
//                td.setCodVisitaMedico( c.getInt( c.getColumnIndex("cod_visita_medico")) );
//                td.setCodMedico( c.getInt( c.getColumnIndex("cod_medico")) );
//                td.setCodMotivo( c.getInt( c.getColumnIndex("cod_motivo")) );
//                td.setObsRegistro( c.getString( c.getColumnIndex("obs")) );
//                td.setCodPersonal( c.getInt( c.getColumnIndex("cod_personal")) );
//                td.setCodBoleta( c.getInt( c.getColumnIndex("cod_boleta")) );
//
//
//
//                listado.add(td);
//            } while (c.moveToNext());
//            c.close();
//            db.close();
//        }
//        return listado;
//    }
//
//    public VisitaMedico  getNoVisita(int codBoleta) {
//        VisitaMedico visitaMedico=new VisitaMedico();
//        String selectQuery = "select cod_visita_medico,cod_medico,cod_motivo,obs,fecha_registro,cod_personal,cod_boleta,(select t.nombre_motivo from tipo_motivo_visita t where t.cod_motivo=v.cod_motivo) as nombre_motivo   from visita_medico v where cod_boleta="+codBoleta;
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//                VisitaMedico td = new VisitaMedico();
//                //td.setCodigoMuestra( c.getString( c.getColumnIndex("codigo_muestra")));
//                //td.setNombreMuestra(      c.getString( c.getColumnIndex("nombre_muestra")));
//                visitaMedico.setCodVisitaMedico( c.getInt( c.getColumnIndex("cod_visita_medico")) );
//                visitaMedico.setCodMedico( c.getInt( c.getColumnIndex("cod_medico")) );
//                visitaMedico.setCodMotivo( c.getInt( c.getColumnIndex("cod_motivo")) );
//                visitaMedico.setObsRegistro( c.getString( c.getColumnIndex("obs")) );
//                visitaMedico.setCodPersonal( c.getInt( c.getColumnIndex("cod_personal")) );
//                visitaMedico.setCodBoleta( c.getInt( c.getColumnIndex("cod_boleta")) );
//
//                visitaMedico.setNombreTipo( c.getString( c.getColumnIndex("nombre_motivo")) );
//
//
//
//
//            } while (c.moveToNext());
//            c.close();
//            db.close();
//        }
//        return visitaMedico;
//    }
//
//    public ArrayList<TipoBajaVisita>  getListadoTipoBaja() {
//        ArrayList<TipoBajaVisita> listado=new ArrayList<TipoBajaVisita>();
//        String selectQuery = "select cod_motivo,nombre_motivo  from tipo_motivo_visita";
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//                TipoBajaVisita td = new TipoBajaVisita();
//
//                td.setCodMotivo( c.getInt( c.getColumnIndex("cod_motivo")) );
//                td.setNombreMotivo( c.getString( c.getColumnIndex("nombre_motivo")) );
//
//
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