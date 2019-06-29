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
import java.util.HashMap;
import java.util.Map;

public class MedicoController extends SQLiteOpenHelper {


    SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String DATA_BASE_NAME = "zeusMobil";
    private static final int DATA_BASE_VERSION = Util.DATA_BASE_VERSION_ZEUS;

    private static final String nombreTablaSalida="medicos";
    private static final String nombreTablaMedicoUbicacion="medicos_ubicacion";

    String sqlCreateTable="create table medicos(cod_med integer,ap_pat_med text,ap_mat_med text,nom_med text,cod_ciudad integer) ";
    String sqlCreateTableMedicoUbicacion="create table medicos_ubicacion(cod_med integer,latitud real,longitud real,cod_lugar_medico integer,hora_lugar_medico text,hora2_lugar_medico text,cod_medico_ubicacion  integer  PRIMARY KEY AUTOINCREMENT ) ";


    public MedicoController(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);

    }


    public void eliminarTablaCrearTabla(String nombreColumna,String nombreTabla){
        if(! verificarColumnTabla(nombreColumna,nombreTabla) ){
            SQLiteDatabase db = this.getReadableDatabase();
            String sqlColumn=" DROP TABLE IF EXISTS medicos_ubicacion ";
            db.execSQL(sqlColumn);
            //Log.i("CREANDO  TABLA: "+nombreTabla+" COLUMNA :"+nombreColumna, sqlColumn);
            creandoTabla();
            db.close();
        }

    }
//
//
//    public void eliminarMedicoUbicacion(MedicoUbicacion medicoUbicacion) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues data = new ContentValues();
//
//        //prefiero que estes con tu esposo que es un verdadero hombre que te respeta no la basura como yo
//
//        //if(pedido.getCodPedido()>0){
//        db.delete("medicos_ubicacion", "cod_medico_ubicacion in("+medicoUbicacion.getCodMedicoubicacion()+")",null);
//
//        //}
//        //		return db.insert("pedidos", null, data);
//
//    }
//
    public void crearColumnaTabla(String nombreColumna,String nombreTabla,String tipoColumna){
        if( !verificarColumnTabla(nombreColumna,nombreTabla) ){
            SQLiteDatabase db = this.getReadableDatabase();
            String sqlColumn=" ALTER TABLE "+nombreTabla+" ADD COLUMN "+nombreColumna+" "+tipoColumna+" ";
            db.execSQL(sqlColumn);
            //  Log.i("CREANDO  TABLA: "+nombreTabla+" COLUMNA :"+nombreColumna, sqlColumn);
            db.close();

        }

    }
//
//    public void crearColumnaPrimayTabla(String nombreColumna,String nombreTabla,String tipoColumna){
//        if( !verificarColumnTabla(nombreColumna,nombreTabla) ){
//            SQLiteDatabase db = this.getReadableDatabase();
//            String sqlColumn=" ALTER TABLE "+nombreTabla+" ADD COLUMN "+nombreColumna+" "+tipoColumna+" PRIMARY KEY AUTOINCREMENT ";
//
//
//
//            db.execSQL(sqlColumn);
//            //     Log.i("CREANDO  TABLA: "+nombreTabla+" COLUMNA :"+nombreColumna, sqlColumn);
//            db.close();
//
//        }
//
//    }
//
//
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
                    //Log.i("VERIFICANDO TABLA: "+nombreTabla+" COLUMNA :"+nombreColumnaVerificar, "EXISTE");
                    return true;
                }
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        //Log.i("VERIFICANDO TABLA: "+nombreTabla+" COLUMNA :"+nombreColumnaVerificar, "NO EXISTE");
        return false;
    }
//    public void registrandoMedicos(Collection<Medico> listado){
//        SQLiteDatabase db = this.getWritableDatabase();
//        try{
//            db.beginTransaction();
//            db.delete("medicos", null, null);
//            for(Medico bean:listado){
//                ContentValues values = new ContentValues();
//                values.put("cod_med", bean.getCodMedico());
//                values.put("ap_pat_med", bean.getApPaternoMedico());
//                values.put("ap_mat_med", bean.getApMaternoMedico());
//                values.put("nom_med", bean.getNombreMedico());
//                values.put("cod_ciudad", bean.getCodCiudad());
//                db.insert("medicos", null,values);
//            }
//            db.setTransactionSuccessful();
//        }catch(Exception e){
//            e.printStackTrace();
//        }	finally{
//            db.endTransaction();
//            db.close();
//
//        }
//    }
//
//
//    public void registrandoMedicoubicacionZeus(Collection<MedicoUbicacion> listado){
//        SQLiteDatabase db = this.getWritableDatabase();
//        try{
//            db.beginTransaction();
//
//            db.delete("medicos_ubicacion", " cod_medico_ubicacion_zeus in(0) ",null);
//            db.delete("medicos_ubicacion", " cod_medico_ubicacion_zeus is null ",null);
//
//
//            for(MedicoUbicacion bean:listado){
//                ContentValues values = new ContentValues();
////                values.put("cod_med", bean.getCodMedico());
//
//                values.put("latitud", bean.getLatidud());
//                values.put("longitud", bean.getLongitud());
//
////                values.put("cod_lugar_medico", bean.getCodLugarMedico());
////                values.put("hora_lugar_medico", bean.getNombreHoraMedico());
////                values.put("hora2_lugar_medico", bean.getNombreHoraMedico2());
//
//
////                String sqlUpdate="  update  medicos_ubicacion set longitud="+bean.getLatidud()+",latitud="+bean.getLongitud()+" where cod_med="+bean.getCodMedico()+" and cod_lugar_medico= "+bean.getCodLugarMedico()+" and hora2_lugar_medico='"+bean.getNombreHoraMedico2()+"' and hora_lugar_medico ='"+bean.getNombreHoraMedico()+"'";
//                //db.u
//
//                int cantidadInsertada=db.update("medicos_ubicacion", values, "  cod_med="+bean.getCodMedico()+" and cod_lugar_medico= "+bean.getCodLugarMedico()+" and hora2_lugar_medico='"+bean.getNombreHoraMedico2()+"' and hora_lugar_medico ='"+bean.getNombreHoraMedico()+"'",null);
//
////                int cantidadInsertada=db.update("medicos_ubicacion", values, " cod_medico_ubicacion_zeus in("+bean.getCodMedicoUbicacionZeus()+") ",null);
//
//                Log.i("Hecho",cantidadInsertada+"");
//                if(cantidadInsertada==0){
//
//                    values.put("cod_med", bean.getCodMedico());
//
//                    values.put("cod_lugar_medico", bean.getCodLugarMedico());
//                    values.put("hora_lugar_medico", bean.getNombreHoraMedico());
//                    values.put("hora2_lugar_medico", bean.getNombreHoraMedico2());
//
//
//
//
//                    values.put("cod_medico_ubicacion_zeus", bean.getCodMedicoUbicacionZeus());
//                    db.insert("medicos_ubicacion", null,values);
//                }
//
//
//            }
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
//    }
//
//
//
//
//    public void registrandoMedicoUbicacion(MedicoUbicacion medicoUbicacion){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        try{
//            db.beginTransaction();
//
//            //db.delete("pedidos_detalle", "cod_pedido in("+codigo[0]+")",null);
//            //db.setTransactionSuccessful();
//
//            ContentValues values = new ContentValues();
//            values.put("cod_med", medicoUbicacion.getCodMedico());
//            values.put("latitud", medicoUbicacion.getLatidud());
//            values.put("longitud", medicoUbicacion.getLongitud());
//            //values.put("cod_turno_medico", medicoUbicacion.getCodTurnoMedico());
//            values.put("cod_lugar_medico", medicoUbicacion.getCodLugarMedico());
//            values.put("hora_lugar_medico", medicoUbicacion.getNombreHoraMedico());
//            values.put("hora2_lugar_medico", medicoUbicacion.getNombreHoraMedico2());
//
//
//
//            db.insert("medicos_ubicacion", null,values);
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
//    public void actualizarSalidaEntrega(Collection<SalidaEntrega> listado){
//
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        try{
//
//
//            db.beginTransaction();
//            for(SalidaEntrega salidaVenta:listado){
//                ContentValues values = new ContentValues();
//                values.put("cod_estado_entregasalida", EstadoEntregaSalida.SINCRONIZADO.codEstadoEntrega);
//                db.update("salida_entrega", values, "cod_salida="+salidaVenta.getCodSalida(), null);
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
//    public void registrandoSalidaEntrega(SalidaEntrega salidaVenta){
//
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        try{
//
//
//            db.beginTransaction();
//            //db.delete("salida", null, null);
//
//
//            ContentValues values = new ContentValues();
//
//            values.put("cod_salida", salidaVenta.getCodSalida());
//            values.put("cod_estado_entregasalida", salidaVenta.getCodEstadoEntregaSalida());
//            values.put("obs_entrega_salida", salidaVenta.getObsEntregaSalida());
//            values.put("fecha_entrega_salida", f.format( new java.util.Date() ));
//            values.put("cod_area", salidaVenta.getCodAreaEmpresa());
//            values.put("cod_cliente_salida", salidaVenta.getCodCliente());
//            values.put("nro_documento_venta", salidaVenta.getNroDocumento());
//            values.put("fecha_salida", f.format( salidaVenta.getFechaSalidaVenta()));
//            values.put("monto_total_venta", salidaVenta.getMontoTotalVenta());
//            db.insert("salida_entrega", null,values);
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
    public void creandoTabla(){

        Log.i("---INICIANDO---", "-----VERIFICANDO TABLA MEDICOS----");


        String selectQuery = " select count(*) as cantidad from sqlite_master where name='"+nombreTablaSalida+"' and type='table' " ;
        Log.i("INFO", selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                int cantidad=c.getInt( c.getColumnIndex("cantidad"));
                if( cantidad==0 ){
                    Log.i("LA TABLA :", "NO EXISTE");
                    Log.i("CREANDO TABLA ", sqlCreateTable);
                    db.execSQL(sqlCreateTable);

                }else {
                    Log.i("LA TABLA :", "------ EXISTE");
                }


            } while (c.moveToNext());
            c.close();
            db.close();
        }



        selectQuery = " select count(*) as cantidad from sqlite_master where name='"+nombreTablaMedicoUbicacion+"' and type='table' " ;
        Log.i("INFO", selectQuery);
        SQLiteDatabase db1 = this.getReadableDatabase();
        Cursor c1 = db1.rawQuery(selectQuery, null);
        if (c1.moveToFirst()) {
            do {
                int cantidad=c1.getInt( c1.getColumnIndex("cantidad"));
                if( cantidad==0 ){
                    Log.i("LA TABLA :", "NO EXISTE");
                    Log.i("CREANDO TABLA ", sqlCreateTableMedicoUbicacion);
                    db1.execSQL(sqlCreateTableMedicoUbicacion);

                }else {
                    Log.i("LA TABLA :", "------ EXISTE");
                }


            } while (c.moveToNext());
            c1.close();
            db1.close();
        }


    }
//
//  /*  public ArrayList<Medico> getListadoMedicos(String str){
//
//        SimpleDateFormat  f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//
//        ArrayList<Medico>   lista=new ArrayList<Medico>();
//        String selectQuery = " SELECT cod_med ,ap_pat_med ,ap_mat_med ,nom_med ,cod_ciudad from medicos where nom_med  like '%" +str+"%' ";
//
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//
//
//
//                Medico s=new Medico();
//                s.setCodMedico(  c.getInt( c.getColumnIndex("cod_med"))   );
//                s.setApPaternoMedico(  c.getString( c.getColumnIndex("ap_pat_med"))   );
//                s.setApMaternoMedico(  c.getString( c.getColumnIndex("ap_mat_med"))   );
//                s.setNombreMedico(  c.getString( c.getColumnIndex("nom_med"))   );
//
//                s.setCodCiudad(  c.getInt( c.getColumnIndex("cod_ciudad"))   );
//
//
//                lista.add(s);
//            } while (c.moveToNext());
//            c.close();
//            db.close();
//        }
//        return lista;
//    }*/
//
//
//    public ArrayList<Medico> getListadoMedicos(String str){
//
//        SimpleDateFormat  f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//
//        ArrayList<Medico>   lista=new ArrayList<Medico>();
//        String selectQuery = " SELECT cod_med ,ap_pat_med ,ap_mat_med ,nom_med ,cod_ciudad from medicos  where ap_pat_med+' 'ap_mat_med+' '+nom_med like '%"+str+"%' order by ap_pat_med  ";
//
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//
//
//
//                Medico s=new Medico();
//                s.setCodMedico(  c.getInt( c.getColumnIndex("cod_med"))   );
//                s.setApPaternoMedico(  c.getString( c.getColumnIndex("ap_pat_med"))   );
//                s.setApMaternoMedico(  c.getString( c.getColumnIndex("ap_mat_med"))   );
//                s.setNombreMedico(   c.getString( c.getColumnIndex("ap_pat_med")) +" "+c.getString( c.getColumnIndex("ap_mat_med"))  +" "+c.getString( c.getColumnIndex("nom_med")) );
//
//                s.setCodCiudad(  c.getInt( c.getColumnIndex("cod_ciudad"))   );
//
//
//                lista.add(s);
//            } while (c.moveToNext());
//            c.close();
//            db.close();
//        }
//        return lista;
//    }
//
//
//
//    public ArrayList<MedicoUbicacion> getListadoMedicosUbicacion(int codMedico){
//
//
//
//        Map<Integer,String> valoresAtencion=new HashMap<Integer,String>();
//
//
//        for(LugarAtencion bean: LugarAtencion.values()){
//            //lista.add( new BeanObject( bean.codigo,bean.nombre ) );
//            //
//            valoresAtencion.put( bean.codigo,bean.nombre );
//        }
//
//
//
//        SimpleDateFormat  f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//
//        ArrayList<MedicoUbicacion>   lista=new ArrayList<MedicoUbicacion>();
//        String selectQuery = " SELECT cod_medico_ubicacion,cod_med,latitud,longitud,( SELECT ap_pat_med  from medicos m where m.cod_med=mu.cod_med) ap_pat_med,( SELECT ap_mat_med  from medicos m where m.cod_med=mu.cod_med) ap_mat_med,( SELECT nom_med  from medicos m where m.cod_med=mu.cod_med) nom_med,cod_lugar_medico,hora_lugar_medico,hora2_lugar_medico from  medicos_ubicacion mu where 0=0 ";
//        if(codMedico!=0){
//            selectQuery+=" and cod_med="+codMedico;
//        }
//
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//
//
//
//                MedicoUbicacion s=new MedicoUbicacion();
//                s.setCodMedico(  c.getInt( c.getColumnIndex("cod_med"))   );
//                s.setLatidud( c.getDouble( c.getColumnIndex("latitud"))  );
//                s.setLongitud( c.getDouble( c.getColumnIndex("longitud"))  );
//                //s.setCodTurnoMedico(  c.getInt( c.getColumnIndex("cod_turno_medico"))  );
//                s.setNombreHoraMedico( c.getString( c.getColumnIndex("hora_lugar_medico")) );
//                s.setNombreHoraMedico2( c.getString( c.getColumnIndex("hora2_lugar_medico"))  );
//
//                s.setCodLugarMedico( c.getInt( c.getColumnIndex("cod_lugar_medico"))    );
//                s.setNombreLugarMedico(  valoresAtencion.get( s.getCodLugarMedico() ) );
//
//                s.setCodMedicoubicacion( c.getInt( c.getColumnIndex("cod_medico_ubicacion"))    );
//
//                s.setNombreMedico(  c.getString( c.getColumnIndex("ap_pat_med"))+" "+ c.getString( c.getColumnIndex("ap_mat_med"))+" "+c.getString( c.getColumnIndex("nom_med")));
//
//
//                lista.add(s);
//            } while (c.moveToNext());
//            c.close();
//            db.close();
//        }
//        return lista;
//    }
//
//    public boolean getListadoMedicosUbicacionLatitud(String codMedico,double latitud,double longitud,String horaInicio,String horaFinal){
//
//
//
//
//        boolean existe=false;
//
//
//
//
//
//        String selectQuery = " SELECT count(*) from  medicos_ubicacion mu where cod_med="+codMedico+" and latitud="+latitud+" and longitud="+longitud+" and hora_lugar_medico="+horaInicio+" and hora2_lugar_medico="+horaFinal;
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//                existe=true;
//            } while (c.moveToNext());
//            c.close();
//            db.close();
//        }
//        return existe;
//    }
//
//    public boolean isRegistradoMedico(int codMed){
//
//        SimpleDateFormat  f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//
//        //ArrayList<MedicoUbicacion>   lista=new ArrayList<MedicoUbicacion>();
//        boolean registrado=false;
//        String selectQuery = " SELECT cod_med,latitud,longitud from  medicos_ubicacion where cod_med="+codMed;
//
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//
//                registrado=true;
//
//
//            } while (c.moveToNext());
//            c.close();
//
//        }
//        db.close();
//        return registrado;
//    }
//
//    public ArrayList<Medico> getListadoMedicos(){
//
//        SimpleDateFormat  f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//
//        ArrayList<Medico>   lista=new ArrayList<Medico>();
//        String selectQuery = " SELECT cod_med ,ap_pat_med ,ap_mat_med ,nom_med ,cod_ciudad from medicos  order by ap_pat_med  ";
//
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//
//
//
//                Medico s=new Medico();
//                s.setCodMedico(  c.getInt( c.getColumnIndex("cod_med"))   );
//                s.setApPaternoMedico(  c.getString( c.getColumnIndex("ap_pat_med"))   );
//                s.setApMaternoMedico(  c.getString( c.getColumnIndex("ap_mat_med"))   );
//                s.setNombreMedico(   c.getString( c.getColumnIndex("ap_pat_med")) +" "+c.getString( c.getColumnIndex("ap_mat_med"))  +" "+c.getString( c.getColumnIndex("nom_med")) );
//
//                s.setCodCiudad(  c.getInt( c.getColumnIndex("cod_ciudad"))   );
//
//
//                lista.add(s);
//            } while (c.moveToNext());
//            c.close();
//            db.close();
//        }
//        return lista;
//    }
//
//
//
//    public ArrayList<Medico> getListadoMedicosNombre(String str){
//
//        SimpleDateFormat  f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//
//        ArrayList<Medico>   lista=new ArrayList<Medico>();
//        String selectQuery = " SELECT cod_med ,ap_pat_med ,ap_mat_med ,nom_med ,cod_ciudad,( SELECT count(mu.cod_med) from  medicos_ubicacion  mu where mu.cod_med=m.cod_med ) as cantidad  from medicos m where ap_pat_med  like '%"+str+"%' order by ap_pat_med  ";
//
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//
//
//
//                Medico s=new Medico();
//                s.setCodMedico(  c.getInt( c.getColumnIndex("cod_med"))   );
//                s.setApPaternoMedico(  c.getString( c.getColumnIndex("ap_pat_med"))   );
//                s.setApMaternoMedico(  c.getString( c.getColumnIndex("ap_mat_med"))   );
//                s.setNombreMedico(   c.getString( c.getColumnIndex("ap_pat_med")) +" "+c.getString( c.getColumnIndex("ap_mat_med"))  +" "+c.getString( c.getColumnIndex("nom_med")) );
//
//                s.setCodCiudad(  c.getInt( c.getColumnIndex("cod_ciudad"))   );
//                //isRegistradoMedico(s.getCodMedico());
//                if(  c.getInt( c.getColumnIndex("cantidad"))==0 ){
//
//                    s.setRegistrado(false);
//                }else{
//                    s.setRegistrado(true);
//                }
//                //s.setRegistrado( isRegistradoMedico(s.getCodMedico()));
//
//
//                lista.add(s);
//            } while (c.moveToNext());
//            c.close();
//            db.close();
//        }
//        return lista;
//    }
//    public ArrayList<SalidaEntrega> getListadoSalidaDespacho(){
//
//        SimpleDateFormat  f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//
//        ArrayList<SalidaEntrega>   lista=new ArrayList<SalidaEntrega>();
//        String selectQuery = " SELECT cod_salida,cod_estado_entregasalida,obs_entrega_salida,fecha_entrega_salida,cod_area,cod_cliente_salida,nro_documento_venta,(select nombre_cliente from clientes c where c.cod_cliente=s.cod_cliente_salida)  as nombre_cliente" +
//                "  FROM salida_entrega s " ;
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
//                s.setNombreCliente( c.getString( c.getColumnIndex("nombre_cliente"))  );
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