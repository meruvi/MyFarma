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

public class SalidaVentaController extends SQLiteOpenHelper {

    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String DATA_BASE_NAME = "zeusMobil";
    private static final int DATA_BASE_VERSION = Util.DATA_BASE_VERSION_ZEUS;
    private static final String nombreTablaSalida = "salida";
    String sqlCreateTable = "create table salida(cod_gestion_salida integer,cod_salida integer,cod_almacen_salida integer,fecha_salida datetime,cod_estado_salida integer, nro_factura_salida integer, cod_tipoventa_salida integer,cod_cliente_salida integer ,obs_salida text,monto_total_salida real,monto_cancelado_salida real,cod_personal_salida integer,cod_area integer,cod_tipo_documento_salida integer,cod_estadoentrega_salida integer,obs_entregasalida text,fecha_entregasalida datetime,nro_factura_salida integer )";

    public SalidaVentaController(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }

    public void creandoTablaSalida() {
        Log.i("---INICIANDO---", "-----VERIFICANDO TABLA SALIDA----");
        String selectQuery = " select count(*) as cantidad from sqlite_master where name='" + nombreTablaSalida + "' and type='table' ";
        Log.i("INFO", selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                int cantidad = c.getInt(c.getColumnIndex("cantidad"));
                if (cantidad == 0) {
                    Log.i("LA TABLA SALIDA :", "NO EXISTE");
                    Log.i("CREANDO TABLA SALIDA", sqlCreateTable);
                    db.execSQL(sqlCreateTable);
                } else {
                    Log.i("LA TABLA SALIDA :", "------ EXISTE");
                }
            } while (c.moveToNext());
            c.close();
            db.close();
        }
    }

//    public ArrayList<Cliente> listadoSalidaCliente(String nombreCliente, int codAreaEmpresa, int codCliente) {
//        /*consulta en produccion*/
//        /*SELECT DISTINCT nombre_cliente,
//                cod_cliente
//        FROM   salida s,
//                clientes c
//        WHERE  c.cod_cliente = s.cod_cliente_salida
//        AND c.nombre_cliente LIKE '%%'
//        AND c.cod_area_empresa = 46
//        AND cod_salida NOT IN (SELECT cod_salida
//        FROM   salida_entrega)
//        UNION
//        SELECT DISTINCT nombre_cliente,
//                cli.cod_cliente
//        FROM   salida_material_promocional sam,
//                clientes cli
//        WHERE  cli.cod_cliente = sam.cod_cliente
//        AND cod_salida_matpromocional NOT IN(SELECT cod_salida_matpromocional
//        FROM   salida_material_entrega)
//        AND nombre_cliente LIKE '%%' */
//        /**/
//
//        String sql = "  select distinct nombre_cliente,cod_cliente  from salida s,clientes c where c.cod_cliente=s.cod_cliente_salida ";
//        sql += " and c.nombre_cliente like '%" + nombreCliente + "%' and c.cod_area_empresa=" + codAreaEmpresa + " and cod_salida not in (SELECT cod_salida FROM salida_entrega WHERE cod_estado_entregado_ultimo IN(1,2)) ";
//        if (codCliente != 0) {
//            sql += " and c.cod_cliente=" + codCliente;
//        }
//        sql += " union ";
//        sql += " select   distinct nombre_cliente,cli.cod_cliente from  salida_material_promocional sam ,clientes  cli where cli.cod_cliente=sam.cod_cliente ";
//        sql += " and cod_salida_matpromocional not in( select cod_salida_matpromocional from salida_material_entrega ) and nombre_cliente like '%" + nombreCliente + "%' ";
//        if (codCliente != 0) {
//            sql += " and cli.cod_cliente=" + codCliente;
//        }
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
//    public ArrayList<SalidaEntrega> listadoSalidaCliente(int codCliente, int codAreaEmpresa) {
//        /*String sql = " select cod_tipoventa_salida,s.fecha_salida,nombre_cliente,nro_factura_salida,monto_total_salida,cod_salida  ,( select  cod_estado_entregasalida from  salida_entrega se where se.cod_salida=s.cod_salida ) as cod_estado_entregasalida  ,( select  obs_entrega_salida from  salida_entrega se where se.cod_salida=s.cod_salida ) as obs_entrega_salida from salida s,clientes c where c.cod_cliente=s.cod_cliente_salida" +
//                "  and c.cod_cliente=" + codCliente + "  and c.cod_area_empresa=" + codAreaEmpresa + " and cod_salida not in(select cod_salida from salida_entrega) ";*/
//
//        String sql = " SELECT cod_tipoventa_salida, " +
//                "       s.fecha_salida, " +
//                "       nombre_cliente, " +
//                "       nro_factura_salida, " +
//                "       monto_total_salida, " +
//                "       cod_salida , " +
//                "       ( " +
//                "              SELECT cod_estado_entregasalida " +
//                "              FROM   salida_entrega se " +
//                "              WHERE  se.cod_salida=s.cod_salida ) AS cod_estado_entregasalida , " +
//                "       ( " +
//                "              SELECT obs_entrega_salida " +
//                "              FROM   salida_entrega se " +
//                "              WHERE  se.cod_salida=s.cod_salida ) AS obs_entrega_salida " +
//                " FROM   salida s, " +
//                "       clientes c " +
//                " WHERE  c.cod_cliente=s.cod_cliente_salida " +
//                " AND    c.cod_cliente= " + codCliente +
//                " AND    c.cod_area_empresa= " + codAreaEmpresa +
//                " AND    cod_salida NOT IN " +
//                "       ( " +
//                "              SELECT cod_salida " +
//                "              FROM   salida_entrega " +
//                "              WHERE cod_estado_entregado_ultimo IN (1,2))"; // No este entregado ni anulado
//
//        ArrayList<SalidaEntrega> listado = new ArrayList<SalidaEntrega>();
//        Log.i("INFO", sql);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(sql, null);
//        if (c.moveToFirst()) {
//            do {
//                SalidaEntrega s = new SalidaEntrega();
//                s.setCodTipoVenta(c.getInt(c.getColumnIndex("cod_tipoventa_salida")));
//                s.setCodSalida(c.getInt(c.getColumnIndex("cod_salida")));
//                s.setCodAreaEmpresa(codAreaEmpresa);
//                s.setCodCliente(codCliente);
//                s.setMontoTotalVenta(c.getDouble(c.getColumnIndex("monto_total_salida")));
//                s.setNroDocumento(c.getInt(c.getColumnIndex("nro_factura_salida")));
//                s.setCodEstadoEntregaSalida(c.getInt(c.getColumnIndex("cod_estado_entregasalida")));
//                s.setObsEntregaSalida(c.getString(c.getColumnIndex("obs_entrega_salida")));
//                try {
//                    s.setFechaSalidaVenta(f.parse(c.getString(c.getColumnIndex("fecha_salida"))));
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                listado.add(s);
//            } while (c.moveToNext());
//            c.close();
//            db.close();
//        }
//        return listado;
//    }
//
//    public void registroSalidaVenta(Collection<SalidaVenta> listado) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        try {
//            db.beginTransaction();
//            db.delete("salida", null, null);
//            for (SalidaVenta salidaVenta : listado) {
//                ContentValues values = new ContentValues();
//                values.put("cod_gestion_salida", salidaVenta.getCodGestion());
//                values.put("cod_salida", salidaVenta.getCodSalidaVenta());
//                values.put("cod_almacen_salida", salidaVenta.getCodAlmacenVenta());
//                values.put("fecha_salida", f.format(salidaVenta.getFechaSalidaVenta()));
//                values.put("cod_estado_salida", salidaVenta.getCodEstadoSalidaVenta());
//                values.put("cod_tipoventa_salida", salidaVenta.getCodTipoVenta());
//                values.put("cod_cliente_salida", salidaVenta.getCodCliente());
//                values.put("obs_salida", salidaVenta.getObsSalidaVenta());
//                values.put("monto_total_salida", salidaVenta.getMontoTotalVenta());
//                values.put("monto_cancelado_salida", salidaVenta.getMontoTotalCancelado());
//                values.put("cod_personal_salida", salidaVenta.getCodPersonal());
//                values.put("cod_area", salidaVenta.getCodAreaEmpresa());
//                values.put("cod_tipo_documento_salida", salidaVenta.getCodTipoDocumentoSalida());
//                values.put("cod_estadoentrega_salida", salidaVenta.getCodEstadoEntregaSalida());
//                values.put("obs_entregasalida", salidaVenta.getObsEntregaSalida());
//                values.put("nro_factura_salida", salidaVenta.getNroFactura());
//
//                if (salidaVenta.getFechaEntregaSalida() != null) {
//                    values.put("fecha_entregasalida", f.format(salidaVenta.getFechaEntregaSalida()));
//                }
//
//                db.insert("salida", null, values);
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
//    public ArrayList<SalidaEntrega> listadoSalidaEntregadoCliente(int codCliente) {
//        String sql = " select  fecha_salida,(select c.nombre_cliente from clientes c where s.cod_cliente_salida=c.cod_cliente),monto_total_venta,nro_documento_venta, fecha_entrega_salida  from " +
//                "   salida_entrega s  where s.cod_cliente_salida=" + codCliente;
//
//        ArrayList<SalidaEntrega> listado = new ArrayList<SalidaEntrega>();
//        Log.i("INFO", sql);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(sql, null);
//        if (c.moveToFirst()) {
//            do {
//                SalidaEntrega s = new SalidaEntrega();
//                try {
//                    s.setFechaSalidaVenta(f.parse(c.getString(c.getColumnIndex("fecha_salida"))));
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                s.setMontoTotalVenta(c.getDouble(c.getColumnIndex("monto_total_venta")));
//                s.setNroDocumento(c.getInt(c.getColumnIndex("nro_documento_venta")));
//                try {
//                    s.setFechaEntregaSalida(f.parse(c.getString(c.getColumnIndex("fecha_entrega_salida"))));
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                listado.add(s);
//            } while (c.moveToNext());
//            c.close();
//            db.close();
//        }
//        return listado;
//    }
//
//    public ArrayList<SalidaMaterialPromocionalEntrega> listadoSalidaMaterialEntregadoCliente(int codCliente) {
//        String sql = " select nro_salida_matpromocional,fecha_salida_material,cantidad_salida_matpromocional,fecha_entrega_salida,(select nombre_matpromocional from mat_promocional m where " +
//                "  m.cod_matpromocional=s.cod_mat_promocional) nombre_matpromocional from salida_material_entrega s  where cod_cliente=" + codCliente;
//
//        ArrayList<SalidaMaterialPromocionalEntrega> listado = new ArrayList<SalidaMaterialPromocionalEntrega>();
//        Log.i("INFO", sql);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(sql, null);
//        if (c.moveToFirst()) {
//            do {
//                SalidaMaterialPromocionalEntrega s = new SalidaMaterialPromocionalEntrega();
//                s.setNroSalidaMaterialPromocional(c.getInt(c.getColumnIndex("nro_salida_matpromocional")));
//                try {
//                    s.setFechaSalidaMaterialPromocional(f.parse(c.getString(c.getColumnIndex("fecha_salida_material"))));
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                s.setCantidadSalidaMaterialPromocional(c.getInt(c.getColumnIndex("cantidad_salida_matpromocional")));
//                try {
//                    s.setFechaEntregaSalidaMaterial(f.parse(c.getString(c.getColumnIndex("fecha_entrega_salida"))));
//                } catch (ParseException e) {
//
//                    e.printStackTrace();
//                }
//                s.setNombreMaterialPromocional(c.getString(c.getColumnIndex("nombre_matpromocional")));
//                listado.add(s);
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
