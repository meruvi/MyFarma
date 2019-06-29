package com.zeus.android.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import com.zeus.android.util.Util;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

public class BoletaVisitaController extends SQLiteOpenHelper {

    SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String TAG=BoletaVisitaController.class.getName();
    private static final String DATA_BASE_NAME = "zeusMobil";
    private static final int DATA_BASE_VERSION = Util.DATA_BASE_VERSION_ZEUS;

    String sqlCreateTableBoleta="create table boleta_visita(cod_boleta integer ,cod_personal_hermes integer,cod_personal_zeus integer,cod_ciclo integer,cod_gestion integer,fecha_ciclo datetime,cod_medico integer, nombre_territorio text,nombre_visitador text,nombre_medico text,nombre_linea text,nombre_gestion text,nro_boleta text,direcciones text,telefono text,celular text,nombre_especialidad text,nombre_supervisor text)" ;
    String nombreTableBoleta="boleta_visita";

    String sqlCreateTableBoletaDetalleMuestra="create table boleta_visita_detalle_muestra(cod_boleta integer ,orden integer, nombre_muestra text,cantidad_muestra integer  , nombre_material text,cantidad_material integer,cod_tipo_material integer)" ;
    String nombreTableBoletaDetalleMuestra="boleta_visita_detalle_muestra";

    //String sqlCreateTableBoletaDetalleMaterial="create table boleta_visita_detalle_material(cod_boleta integer ,orden integer, nombre_material text,cantidad_material integer,cod_tipo_material integer)" ;
    //String nombreTableBoletaDetalleMaterial="boleta_visita_detalle_material";


    String sqlCreateTableBoletaVisitaPersonal="create table boleta_visita_personal(cod_boleta integer ,firma blob, latitud double,longitud double,fecha_visita datetime,cod_personal integer,observaciones_boleta text,estado_boleta text)" ;
    String nombreCreateTableBoletaVisitaPersonal="boleta_visita_personal";


    String sqlCreateTableBoletaVisitaPersonalCopia="create table boleta_visita_personal_copia(cod_boleta integer ,firma blob, latitud double,longitud double,fecha_visita datetime,cod_personal integer,observaciones_boleta text,estado_boleta text)" ;
    String nombreCreateTableBoletaVisitaPersonalCopia="boleta_visita_personal_copia";



    String sqlCreateTableBoletaDetalleMuestraPersonal="create table boleta_visita_detalle_personal(cod_boleta integer ,codigo_muestra text, nombre_muestra text,cantidad_muestra integer, cantidad_muestra_personal integer,nombre_material text,cantidad_material integer,cantidad_material_personal integer,cod_tipo_material integer )" ;
    String nombreTableBoletaDetalleMuestraPersonal="boleta_visita_detalle_personal";




    /**
     * @param context ESTE PARAMETRO SE ESPECIFICA EL CONTEXTO.
     */
    public BoletaVisitaController(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);



    }

    public void crearTablasBoletaVisita(){

        creandoTabla(nombreTableBoleta,sqlCreateTableBoleta);
        creandoTabla(nombreTableBoletaDetalleMuestra,sqlCreateTableBoletaDetalleMuestra);
        // creandoTabla(nombreTableBoletaDetalleMaterial,sqlCreateTableBoletaDetalleMaterial);
        creandoTabla(nombreCreateTableBoletaVisitaPersonal,sqlCreateTableBoletaVisitaPersonal);
        creandoTabla(nombreTableBoletaDetalleMuestraPersonal,sqlCreateTableBoletaDetalleMuestraPersonal);

    }


//    public void crearTablasBoletaVisitaCopia(){
//
//        creandoTabla(nombreCreateTableBoletaVisitaPersonalCopia,sqlCreateTableBoletaVisitaPersonalCopia);
//
//    }
//
    @Override
    public void onCreate(SQLiteDatabase arg0) {
        // TODO Auto-generated method stub

    }



    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }


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
//    public ArrayList<BoletaVisita> getListadoMedicoBuscador(String str){
//
//
//        StringBuilder selectQuery=new StringBuilder();
//        selectQuery.append( " select    (select count(*) from visita_medico m where m.cod_boleta=bc.cod_boleta  ) as existe_no_visita, cod_boleta,nombre_medico,nombre_linea,nro_boleta,(select count(*) from boleta_visita_personal b where b.cod_boleta=bc.cod_boleta  ) as existe_boleta,(select distinct estado_boleta from boleta_visita_personal b where b.cod_boleta=bc.cod_boleta  ) as estado_boleta,(select distinct estado_boleta from visita_medico m where m.cod_boleta=bc.cod_boleta  ) as estado_boleta_no,fecha_ciclo from boleta_visita bc where nombre_medico like '"+str+"%'  order by cod_boleta " );
//
//
//        Log.i(TAG, selectQuery.toString());
//        SQLiteDatabase db = this.getReadableDatabase();
//        ArrayList<BoletaVisita>  listado=new ArrayList<BoletaVisita>();
//        Cursor c = db.rawQuery(selectQuery.toString(), null);
//        if (c.moveToFirst()) {
//            do {
//
//
//
//                BoletaVisita boletaVisita=new BoletaVisita();
//                //fecha_ciclo
//                ///boletaVisita.setFechaCiclo(  c.getString( c.getColumnIndex("cod_boleta"))  );
//
//
//                try {
//                    boletaVisita.setFechaCiclo( f.parse(  c.getString( c.getColumnIndex("fecha_ciclo"))  ));
//                } catch (ParseException e1) {
//                    // TODO Auto-generated catch block
//                    e1.printStackTrace();
//                }
//
//
//                boletaVisita.setCodBoleta(  c.getInt( c.getColumnIndex("cod_boleta"))  );
//                boletaVisita.setNombreMedico(  c.getString( c.getColumnIndex("nombre_medico"))  );
//                boletaVisita.setNroBoleta( c.getString( c.getColumnIndex("nro_boleta"))   );
//                boletaVisita.setNombreLinea( c.getString( c.getColumnIndex("nombre_linea"))    );
//                boletaVisita.setExisteBoleta(  c.getInt( c.getColumnIndex("existe_boleta"))  );
//                boletaVisita.setExisteNoVisita(  c.getInt( c.getColumnIndex("existe_no_visita"))  );
//
//
//
//                String estado_boleta=c.getString( c.getColumnIndex("estado_boleta"));
//                estado_boleta=(estado_boleta==null)?"0":estado_boleta;
//                estado_boleta=(estado_boleta.equals(""))?"0":estado_boleta;
//
//
//                String estado_boleta_no=c.getString( c.getColumnIndex("estado_boleta_no"));
//                estado_boleta_no=(estado_boleta_no==null)?"0":estado_boleta_no;
//                estado_boleta_no=(estado_boleta_no.equals(""))?"0":estado_boleta_no;
//
//                if( estado_boleta.equals("0") ){
//                    estado_boleta=estado_boleta_no;
//
//                }
//
//
//
//                boletaVisita.setEstadoBoleta( estado_boleta );
//
//               /* if(estado_boleta.equals("1") ){
//
//                    boletaVisita.setExisteBoleta(  1000  );
//                }
//
//                if(estado_boleta.equals("2") ){
//
//                    boletaVisita.setExisteBoleta(  2000  );
//                }*/
//
//
//                listado.add(boletaVisita);
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
//
//
//
//    public BoletaVisita getListadoMedicoBoleta(int codBoleta){
//
//
//        StringBuilder selectQuery=new StringBuilder();
//        selectQuery.append( " select cod_boleta ,cod_personal_hermes ,cod_personal_zeus ,cod_ciclo,cod_gestion ,fecha_ciclo ,cod_medico , " +
//                " nombre_territorio ,nombre_visitador ,nombre_medico ,nombre_linea ,nombre_gestion ,nro_boleta ," +
//                "direcciones ,telefono ,celular ,nombre_especialidad ,nombre_supervisor from boleta_visita where cod_boleta = "+codBoleta );
//        Log.i(TAG, selectQuery.toString());
//        SQLiteDatabase db = this.getReadableDatabase();
//        ArrayList<BoletaVisita>  listado=new ArrayList<BoletaVisita>();
//
//        BoletaVisita boletaVisita=new BoletaVisita();
//        Cursor c = db.rawQuery(selectQuery.toString(), null);
//        if (c.moveToFirst()) {
//            do {
//
//                boletaVisita.setCodBoleta(  c.getInt( c.getColumnIndex("cod_boleta"))  );
//                boletaVisita.setCodPersonalHermes(  c.getInt( c.getColumnIndex("cod_personal_hermes"))  );
//                boletaVisita.setCodPersonalZeus(  c.getInt( c.getColumnIndex("cod_personal_zeus"))  );
//                boletaVisita.setCodCiclo(  c.getInt( c.getColumnIndex("cod_ciclo"))  );
//                boletaVisita.setCodGestion(  c.getInt( c.getColumnIndex("cod_gestion"))  );
//                try {
//                    boletaVisita.setFechaCiclo(  f.parse(   c.getString( c.getColumnIndex("fecha_ciclo")) ) );
//                } catch (ParseException e1) {
//
//                    e1.printStackTrace();
//                }
//
//                boletaVisita.setCodMedico(  c.getInt( c.getColumnIndex("cod_medico"))  );
//                boletaVisita.setNombreTerritorio(  c.getString( c.getColumnIndex("nombre_territorio"))  );
//                boletaVisita.setNombreVisitador(  c.getString( c.getColumnIndex("nombre_visitador"))  );
//                boletaVisita.setNombreMedico(  c.getString( c.getColumnIndex("nombre_medico"))  );
//                boletaVisita.setNombreLinea(  c.getString( c.getColumnIndex("nombre_linea"))  );
//                boletaVisita.setNombreGestion(  c.getString( c.getColumnIndex("nombre_gestion"))  );
//                boletaVisita.setNroBoleta(  c.getString( c.getColumnIndex("nro_boleta"))  );
//                boletaVisita.setDirecciones(  c.getString( c.getColumnIndex("direcciones"))  );
//                boletaVisita.setTelefono(  c.getString( c.getColumnIndex("telefono"))  );
//                boletaVisita.setCelular(  c.getString( c.getColumnIndex("celular"))  );
//                boletaVisita.setNombreEspecialidad(  c.getString( c.getColumnIndex("nombre_especialidad"))  );
//                boletaVisita.setNombreSupervisor(  c.getString( c.getColumnIndex("nombre_supervisor"))  );
//                String sql=" select cod_boleta,orden,nombre_muestra,cantidad_muestra,nombre_material,cantidad_material,cod_tipo_material,codigo_muestra,( select cantidad_muestra from boleta_visita_detalle_personal bp where bp.cod_boleta=cd.cod_boleta and bp.codigo_muestra=cd.codigo_muestra  ) as cantidad_muestra_personal,( select cantidad_material from boleta_visita_detalle_personal bp where bp.cod_boleta=cd.cod_boleta and bp.codigo_muestra=cd.codigo_muestra  ) as cantidad_material_personal  from boleta_visita_detalle_muestra cd where cod_boleta="+codBoleta+" order by orden ";
//                //String sql=" select   bmd.cod_boleta,orden,bmd.nombre_muestra,cd.cantidad_muestra,bmd.nombre_material,cd.cantidad_material,bmd.cod_tipo_material,bmd.codigo_muestra,bmd.cantidad_muestra as  cantidad_muestra_personal , bmd.cantidad_material as cantidad_material_personal  from  boleta_visita_detalle_personal bmd left join boleta_visita_detalle_muestra cd      on  bmd.cod_boleta=cd.cod_boleta and bmd.codigo_muestra=cd.codigo_muestra where bmd.cod_boleta="+codBoleta+" order by orden   ";
//                Log.i("sql",sql);
//                int orden=0;
//                Cursor cDetalle = db.rawQuery(sql, null);
//                if (cDetalle.moveToFirst()) {
//                    do {
//                        BoletaVisitaDetalleMuestra boletaVisitaDetalleMuestra = new BoletaVisitaDetalleMuestra();
//
//                        boletaVisitaDetalleMuestra.setCodBoleta(cDetalle.getInt(cDetalle.getColumnIndex("cod_boleta")));
//                        boletaVisitaDetalleMuestra.setOrden(cDetalle.getInt(cDetalle.getColumnIndex("orden")));
//                        boletaVisitaDetalleMuestra.setNombreMuestra(cDetalle.getString(cDetalle.getColumnIndex("nombre_muestra")));
//
//                        //cantidad_muestra_personal
//
//                        Log.i("cantidad_muestra:1---",cDetalle.getInt(cDetalle.getColumnIndex("cantidad_muestra"))+"");
//                        Log.i("cantidad_muestra:2---",cDetalle.getInt(cDetalle.getColumnIndex("cantidad_muestra_personal"))+"");
//
//                        if( cDetalle.getInt(cDetalle.getColumnIndex("cantidad_muestra_personal"))==0 ){
//                            boletaVisitaDetalleMuestra.setCantidadMuestra(cDetalle.getInt(cDetalle.getColumnIndex("cantidad_muestra")));
//
//                        }else{
//                            boletaVisitaDetalleMuestra.setCantidadMuestra(cDetalle.getInt(cDetalle.getColumnIndex("cantidad_muestra_personal")));
//
//                        }
//
//
//
//
//                        if( cDetalle.getInt(cDetalle.getColumnIndex("cantidad_material_personal"))==0 ){
//                            boletaVisitaDetalleMuestra.setCantidadMaterial(cDetalle.getInt(cDetalle.getColumnIndex("cantidad_material")));
//
//                        }else{
//                            boletaVisitaDetalleMuestra.setCantidadMaterial(cDetalle.getInt(cDetalle.getColumnIndex("cantidad_material_personal")));
//
//                        }
//
//                        boletaVisitaDetalleMuestra.setNombreMaterial(cDetalle.getString(cDetalle.getColumnIndex("nombre_material")));
//                        // boletaVisitaDetalleMuestra.setCantidadMaterial(cDetalle.getInt(cDetalle.getColumnIndex("cantidad_material")));
//                        boletaVisitaDetalleMuestra.setTipoMaterial(cDetalle.getInt(cDetalle.getColumnIndex("cod_tipo_material")));
//                        boletaVisitaDetalleMuestra.setCodigoMuestra(cDetalle.getString(cDetalle.getColumnIndex("codigo_muestra")));
//                        orden=boletaVisitaDetalleMuestra.getOrden();
//                        boletaVisita.getDetalle().add(boletaVisitaDetalleMuestra);
//
//
//                    } while (cDetalle.moveToNext());
//                    cDetalle.close();
//
//
//                }
//
//
//
//
//
//
//                sql=" select cod_boleta,codigo_muestra,nombre_muestra,cantidad_muestra_personal,nombre_material,cantidad_material_personal,cod_tipo_material from boleta_visita_detalle_personal bdp where  bdp.cod_boleta="+codBoleta+" and cod_tipo_material=4";
//                Log.i("sql",sql);
//
//                cDetalle = db.rawQuery(sql, null);
//                if (cDetalle.moveToFirst()) {
//                    do {orden++;
//                        BoletaVisitaDetalleMuestra boletaVisitaDetalleMuestra = new BoletaVisitaDetalleMuestra();
//
//                        boletaVisitaDetalleMuestra.setCodBoleta(cDetalle.getInt(cDetalle.getColumnIndex("cod_boleta")));
//                        boletaVisitaDetalleMuestra.setOrden(orden);
//                        boletaVisitaDetalleMuestra.setNombreMuestra(cDetalle.getString(cDetalle.getColumnIndex("nombre_muestra")));
//
//                        boletaVisitaDetalleMuestra.setCantidadMuestra(cDetalle.getInt(cDetalle.getColumnIndex("cantidad_muestra_personal")));
//
//                        boletaVisitaDetalleMuestra.setCantidadMaterial(cDetalle.getInt(cDetalle.getColumnIndex("cantidad_material_personal")));
//                        boletaVisitaDetalleMuestra.setNombreMaterial(cDetalle.getString(cDetalle.getColumnIndex("nombre_material")));
//
//
//
//
//                        boletaVisitaDetalleMuestra.setTipoMaterial(cDetalle.getInt(cDetalle.getColumnIndex("cod_tipo_material")));
//                        boletaVisitaDetalleMuestra.setCodigoMuestra(cDetalle.getString(cDetalle.getColumnIndex("codigo_muestra")));
//
//                        boletaVisita.getDetalle().add(boletaVisitaDetalleMuestra);
//
//
//                    } while (cDetalle.moveToNext());
//                    cDetalle.close();
//
//
//                }
//
//            } while (c.moveToNext());
//            c.close();
//            db.close();
//        }
//
//        return boletaVisita;
//    }
//
//
//    public BoletaVisitaMedicoPersonal getBoleta(int codBoleta){
//
//
//        StringBuilder selectQuery=new StringBuilder();
//        selectQuery.append( "select firma,fecha_visita,observaciones_boleta from boleta_visita_personal where cod_boleta="+codBoleta  );
//        Log.i(TAG, selectQuery.toString());
//        SQLiteDatabase db = this.getReadableDatabase();
//        BoletaVisitaMedicoPersonal  boletaVisita=new BoletaVisitaMedicoPersonal();
//
//
//        Cursor c = db.rawQuery(selectQuery.toString(), null);
//        if (c.moveToFirst()) {
//            do {
//
//                boletaVisita.setCodBoleta( codBoleta  );
//                boletaVisita.setFirma(  c.getBlob( c.getColumnIndex("firma") ) );
//
//                try {
//                    boletaVisita.setFecha_visita(  f.parse(   c.getString( c.getColumnIndex("fecha_visita")) ) );
//                } catch (ParseException e1) {
//                    e1.printStackTrace();
//                }
//                boletaVisita.setObservacion( c.getString( c.getColumnIndex("observaciones_boleta"))   );
//
//
//
//            } while (c.moveToNext());
//            c.close();
//            db.close();
//        }
//
//        return boletaVisita;
//    }
//
//
//
//    public void registroBoletaVisita(Collection<BoletaVisita> listado){
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        try
//        {
//            db.beginTransaction();
//            db.delete("boleta_visita", null, null);
//            db.delete("boleta_visita_detalle_muestra", null, null);
//
//            //android.webkit.WebView
//            for(BoletaVisita c:listado){
//                ContentValues values = new ContentValues();
//                values.put("cod_boleta", c.getCodBoleta());
//                values.put("cod_personal_hermes", c.getCodPersonalHermes());
//                values.put("cod_personal_zeus", c.getCodPersonalZeus());
//                values.put("cod_ciclo", c.getCodCiclo());
//
//                //values.put("cod_ciclo", c.getCodCiclo());
//
//
//                values.put("cod_gestion", c.getCodGestion());
//                if( c.getFechaCiclo()!=null ){
//                    values.put("fecha_ciclo", f.format( c.getFechaCiclo()  ));
//                }
//                values.put("cod_medico", c.getCodMedico());
//                values.put("nombre_territorio", c.getNombreTerritorio());
//                values.put("nombre_visitador", c.getNombreVisitador());
//                values.put("nombre_medico", c.getNombreMedico());
//                values.put("nombre_linea", c.getNombreLinea());
//                values.put("nombre_gestion", c.getNombreGestion());
//                values.put("nro_boleta", c.getNroBoleta());
//                values.put("direcciones", c.getDirecciones());
//                values.put("telefono", c.getTelefono());
//                values.put("celular", c.getCelular());
//                values.put("nombre_especialidad", c.getNombreEspecialidad());
//                values.put("nombre_supervisor", c.getNombreSupervisor());
//                db.insert("boleta_visita", null,values);
//
//                for(BoletaVisitaDetalleMuestra detalle:c.getDetalle() ){
//
//                    ContentValues data = new ContentValues();
//                    data.put("cod_boleta", detalle.getCodBoleta());
//                    data.put("orden", detalle.getOrden());
//                    data.put("nombre_muestra", detalle.getNombreMuestra());
//                    data.put("cantidad_muestra", detalle.getCantidadMuestra());
//                    data.put("nombre_material", detalle.getNombreMaterial());
//                    data.put("cantidad_material", detalle.getCantidadMaterial());
//                    data.put("cod_tipo_material", detalle.getTipoMaterial());
//                    data.put("codigo_muestra", detalle.getCodigoMuestra());
//                    //  Log.i(TAG,detalle.getCodigoMuestra()+"");
//
//                    db.insert("boleta_visita_detalle_muestra", null,data);
//
//                }
//
//                ContentValues dataUpdate = new ContentValues();
//
//                dataUpdate.put("estado_boleta", c.getEstadoBoleta());
//
//
//                String sql2=" cod_boleta in("+c.getCodBoleta()+") ";
//                db.update("boleta_visita_personal", dataUpdate, sql2,null);
//
//
//                ContentValues dataUpdate1 = new ContentValues();
//
//                dataUpdate1.put("estado_boleta", c.getEstadoBoleta());
//
//
//                sql2=" cod_boleta in("+c.getCodBoleta()+") ";
//                db.update("visita_medico", dataUpdate1, sql2,null);
//
//
//
//
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
//    public void registroBoletaVisitaCopiagenera(Collection<BoletaVisita> listado){
//
//
//    }
//
//
//    public void registroBoletaVisitaCopia(Collection<BoletaVisita> listado){
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        try
//        {
//            db.beginTransaction();
//            db.delete("boleta_visita", null, null);
//            db.delete("boleta_visita_detalle_muestra", null, null);
//
//
//            for(BoletaVisita c:listado){
//                ContentValues values = new ContentValues();
//                values.put("cod_boleta", c.getCodBoleta());
//                values.put("cod_personal_hermes", c.getCodPersonalHermes());
//                values.put("cod_personal_zeus", c.getCodPersonalZeus());
//                values.put("cod_ciclo", c.getCodCiclo());
//
//
//
//
//                values.put("cod_gestion", c.getCodGestion());
//                if( c.getFechaCiclo()!=null ){
//                    values.put("fecha_ciclo", f.format( c.getFechaCiclo()  ));
//                }
//                values.put("cod_medico", c.getCodMedico());
//                values.put("nombre_territorio", c.getNombreTerritorio());
//                values.put("nombre_visitador", c.getNombreVisitador());
//                values.put("nombre_medico", c.getNombreMedico());
//                values.put("nombre_linea", c.getNombreLinea());
//                values.put("nombre_gestion", c.getNombreGestion());
//                values.put("nro_boleta", c.getNroBoleta());
//                values.put("direcciones", c.getDirecciones());
//                values.put("telefono", c.getTelefono());
//                values.put("celular", c.getCelular());
//                values.put("nombre_especialidad", c.getNombreEspecialidad());
//                values.put("nombre_supervisor", c.getNombreSupervisor());
//                db.insert("boleta_visita", null,values);
//
//                for(BoletaVisitaDetalleMuestra detalle:c.getDetalle() ){
//
//                    ContentValues data = new ContentValues();
//                    data.put("cod_boleta", detalle.getCodBoleta());
//                    data.put("orden", detalle.getOrden());
//                    data.put("nombre_muestra", detalle.getNombreMuestra());
//                    data.put("cantidad_muestra", detalle.getCantidadMuestra());
//                    data.put("nombre_material", detalle.getNombreMaterial());
//                    data.put("cantidad_material", detalle.getCantidadMaterial());
//                    data.put("cod_tipo_material", detalle.getTipoMaterial());
//                    db.insert("boleta_visita_detalle_muestra", null,data);
//
//                }
//
//                ContentValues dataUpdate = new ContentValues();
//
//                dataUpdate.put("estado_boleta", c.getEstadoBoleta());
//
//
//                String sql2=" cod_boleta in("+c.getCodBoleta()+") ";
//                db.update("boleta_visita_personal", dataUpdate, sql2,null);
//
//
//
//
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
//    public void registroVisitaMedicoDetalle(BoletaVisita c,Context ctx){
//        Log.i(TAG,"registroVisitaMedicoDetalle:"+c.getDetalle().size());
//        SQLiteDatabase db = this.getWritableDatabase();
//
//
//        try
//        {
//            db.beginTransaction();
//            ContentValues data = new ContentValues();
//
//            java.util.Date fecha=new java.util.Date();
//
//            data.put("fecha_visita", f.format( new java.util.Date() ));
//            String sql=" cod_boleta in("+c.getCodBoleta()+") ";
//            Log.i("boleta",sql);
//            int resultado=db.update("boleta_visita_personal", data, sql,null);
//            if(resultado==0){
//                data.put("estado_boleta", "0");
//                data.put("latitud", 0);
//                data.put("longitud", 0);
//                data.put("cod_personal", 0);
//                data.put("cod_boleta", c.getCodBoleta());
//
//                if( fecha!=null ){
//                    data.put("fecha_visita", f.format( fecha ));
//                }
//                db.insert("boleta_visita_personal", null,data);
//            }
//
//            db.delete("boleta_visita_detalle_personal", "cod_boleta in("+c.getCodBoleta()+")",null);
//
//            for(BoletaVisitaDetalleMuestra  beanMuestra:c.getDetalle()){
//
//                ContentValues valuesMuestra = new ContentValues();
//                valuesMuestra.put("cod_boleta",c.getCodBoleta());
//                valuesMuestra.put("codigo_muestra",beanMuestra.getCodigoMuestra());
//                valuesMuestra.put("nombre_muestra",beanMuestra.getNombreMuestra());
//                valuesMuestra.put("cantidad_muestra",beanMuestra.getCantidadMuestra());
//                valuesMuestra.put("cantidad_muestra_personal",beanMuestra.getCantidadMuestra());
//                valuesMuestra.put("nombre_material",beanMuestra.getNombreMaterial());
//                valuesMuestra.put("cantidad_material",beanMuestra.getCantidadMaterial());
//                valuesMuestra.put("cantidad_material_personal",beanMuestra.getCantidadMaterial());
//                valuesMuestra.put("cod_tipo_material",beanMuestra.getTipoMaterial());
//
//                db.insert("boleta_visita_detalle_personal", null,valuesMuestra);
//            }
//
//            db.setTransactionSuccessful();
//
//
//        }catch(Exception e){
//            Toast.makeText(ctx,e.getMessage(),Toast.LENGTH_LONG).show();
//            e.printStackTrace();
//
//        }finally{
//            db.endTransaction();
//        }
//        Toast.makeText(ctx,"Se registro Correctamente.",Toast.LENGTH_LONG).show();
//        db.close();
//
//
//    }
//
//
//    public void registroVisitaMedico(BoletaVisita c, Bitmap firma, String obs){
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        ByteArrayOutputStream stream=null;
//        byte[] byteArray=null;
//        try
//        {
//            db.beginTransaction();
//
//
//            db.delete("boleta_visita_personal", "cod_boleta in("+c.getCodBoleta()+")",null);
//
//
//
//            java.util.Date  fecha=new java.util.Date();
//
//            ContentValues values = new ContentValues();
//            values.put("cod_boleta", c.getCodBoleta());
//
//
//
//
//
//            final int maxSize = 300;
//            int outWidth;
//            int outHeight;
//            int inWidth = firma.getWidth();
//            int inHeight = firma.getHeight();
//            if(inWidth > inHeight){
//                outWidth = maxSize;
//                outHeight = (inHeight * maxSize) / inWidth;
//            } else {
//                outHeight = maxSize;
//                outWidth = (inWidth * maxSize) / inHeight;
//            }
//
//            firma= Bitmap.createScaledBitmap(firma, outWidth, outHeight, false);
//
//
//
//
//            stream = new ByteArrayOutputStream();
//
//            firma.compress(Bitmap.CompressFormat.PNG, 100, stream);
//            //firma.compress(/)
//
//
//
//
//
//
//
//
//
//
//            byteArray = stream.toByteArray();
//
//
//
//
//            //estado_boleta
//            values.put("estado_boleta", "0");
//
//            values.put("firma ", byteArray);
//            values.put("latitud", 0);
//            values.put("longitud", 0);
//            values.put("cod_personal", 0);
//            values.put("cod_personal", 0);
//            //observaciones_boleta
//
//            values.put("observaciones_boleta", obs);
//
//            if( fecha!=null ){
//                values.put("fecha_visita", f.format( fecha ));
//            }
//
//
//            db.insert("boleta_visita_personal", null,values);
//
//
//
//
//
//            db.setTransactionSuccessful();
//
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally{
//            db.endTransaction();
//        }
//        firma=null;
//        stream=null;
//        byteArray=null;
//        db.close();
//    }
//
//
//    public void updateLatitudLongitud(BoletaVisitaMedicoPersonal boletaVisitaMedicoPersonal) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues data = new ContentValues();
//
//
//
//        data.put("latitud", boletaVisitaMedicoPersonal.getLatitud());
//        data.put("longitud", boletaVisitaMedicoPersonal.getLongitud());
//
//        String sql2=" cod_boleta in("+boletaVisitaMedicoPersonal.getCodBoleta()+") ";
//        db.update("boleta_visita_personal", data, sql2,null);
//
//
//    }
//
//    public void updateObervaciones(int codBoleta,String obs) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues data = new ContentValues();
//
//
//
//        data.put("observaciones_boleta", obs);
//
//
//        String sql2=" cod_boleta in("+codBoleta+") ";
//        db.update("boleta_visita_personal", data, sql2,null);
//
//
//    }
//
//
//    public ArrayList<BoletaVisitaMedicoPersonal> getListadoVisitaMedico(){
//
//
//        StringBuilder selectQuery=new StringBuilder();
//        selectQuery.append( "select  cod_boleta ,firma, latitud ,longitud ,fecha_visita ,cod_personal,observaciones_boleta from boleta_visita_personal where estado_boleta='0'" );
//
//
//        Log.i(TAG, selectQuery.toString());
//        SQLiteDatabase db = this.getReadableDatabase();
//        ArrayList<BoletaVisitaMedicoPersonal>  listado=new ArrayList<BoletaVisitaMedicoPersonal>();
//        Cursor c = db.rawQuery(selectQuery.toString(), null);
//        if (c.moveToFirst()) {
//            do {
//
//
//                BoletaVisitaMedicoPersonal boletaVisita=new BoletaVisitaMedicoPersonal();
//
//                boletaVisita.setCodBoleta(  c.getInt( c.getColumnIndex("cod_boleta"))  );
//                boletaVisita.setFirma( c.getBlob( c.getColumnIndex("firma") ) );
//
//                boletaVisita.setLatitud(  c.getDouble( c.getColumnIndex("latitud"))  );
//                boletaVisita.setLongitud(  c.getDouble( c.getColumnIndex("longitud"))  );
//
//                boletaVisita.setCodPersonal(  c.getInt( c.getColumnIndex("cod_personal"))  );
//
//                boletaVisita.setObservacion(  c.getString( c.getColumnIndex("observaciones_boleta"))  );
//
//                try {
//                    boletaVisita.setFecha_visita(  f.parse(   c.getString( c.getColumnIndex("fecha_visita")) ) );
//                } catch (ParseException e1) {
//
//                    e1.printStackTrace();
//                }
//                String sqlDetalle="  select  codigo_muestra,nombre_muestra,cantidad_muestra,nombre_material,cantidad_material from boleta_visita_detalle_personal where cod_boleta="+boletaVisita.getCodBoleta();
//                // Log.i("sqlDetalle",sqlDetalle);
//
//
//                Cursor cursorDetalleMuestra = db.rawQuery(sqlDetalle, null);
//
//                if( cursorDetalleMuestra.getCount()>0  ){
//                    if (cursorDetalleMuestra.moveToFirst()) {
//                        do {
//
//                            BoletaVisitaMedicoPersonalDetalle beanDetalle=new BoletaVisitaMedicoPersonalDetalle();
//                            beanDetalle.setCodBoleta( boletaVisita.getCodBoleta() );
//                            beanDetalle.setCodigoMuestra(  cursorDetalleMuestra.getString( cursorDetalleMuestra.getColumnIndex("codigo_muestra"))  );
//                            beanDetalle.setNombreMuestra(  cursorDetalleMuestra.getString( cursorDetalleMuestra.getColumnIndex("nombre_muestra"))  );
//                            beanDetalle.setCantidadMuestra(  cursorDetalleMuestra.getInt( cursorDetalleMuestra.getColumnIndex("cantidad_muestra"))  );
//                            beanDetalle.setNombreMaterial(  cursorDetalleMuestra.getString( cursorDetalleMuestra.getColumnIndex("nombre_material"))  );
//                            beanDetalle.setCantidadMaterial(  cursorDetalleMuestra.getInt( cursorDetalleMuestra.getColumnIndex("cantidad_material"))  );
//                            //   Log.i("beanDetalle:",beanDetalle.getNombreMuestra());
//                            boletaVisita.getDetalle().add(beanDetalle);
//
//                        } while (cursorDetalleMuestra.moveToNext());
//
//
//                    }
//
//
//
//                }
//                cursorDetalleMuestra.close();
//
//
//
//
//
//
//
//                listado.add(boletaVisita);
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
//
//
//    public String getSQL(){
//
//
//        StringBuilder selectQuery=new StringBuilder();
//        selectQuery.append( "SELECT  sql FROM sqlite_master where tbl_name='boleta_visita_personal'" );
//        String sqlEstructura="";
//
//        Log.i(TAG, selectQuery.toString());
//        SQLiteDatabase db = this.getReadableDatabase();
//        ArrayList<BoletaVisitaMedicoPersonal>  listado=new ArrayList<BoletaVisitaMedicoPersonal>();
//        Cursor c = db.rawQuery(selectQuery.toString(), null);
//        if (c.moveToFirst()) {
//            do {
//                //sqlEstructura
//
//                c.getString( c.getColumnIndex("observaciones_boleta"));
//
//                /*BoletaVisitaMedicoPersonal boletaVisita=new BoletaVisitaMedicoPersonal();
//
//                boletaVisita.setCodBoleta(  c.getInt( c.getColumnIndex("cod_boleta"))  );
//                boletaVisita.setFirma( c.getBlob( c.getColumnIndex("firma") ) );
//
//                boletaVisita.setLatitud(  c.getDouble( c.getColumnIndex("latitud"))  );
//                boletaVisita.setLongitud(  c.getDouble( c.getColumnIndex("longitud"))  );
//
//                boletaVisita.setCodPersonal(  c.getInt( c.getColumnIndex("cod_personal"))  );
//
//                boletaVisita.setObservacion(  c.getString( c.getColumnIndex("observaciones_boleta"))  );
//
//                try {
//                    boletaVisita.setFecha_visita(  f.parse(   c.getString( c.getColumnIndex("fecha_visita")) ) );
//                } catch (ParseException e1) {
//
//                    e1.printStackTrace();
//                }
//
//                listado.add(boletaVisita);
//                */
//
//
//            } while (c.moveToNext());
//            c.close();
//            db.close();
//        }
//
//        return sqlEstructura;
//    }
//
//
//
//    public ArrayList<BoletaVisitaMedicoPersonal3> getListadoVisitaMedicoCopia(){
//
//        //14 dic
//        StringBuilder selectQuery=new StringBuilder();
//        selectQuery.append( "select  cod_boleta ,firma, latitud ,longitud ,fecha_visita ,cod_personal,observaciones_boleta,estado_boleta from boleta_visita_personal where fecha_visita<='2016-12-13'" );
//        Log.i(TAG, selectQuery.toString());
//        SQLiteDatabase db = this.getReadableDatabase();
//        ArrayList<BoletaVisitaMedicoPersonal3>  listado=new ArrayList<BoletaVisitaMedicoPersonal3>();
//        Cursor c = db.rawQuery(selectQuery.toString(), null);
//        if (c.moveToFirst()) {
//            do {
//                BoletaVisitaMedicoPersonal3 boletaVisita=new BoletaVisitaMedicoPersonal3();
//                boletaVisita.setCodBoleta(  c.getInt( c.getColumnIndex("cod_boleta"))  );
//                boletaVisita.setFirma( c.getBlob( c.getColumnIndex("firma") ) );
//                boletaVisita.setLatitud(  c.getDouble( c.getColumnIndex("latitud"))  );
//                boletaVisita.setLongitud(  c.getDouble( c.getColumnIndex("longitud"))  );
//                boletaVisita.setCodPersonal(  c.getInt( c.getColumnIndex("cod_personal"))  );
//                boletaVisita.setObservacion(  c.getString( c.getColumnIndex("observaciones_boleta"))  );
//                boletaVisita.setEstadoBoleta(  c.getInt( c.getColumnIndex("estado_boleta"))  );
//
//                try {
//                    boletaVisita.setFecha_visita(  f.parse(   c.getString( c.getColumnIndex("fecha_visita")) ) );
//                } catch (ParseException e1) {
//
//                    e1.printStackTrace();
//                }
//                listado.add(boletaVisita);
//            } while (c.moveToNext());
//
//
//            c.close();
//            db.close();
//
//            for(BoletaVisitaMedicoPersonal3 bean:listado){
//                //registroVisitaMedicoCopia();
//                Log.i("Boletas:",bean.getObservacion()+":"+bean.getFecha_visita());
//                registroVisitaMedicoCopia(bean);
//
//            }
//            updateBoletaCopia();
//
//
//        }
//
//        return listado;
//    }
//
//    public int getListadoVisitaMedicoCopiaCount(){
//
//        int cantidad=0;
//        StringBuilder selectQuery=new StringBuilder();
//        selectQuery.append( "select  count(*) as filas from boleta_visita_personal_copia " );
//        Log.i(TAG, selectQuery.toString());
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery.toString(), null);
//        if (c.moveToFirst()) {
//            do {
//                cantidad=c.getInt( c.getColumnIndex("filas"));
//            } while (c.moveToNext());
//
//
//            c.close();
//            db.close();
//        }
//
//        return cantidad;
//    }
//
//    public void registroVisitaMedicoCopia(BoletaVisitaMedicoPersonal3 c){
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        ByteArrayOutputStream stream=null;
//        byte[] byteArray=null;
//        try
//        {
//            db.beginTransaction();
//            // db.delete("boleta_visita_personal_copia", "cod_boleta in("+c.getCodBoleta()+")",null);
//
//            ContentValues values = new ContentValues();
//            values.put("cod_boleta", c.getCodBoleta());
//          /*  final int maxSize = 300;
//            int outWidth;
//            int outHeight;
//            int inWidth = firma.getWidth();
//            int inHeight = firma.getHeight();
//            if(inWidth > inHeight){
//                outWidth = maxSize;
//                outHeight = (inHeight * maxSize) / inWidth;
//            } else {
//                outHeight = maxSize;
//                outWidth = (inWidth * maxSize) / inHeight;
//            }
//
//            firma= Bitmap.createScaledBitmap(firma, outWidth, outHeight, false);
//            stream = new ByteArrayOutputStream();
//            firma.compress(Bitmap.CompressFormat.PNG, 100, stream);
//            byteArray = stream.toByteArray();
//            */
//
//            values.put("estado_boleta", c.getEstadoBoleta());
//            values.put("firma ", c.getFirma());
//            values.put("latitud", c.getLatitud());
//            values.put("longitud", c.getLongitud());
//            values.put("cod_personal", c.getCodPersonal());
//
//            values.put("observaciones_boleta", c.getObservacion());
//            if( c.getFecha_visita()!=null ){
//                values.put("fecha_visita", f.format( c.getFecha_visita() ));
//            }
//            db.insert("boleta_visita_personal_copia", null,values);
//            db.setTransactionSuccessful();
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally{
//            db.endTransaction();
//        }
//        //  firma=null;
//        //  stream=null;
//        //  byteArray=null;
//        db.close();
//    }
//
//
//    public void updateBoletaCopia(){
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        try
//        {
//            db.beginTransaction();
//
//            String sql=" update boleta_visita_personal set cod_personal=cod_boleta  where fecha_visita<='2016-12-13 23:59:59'  ";
//            db.execSQL(sql);
//            sql=" update boleta_visita_personal set cod_boleta=cod_boleta*(-1)  where fecha_visita<='2016-12-13 23:59:59'  ";
//            db.execSQL(sql);
//
//            db.setTransactionSuccessful();
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally{
//            db.endTransaction();
//        }
//
//        db.close();
//    }



}