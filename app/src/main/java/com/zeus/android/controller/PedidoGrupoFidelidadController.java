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
import java.util.Calendar;
import java.util.Collection;

public class PedidoGrupoFidelidadController extends SQLiteOpenHelper {
    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String DATA_BASE_NAME = "zeusMobil";
    private static final int DATA_BASE_VERSION = Util.DATA_BASE_VERSION_ZEUS;
    private static final String nombreTablaPedidoGrupoFidelidad = "pedido_grupo_fidelidad";
    String sqlCreateTable = "create table pedido_grupo_fidelidad(COD_PEDIDO integer, ID_GRUPO_POLITICA_PRECIO integer, ID_POLITICA_PRECIO_CORTE integer, COD_MES integer, COD_GESTION integer, MONTO_ACUMULADO real, DESCUENTO_FIDELIDAD real, MONTO_DESCUENTO real, MONTO_ACCESO real, ES_APLICADO_DESCUENTO int)";

    public PedidoGrupoFidelidadController(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }

    public void crearTablaPedidoGrupoFidelidad() {
        Log.i("---INICIANDO---", "-----VERIFICANDO TABLA PEDIDO GRUPO FIDELIDAD ----");
        String selectQuery = " select count(*) as cantidad from sqlite_master where name='" + nombreTablaPedidoGrupoFidelidad + "' and type='table' ";
        Log.i("INFO", selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                int cantidad = c.getInt(c.getColumnIndex("cantidad"));
                if (cantidad == 0) {
                    Log.i("TABLA PEDIDOGRUPOFIDE:", "NO EXISTE");
                    Log.i("CREANDO PEDIDOGRUPOFIDE", sqlCreateTable);
                    db.execSQL(sqlCreateTable);
                    Log.i("CREANDO PEDIDOGRUPOFIDE", "TABLA CREADA....OK");
                } else {
                    Log.i("TABLA PEDIDOGRUPOFIDE:", "------ EXISTE");
                }
            } while (c.moveToNext());
            c.close();
            db.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*try{
            Log.e("GONZALO-CREAR", "CREANDO LA TABLA POLITICA DE PRECIOS");
            db.execSQL(sqlCreateTable);
            Log.e("GONZALO-CREADO", "CREANDO LA TABLA POLITICA DE PRECIOS");
        }catch (Exception e){
            Log.e("GONZALO-ERROR", ""+e.toString());
        }*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
//    public int actualizarPedidoGrupoFidelidad(Collection<PedidoGrupoFidelidad> listPedidoGrupoFidelidad) {
//
//        int cantidadInsertada = 0;
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        try {
//            db.beginTransaction();
//
//            String codigos[] = new String[0];
//            db.delete("com_grupo_fidelidad", "", codigos);
//
//            for (PedidoGrupoFidelidad bean : listPedidoGrupoFidelidad) {
//                ContentValues values = new ContentValues();
//
//                values.put("COD_PEDIDO", bean.getCodPedido());
//                values.put("ID_GRUPO_POLITICA_PRECIO",bean.getIdGrupoPoliticaPrecio());
//                values.put("ID_POLITICA_PRECIO_CORTE",bean.getIdPoliticaPrecioCorte());
//                values.put("COD_MES",bean.getCodMmes());
//                values.put("COD_GESTION",bean.getCodGestion());
//                values.put("MONTO_ACUMULADO",bean.getMontoAcumulado());
//                values.put("DESCUENTO_FIDELIDAD", bean.getDescuentoFidelidad());
//                values.put("MONTO_DESCUENTO ", bean.getMontoDescuento());
//                values.put("MONTO_ACCESO", bean.getMontoAcceso());
//                values.put("ES_APLICADO_DESCUENTO", bean.getEsAplicadoDescuento());
//
//                db.insert("pedido_grupo_fidelidad", null, values);
//
//                cantidadInsertada++;
//            }
//            db.setTransactionSuccessful();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//
//            db.endTransaction();
//            db.close();
//        }
//
//        return cantidadInsertada;
//    }
//
//    public ArrayList<PedidoGrupoFidelidad> obtenerTablaCorteOffline(int codCliente) {
//
//        int mesCalculado = 0;
//
//        Calendar fecha = Calendar.getInstance();
//        int mes = fecha.get(Calendar.MONTH) + 1;
//        if (mes > 1) {
//            mesCalculado =  mes -1;
//        } else {
//            mesCalculado =  12;
//        }
//
//        Log.e("MESCALCULADO: ", "" + mesCalculado);
//
//        String sql = " select * from com_politica_precio_corte where COD_CLIENTE = " + codCliente + " and COD_MES = " + mesCalculado;
//        ArrayList<PedidoGrupoFidelidad> listaRequestPolPrec=new ArrayList<PedidoGrupoFidelidad>();
//        Log.i("QUERY:", sql);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(sql, null);
//        if (c.moveToFirst()) {
//            do{
//
//                PedidoGrupoFidelidad item = new PedidoGrupoFidelidad();
//
//                item.setIdPoliticaPrecioCorte(c.getInt(c.getColumnIndex("ID")));
//                item.setIdGrupoPoliticaPrecio(c.getInt(c.getColumnIndex("ID_GRUPO_POLITICA_PRECIO")));
//                item.setCodMmes(c.getInt(c.getColumnIndex("COD_MES")));
//                item.setCodGestion(c.getInt(c.getColumnIndex("COD_GESTION")));
//                item.setMontoAcumulado(c.getDouble(c.getColumnIndex("VENTA_BOLIVIANOS")));
//                item.setDescuentoFidelidad(c.getDouble(c.getColumnIndex("DESCUENTO_COMERCIAL")));
//                item.setMontoDescuento(c.getDouble(c.getColumnIndex("DESCUENTO")));
//                item.setMontoAcceso(c.getDouble(c.getColumnIndex("MONTO_ACCESO")));
//                //item.setEsAplicadoDescuento();
//
//                listaRequestPolPrec.add(item);
//
//            } while (c.moveToNext());
//
//            //c.close();
//        }
//        db.close();
//
//        return listaRequestPolPrec;
//    }
//
//    public PedidoGrupoFidelidad getBean(int codPedido, int idGrupo) {
//
//        String sql = "select * from pedido_grupo_fidelidad where COD_PEDIDO = " + codPedido + " and ES_APLICADO_DESCUENTO = 1 and ID_GRUPO_POLITICA_PRECIO = " + idGrupo;
//        Log.i("QUERY:", sql);
//        PedidoGrupoFidelidad item = new PedidoGrupoFidelidad();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(sql, null);
//        if (c.moveToFirst()) {
//
//            item.setCodPedido(c.getInt(c.getColumnIndex("COD_PEDIDO")));
//            item.setIdPoliticaPrecioCorte(c.getInt(c.getColumnIndex("ID_POLITICA_PRECIO_CORTE")));
//            item.setIdGrupoPoliticaPrecio(c.getInt(c.getColumnIndex("ID_GRUPO_POLITICA_PRECIO")));
//            item.setCodMmes(c.getInt(c.getColumnIndex("COD_MES")));
//            item.setCodGestion(c.getInt(c.getColumnIndex("COD_GESTION")));
//            item.setMontoAcumulado(c.getDouble(c.getColumnIndex("MONTO_ACUMULADO")));
//            item.setDescuentoFidelidad(c.getDouble(c.getColumnIndex("DESCUENTO_FIDELIDAD")));
//            item.setMontoDescuento(c.getDouble(c.getColumnIndex("MONTO_DESCUENTO")));
//            item.setMontoAcceso(c.getDouble(c.getColumnIndex("MONTO_ACCESO")));
//            item.setEsAplicadoDescuento(c.getInt(c.getColumnIndex("ES_APLICADO_DESCUENTO")));
//
//        }
//        db.close();
//
//        return item;
//    }
}