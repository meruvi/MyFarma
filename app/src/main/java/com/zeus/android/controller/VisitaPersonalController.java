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

public class VisitaPersonalController extends SQLiteOpenHelper {
    String TAG = VisitaPersonalController.class.getName();

    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String DATA_BASE_NAME = "zeusMobil";
    private static final int DATA_BASE_VERSION = Util.DATA_BASE_VERSION_ZEUS;

    private static String nombreTabla = "visita_personal";

    String sqlCreateTable = "create table " + nombreTabla + "(cod_visita_personal integer PRIMARY KEY,cod_estado_visita_personal integer,cod_personal integer,cod_area_empresa integer,cod_cliente integer,fecha_vista datetime,latitud text,longitud text,cod_tipo_visita_personal integer,obs_visita_personal)";
    String sqlCreateTableTipoVisita = "create table tipo_visita(cod_tipo_visita integer PRIMARY KEY,nombre_tipo_visita text)";

//    /**
//     * @param listado
//     */
//    public void registroTipoVisitaPersonal(Collection<TipoVisitaPersonal> listado) {
//        Log.i(TAG, "INGRESANDO registroTipoVisitaPersonal");
//        SQLiteDatabase db = this.getWritableDatabase();
//        try {
//            db.beginTransaction();
//            db.delete("tipo_visita", null, null);
//            for (TipoVisitaPersonal c : listado) {
//                ContentValues values = new ContentValues();
//                values.put("cod_tipo_visita", c.getCodTipoVisita());
//                values.put("nombre_tipo_visita", c.getNombreTipoVisita());
//
//                db.insert("tipo_visita", null, values);
//            }
//            db.setTransactionSuccessful();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            db.endTransaction();
//        }
//    }
//
    /**
     * @param context ESTE PARAMETRO SE ESPECIFICA EL CONTEXTO.
     */
    public VisitaPersonalController(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }
//
    public void creandoTabla() {
        Log.i("---INICIANDO---", "-----VERIFICANDO TABLA----");

        String selectQuery = " select count(*) as cantidad from sqlite_master where name='" + nombreTabla + "' and type='table' ";
        Log.i("INFO", selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                int cantidad = c.getInt(c.getColumnIndex("cantidad"));
                if (cantidad == 0) {
                    Log.i("LA TABLA  :", "NO EXISTE");
                    Log.i("CREANDO TABLA " + nombreTabla, sqlCreateTable);
                    db.execSQL(sqlCreateTable);
                } else {
                    Log.i("LA TABLA " + nombreTabla + " :", "------ EXISTE");
                }
            } while (c.moveToNext());
            c.close();
            db.close();
        }
        creandoTablaTipoVisita();
    }
//
//    /**
//     *
//     */
    public void creandoTablaTipoVisita() {
        Log.i("---INICIANDO---", "-----VERIFICANDO TABLA----");

        String selectQuery = " select count(*) as cantidad from sqlite_master where name='tipo_visita' and type='table' ";
        Log.i(TAG, selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                int cantidad = c.getInt(c.getColumnIndex("cantidad"));
                if (cantidad == 0) {
                    Log.i(TAG, "NO EXISTE tabla tipo_visita");
                    Log.i(TAG, sqlCreateTableTipoVisita);
                    db.execSQL(sqlCreateTableTipoVisita);
                } else {
                    Log.i(TAG, "------ EXISTE");
                }
            } while (c.moveToNext());
            c.close();
            db.close();
        }
    }
//
//    public int getMaximoCobranza(int codAreaEmpresa) {
//
//        int maximo = 0;
//
//        String selectQuery = " SELECT max(cod_visita_personal) as maximo FROM " + nombreTabla + " where cod_area_empresa=" + codAreaEmpresa;
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//                maximo = c.getInt(c.getColumnIndex("maximo"));
//            } while (c.moveToNext());
//            c.close();
//        }
//        maximo++;
//        db.close();
//        return maximo;
//    }
//
//    public long registrarGuardarCobranza(VisitaPersonal visitaPersonal) {
//        int codVisitaPersonal = getMaximoCobranza(visitaPersonal.getCodAreaEmpresa());
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues data = new ContentValues();
//
//        data.put("cod_visita_personal", codVisitaPersonal);
//        data.put("cod_estado_visita_personal", EstadoVisitaPersonal.REGISTRADO.codEstadoVisitaPersonal);
//        data.put("cod_personal", visitaPersonal.getCodPersonal());
//        data.put("cod_area_empresa", visitaPersonal.getCodAreaEmpresa());
//        data.put("cod_cliente", visitaPersonal.getCodCliente());
//        data.put("fecha_vista", f.format(visitaPersonal.getFechaVisita()));
//        data.put("latitud", visitaPersonal.getLatitud());
//        data.put("longitud", visitaPersonal.getLongitud());
//        data.put("cod_tipo_visita_personal", visitaPersonal.getCodTipoVisitaPersonal());
//        data.put("obs_visita_personal", visitaPersonal.getObsVisitaPersonal());
//
//        long result = db.insert(nombreTabla, null, data);
//        db.close();
//        return result;
//    }
//
//    public void updateEstadoVisitaPersonal(VisitaPersonal visitaPersonal) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues data = new ContentValues();
//	/*String codigo[]={String.valueOf(pedido.getCodPedido())};
//    Log.i("codigo delete:", codigo[0]+"");
//	if(pedido.getCodPedido()>0){
//		db.delete("pedidos", "cod_pedido in("+codigo[0]+")",null);
//	}
//
//
//	if(pedido.getCodPedido()>0){
//		data.put("cod_pedido",pedido.getCodPedido() );
//	}
//	*/
//        data.put("cod_estado_visita_personal", EstadoVisitaPersonal.SINCRONIZADO.codEstadoVisitaPersonal);
//        //data.put("cod_estadopedido", pedido.getCodEstadoPedido());
//
//        db.update("visita_personal", data, " cod_personal in(" + visitaPersonal.getCodPersonal() + ") and cod_cliente=" + visitaPersonal.getCodCliente(), null);
//    }
//
//    public ArrayList<VisitaPersonal> getListadoVisita(int codEstadoVisitaPersonal, int rows) {
//        StringBuilder selectQuery = new StringBuilder();
//        selectQuery.append(" select (select   nombre_tipo_visita  from tipo_visita t where t.cod_tipo_visita=vc.cod_tipo_visita_personal) as nombre_tipo_visita ,cod_visita_personal,cod_estado_visita_personal,cod_personal,cod_area_empresa,cod_cliente,fecha_vista,latitud,longitud,cod_tipo_visita_personal,obs_visita_personal,(select nombre_cliente from clientes c where c.cod_cliente=vc.cod_cliente) nombre_cliente");
//        selectQuery.append(" from visita_personal  vc    ");
//        if (codEstadoVisitaPersonal != 0) {
//            selectQuery.append(" where cod_estado_visita_personal=" + codEstadoVisitaPersonal);
//        }
//        selectQuery.append(" order by cod_visita_personal desc ");
//        //selectQuery.append(" order by cod_visita_personal desc limit "+rows);
//
//        Log.i("INFO", selectQuery.toString());
//        SQLiteDatabase db = this.getReadableDatabase();
//        ArrayList<VisitaPersonal> listado = new ArrayList<VisitaPersonal>();
//        Cursor c = db.rawQuery(selectQuery.toString(), null);
//        if (c.moveToFirst()) {
//            do {
//                VisitaPersonal visitaPersonal = new VisitaPersonal();
//                visitaPersonal.setCodVisitaPersonal(c.getInt(c.getColumnIndex("cod_visita_personal")));
//                visitaPersonal.setCodEstadoVisitaPersonal(c.getInt(c.getColumnIndex("cod_estado_visita_personal")));
//                visitaPersonal.setCodPersonal(c.getInt(c.getColumnIndex("cod_personal")));
//                visitaPersonal.setCodAreaEmpresa(c.getInt(c.getColumnIndex("cod_area_empresa")));
//                visitaPersonal.setCodCliente(c.getInt(c.getColumnIndex("cod_cliente")));
//
//                try {
//                    visitaPersonal.setFechaVisita(f.parse(c.getString(c.getColumnIndex("fecha_vista"))));
//                } catch (ParseException e1) {
//                    // TODO Auto-generated catch block
//                    e1.printStackTrace();
//                }
//                visitaPersonal.setLatitud(c.getDouble(c.getColumnIndex("latitud")));
//                visitaPersonal.setLongitud(c.getDouble(c.getColumnIndex("longitud")));
//                visitaPersonal.setCodTipoVisitaPersonal(c.getInt(c.getColumnIndex("cod_tipo_visita_personal")));
//
//                visitaPersonal.setObsVisitaPersonal(c.getString(c.getColumnIndex("obs_visita_personal")));
//                visitaPersonal.setNombreVisitaCliente(c.getString(c.getColumnIndex("nombre_cliente")));
//                visitaPersonal.setNombreTipoVisitaPersonal(c.getString(c.getColumnIndex("nombre_tipo_visita")));
//
//                listado.add(visitaPersonal);
//            } while (c.moveToNext());
//            c.close();
//            db.close();
//        }
//        return listado;
//    }
//
//    public ArrayList<TipoVisitaPersonal> getTipoVisita() {
//        StringBuilder selectQuery = new StringBuilder();
//        selectQuery.append(" select   cod_tipo_visita,nombre_tipo_visita  from tipo_visita where cod_tipo_visita not in(1,3) ");
//
//        Log.i("INFO", selectQuery.toString());
//        SQLiteDatabase db = this.getReadableDatabase();
//        ArrayList<TipoVisitaPersonal> listado = new ArrayList<TipoVisitaPersonal>();
//        Cursor c = db.rawQuery(selectQuery.toString(), null);
//        if (c.moveToFirst()) {
//            do {
//                TipoVisitaPersonal visitaPersonal = new TipoVisitaPersonal();
//                visitaPersonal.setCodTipoVisita(c.getInt(c.getColumnIndex("cod_tipo_visita")));
//                visitaPersonal.setNombreTipoVisita(c.getString(c.getColumnIndex("nombre_tipo_visita")));
//
//                listado.add(visitaPersonal);
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