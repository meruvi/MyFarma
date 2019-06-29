package com.zeus.android.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.zeus.android.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OfertaController extends SQLiteOpenHelper {


    private static final String DATA_BASE_NAME = "zeusMobil";
    private static final int DATA_BASE_VERSION = Util.DATA_BASE_VERSION_ZEUS;
    private static final String TAG = OfertaController.class.getCanonicalName();



    public OfertaController(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        //Log.i("oldVersion:", oldVersion+"");
        //Log.i("newVersion:", newVersion+"");


        //String SQLUpdateV2  = "alter table campanias_ofertasproductos add column cod_presentacion_bonifi integer";
        //if (oldVersion == 1 && newVersion == 2)
        //      db.execSQL(SQLUpdateV2);




    }


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
//					Log.i("VERIFICANDO TABLA: "+nombreTabla+" COLUMNA :"+nombreColumnaVerificar, "EXISTE");
                    return true;
                }
            } while (c.moveToNext());
        }
        c.close();
        db.close();
//		Log.i("VERIFICANDO TABLA: "+nombreTabla+" COLUMNA :"+nombreColumnaVerificar, "NO EXISTE");
        return false;
    }
//
//    public long actualizarCampaniasOfertas(Collection<CampaniasOfertas> listado) {
//
//
//
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        long cantidadInsertada = 0;
//        try
//        {
//            db.beginTransaction();
//            db.delete("oferta_agencia", null, null);
//            db.delete("campanias_ofertasproductos", null, null);
//            db.delete("campanias_ofertas", null, null);
//            db.delete("campanias_ofertas_premios", null, null);
//            db.delete("campanias_oferta_condibonif", null, null);
//            db.delete("campanias_ofertasproductosbonifi", null, null);
//
//
//            for(CampaniasOfertas c:listado){
//
//                ContentValues values = new ContentValues();
//                values.put("cod_oferta", c.getCodoferta());
//                values.put("nombre_oferta", c.getNombreoferta());
//                values.put("cod_campania", c.getCodcampania());
//                values.put("fecha_inicio", c.getFechainicio());
//                values.put("fecha_final", c.getFechafinal());
//                values.put("cod_estadoscampaniasofertas", c.getCodestadoscampaniasofertas());
//                values.put("monto_oferta", c.getMontooferta());
//                values.put("cod_matpromocional", c.getCodmatpromocional());
//                values.put("porcentaje_descuentoferta", c.getPorcentajedescuentoferta());
//                values.put("cod_tipoventa", c.getCodtipoventa());
//                values.put("cod_estadocampaniapedido", c.getCodestadocampaniapedido());
//                values.put("cod_tipo_precio", c.getCodtipoprecio());
//                values.put("cod_tipooferta", c.getCodtipooferta());
//                //values.put("cod_tipooferta", c.getCodtipooferta());
//
//
//
//                cantidadInsertada = db.insert("campanias_ofertas", null,values);
//                actualizarCampaniasOfertasDetalle( c.getDetalle(),db );
//                actualizarCampaniasOfertasAgencias(c.getAgencias(),db );
//                actualizarCampaniasOfertasPremios(c.getListCampaniaOfertaPremio() ,db);
//                actualizarCampaniasOfertasCondiciones(c.getCampaniaOfertaCondiBonifDetalle() ,db);
//
//                actualizarCampaniasOfertasTipoCliente(c.getListCampaniaOfertaTipoCliente(),db);
//                actualizarCampaniasOfertasCadena(c.getListCampaniaOfertaCadena(),db);
//
//
//
//
//                cantidadInsertada++;
//            }
//            db.setTransactionSuccessful();
//
//
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally{
//            db.endTransaction();
//        }
//
//
//
//
//
//        return cantidadInsertada;
//    }
//
//
//    public long actualizarCampaniasOfertasDetalle(List<CampaniasOfertasproductos> listado, SQLiteDatabase db) {
//
//        //SQLiteDatabase db = this.getWritableDatabase();
//        //	db.execSQL("alter table campanias_ofertasproductos add column cod_presentacion_bonifi integer");
//
//        //db.delete("campanias_ofertasproductos", null, null);
//        long cantidadInsertada = 0;
//        for(CampaniasOfertasproductos c:listado){
//
//            ContentValues values = new ContentValues();
//            values.put("cod_oferta", c.getCodoferta());
//            values.put("cod_presentacion", c.getCodpresentacion());
//            values.put("cod_tipopuntaje", c.getCodtipopuntaje());
//            values.put("producto_obligatorio", c.getProductoobligatorio());
//            values.put("precio_lista", c.getPreciolista());
//            values.put("porcentaje_descuento", c.getPorcentajedescuento());
//            values.put("unidad_oferta", c.getUnidadoferta());
//            values.put("unidad_bonificacion", c.getUnidadbonificacion());
//            values.put("precio", c.getPrecio());
//            values.put("precio_final", c.getPreciofinal());
//            values.put("cantidad_oferta", c.getCantidadoferta());
//            values.put("cantidad_ofertabonificacion", c.getCantidadofertabonificacion());
//            values.put("unidad_minima", c.getUnidadminima());
//            values.put("cod_ofertadetalle", c.getCodofertadetalle());
//            values.put("cantidad_ofertaminima", c.getCantidadofertaminima());
//            values.put("cod_presentacion_bonifi", c.getCodPresentacionBonificacion());
//            values.put("CANTIDAD_UNITARIA_BONIFICACION", c.getCantidadUnitariaBonificacion());
//
//            cantidadInsertada = db.insert("campanias_ofertasproductos", null,values);
//            cantidadInsertada++;
//        }
//        return cantidadInsertada;
//    }
//
//    public long actualizarCampaniasOfertasAgencias(List<OfertAgencia>  listado,SQLiteDatabase db) {
//
//        //SQLiteDatabase db = this.getWritableDatabase();
//
//        long cantidadInsertada = 0;
//        for(OfertAgencia c:listado){
//
//            ContentValues values = new ContentValues();
//            values.put("cod_oferta", c.getCodoferta());
//            values.put("cod_area_empresa", c.getCodareaempresa());
//            cantidadInsertada = db.insert("oferta_agencia", null,values);
//            cantidadInsertada++;
//        }
//        return cantidadInsertada;
//    }
//
//    public long actualizarCampaniasOfertasPremios(List<CampaniaOfertaPremio>  listado,SQLiteDatabase db) {
//
//        //SQLiteDatabase db = this.getWritableDatabase();
//
//        long cantidadInsertada = 0;
//        for(CampaniaOfertaPremio c:listado){
//
//            ContentValues values = new ContentValues();
//            values.put("cod_oferta", c.getCodOferta());
//            values.put("cod_matpromocional", c.getCodMatPromocional());
//            values.put("monto_oferta", c.getMontoOferta());
//
//
//            cantidadInsertada = db.insert("campanias_ofertas_premios", null,values);
//            cantidadInsertada++;
//        }
//        return cantidadInsertada;
//    }
//
//    public long actualizarMaterial(Collection<MatPromocional>  listado) {
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete("mat_promocional", null, null);
//        long cantidadInsertada = 0;
//        for(MatPromocional c:listado){
//
//            ContentValues values = new ContentValues();
//            values.put("cod_matpromocional", c.getCodMatPromocional());
//            values.put("nombre_matpromocional", c.getNombreMatPromocional());
//
//
//
//            cantidadInsertada = db.insert("mat_promocional", null,values);
//            cantidadInsertada++;
//        }
//        return cantidadInsertada;
//    }
//
//
//    public long actualizarCampaniasOfertasBonificacion(List<CampaniasOfertasProductosBonifi>  listado,SQLiteDatabase db) {
//
//        //SQLiteDatabase db = this.getWritableDatabase();
//
//        long cantidadInsertada = 0;
//        for(CampaniasOfertasProductosBonifi c:listado){
//
//            ContentValues values = new ContentValues();
//            values.put("cod_oferta", c.getCodOferta());
//            values.put("cod_presentacion", c.getCodPresentacion());
//            values.put("unidad_bonificacion", c.getUnidadBonificacion());
//            values.put("cod_ofertadetalle", c.getCodOfertaDetalle());
//            cantidadInsertada = db.insert("campanias_ofertasproductosbonifi", null,values);
//            cantidadInsertada++;
//        }
//        return cantidadInsertada;
//    }
//
//
//    public long actualizarCampaniasOfertasCondiciones(List<CampaniaOfertaCondiBonif>  listado,SQLiteDatabase db) {
//
//        //SQLiteDatabase db = this.getWritableDatabase();
//
//        long cantidadInsertada = 0;
//        for(CampaniaOfertaCondiBonif c:listado){
//
//            ContentValues values = new ContentValues();
//            values.put("cod_oferta", c.getCodOferta());
//            values.put("cantidad_minima", c.getCantidadMinima());
//            values.put("cantidad_maxima", c.getCantidadMaxima());
//            values.put("unidad_bonificacion", c.getUnidadBonificacion());
//            cantidadInsertada = db.insert("campanias_oferta_condibonif", null,values);
//            cantidadInsertada++;
//        }
//        return cantidadInsertada;
//    }
//
//    public long actualizarCampaniasOfertasTipoCliente(List<CampaniaOfertaTipoCliente>  listado, SQLiteDatabase db) {
//
//
//
//        long cantidadInsertada = 0;
//        for(CampaniaOfertaTipoCliente c:listado){
//
//            ContentValues values = new ContentValues();
//            values.put("cod_oferta", c.getCodOferta());
//            values.put("cantidad_minima", c.getCodTipoCliente());
//
//            cantidadInsertada = db.insert("campanias_oferta_condibonif", null,values);
//            cantidadInsertada++;
//        }
//        return cantidadInsertada;
//    }
//
//    public long actualizarCampaniasOfertasCadena(List<CampaniaOfertaCadena>  listado, SQLiteDatabase db) {
//
//
//
//        long cantidadInsertada = 0;
//        for(CampaniaOfertaCadena c:listado){
//
//            ContentValues values = new ContentValues();
//            values.put("cod_campania", c.getCodOferta());
//            values.put("cod_linea", c.getCodCadena());
//            cantidadInsertada = db.insert("campania_linea", null,values);
//            cantidadInsertada++;
//        }
//        return cantidadInsertada;
//    }
//
//
//    public List<CampaniaOfertaPremio2> getListadoOfertasPremios(List<CampaniaOfertaPremio2> listado,double importeOfertaPedido,int... codOferta) {
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        for(int codigo :codOferta){
//            String sqlCampaniasPresentacion2 = " select c.cod_matpromocional,c.cod_oferta,(select cam.nombre_oferta from campanias_ofertas cam where cam.cod_oferta=c.cod_oferta) as nombre_oferta,m.nombre_matpromocional, ";
//            sqlCampaniasPresentacion2 += " c.monto_oferta from campanias_ofertas_premios c,mat_promocional m where m.cod_matpromocional=c.cod_matpromocional and c.cod_oferta in("+codigo+") order by monto_oferta" ;
//
//
//
//            Log.i("INFO", sqlCampaniasPresentacion2);
//
//            Cursor c = db.rawQuery(sqlCampaniasPresentacion2, null);
//            if (c.moveToFirst()) {
//
//                do {
//                    CampaniaOfertaPremio2 cDetalle=new CampaniaOfertaPremio2();
//                    cDetalle.setImporteOfertaPedido(importeOfertaPedido);
//                    cDetalle.setNombreOferta( c.getString(   c.getColumnIndex("nombre_oferta") ) );
//                    cDetalle.setNombreMaterialPremio( c.getString(   c.getColumnIndex("nombre_matpromocional") ) );
//                    cDetalle.setCodOferta(  c.getInt(   c.getColumnIndex("cod_oferta"))  );
//                    cDetalle.setCodMatPromocional(  c.getInt(   c.getColumnIndex("cod_matpromocional"))  );
//                    cDetalle.setMontoOferta( c.getDouble(   c.getColumnIndex("monto_oferta")) );
//
//                    if(importeOfertaPedido>=cDetalle.getMontoOferta()){
//                        cDetalle.setCantidadPedidoPremio(  (int)(importeOfertaPedido /cDetalle.getMontoOferta())    );
//                        listado.add(cDetalle);
//                    }
//                } while (c.moveToNext());
//
//
//
//            }
//            Log.i("CANTIDAD PRODUCTOS", String.valueOf( listado.size()));
//
//            c.close();
//
//
//
//        }
//
//        db.close();
//        return listado;
//    }
//
//
//    public List<CampaniaOfertaPremio2> listadoOfertaPremios(int codOferta) {
//
//
//
//        String sqlCampaniasPresentacion2 = " select c.cod_matpromocional,c.cod_oferta,(select cam.nombre_oferta from campanias_ofertas cam where cam.cod_oferta=c.cod_oferta) as nombre_oferta,m.nombre_matpromocional, ";
//        sqlCampaniasPresentacion2 += " c.monto_oferta from campanias_ofertas_premios c,mat_promocional m where m.cod_matpromocional=c.cod_matpromocional and c.cod_oferta in("+codOferta+") order by monto_oferta" ;
//
//        List<CampaniaOfertaPremio2> listado=new ArrayList<CampaniaOfertaPremio2>();
//
//        Log.i("INFO", sqlCampaniasPresentacion2);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(sqlCampaniasPresentacion2, null);
//        if (c.moveToFirst()) {
//            do {
//
//
//                CampaniaOfertaPremio2 cDetalle=new CampaniaOfertaPremio2();
//                //cDetalle.setUnidadMinima( cDetalle.getCantidadMinima()*cantidadPresentacionPro  );
//                //  cDetalle.setCantidadUnitaria( cDetalle.getCantidad()*cantidadPresentacionPro  );
//                //  cDetalle.setImporteOfertaPedido(importeOfertaPedido);
//
//
//                cDetalle.setNombreOferta( c.getString(   c.getColumnIndex("nombre_oferta") ) );
//                cDetalle.setNombreMaterialPremio( c.getString(   c.getColumnIndex("nombre_matpromocional") ) );
//
//                cDetalle.setCodOferta(  c.getInt(   c.getColumnIndex("cod_oferta"))  );
//                cDetalle.setCodMatPromocional(  c.getInt(   c.getColumnIndex("cod_matpromocional"))  );
//
//
//
//
//
//
//                cDetalle.setMontoOferta( c.getDouble(   c.getColumnIndex("monto_oferta")) );
//
//                listado.add(cDetalle);
//
//
//
//
//            } while (c.moveToNext());
//        }
//        Log.i("CANTIDAD PRODUCTOS", String.valueOf( listado.size()));
//
//        c.close();
//
//
//
//
//
//
//        return listado;
//    }
//
//
//
//
//
//
//    public ArrayList<CampaniasOfertaDetalle> getListadoOfertasCabecera(int codPresentacion,String fechaPedido,int codAreaEmpresa,int codTipoVenta,int codTipoCliente,int codCadena,int codModalidadVenta) {
//
//        ArrayList<CampaniasOfertaDetalle> listado=new ArrayList<CampaniasOfertaDetalle>();
//
//        String sqlCampaniasPresentacion2 = "";
//
//        sqlCampaniasPresentacion2=" select (  select cl.cod_linea from campania_linea cl  where cl.cod_campania=co.cod_oferta) as cod_cadena ,(select   cantidad_minima  from  campanias_oferta_condibonif where cod_oferta=co.cod_oferta) as cod_tipo_cliente,    cod_campania,cod_tipooferta,NOMBRE_OFERTA AS NOMBRE_OFERTA,co.COD_OFERTA AS COD_OFERTA,co.COD_MATPROMOCIONAL AS COD_MATPROMOCIONAL, ";
//        sqlCampaniasPresentacion2 += " MONTO_OFERTA AS MONTO_OFERTA, (select m.NOMBRE_MATPROMOCIONAL from MAT_PROMOCIONAL m where m.COD_MATPROMOCIONAL=co.COD_MATPROMOCIONAL) as NOMBRE_MATPROMOCIONAL ";
//        sqlCampaniasPresentacion2 += " from CAMPANIAS_OFERTAS co where co.COD_OFERTA in(select distinct co1.COD_OFERTA ";
//        sqlCampaniasPresentacion2 += " from CAMPANIAS_OFERTAS co1,     CAMPANIAS_OFERTASPRODUCTOS cop1 where cop1.COD_OFERTA = co1.COD_OFERTA and       cod_presentacion = "+codPresentacion+" and ";
//        sqlCampaniasPresentacion2 += "       '"+fechaPedido+"'      between co1.FECHA_INICIO and    co1.FECHA_FINAL AND     COD_ESTADOSCAMPANIASOFERTAS = 3 and co1.COD_OFERTA in (select cod_oferta from OFERTA_AGENCIA where cod_area_empresa = "+codAreaEmpresa+") ) ";
//
//
//        Log.i("INFO", sqlCampaniasPresentacion2);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(sqlCampaniasPresentacion2, null);
//        if (c.moveToFirst()) {
//            do {
//                int codTipoClienteOferta=c.getInt(   c.getColumnIndex("cod_tipo_cliente"));
//                int codCadenaOferta=c.getInt(   c.getColumnIndex("cod_cadena"));
//
//                CampaniasOfertaDetalle cDetalle=new CampaniasOfertaDetalle();
//                cDetalle.setCodTipoOferta(  c.getInt(   c.getColumnIndex("cod_tipooferta"))  );
//                cDetalle.setNombreOferta( c.getString(   c.getColumnIndex("NOMBRE_OFERTA") ) );
//                cDetalle.setCodOferta(  c.getInt(   c.getColumnIndex("COD_OFERTA"))  );
//                cDetalle.setCodMatPromocionalOferta(  c.getInt(   c.getColumnIndex("COD_MATPROMOCIONAL") ) );
//                cDetalle.setNombrePremio(  c.getString(   c.getColumnIndex("NOMBRE_MATPROMOCIONAL") ) );
//                cDetalle.setMontoOferta( c.getDouble(   c.getColumnIndex("MONTO_OFERTA")) );
//                cDetalle.setCodCampania(   c.getInt(   c.getColumnIndex("cod_campania") ) );
//
//                Log.i("codOferta", cDetalle.getCodOferta()+"" );
//                Log.i("codTipoClienteOferta","codTipoClienteOferta:"+codTipoClienteOferta);
//                Log.i("codTipoCliente","codTipoCliente:"+codTipoCliente);
//
//
//                Log.i("codCadenaOferta","codCadenaOferta:"+codCadenaOferta);
//                Log.i("codCadena","codCadena:"+codCadena);
//                Log.i(TAG,"cDetalle.getCodTipoOferta():"+cDetalle.getCodTipoOferta());
//
//
//                if(cDetalle.getCodTipoOferta()==3){
//
//
//
//                    if(  existenciaModalidadVenta(cDetalle.getCodOferta(),codModalidadVenta)>0  ){
//                        listado.add(cDetalle);
//
//                    }
//
//
//
//
//
//                }else{
//                    if(codModalidadVenta==ModalidadVentas.MODALIDAD_FARMACIA_BOTICA.codigo ||  codModalidadVenta==ModalidadVentas.MODALIDAD_SF_CLIN_HOSP.codigo){
//                        listado.add(cDetalle);
//
//                    }
//
//
//
//                }
//
//
//
//            } while (c.moveToNext());
//            c.close();
//        }
//        Log.i("CANTIDAD PRODUCTOS", String.valueOf( listado.size()));
//        return listado;
//    }
//    public int existenciaModalidadVenta(int codOferta,int codModalidad) {
//        String selectQuery="  select   count(*) cantidad_registros  from campanias_oferta_condibonif c where cod_oferta="+codOferta+" and c.cantidad_minima="+codModalidad;
//        Log.i(TAG,selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        int cantidadRegistros=0;
//        if (c.moveToFirst()) {
//            do {
//
//                cantidadRegistros=c.getInt(   c.getColumnIndex("cantidad_registros")  );
//
//
//            } while (c.moveToNext());
//            c.close();
//            db.close();
//        }
//
//        return cantidadRegistros;
//
//    }
//
//    public ArrayList<CampaniasOfertaDetalle> getListadoOfertaDetalle(int codPresentacion,String fechaPedido,int codAreaEmpresa,int codTipoCliente,int codCadena,int codModalidadVenta) {
//
//        ArrayList<CampaniasOfertaDetalle> listado=new ArrayList<CampaniasOfertaDetalle>();
//
//
//
//
//
//
//        String sqlCampaniasPresentacion2 = "select CANTIDAD_UNITARIA_BONIFICACION,(  select cl.cod_linea from campania_linea cl  where cl.cod_campania=co.cod_oferta) as cod_cadena ,(select   cantidad_minima  from  campanias_oferta_condibonif where cod_oferta=co.cod_oferta) as cod_tipo_cliente,      cod_presentacion_bonifi,cod_tipooferta,COD_CAMPANIA AS COD_CAMPANIA ,NOMBRE_OFERTA AS NOMBRE_OFERTA,cod_tipopuntaje AS cod_tipopuntaje ,cop.COD_OFERTA AS COD_OFERTA , UNIDAD_OFERTA AS UNIDAD_OFERTA,UNIDAD_BONIFICACION AS UNIDAD_BONIFICACION,PRECIO AS PRECIO ,PORCENTAJE_DESCUENTO AS PORCENTAJE_DESCUENTO ,PRODUCTO_OBLIGATORIO AS PRODUCTO_OBLIGATORIO,PORCENTAJE_DESCUENTOFERTA AS PORCENTAJE_DESCUENTOFERTA,CANTIDAD_OFERTA AS CANTIDAD_OFERTA,CANTIDAD_OFERTABONIFICACION AS CANTIDAD_OFERTABONIFICACION ,COD_TIPOVENTA AS COD_TIPOVENTA,UNIDAD_MINIMA AS UNIDAD_MINIMA ,co.COD_MATPROMOCIONAL AS COD_MATPROMOCIONAL ,CANTIDAD_OFERTAMINIMA AS CANTIDAD_OFERTAMINIMA,PORCENTAJE_DESCUENTOFERTA AS PORCENTAJE_DESCUENTOFERTA,MONTO_OFERTA AS MONTO_OFERTA from CAMPANIAS_OFERTAS co,CAMPANIAS_OFERTASPRODUCTOS cop ";
//        sqlCampaniasPresentacion2 += " where cop.COD_OFERTA=co.COD_OFERTA and cod_presentacion=" + codPresentacion;
//        sqlCampaniasPresentacion2 += " and COD_ESTADOSCAMPANIASOFERTAS=3 ";
//        //sqlCampaniasPresentacion2 += " and  ";
//        sqlCampaniasPresentacion2 += "    and   '"+fechaPedido+"'      between co.FECHA_INICIO and    co.FECHA_FINAL AND     COD_ESTADOSCAMPANIASOFERTAS = 3  ";
//        sqlCampaniasPresentacion2 += "  and co.COD_OFERTA in (select cod_oferta from OFERTA_AGENCIA where cod_area_empresa = "+codAreaEmpresa+")  ";
//
//
//        Log.i("INFO", sqlCampaniasPresentacion2);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(sqlCampaniasPresentacion2, null);
//        if (c.moveToFirst()) {
//            do {
//
//                int codTipoClienteOferta=c.getInt(   c.getColumnIndex("cod_tipo_cliente"));
//                int codCadenaOferta=c.getInt(   c.getColumnIndex("cod_cadena"));
//
//
//
//                CampaniasOfertaDetalle cDetalle=new CampaniasOfertaDetalle();
//                cDetalle.setCodPresentacionBonifi( c.getInt(   c.getColumnIndex("cod_presentacion_bonifi")  )  );
//
//                cDetalle.setCodTipoOferta( c.getInt(   c.getColumnIndex("cod_tipooferta")  )  );
//                cDetalle.setCodCampania( c.getInt(   c.getColumnIndex("COD_CAMPANIA")  )  );
//                cDetalle.setNombreOferta( c.getString(   c.getColumnIndex("NOMBRE_OFERTA") ) );
//
//                cDetalle.setCodTipoPuntaje( c.getInt(   c.getColumnIndex("cod_tipopuntaje"))   );
//                cDetalle.setCodOferta(  c.getInt(   c.getColumnIndex("COD_OFERTA"))  );
//                cDetalle.setCantidadUnitaria(  c.getInt(   c.getColumnIndex("UNIDAD_OFERTA") ) );
//                cDetalle.setCantidadUnitariaBonificacion(  c.getInt(   c.getColumnIndex("UNIDAD_BONIFICACION"))  );
//                cDetalle.setCodTipoPrecio(   c.getInt(   c.getColumnIndex("PRECIO") ));
//                cDetalle.setPorcentajeDescuento(  c.getDouble(   c.getColumnIndex("PORCENTAJE_DESCUENTO")) );
//                cDetalle.setProductoObligatorio(   c.getInt(   c.getColumnIndex("PRODUCTO_OBLIGATORIO")) );
//                cDetalle.setCantidad(  c.getInt(   c.getColumnIndex("CANTIDAD_OFERTA"))  );
//                cDetalle.setCantidadBonificacion(  c.getInt(   c.getColumnIndex("CANTIDAD_OFERTA") ) );
//                cDetalle.setUnidadMinima(  c.getInt(   c.getColumnIndex("UNIDAD_MINIMA")  ));
//                cDetalle.setCodMatPromocionalOferta(  c.getInt(   c.getColumnIndex("COD_MATPROMOCIONAL") ) );
//                cDetalle.setCantidadMinima( c.getInt(   c.getColumnIndex("CANTIDAD_OFERTAMINIMA") ) );
//                cDetalle.setPorcentajeDescuentoTotal(   c.getDouble(   c.getColumnIndex("PORCENTAJE_DESCUENTOFERTA"))  );
//                //CANTIDAD_UNITARIA_BONIFICACION
//                cDetalle.setDescuentoBUnidades(   c.getInt(   c.getColumnIndex("CANTIDAD_UNITARIA_BONIFICACION"))  );
//
//
//                if(cDetalle.getCodTipoOferta()==3){
//					/*if(codTipoClienteOferta==0){
//						if(codCadenaOferta==0){
//							listado.add(cDetalle);
//
//						}else{
//
//							if(codCadena==codCadenaOferta){
//								listado.add(cDetalle);
//
//							}
//						}
//
//
//
//					}else{
//
//						if( codTipoClienteOferta==codTipoCliente ){
//
//							listado.add(cDetalle);
//
//						}else{
//
//							if(codCadena==codCadenaOferta){
//								listado.add(cDetalle);
//
//							}
//						}
//
//					}*/
//
//
//
//                    if(  existenciaModalidadVenta(cDetalle.getCodOferta(),codModalidadVenta)>0  ){
//                        listado.add(cDetalle);
//
//                    }
//
//                }else{
//                    if(codModalidadVenta==ModalidadVentas.MODALIDAD_FARMACIA_BOTICA.codigo ||  codModalidadVenta==ModalidadVentas.MODALIDAD_SF_CLIN_HOSP.codigo){
//                        listado.add(cDetalle);
//
//                    }
//
//
//
//                }
//
//				/*if(codCadenaOferta==0) {
//					if (codTipoClienteOferta == 0) {
//
//						if (codCadenaOferta == 0) {
//
//							listado.add(cDetalle);
//
//						} else {
//
//							if (codCadena == codCadenaOferta) {
//								listado.add(cDetalle);
//
//							}
//						}
//
//
//					} else {
//
//						if (codTipoClienteOferta == codTipoCliente) {
//
//							listado.add(cDetalle);
//
//						} else {
//
//							if (codCadena == codCadenaOferta) {
//								listado.add(cDetalle);
//
//							}
//						}
//
//
//					}
//
//
//				}*/
//
//
//
//
//            } while (c.moveToNext());
//            c.close();
//        }
//        Log.i("CANTIDAD PRODUCTOS", String.valueOf( listado.size()));
//        return listado;
//    }
//
//
//
//
//    public ArrayList<CampaniasOfertas> getListadoOfertasAcumuladas() {
//
//        ArrayList<CampaniasOfertas> listado=new ArrayList<CampaniasOfertas>();
//        // String selectQuery = "select cod_presentacion,nombre_producto_presentacion,cantidad_presentacion,precio_minimo,precio_corriente,precio_zona_viaje,precio_lista from presentaciones_producto where nombre_producto_presentacion like '"+str+"%'" ;
//
//        String sqlCampaniasPresentacion2 = "";
//        //String sqlCampaniasPresentacion2 = "select COD_CAMPANIA AS COD_CAMPANIA ,NOMBRE_OFERTA AS NOMBRE_OFERTA,cod_tipopuntaje AS cod_tipopuntaje ,cop.COD_OFERTA AS COD_OFERTA , UNIDAD_OFERTA AS UNIDAD_OFERTA,UNIDAD_BONIFICACION AS UNIDAD_BONIFICACION,PRECIO AS PRECIO ,PORCENTAJE_DESCUENTO AS PORCENTAJE_DESCUENTO ,PRODUCTO_OBLIGATORIO AS PRODUCTO_OBLIGATORIO,PORCENTAJE_DESCUENTOFERTA AS PORCENTAJE_DESCUENTOFERTA,CANTIDAD_OFERTA AS CANTIDAD_OFERTA,CANTIDAD_OFERTABONIFICACION AS CANTIDAD_OFERTABONIFICACION ,COD_TIPOVENTA AS COD_TIPOVENTA,UNIDAD_MINIMA AS UNIDAD_MINIMA ,co.COD_MATPROMOCIONAL AS COD_MATPROMOCIONAL ,CANTIDAD_OFERTAMINIMA AS CANTIDAD_OFERTAMINIMA,PORCENTAJE_DESCUENTOFERTA AS PORCENTAJE_DESCUENTOFERTA,MONTO_OFERTA AS MONTO_OFERTA from CAMPANIAS_OFERTAS co,CAMPANIAS_OFERTASPRODUCTOS cop";
//        //sqlCampaniasPresentacion2 += " where cop.COD_OFERTA=co.COD_OFERTA and cod_presentacion=" + codPresentacion;
//        //sqlCampaniasPresentacion2 += " and '" + fechaPedido + "' between co.FECHA_INICIO and co.FECHA_FINAL AND COD_ESTADOSCAMPANIASOFERTAS=3 ";
//        //sqlCampaniasPresentacion2 += " and co.COD_OFERTA in(select cod_oferta from  OFERTA_AGENCIA where cod_area_empresa="+codAreaEmpresa+") and (COD_TIPOVENTA="+codTipoVenta+" or COD_TIPOVENTA=0)";
//
//        sqlCampaniasPresentacion2=" select FECHA_FINAL,FECHA_INICIO,cod_tipooferta,NOMBRE_OFERTA AS NOMBRE_OFERTA,co.COD_OFERTA AS COD_OFERTA,co.COD_MATPROMOCIONAL AS COD_MATPROMOCIONAL, ";
//        sqlCampaniasPresentacion2 += " MONTO_OFERTA AS MONTO_OFERTA, (select m.NOMBRE_MATPROMOCIONAL from MAT_PROMOCIONAL m where m.COD_MATPROMOCIONAL=co.COD_MATPROMOCIONAL) as NOMBRE_MATPROMOCIONAL ";
//        sqlCampaniasPresentacion2 += " from CAMPANIAS_OFERTAS co where OFERTA_ACUMULADA=1";
//
//        //sqlCampaniasPresentacion2 += " from CAMPANIAS_OFERTAS co1,     CAMPANIAS_OFERTASPRODUCTOS cop1 where cop1.COD_OFERTA = co1.COD_OFERTA and       cod_presentacion = "+codPresentacion+" and ";
//        //sqlCampaniasPresentacion2 += "       '"+fechaPedido+"'      between co1.FECHA_INICIO and    co1.FECHA_FINAL AND     COD_ESTADOSCAMPANIASOFERTAS = 3 and co1.COD_OFERTA in (select cod_oferta from OFERTA_AGENCIA where cod_area_empresa = "+codAreaEmpresa+") ) ";
//
//
//
//        Log.i("INFO", sqlCampaniasPresentacion2);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(sqlCampaniasPresentacion2, null);
//        if (c.moveToFirst()) {
//            do {
//
//
//                CampaniasOfertas cDetalle=new CampaniasOfertas();
//
//
//                //cDetalle.setUnidadMinima( cDetalle.getCantidadMinima()*cantidadPresentacionPro  );
//                //  cDetalle.setCantidadUnitaria( cDetalle.getCantidad()*cantidadPresentacionPro  );
//
//
//                //cDetalle.setCodCampania( c.getInt(   c.getColumnIndex("COD_CAMPANIA")  )  );
//                cDetalle.setNombreoferta( c.getString(   c.getColumnIndex("NOMBRE_OFERTA") ) );
//
//                //    cDetalle.setFechainicio( c.getString(   c.getColumnIndex("FECHA_INICIO") ) );
//                //   cDetalle.setFechafinal( c.getString(   c.getColumnIndex("FECHA_FINAL") ) );
//
//
//
//                // cDetalle.setCodTipoPuntaje( c.getInt(   c.getColumnIndex("cod_tipopuntaje"))   );
//                cDetalle.setCodoferta(  c.getInt(   c.getColumnIndex("COD_OFERTA"))  );
//                //cDetalle.setCantidadUnitaria(  c.getInt(   c.getColumnIndex("UNIDAD_OFERTA") ) );
//
//
//                //cDetalle.setCantidadUnitariaBonificacion(  c.getInt(   c.getColumnIndex("UNIDAD_BONIFICACION"))  );
//
//
//
//
//
//                listado.add(cDetalle);
//
//
//            } while (c.moveToNext());
//            c.close();
//        }
//        Log.i("CANTIDAD PRODUCTOS", String.valueOf( listado.size()));
//        return listado;
//    }
//
//
//    public ArrayList<CampaniasOfertaDetalle> getListadoOfertas(int codPresentacion,String fechaPedido,int codAreaEmpresa,int codTipoVenta) {
//
//        ArrayList<CampaniasOfertaDetalle> listado=new ArrayList<CampaniasOfertaDetalle>();
//        // String selectQuery = "select cod_presentacion,nombre_producto_presentacion,cantidad_presentacion,precio_minimo,precio_corriente,precio_zona_viaje,precio_lista from presentaciones_producto where nombre_producto_presentacion like '"+str+"%'" ;
//
//
//        String sqlCampaniasPresentacion2 = "select COD_CAMPANIA AS COD_CAMPANIA ,NOMBRE_OFERTA AS NOMBRE_OFERTA,cod_tipopuntaje AS cod_tipopuntaje ,cop.COD_OFERTA AS COD_OFERTA , UNIDAD_OFERTA AS UNIDAD_OFERTA,UNIDAD_BONIFICACION AS UNIDAD_BONIFICACION,PRECIO AS PRECIO ,PORCENTAJE_DESCUENTO AS PORCENTAJE_DESCUENTO ,PRODUCTO_OBLIGATORIO AS PRODUCTO_OBLIGATORIO,PORCENTAJE_DESCUENTOFERTA AS PORCENTAJE_DESCUENTOFERTA,CANTIDAD_OFERTA AS CANTIDAD_OFERTA,CANTIDAD_OFERTABONIFICACION AS CANTIDAD_OFERTABONIFICACION ,COD_TIPOVENTA AS COD_TIPOVENTA,UNIDAD_MINIMA AS UNIDAD_MINIMA ,co.COD_MATPROMOCIONAL AS COD_MATPROMOCIONAL ,CANTIDAD_OFERTAMINIMA AS CANTIDAD_OFERTAMINIMA,PORCENTAJE_DESCUENTOFERTA AS PORCENTAJE_DESCUENTOFERTA,MONTO_OFERTA AS MONTO_OFERTA from CAMPANIAS_OFERTAS co,CAMPANIAS_OFERTASPRODUCTOS cop";
//        sqlCampaniasPresentacion2 += " where cop.COD_OFERTA=co.COD_OFERTA and cod_presentacion=" + codPresentacion;
//        sqlCampaniasPresentacion2 += " and '" + fechaPedido + "' between co.FECHA_INICIO and co.FECHA_FINAL AND COD_ESTADOSCAMPANIASOFERTAS=3 ";
//        sqlCampaniasPresentacion2 += " and co.COD_OFERTA in(select cod_oferta from  OFERTA_AGENCIA where cod_area_empresa="+codAreaEmpresa+") and (COD_TIPOVENTA="+codTipoVenta+" or COD_TIPOVENTA=0)";
//
//
//
//        Log.i("INFO", sqlCampaniasPresentacion2);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(sqlCampaniasPresentacion2, null);
//        if (c.moveToFirst()) {
//            do {
//
//
//                CampaniasOfertaDetalle cDetalle=new CampaniasOfertaDetalle();
//                //cDetalle.setUnidadMinima( cDetalle.getCantidadMinima()*cantidadPresentacionPro  );
//                //  cDetalle.setCantidadUnitaria( cDetalle.getCantidad()*cantidadPresentacionPro  );
//                cDetalle.setCodCampania( c.getInt(   c.getColumnIndex("COD_CAMPANIA")  )  );
//                cDetalle.setNombreOferta( c.getString(   c.getColumnIndex("NOMBRE_OFERTA") ) );
//
//                cDetalle.setCodTipoPuntaje( c.getInt(   c.getColumnIndex("cod_tipopuntaje"))   );
//                cDetalle.setCodOferta(  c.getInt(   c.getColumnIndex("COD_OFERTA"))  );
//                cDetalle.setCantidadUnitaria(  c.getInt(   c.getColumnIndex("UNIDAD_OFERTA") ) );
//
//
//                cDetalle.setCantidadUnitariaBonificacion(  c.getInt(   c.getColumnIndex("UNIDAD_BONIFICACION"))  );
//
//
//                cDetalle.setCodTipoPrecio(   c.getInt(   c.getColumnIndex("PRECIO") ));
//                cDetalle.setPorcentajeDescuento(  c.getDouble(   c.getColumnIndex("PORCENTAJE_DESCUENTO")) );
//                cDetalle.setProductoObligatorio(   c.getInt(   c.getColumnIndex("PRODUCTO_OBLIGATORIO")) );
//
//                cDetalle.setCantidad(  c.getInt(   c.getColumnIndex("CANTIDAD_OFERTA"))  );
//                cDetalle.setCantidadBonificacion(  c.getInt(   c.getColumnIndex("CANTIDAD_OFERTA") ) );
//                cDetalle.setUnidadMinima(  c.getInt(   c.getColumnIndex("UNIDAD_MINIMA")  ));
//                cDetalle.setCodMatPromocionalOferta(  c.getInt(   c.getColumnIndex("COD_MATPROMOCIONAL") ) );
//                cDetalle.setCantidadMinima( c.getInt(   c.getColumnIndex("CANTIDAD_OFERTAMINIMA") ) );
//
//
//                cDetalle.setPorcentajeDescuentoTotal(   c.getDouble(   c.getColumnIndex("PORCENTAJE_DESCUENTOFERTA"))  );
//
//                cDetalle.setMontoOferta( c.getDouble(   c.getColumnIndex("MONTO_OFERTA")) );
//
//
//                listado.add(cDetalle);
//
//
//            } while (c.moveToNext());
//            c.close();
//        }
//        Log.i("CANTIDAD PRODUCTOS", String.valueOf( listado.size()));
//        return listado;
//    }
//
//
//
//
//    public ArrayList<CampaniasOfertaDetalle> getOfertaDetalle(int codPresentacion,int codOferta) {
//
//        ArrayList<CampaniasOfertaDetalle> listado=new ArrayList<CampaniasOfertaDetalle>();
//        // String selectQuery = "select cod_presentacion,nombre_producto_presentacion,cantidad_presentacion,precio_minimo,precio_corriente,precio_zona_viaje,precio_lista from presentaciones_producto where nombre_producto_presentacion like '"+str+"%'" ;
//
//
//        String sqlCampaniasPresentacion2 = "select CANTIDAD_UNITARIA_BONIFICACION,cod_presentacion_bonifi,cod_tipooferta,COD_CAMPANIA AS COD_CAMPANIA ,NOMBRE_OFERTA AS NOMBRE_OFERTA,cod_tipopuntaje AS cod_tipopuntaje ,cop.COD_OFERTA AS COD_OFERTA , UNIDAD_OFERTA AS UNIDAD_OFERTA,UNIDAD_BONIFICACION AS UNIDAD_BONIFICACION,PRECIO AS PRECIO ,PORCENTAJE_DESCUENTO AS PORCENTAJE_DESCUENTO ,PRODUCTO_OBLIGATORIO AS PRODUCTO_OBLIGATORIO,PORCENTAJE_DESCUENTOFERTA AS PORCENTAJE_DESCUENTOFERTA,CANTIDAD_OFERTA AS CANTIDAD_OFERTA,CANTIDAD_OFERTABONIFICACION AS CANTIDAD_OFERTABONIFICACION ,COD_TIPOVENTA AS COD_TIPOVENTA,UNIDAD_MINIMA AS UNIDAD_MINIMA ,co.COD_MATPROMOCIONAL AS COD_MATPROMOCIONAL ,CANTIDAD_OFERTAMINIMA AS CANTIDAD_OFERTAMINIMA,PORCENTAJE_DESCUENTOFERTA AS PORCENTAJE_DESCUENTOFERTA,MONTO_OFERTA AS MONTO_OFERTA from CAMPANIAS_OFERTAS co,CAMPANIAS_OFERTASPRODUCTOS cop";
//        sqlCampaniasPresentacion2 += " where cop.COD_OFERTA=co.COD_OFERTA and cod_presentacion=" + codPresentacion;
//        sqlCampaniasPresentacion2 += " and COD_ESTADOSCAMPANIASOFERTAS=3 ";
//        sqlCampaniasPresentacion2 += " and co.COD_OFERTA in("+codOferta+") ";
//
//
//
//        Log.i("INFO", sqlCampaniasPresentacion2);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(sqlCampaniasPresentacion2, null);
//        if (c.moveToFirst()) {
//            do {
//
//
//                CampaniasOfertaDetalle cDetalle=new CampaniasOfertaDetalle();
//                //cDetalle.setUnidadMinima( cDetalle.getCantidadMinima()*cantidadPresentacionPro  );
//                //  cDetalle.setCantidadUnitaria( cDetalle.getCantidad()*cantidadPresentacionPro  );
//                cDetalle.setCodPresentacionBonifi( c.getInt(   c.getColumnIndex("cod_presentacion_bonifi")  )  );
//                cDetalle.setDescuentoBUnidades( c.getInt(   c.getColumnIndex("CANTIDAD_UNITARIA_BONIFICACION")  )  );
//
//
//                cDetalle.setCodTipoOferta( c.getInt(   c.getColumnIndex("cod_tipooferta")  )  );
//                cDetalle.setCodCampania( c.getInt(   c.getColumnIndex("COD_CAMPANIA")  )  );
//                cDetalle.setNombreOferta( c.getString(   c.getColumnIndex("NOMBRE_OFERTA") ) );
//
//                cDetalle.setCodTipoPuntaje( c.getInt(   c.getColumnIndex("cod_tipopuntaje"))   );
//                cDetalle.setCodOferta(  c.getInt(   c.getColumnIndex("COD_OFERTA"))  );
//                cDetalle.setCantidadUnitaria(  c.getInt(   c.getColumnIndex("UNIDAD_OFERTA") ) );
//
//
//                cDetalle.setCantidadUnitariaBonificacion(  c.getInt(   c.getColumnIndex("UNIDAD_BONIFICACION"))  );
//
//
//                cDetalle.setCodTipoPrecio(   c.getInt(   c.getColumnIndex("PRECIO") ));
//                cDetalle.setPorcentajeDescuento(  c.getDouble(   c.getColumnIndex("PORCENTAJE_DESCUENTO")) );
//                cDetalle.setProductoObligatorio(   c.getInt(   c.getColumnIndex("PRODUCTO_OBLIGATORIO")) );
//
//                cDetalle.setCantidad(  c.getInt(   c.getColumnIndex("CANTIDAD_OFERTA"))  );
//                cDetalle.setCantidadBonificacion(  c.getInt(   c.getColumnIndex("CANTIDAD_OFERTA") ) );
//                cDetalle.setUnidadMinima(  c.getInt(   c.getColumnIndex("UNIDAD_MINIMA")  ));
//                cDetalle.setCodMatPromocionalOferta(  c.getInt(   c.getColumnIndex("COD_MATPROMOCIONAL") ) );
//                cDetalle.setCantidadMinima( c.getInt(   c.getColumnIndex("CANTIDAD_OFERTAMINIMA") ) );
//
//
//                cDetalle.setPorcentajeDescuentoTotal(   c.getDouble(   c.getColumnIndex("PORCENTAJE_DESCUENTOFERTA"))  );
//
//                cDetalle.setMontoOferta( c.getDouble(   c.getColumnIndex("MONTO_OFERTA")) );
//
//
//
//                listado.add(cDetalle);
//
//
//            } while (c.moveToNext());
//            c.close();
//        }
//        Log.i("CANTIDAD PRODUCTOS", String.valueOf( listado.size()));
//        return listado;
//    }
//
//    public PresentacionesProductos getOfertaDetalleBonificacion(int codPresentacion,int codOferta,int cantidadPedidoUnitario) {
//
//        //ArrayList<PresentacionesProductos> listado=new ArrayList<PresentacionesProductos>();
//        // String selectQuery = "select cod_presentacion,nombre_producto_presentacion,cantidad_presentacion,precio_minimo,precio_corriente,precio_zona_viaje,precio_lista from presentaciones_producto where nombre_producto_presentacion like '"+str+"%'" ;
//
//        PresentacionesProductos p=new PresentacionesProductos();
//
//        String sql=" select p.nombre_producto_presentacion,cantidad_presentacion ,p.cod_presentacion,unidad_bonificacion,cantidad_ofertaminima from  campanias_ofertasproductos c,presentaciones_producto p  ";
//        sql+=" where p.cod_presentacion=c.cod_presentacion_bonifi and c.cod_oferta  ="+codOferta+" and c.cod_presentacion="+codPresentacion+" order by p.nombre_producto_presentacion ";
//
//
//
//
//
//        Log.i("INFO", sql);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(sql, null);
//        if (c.moveToFirst()) {
//            do {
//                //p.setC(   c.getInt(   c.getColumnIndex("cod_presentacion")  )  );
//                int cantidad_presentacion=c.getInt(   c.getColumnIndex("cantidad_presentacion")  );
//                int unidad_bonificacion= c.getInt(   c.getColumnIndex("unidad_bonificacion")  ) ;
//                int cantidad_ofertaminima= c.getInt(   c.getColumnIndex("cantidad_ofertaminima")  ) ;
//                int cantidad_ofertaminima_unidad=cantidad_ofertaminima*cantidad_presentacion;
//                Log.i("cantidadPedidoUnitario:", cantidadPedidoUnitario+"");
//                if(  cantidadPedidoUnitario>=cantidad_ofertaminima_unidad ){
//                    int cantidadUnidadBonificacion=(cantidadPedidoUnitario*unidad_bonificacion)/cantidad_ofertaminima_unidad;
//                    p=new PresentacionesProductos();
//                    p.setNombreProductoPresentacion(  c.getString(   c.getColumnIndex("nombre_producto_presentacion") )    );
//                    p.setCantidadPresentacion(  cantidad_presentacion );
//                    p.setCodPresentacion(   c.getInt(   c.getColumnIndex("cod_presentacion")  )  );
//                    p.setCodOferta(  codOferta  );
//                    p.setCantidadUnitaria( cantidadUnidadBonificacion  );
//                }
//
//
//
//                //  listado.add(cDetalle);
//
//
//            } while (c.moveToNext());
//            c.close();
//            db.close();
//        }
//
//        return p;
//    }
//
//
//    public int getMaximaBonificacion(int codOferta,int cantidadPresentacion,int cantidad) {
//
//        int cantidadBonificacionMaxima=0;
//
//
//
//        String sql=" select  cantidad_minima,unidad_bonificacion  from campanias_oferta_condibonif where cod_oferta in("+codOferta+") and cantidad_minima*"+cantidadPresentacion+"<="+cantidad;
//        Log.i("INFO", sql);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(sql, null);
//        if (c.moveToFirst()) {
//            do {
//
//                int cantidad_minima=c.getInt(   c.getColumnIndex("cantidad_minima")  );
//                int unidad_bonificacion= c.getInt(   c.getColumnIndex("unidad_bonificacion")  ) ;
//
//                if(cantidad_minima>0){
//                    cantidadBonificacionMaxima=(cantidad*unidad_bonificacion)/(cantidad_minima*cantidadPresentacion);
//                }
//
//
//
//            } while (c.moveToNext());
//            c.close();
//            db.close();
//        }
//        //Log.i("cantidadBonificacionSaborMaxima:", String.valueOf(cantidadBonificacionMaxima));
//        return cantidadBonificacionMaxima;
//    }
//
//
//    public String getNombreOferta(int codOferta) {
//
//        String nombreOferta="";
//        String sql="select nombre_oferta from campanias_ofertas where cod_oferta in("+codOferta+") ";
//        Log.i("INFO", sql);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(sql, null);
//        if (c.moveToFirst()) {
//            do {
//
//                nombreOferta=c.getString(   c.getColumnIndex("nombre_oferta")  );
//
//
//
//            } while (c.moveToNext());
//            c.close();
//            db.close();
//        }
//        // Log.i("cantidadBonificacionSaborMaxima:", String.valueOf(cantidadBonificacionMaxima));
//        return nombreOferta;
//    }
//
//
//    public ArrayList<PresentacionesProductos> getOfertaDetalleBonificacion(int codOferta) {
//
//
//
//        ArrayList<PresentacionesProductos>  listado=new ArrayList<PresentacionesProductos>();
//
//        String sql=" select cod_presentacion,(select  nombre_producto_presentacion from presentaciones_producto p where p.cod_presentacion=c.cod_presentacion) as nombre_producto_presentacion  from campanias_ofertasproductosbonifi c where c.cod_oferta in("+codOferta+")";
//        Log.i("INFO", sql);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(sql, null);
//        if (c.moveToFirst()) {
//            do {
//                //p.setC(   c.getInt(   c.getColumnIndex("cod_presentacion")  )  );
//                int cod_presentacion=c.getInt(   c.getColumnIndex("cod_presentacion")  );
//                String nombre_producto_presentacion=c.getString(   c.getColumnIndex("nombre_producto_presentacion")  );
//
//                PresentacionesProductos p=new PresentacionesProductos();
//                p.setCodPresentacion(cod_presentacion );
//                p.setNombreProductoPresentacion(nombre_producto_presentacion);
//                listado.add(p);
//
//
//            } while (c.moveToNext());
//            c.close();
//            db.close();
//        }
//        //Log.i("listado_productos_bonificacion_sabores", String.valueOf(listado.size()));
//        return listado;
//    }
//
//
//
//    public ArrayList<CampaniaOfertaPremio2> listadoOfertaAcumuladaMaterial(int codOferta,double montoVenta,String codMaterialFiltro) {
//
//
//
//        String sqlCampaniasPresentacion2 = " select c.cod_matpromocional,c.cod_oferta,(select cam.nombre_oferta from campanias_ofertas cam where cam.cod_oferta=c.cod_oferta) as nombre_oferta,m.nombre_matpromocional, ";
//        //sqlCampaniasPresentacion2 += " c.monto_oferta from campanias_ofertas_premios c,mat_promocional m where m.cod_matpromocional=c.cod_matpromocional and c.cod_oferta in("+codOferta+") order by monto_oferta" ;
//        sqlCampaniasPresentacion2 = " select c.cod_oferta,c.cod_matpromocional,(select m.nombre_matpromocional from " +
//                "  mat_promocional  m where m.cod_matpromocional=c.cod_matpromocional) nombre_matpromocional ,c.monto_oferta " +
//                " from  campanias_ofertas_premios c where c.cod_oferta="+codOferta+" and c.monto_oferta<="+montoVenta+" and c.cod_matpromocional not in("+codMaterialFiltro+")";
//
//
//        ArrayList<CampaniaOfertaPremio2> listado=new ArrayList<CampaniaOfertaPremio2>();
//
//        Log.i("INFO", sqlCampaniasPresentacion2);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(sqlCampaniasPresentacion2, null);
//        if (c.moveToFirst()) {
//            do {
//
//
//                CampaniaOfertaPremio2 cDetalle=new CampaniaOfertaPremio2();
//
//                cDetalle.setCodOferta(  c.getInt(   c.getColumnIndex("cod_oferta"))  );
//                cDetalle.setCodMatPromocional(  c.getInt(   c.getColumnIndex("cod_matpromocional"))  );
//                cDetalle.setNombreMaterialPremio( c.getString(   c.getColumnIndex("nombre_matpromocional") ) );
//                cDetalle.setMontoOferta(  c.getDouble(   c.getColumnIndex("monto_oferta"))  );
//
//                listado.add(cDetalle);
//
//
//
//
//            } while (c.moveToNext());
//        }
//        Log.i("CANTIDAD PRODUCTOS", String.valueOf( listado.size()));
//
//        c.close();
//
//
//
//
//
//
//        return listado;
//    }




}