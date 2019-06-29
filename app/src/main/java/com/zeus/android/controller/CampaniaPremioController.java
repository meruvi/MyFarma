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

public class CampaniaPremioController extends SQLiteOpenHelper {




    SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
    private static final String DATA_BASE_NAME = "zeusMobil";
    private static final int DATA_BASE_VERSION = Util.DATA_BASE_VERSION_ZEUS;

    public CampaniaPremioController(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);

    }


    public void creandoTabla(String nombreTablaSalida,String sqlCreateTable){

        Log.i("---INICIANDO---", "-----VERIFICANDO TABLA SALIDA "+nombreTablaSalida+"----");


        String selectQuery = " select count(*) as cantidad from sqlite_master where name='"+nombreTablaSalida+"' and type='table' " ;
        Log.i("INFO", selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                int cantidad=c.getInt( c.getColumnIndex("cantidad"));
                if( cantidad==0 ){
                    Log.i("LA TABLA SALIDA "+nombreTablaSalida+":", "NO EXISTE");
                    Log.i("CREANDO TABLA "+nombreTablaSalida, sqlCreateTable);
                    db.execSQL(sqlCreateTable);

                }else {
                    Log.i("LA TABLA "+nombreTablaSalida+":", "------ EXISTE");
                }


            } while (c.moveToNext());
            c.close();
            db.close();
        }
    }
//
//    public long guardarSeguimientoCampaniaCliente(Collection<SeguimientoCampaniaCliente> obj) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete("seguimiento_campania_cliente_temp", "", null);
//        long cantidadInsertada = 0;
//        for(SeguimientoCampaniaCliente seguimientoCampaniaCliente:obj){
//            ContentValues values = new ContentValues();
//            values.put("cod_cliente", seguimientoCampaniaCliente.getCodCliente());
//            values.put("nombre_cliente", seguimientoCampaniaCliente.getNombreCliente());
//            values.put("objetivo", seguimientoCampaniaCliente.getObjetivo());
//            values.put("monto_venta", seguimientoCampaniaCliente.getMontoVenta());
//
//            cantidadInsertada += db.insert("seguimiento_campania_cliente_temp", null,values);
//
//
//        }
//
//
//        return cantidadInsertada;
//    }
//
//
//    public long registrarCampania(Collection<Campania> listado) {
//
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        long cantidadInsertada = 0;
//
//        try{
//
//            db.beginTransaction();
//            db.delete("campanias", "", null);
//            db.delete("campanias_premios", "", null);
//            for(Campania c:listado){
//                ContentValues values = new ContentValues();
//                values.put("cod_campania", c.getCodCampanias());
//                values.put("nombre_campania", c.getNombreCampania());
//                values.put("fecha_inicio",f.format( c.getFechaInicio()));
//                values.put("fecha_final",f.format( c.getFechaFinal()));
//                values.put("crecimiento", c.getCrecimiento());
//                values.put("monto_base", c.getMontoBase());
//                cantidadInsertada += db.insert("campanias", null,values);
//                for(CampaniaPremio  p:c.getPremios()){
//                    ContentValues valuesPre = new ContentValues();
//                    valuesPre.put("cod_campania", p.getCodCampania());
//                    valuesPre.put("nombre_material", p.getNombreMaterial());
//                    valuesPre.put("cod_matpromocional", p.getCodMaterialPromocional());
//                    valuesPre.put("cantidad_premio", p.getCantidadPremio());
//                    valuesPre.put("cod_area_empresa", p.getCodAreaEmpresa());
//                    cantidadInsertada += db.insert("campanias_premios", null,valuesPre);
//
//                }
//                for(CampaniaLinea l:c.getLineas() ){
//                    ContentValues valuesLin = new ContentValues();
//                    valuesLin.put("cod_campania", l.getCodCampania());
//                    valuesLin.put("cod_linea", l.getCodLinea());
//                    cantidadInsertada += db.insert("campania_linea", null,valuesLin);
//                }
//
//                for(CampaniaProducto l:c.getProductos() ){
//                    ContentValues valuesLin = new ContentValues();
//                    valuesLin.put("cod_campania", l.getCodCampania());
//                    valuesLin.put("cod_presentacion", l.getCodPresentacion());
//                    cantidadInsertada += db.insert("campania_producto", null,valuesLin);
//                }
//
//            }
//            db.setTransactionSuccessful();
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally{
//
//            db.endTransaction();
//            db.close();
//        }
//
//
//
//        return cantidadInsertada;
//    }
//
//    public ArrayList<CampaniaPremio> getCampaniaPremio(int codAreaEmpresa) {
//        String selectQuery = "select   cod_campania,nombre_material ,cod_matpromocional,cantidad_premio,cod_area_empresa from campanias_premios  where cod_area_empresa="+codAreaEmpresa+" order by nombre_material ";
//        ArrayList<CampaniaPremio> listado=new ArrayList<CampaniaPremio>();
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//                CampaniaPremio campaniaPremio=new CampaniaPremio();
//                campaniaPremio.setCodCampania(  ( c.getInt(c.getColumnIndex("cod_campania")))   );
//                campaniaPremio.setCodMaterialPromocional(  ( c.getInt(c.getColumnIndex("cod_matpromocional")))   );
//                campaniaPremio.setCantidadPremio(  ( c.getInt(c.getColumnIndex("cantidad_premio")))   );
//                campaniaPremio.setCodAreaEmpresa(  ( c.getInt(c.getColumnIndex("cod_area_empresa")))   );
//                campaniaPremio.setNombreMaterial(( c.getString(c.getColumnIndex("nombre_material"))) );
//                listado.add(campaniaPremio);
//
//
//            } while (c.moveToNext());
//        }
//        db.close();
//        return listado;
//    }
//
//
//    public ArrayList<Campania> getListadoCampanis() {
//
//        SimpleDateFormat  f=new SimpleDateFormat("yyyy-MM-dd");
//        String selectQuery = "select cod_campania,nombre_campania,fecha_inicio,fecha_final from campanias";
//        ArrayList<Campania> listado=new ArrayList<Campania>();
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//
//                Campania campania=new Campania();
//                campania.setNombreCampania(  ( c.getString(c.getColumnIndex("nombre_campania"))) );
//
//                try{
//
//                    campania.setFechaInicio(  f.parse  (( c.getString(c.getColumnIndex("fecha_inicio"))) ) );
//                    campania.setFechaFinal(  f.parse  (( c.getString(c.getColumnIndex("fecha_final"))) ) );
//
//
//                }catch(Exception e){
//                    e.printStackTrace();
//                }
//
//
//
//                listado.add(campania);
//
//
//            } while (c.moveToNext());
//        }
//        db.close();
//        return listado;
//    }
//
//
//    public ArrayList<CampaniaPremio> getCampaniaPremio(int codAreaEmpresa,int cantidadPuntos,int codCampania) {
//
//
//
//        String selectQuery = "select   cod_campania,nombre_material ,cod_matpromocional,cantidad_premio,cod_area_empresa from campanias_premios  where cod_area_empresa="+codAreaEmpresa+" and cantidad_premio<="+cantidadPuntos+"  and cod_campania="+codCampania+" order by nombre_material ";
//        ArrayList<CampaniaPremio> listado=new ArrayList<CampaniaPremio>();
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//                CampaniaPremio campaniaPremio=new CampaniaPremio();
//
//                campaniaPremio.setCodCampania(  ( c.getInt(c.getColumnIndex("cod_campania")))   );
//                campaniaPremio.setCodMaterialPromocional(  ( c.getInt(c.getColumnIndex("cod_matpromocional")))   );
//                campaniaPremio.setCantidadPremio(  ( c.getInt(c.getColumnIndex("cantidad_premio")))   );
//                campaniaPremio.setCodAreaEmpresa(  ( c.getInt(c.getColumnIndex("cod_area_empresa")))   );
//
//                campaniaPremio.setNombreMaterial(( c.getString(c.getColumnIndex("nombre_material"))) );
//
//
//
//                listado.add(campaniaPremio);
//
//
//            } while (c.moveToNext());
//        }
//        db.close();
//        return listado;
//    }
//
//    public ArrayList<Campania> getCampaniaMP(int codAreaEmpresa,int codPresentacion) {
//
//        SimpleDateFormat f5=new SimpleDateFormat("yyyy-MM-dd");
//
//        String selectQuery = "select DISTINCT  cp.cod_campania,nombre_campania,fecha_inicio,fecha_final from campanias_premios cp  inner join campanias cam   " +
//                "  on cp.cod_campania=cam.cod_campania" +
//                " where cp.COD_CAMPANIA in(" +
//                " select c.COD_CAMPANIA from CAMPANIAS c inner join CAMPANIA_PRODUCTO cp on cp.COD_CAMPANIA=c.COD_CAMPANIA" +
//                " where cp.cod_presentacion= " +codPresentacion+" "+
//                " ) and cp.cod_area_empresa= "+codAreaEmpresa;
//
//        ArrayList<Campania> listado=new ArrayList<Campania>();
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if(c.moveToFirst()) {
//            do {
//                Campania cam=new Campania();
//
//                cam.setCodCampanias(  ( c.getInt(c.getColumnIndex("cod_campania")))   );
//                cam.setNombreCampania(  ( c.getString(c.getColumnIndex("nombre_campania")))   );
//                try{
//                    cam.setFechaInicio(  f5.parse( ( c.getString(c.getColumnIndex("fecha_inicio")))    )   );
//                    cam.setFechaFinal(  f5.parse( ( c.getString(c.getColumnIndex("fecha_final")))    )   );
//                }catch(Exception e){
//                    e.printStackTrace();
//
//                }
//
//
//
//
//                listado.add(cam);
//
//
//            } while (c.moveToNext());
//        }
//        c.close();
//        for(Campania  cam:listado ){
//
//
//            selectQuery = " select cp.cod_campania,cam.nombre_campania,cam.fecha_inicio,cam.fecha_final,nombre_material,cantidad_premio from campanias_premios cp  inner join campanias cam" +
//                    "  on cp.cod_campania=cam.cod_campania " +
//                    " where cp.COD_CAMPANIA in("+cam.getCodCampanias()+" ) and cp.cod_area_empresa= " +codAreaEmpresa;
//
//
//            Log.i("INFO", selectQuery);
//            Cursor cPremios = db.rawQuery(selectQuery, null);
//            if(cPremios.moveToFirst()) {
//                do {
//                    CampaniaPremio mp=new CampaniaPremio();
//                    mp.setNombreMaterial(( cPremios.getString(cPremios.getColumnIndex("nombre_material")))  );
//                    mp.setCantidadPremio(( cPremios.getInt(cPremios.getColumnIndex("cantidad_premio")))  );
//                    mp.setCodCampania( cam.getCodCampanias() );
//
//                    cam.getPremios().add( mp );
//
//
//
//
//                } while (cPremios.moveToNext());
//            }
//            cPremios.close();
//
//
//
//
//        }
//
//
//        db.close();
//        return listado;
//    }
//
//    public int getMinimoCantidadPuntos(int codAreaEmpresa) {
//
//
//        int minCantidad=0;
//        String selectQuery = "select   min(cantidad_premio)  as minimo from campanias_premios  where cod_area_empresa="+codAreaEmpresa+" order by nombre_material ";
//
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//
//                minCantidad=( c.getInt(c.getColumnIndex("minimo")))  ;
//
//
//            } while (c.moveToNext());
//        }
//        db.close();
//        return minCantidad;
//    }



    @Override
    public void onCreate(SQLiteDatabase db) {


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