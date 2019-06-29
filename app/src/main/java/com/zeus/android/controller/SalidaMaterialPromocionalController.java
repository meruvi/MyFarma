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

public class SalidaMaterialPromocionalController extends SQLiteOpenHelper {

    Context context;
    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String DATA_BASE_NAME = "zeusMobil";
    private static final int DATA_BASE_VERSION = Util.DATA_BASE_VERSION_ZEUS;

    private static final String nombreTablaSalida = "salida_material_promocional";
    String sqlCreateTable = "create table " + nombreTablaSalida
            + "(cod_salida_matpromocional integer,cod_cliente integer,nro_salida_matpromocional integer,cod_mat_promocional integer,cantidad_salida_matpromocional integer,cod_gestion integer,fecha_salida_material datetime,obs_salida_matpromocional text,cod_salida_venta integer,obs_salida_venta text)";

    public SalidaMaterialPromocionalController(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
        this.context = context;
    }

    public void creandoTabla() {
        Log.i("---INICIANDO---", "-----VERIFICANDO TABLA " + nombreTablaSalida + "----");

        String selectQuery = " select count(*) as cantidad from sqlite_master where name='" + nombreTablaSalida + "' and type='table' ";
        Log.i("INFO", selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                int cantidad = c.getInt(c.getColumnIndex("cantidad"));
                if (cantidad == 0) {
                    Log.i(context.getClass().getSimpleName(), "LA TABLA " + nombreTablaSalida + " :............NO EXISTE");
                    Log.i(context.getClass().getSimpleName(),"CREANDO TABLA " + nombreTablaSalida + ".........." + sqlCreateTable);
                    db.execSQL(sqlCreateTable);
                } else {
                    Log.i(context.getClass().getSimpleName(),"LA TABLA " + nombreTablaSalida + " :............ EXISTE");
                }
            } while (c.moveToNext());
            c.close();
            db.close();
        }
    }

//    public ArrayList<Cliente> listadoSalidaCliente(String nombreCliente, int codAreaEmpresa) {
//        String sql = " select distinct nombre_cliente,cod_cliente  from salida s,clientes c where c.cod_cliente=s.cod_cliente_salida"
//                + "  and c.nombre_cliente like '%"
//                + nombreCliente
//                + "%' and c.cod_area_empresa=" + codAreaEmpresa;
//
//        ArrayList<Cliente> listado = new ArrayList<Cliente>();
//        Log.i("INFO", sql);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(sql, null);
//        if (c.moveToFirst()) {
//            do {
//                Cliente cliente = new Cliente();
//                cliente.setCodCliente(c.getInt(c.getColumnIndex("cod_cliente")));
//                cliente.setNombreCliente(c.getString(c.getColumnIndex("nombre_cliente")));
//                listado.add(cliente);
//            } while (c.moveToNext());
//            c.close();
//            db.close();
//        }
//        return listado;
//    }
//
//    public ArrayList<SalidaMaterialPromocionalEntrega> listadoSalidaMaterialCliente(int codCliente) {
//        String sql = " select cod_salida_matpromocional,nombre_cliente,nro_salida_matpromocional,fecha_salida_material,nombre_matpromocional,cantidad_salida_matpromocional,obs_salida_matpromocional,obs_salida_venta "
//                + " , c.cod_cliente,cod_mat_promocional from salida_material_promocional s,clientes c,mat_promocional m where c.cod_cliente=s.cod_cliente "
//                + "  and m.cod_matpromocional=s.cod_mat_promocional and s.cod_cliente="
//                + codCliente + "    order by nombre_cliente  ";
//        ArrayList<SalidaMaterialPromocionalEntrega> listado = new ArrayList<SalidaMaterialPromocionalEntrega>();
//        Log.i("INFO", sql);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(sql, null);
//        if (c.moveToFirst()) {
//            do {
//                SalidaMaterialPromocionalEntrega s = new SalidaMaterialPromocionalEntrega();
//                s.setCodSalidaMaterialPromocional(c.getInt(c.getColumnIndex("cod_salida_matpromocional")));
//                s.setNombreCliente(c.getString(c.getColumnIndex("nombre_cliente")));
//                s.setNroSalidaMaterialPromocional(c.getInt(c.getColumnIndex("nro_salida_matpromocional")));
//                s.setNombreMaterialPromocional(c.getString(c.getColumnIndex("nombre_matpromocional")));
//                s.setCantidadSalidaMaterialPromocional(c.getInt(c.getColumnIndex("cantidad_salida_matpromocional")));
//                s.setObsMaterialOferta(c.getString(c.getColumnIndex("obs_salida_matpromocional")));
//                s.setObsSalidaventa(c.getString(c.getColumnIndex("obs_salida_venta")));
//                try {
//                    s.setFechaSalidaMaterialPromocional((f.parse(c.getString(c.getColumnIndex("fecha_salida_material")))));
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                s.setCodCliente(c.getInt(c.getColumnIndex("cod_cliente")));
//                s.setCodMaterialPromocional(c.getInt(c.getColumnIndex("cod_mat_promocional")));
//                listado.add(s);
//            } while (c.moveToNext());
//            c.close();
//        }
//        db.close();
//        return listado;
//    }
//
//    public ArrayList<SalidaMaterialPromocionalEntrega> listadoSalidaMaterialClienteEntregado(int codCliente) {
//        String sql = " select cod_salida_matpromocional,nombre_cliente,nro_salida_matpromocional,fecha_salida_material,nombre_matpromocional,cantidad_salida_matpromocional,obs_salida_matpromocional,obs_salida_venta "
//                + " from salida_material_promocional s,clientes c,mat_promocional m where c.cod_cliente=s.cod_cliente "
//                + "  and m.cod_matpromocional=s.cod_mat_promocional and s.cod_cliente="
//                + codCliente + "    order by nombre_cliente  ";
//
//        ArrayList<SalidaMaterialPromocionalEntrega> listado = new ArrayList<SalidaMaterialPromocionalEntrega>();
//        Log.i("INFO", sql);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(sql, null);
//        if (c.moveToFirst()) {
//            do {
//                SalidaMaterialPromocionalEntrega s = new SalidaMaterialPromocionalEntrega();
//                s.setCodSalidaMaterialPromocional(c.getInt(c.getColumnIndex("cod_salida_matpromocional")));
//                s.setNombreCliente(c.getString(c.getColumnIndex("nombre_cliente")));
//                s.setNroSalidaMaterialPromocional(c.getInt(c.getColumnIndex("nro_salida_matpromocional")));
//                s.setNombreMaterialPromocional(c.getString(c.getColumnIndex("nombre_matpromocional")));
//                s.setCantidadSalidaMaterialPromocional(c.getInt(c.getColumnIndex("cantidad_salida_matpromocional")));
//                s.setObsMaterialOferta(c.getString(c.getColumnIndex("obs_salida_matpromocional")));
//                s.setObsSalidaventa(c.getString(c.getColumnIndex("obs_salida_venta")));
//                try {
//                    s.setFechaSalidaMaterialPromocional((f.parse(c.getString(c.getColumnIndex("fecha_salida_material")))));
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                listado.add(s);
//            } while (c.moveToNext());
//            c.close();
//        }
//        db.close();
//        return listado;
//    }
//
//    public void registroSalidaMaterialPromocional(Collection<SalidaMaterialPromocional> listado) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        try {
//            db.beginTransaction();
//            db.delete(nombreTablaSalida, null, null);
//            for (SalidaMaterialPromocional salidaVenta : listado) {
//                ContentValues values = new ContentValues();
//                values.put("cod_salida_matpromocional", salidaVenta.getCodSalidaMatPromocional());
//                values.put("cod_cliente", salidaVenta.getCodCliente());
//                values.put("nro_salida_matpromocional", salidaVenta.getNroSalidaMatPromocional());
//                values.put("cod_mat_promocional", salidaVenta.getCodMatPromocional());
//                values.put("cantidad_salida_matpromocional", salidaVenta.getCantidadSalidaMatPromocional());
//                values.put("cod_gestion", salidaVenta.getCodGestion());
//                values.put("obs_salida_matpromocional", salidaVenta.getObsSalidaMaterialPromocional());
//                values.put("cod_salida_venta", salidaVenta.getCodSalidaVenta());
//                values.put("obs_salida_venta", salidaVenta.getObsSalidaVenta());
//                values.put("fecha_salida_material", f.format(salidaVenta.getFechaSalidaMaterialPromocional()));
//                db.insert(nombreTablaSalida, null, values);
//            }
//            db.setTransactionSuccessful();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            db.endTransaction();
//            db.close();
//        }
//    }
//
//    public void registroMaterialPromocional(Collection<MatPromocional> listado) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        try {
//            db.beginTransaction();
//            db.delete("mat_promocional", null, null);
//            for (MatPromocional salidaVenta : listado) {
//                ContentValues values = new ContentValues();
//                values.put("cod_matpromocional", salidaVenta.getCodMatPromocional());
//                values.put("nombre_matpromocional", salidaVenta.getNombreMatPromocional());
//                db.insert("mat_promocional", null, values);
//            }
//            db.setTransactionSuccessful();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            db.endTransaction();
//            db.close();
//        }
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