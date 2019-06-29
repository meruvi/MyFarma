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
import java.util.List;

public class CobranzaController extends SQLiteOpenHelper {
    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String DATA_BASE_NAME = "zeusMobil";
    private static final int DATA_BASE_VERSION = Util.DATA_BASE_VERSION_ZEUS;
    private static String nombreTabla = "cobranza";
    private static String nombreTablaDetalle = "cobranza_detalle";
    String sqlCreateTable = "create table " + nombreTabla + "(cod_cobranza integer ,cod_personal integer,nro_cobranza integer,cod_tipo_cobranza integer,cod_estado_cobranza integer,fecha_cobranza datetime,obs_cobranza text,cod_area_empresa integer,cod_cliente integer default 0, nro_recibo integer,fecha_recibo datetime,cod_tipo_pago integer,cod_banco integer,nro_cheque text,fecha_cheque datetime,sincronizado_registro integer default 0 ,cod_cobranza_talonario integer default 0,cod_tipo_venta integer)";
    String sqlCreateTableDetalle = "create table " + nombreTablaDetalle + "(cod_salidaventa integer,cod_cobranza integer,cod_tipo_pago integer,monto_cobranza real,cod_banco integer,nro_cheque text,nro_cobranzadetalle integer,fecha_cobranzadetalle datetime,cod_area_empresa integer,monto_total_venta real,nro_doc_venta text,fecha_salida_venta datetime)";
    String sqlCreateTableBanco = "create table banco(cod_banco integer primary key,nombre_banco text)";
    String sqlCreateTableTalonario = "create table cobranza_talonario(cod_cobranza_talonario integer ,inicio_talonario integer ,fin_talonario integer,cod_personal integer )";
    Context context;

    public CobranzaController(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
        this.context = context;
    }

    public void creandoTablaDetalle() {
        creandoTabla();
        nombreTabla = nombreTablaDetalle;
        sqlCreateTable = sqlCreateTableDetalle;
        creandoTabla();
        creandoTablaBanco();
        creandoTablaTalonario();
    }
//
    public void crearColumnaTabla(String nombreColumna, String nombreTabla, String tipoColumna) {
        if (!verificarColumnTabla(nombreColumna, nombreTabla)) {
            SQLiteDatabase db = this.getReadableDatabase();
            String sqlColumn = " ALTER TABLE " + nombreTabla + " ADD COLUMN " + nombreColumna + " " + tipoColumna + " ";
            db.execSQL(sqlColumn);
            Log.i(context.getClass().getSimpleName(), "CREANDO  TABLA: " + nombreTabla + " COLUMNA :" + nombreColumna + "............" + sqlColumn);
            db.close();
        }
    }
//
    public boolean verificarColumnTabla(String nombreColumnaVerificar, String nombreTabla) {
        String selectQuery = "PRAGMA table_info(" + nombreTabla + ")";
        Log.i("INFO", selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                String nombreColumna = c.getString(c.getColumnIndex("name"));
                if (nombreColumnaVerificar.equals(nombreColumna)) {
                    Log.i(context.getClass().getSimpleName(),"VERIFICANDO TABLA: " + nombreTabla + " COLUMNA :" + nombreColumnaVerificar + "............EXISTE");
                    return true;
                }
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        Log.i(context.getClass().getSimpleName(),"VERIFICANDO TABLA: " + nombreTabla + " COLUMNA :" + nombreColumnaVerificar + "..............NO EXISTE");
        return false;
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
//
    public void creandoTablaBanco() {
        Log.i("---INICIANDO---", "-----VERIFICANDO TABLA----");
        String selectQuery = " select count(*) as cantidad from sqlite_master where name='banco' and type='table' ";
        Log.i("INFO", selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                int cantidad = c.getInt(c.getColumnIndex("cantidad"));
                if (cantidad == 0) {
                    Log.i("LA TABLA  :", "NO EXISTE");
                    Log.i("CREANDO TABLA BANCO", sqlCreateTableBanco);
                    db.execSQL(sqlCreateTableBanco);
                } else {
                    Log.i("LA TABLA BANCO :", "------ EXISTE");
                }
            } while (c.moveToNext());
            c.close();
            db.close();
        }
    }
//
    public void creandoTablaTalonario() {
        Log.i("---INICIANDO---", "-----VERIFICANDO TABLA----");
        String selectQuery = " select count(*) as cantidad from sqlite_master where name='cobranza_talonario' and type='table' ";
        Log.i("INFO", selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                int cantidad = c.getInt(c.getColumnIndex("cantidad"));
                if (cantidad == 0) {
                    Log.i("LA TABLA  :", "NO EXISTE");
                    Log.i("CREANDO TABLA talonario", sqlCreateTableTalonario);
                    db.execSQL(sqlCreateTableTalonario);
                } else {
                    Log.i("LA TABLA BANCO :", "------ EXISTE");
                }
            } while (c.moveToNext());
            c.close();
            db.close();
        }
    }
//
//    public int getMaximoCobranza(int codAreaEmpresa) {
//        int maximo = 0;
//        String selectQuery = " SELECT max(cod_cobranza) as maximo FROM cobranza where cod_area_empresa=" + codAreaEmpresa;
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//                maximo = c.getInt(c.getColumnIndex("maximo"));
//            } while (c.moveToNext());
//            c.close();
//            db.close();
//        }
//        maximo++;
//        return maximo;
//    }
//
//    public int getNroRecibo(int codAreaEmpresa, Personal personal, CobranzaTalonario cobranzaTalonario) {
//        int maximo = 0;
//        String selectQuery = " SELECT max(nro_recibo) as maximo FROM cobranza where cod_area_empresa=" + codAreaEmpresa + " and cod_personal=" + personal.getCodPersonal() + "  and cod_cobranza_talonario in(  select  cod_cobranza_talonario  from cobranza_talonario ct where ct.cod_personal=" + personal.getCodPersonal() + ")  ";
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//                maximo = c.getInt(c.getColumnIndex("maximo"));
//            } while (c.moveToNext());
//            c.close();
//        }
//        Log.i("maximo", maximo + "");
//        if (maximo == 0) {
//            maximo = cobranzaTalonario.getInicioTalonario();
//        } else {
//            if (maximo == cobranzaTalonario.getFinTalonario()) {
//                maximo = -1;
//            } else {
//                maximo++;
//            }
//        }
//        return maximo;
//    }
//
//    public CobranzaTalonario getCobranzaTalonario(int codAreaEmpresa, Personal personal) {
//        CobranzaTalonario cobranzaTalonario = new CobranzaTalonario();
//        String selectQuery = "   select  cod_cobranza_talonario,inicio_talonario,fin_talonario ,cod_personal from cobranza_talonario ct where ct.cod_personal=" + personal.getCodPersonal();
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//                cobranzaTalonario.setCodCobranzaTalonario(c.getInt(c.getColumnIndex("cod_cobranza_talonario")));
//                cobranzaTalonario.setInicioTalonario(c.getInt(c.getColumnIndex("inicio_talonario")));
//                cobranzaTalonario.setFinTalonario(c.getInt(c.getColumnIndex("fin_talonario")));
//                cobranzaTalonario.setCodPersonal(c.getInt(c.getColumnIndex("cod_personal")));
//            } while (c.moveToNext());
//            c.close();
//        }
//        db.close();
//        return cobranzaTalonario;
//    }
//
//    public long registrarGuardarCobranza2(Cobranza cobranza, List<CobranzaDetalle> detalle) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues data = new ContentValues();
//        data.put("cod_cobranza", cobranza.getCodCobranza());
//        data.put("cod_personal", cobranza.getCodPersonal());
//        data.put("nro_cobranza", cobranza.getNroCobranza());
//        data.put("cod_tipo_cobranza", cobranza.getCodTipoCobranza());
//        data.put("cod_estado_cobranza", cobranza.getCodEstadoCobranza());
//        data.put("fecha_cobranza", f.format(cobranza.getFechaCobranza()));
//        data.put("obs_cobranza", cobranza.getObsCobranza());
//        data.put("cod_area_empresa", cobranza.getCodAreaEmpresa());
//
//        ContentValues dataDetalle = new ContentValues();
//        for (CobranzaDetalle cobranzaDetalle : detalle) {
//            if (cobranzaDetalle.getMontoCobranza() > 0) {
//                dataDetalle.put("cod_salidaventa", cobranzaDetalle.getCodSalidaVenta());
//                dataDetalle.put("cod_cobranza", cobranzaDetalle.getCodCobranza());
//                dataDetalle.put("cod_tipo_pago", cobranzaDetalle.getCodTipoPago());
//                dataDetalle.put("monto_cobranza", cobranzaDetalle.getMontoCobranza());
//                dataDetalle.put("cod_banco", cobranzaDetalle.getCodBanco());
//                dataDetalle.put("nro_cheque", cobranzaDetalle.getNroCheque());
//                dataDetalle.put("nro_cobranzadetalle", cobranzaDetalle.getNroCobranzaDetalle());
//                dataDetalle.put("fecha_cobranzadetalle", f.format(cobranzaDetalle.getFechaCobranzaDetalle()));
//                dataDetalle.put("cod_area_empresa", cobranzaDetalle.getCodAreaEmpresa());
//            }
//        }
//        return db.insert("cobranza", null, data);
//    }
//
//    public long registrarGuardarCobranza(Cobranza cobranza, List<CuentaCobrar> detalle) {
//        cobranza.setCodCobranza(getMaximoCobranza(cobranza.getCodAreaEmpresa()));
//        cobranza.setNroCobranza(cobranza.getCodCobranza());
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues data = new ContentValues();
//
//        data.put("cod_cobranza", cobranza.getCodCobranza());
//        data.put("cod_personal", cobranza.getCodPersonal());
//        data.put("nro_cobranza", cobranza.getNroCobranza());
//        data.put("cod_tipo_cobranza", cobranza.getCodTipoCobranza());
//        data.put("cod_estado_cobranza", EstadoCobranza.REGISTRADO.codEstadoCobranza);
//        data.put("sincronizado_registro", SincronizadoBeanObject.SINCRONIZADO_NO.codigo);
//        data.put("fecha_cobranza", f.format(cobranza.getFechaCobranza()));
//        data.put("obs_cobranza", cobranza.getObsCobranza());
//        data.put("cod_area_empresa", cobranza.getCodAreaEmpresa());
//        data.put("cod_cliente", cobranza.getCodCliente());
//        data.put("nro_recibo", cobranza.getNroRecibo());
//        data.put("fecha_recibo", f.format(cobranza.getFechaCobranza()));
//        data.put("cod_tipo_pago", cobranza.getCodTipoPago());
//        data.put("cod_banco", cobranza.getCodBanco());
//        data.put("nro_cheque", cobranza.getNroCheque());
//        data.put("cod_cobranza_talonario", cobranza.getCodCobranzaTalonario());
//
//        if (cobranza.getCodTipoPago() == TipoPago.CHECHE_POSTDATADO.codTipoPago) {
//            if (cobranza.getFechaCheque() != null) {
//                data.put("fecha_cheque", f.format(cobranza.getFechaCheque()));
//            }
//        }
//
//        long result = db.insert("cobranza", null, data);
//        ContentValues dataDetalle = new ContentValues();
//        Log.i("Size:", detalle.size() + "");
//        for (CuentaCobrar cobranzaDetalle : detalle) {
//            if (cobranzaDetalle.getCodEstadoSeleccion() == 1) {
//                dataDetalle.put("cod_salidaventa", cobranzaDetalle.getCodSalidaVenta());
//                dataDetalle.put("cod_cobranza", cobranza.getCodCobranza());
//
//                double montoCobranza = cobranzaDetalle.getMontoCancelado() - cobranzaDetalle.getMontoCanceladoCopia();
//
//                Log.i("getMontoCancelado:", cobranzaDetalle.getMontoCancelado() + "");
//                Log.i("getMontoCanceladoCopia:", cobranzaDetalle.getMontoCanceladoCopia() + "");
//
//                dataDetalle.put("monto_saldo", cobranzaDetalle.getSaldoCobranza());
//                dataDetalle.put("monto_cobranza", montoCobranza);
//                dataDetalle.put("cod_area_empresa", cobranza.getCodAreaEmpresa());
//                dataDetalle.put("monto_total_venta", cobranzaDetalle.getMontoTotal());
//                dataDetalle.put("nro_doc_venta", cobranzaDetalle.abrevTipoDocVenta + "-" + cobranzaDetalle.getNroFactura());
//                dataDetalle.put("fecha_salida_venta", f.format(cobranzaDetalle.getFechaSalidaVenta()));
//
//                db.insert("cobranza_detalle", null, dataDetalle);
//
//                String sqlUpdateCuentas = " update cuentas_porcobrar set monto_cancelado=monto_cancelado+" + montoCobranza + " where cod_salidaventa=" + cobranzaDetalle.getCodSalidaVenta() + " and cod_area_empresa=" + cobranza.getCodAreaEmpresa();
//                Log.i("ACTUALIZAR:", sqlUpdateCuentas);
//                db.execSQL(sqlUpdateCuentas);
//            }
//        }
//        VisitaPersonal visitaPersonal = new VisitaPersonal();
//        visitaPersonal.setCodPersonal(cobranza.getCodPersonal());
//        visitaPersonal.setCodAreaEmpresa(cobranza.getCodAreaEmpresa());
//        visitaPersonal.setCodCliente(cobranza.getCodCliente());
//        visitaPersonal.setFechaVisita(new java.util.Date());
//        visitaPersonal.setLatitud(0.0d);
//        visitaPersonal.setLongitud(0.0d);
//        visitaPersonal.setCodTipoVisitaPersonal(TipoVisita.REGISTRO_COBRANZA.codTipoVisita);
//
//        LocationTransaccion locationTransaccion = new LocationTransaccion(visitaPersonal, context);
//        return result;
//    }
//
//    public long registrarAnulacionCobranza(Cobranza cobranza) {
//        cobranza.setCodCobranza(getMaximoCobranza(cobranza.getCodAreaEmpresa()));
//        cobranza.setNroCobranza(cobranza.getCodCobranza());
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues data = new ContentValues();
//        data.put("cod_cobranza", cobranza.getCodCobranza());
//        data.put("cod_personal", cobranza.getCodPersonal());
//        data.put("nro_cobranza", cobranza.getNroCobranza());
//        data.put("cod_tipo_cobranza", cobranza.getCodTipoCobranza());
//        data.put("cod_estado_cobranza", EstadoCobranza.ANULADO.codEstadoCobranza);
//        data.put("sincronizado_registro", SincronizadoBeanObject.SINCRONIZADO_NO.codigo);
//        data.put("fecha_cobranza", f.format(cobranza.getFechaCobranza()));
//        data.put("obs_cobranza", cobranza.getObsCobranza());
//        data.put("cod_area_empresa", cobranza.getCodAreaEmpresa());
//        data.put("cod_cliente", cobranza.getCodCliente());
//        data.put("nro_recibo", cobranza.getNroRecibo());
//        data.put("fecha_recibo", f.format(cobranza.getFechaCobranza()));
//        data.put("cod_tipo_pago", cobranza.getCodTipoPago());
//        data.put("cod_banco", cobranza.getCodBanco());
//        data.put("nro_cheque", cobranza.getNroCheque());
//        data.put("cod_cobranza_talonario", cobranza.getCodCobranzaTalonario());
//
//        long result = db.insert("cobranza", null, data);
//        ContentValues dataDetalle = new ContentValues();
//        return result;
//    }
//
//
//    public ArrayList<Cobranza> getListadoCobranza(Personal personal) {
//        Log.i("INFO", "getListadoCobranzagetListadoCobranzagetListadoCobranza::::::::::>" + personal);
//        String selectQuery = " select cod_estado_cobranza,nro_cobranza,fecha_cobranza,(select nombre_cliente from clientes cli where  c.cod_cliente=cli.cod_cliente ) nombre_cliente   ,(select sum(monto_cobranza) from cobranza_detalle cd where " +
//                " cd.cod_cobranza=c.cod_cobranza)   monto_cobranza,cod_cobranza,sincronizado_registro,cod_tipo_pago,cod_banco,nro_recibo,fecha_recibo,nro_cheque,fecha_cheque,obs_cobranza  from cobranza c where  cod_personal=" + personal.getCodPersonal() + " and cod_area_empresa=" + personal.getCodAreaEmpresa() + " order by  cod_cobranza desc";
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        ArrayList<Cobranza> listado = new ArrayList<Cobranza>();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//                Cobranza cobranza = new Cobranza();
//                cobranza.setObsCobranza(c.getString(c.getColumnIndex("obs_cobranza")));
//                cobranza.setCodCobranza(c.getInt(c.getColumnIndex("cod_cobranza")));
//                cobranza.setSincronizadoRegistro(c.getInt(c.getColumnIndex("sincronizado_registro")));
//                cobranza.setNroCobranza(c.getInt(c.getColumnIndex("nro_cobranza")));
//                cobranza.setTotalCobranza(c.getDouble(c.getColumnIndex("monto_cobranza")));
//                cobranza.setNombreCliente(c.getString(c.getColumnIndex("nombre_cliente")));
//                cobranza.setCodEstadoCobranza(c.getInt(c.getColumnIndex("cod_estado_cobranza")));
//                cobranza.setCodTipoPago(c.getInt(c.getColumnIndex("cod_tipo_pago")));
//                cobranza.setCodBanco(c.getInt(c.getColumnIndex("cod_banco")));
//                cobranza.setNroRecibo(c.getInt(c.getColumnIndex("nro_recibo")));
//                try {
//                    cobranza.setFechaRecibo(f.parse(c.getString(c.getColumnIndex("fecha_recibo"))));
//                } catch (ParseException e1) {
//                    e1.printStackTrace();
//                }
//                cobranza.setNroCheque(c.getString(c.getColumnIndex("nro_cheque")));
//                if (cobranza.getCodTipoPago() == TipoPago.CHECHE_POSTDATADO.codTipoPago) {
//                    try {
//                        cobranza.setFechaCheque(f.parse(c.getString(c.getColumnIndex("fecha_cheque"))));
//                    } catch (ParseException e1) {
//                        e1.printStackTrace();
//                    }
//                }
//                if (cobranza.getCodEstadoCobranza() == EstadoCobranza.ANULADO.codEstadoCobranza) {
//                    cobranza.setNombreEstadoCobranza(EstadoCobranza.ANULADO.nombreEstadoCobranza);
//                } else {
//                    cobranza.setNombreEstadoCobranza(EstadoCobranza.REGISTRADO.nombreEstadoCobranza);
//                }
//
//                try {
//                    cobranza.setFechaCobranza(f.parse(c.getString(c.getColumnIndex("fecha_cobranza"))));
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                listado.add(cobranza);
//            } while (c.moveToNext());
//            c.close();
//            db.close();
//        }
//        return listado;
//    }
//
//    public ArrayList<CuentaCobrar> getListadoCobranzaDetalle(int codCobranza) {
//        String selectQuery = " select nro_doc_venta,cd.monto_cobranza,cod_salidaventa,cod_area_empresa,fecha_salida_venta,monto_saldo from cobranza_detalle cd where cd.cod_cobranza=" + codCobranza;
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        ArrayList<CuentaCobrar> listado = new ArrayList<CuentaCobrar>();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//                CuentaCobrar cobranza = new CuentaCobrar();
//                cobranza.setAbrevTipoDocVenta(c.getString(c.getColumnIndex("nro_doc_venta")));
//                cobranza.setMontoCancelado(c.getDouble(c.getColumnIndex("monto_cobranza")));
//                cobranza.setCodSalidaVenta(c.getInt(c.getColumnIndex("cod_salidaventa")));
//                cobranza.setCodAreaEmpresa(c.getInt(c.getColumnIndex("cod_area_empresa")));
//
//                if (c.getString(c.getColumnIndex("monto_saldo")) == null) {
//                    cobranza.setSaldoCobranza(-1000.0d);
//                } else {
//                    cobranza.setSaldoCobranza(c.getDouble(c.getColumnIndex("monto_saldo")));
//                }
//                try {
//                    cobranza.setFechaSalidaVenta(f.parse(c.getString(c.getColumnIndex("fecha_salida_venta"))));
//                } catch (ParseException e1) {
//                    e1.printStackTrace();
//                }
//                listado.add(cobranza);
//            } while (c.moveToNext());
//            c.close();
//            db.close();
//        }
//        return listado;
//    }
//
//    public ArrayList<CobranzaGroupDay> getListadoCobranzaDia(Personal personal) {
//        SimpleDateFormat f5 = new SimpleDateFormat("yyyy-MM-dd");
//        String selectQuery = " select strftime( '%d',fecha_cobranza) day  , strftime( '%m',fecha_cobranza) month ,strftime( '%Y',fecha_cobranza) year,sum(monto_cobranza)  monto_cobranza_dia from cobranza c,cobranza_detalle cd ";
//        selectQuery += " where cd.cod_cobranza=c.cod_cobranza and cod_personal=" + personal.getCodPersonal() + " and cd.cod_area_empresa=" + personal.getCodAreaEmpresa() + " and cod_estado_cobranza not in (" + EstadoCobranza.ANULADO.codEstadoCobranza + ") ";
//        selectQuery += " group by year,month,day  order by fecha_cobranza desc ";
//        int nroDia = 1;
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        ArrayList<CobranzaGroupDay> listado = new ArrayList<CobranzaGroupDay>();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//                CobranzaGroupDay cobranzaGroupDay = new CobranzaGroupDay();
//                cobranzaGroupDay.setNroRegistroDia(nroDia);
//                try {
//                    cobranzaGroupDay.setFechaDiaMes(f5.parse(c.getString(c.getColumnIndex("year")) + "-" + c.getString(c.getColumnIndex("month")) + "-" + c.getString(c.getColumnIndex("day"))));
//                } catch (Exception e) {
//                    Log.e("getListadoCobranzaDia", e.toString());
//                }
//                cobranzaGroupDay.setMontoDiaMes(c.getDouble(c.getColumnIndex("monto_cobranza_dia")));
//                listado.add(cobranzaGroupDay);
//                nroDia++;
//            } while (c.moveToNext());
//            c.close();
//            db.close();
//        }
//        return listado;
//    }
//
//
//    public ArrayList<Cobranza> getListadoCobranzaNoSincronizada(Personal personal) {
//        String selectQuery = " select cod_cobranza_talonario,fecha_cheque,nro_cheque,cod_banco,cod_tipo_pago,fecha_recibo,nro_recibo,cod_cobranza,cod_personal,nro_cobranza,cod_tipo_cobranza,cod_estado_cobranza,fecha_cobranza,obs_cobranza,cod_area_empresa,cod_cliente from cobranza c where sincronizado_registro in(" + SincronizadoBeanObject.SINCRONIZADO_NO.codigo + ") and cod_personal=" + personal.getCodPersonal() + " and cod_area_empresa=" + personal.getCodAreaEmpresa();
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        ArrayList<Cobranza> listado = new ArrayList<Cobranza>();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//                Cobranza cobranza = new Cobranza();
//                cobranza.setCodCobranzaTalonario(c.getInt(c.getColumnIndex("cod_cobranza_talonario")));
//                cobranza.setCodCobranza(c.getInt(c.getColumnIndex("cod_cobranza")));
//                cobranza.setCodPersonal(c.getInt(c.getColumnIndex("cod_personal")));
//                cobranza.setNroCobranza(c.getInt(c.getColumnIndex("nro_recibo")));
//                cobranza.setCodTipoCobranza(c.getInt(c.getColumnIndex("cod_tipo_cobranza")));
//                cobranza.setCodEstadoCobranza(c.getInt(c.getColumnIndex("cod_estado_cobranza")));
//                try {
//                    cobranza.setFechaCobranza(f.parse(c.getString(c.getColumnIndex("fecha_cobranza"))));
//                    cobranza.setFechaRecibo(f.parse(c.getString(c.getColumnIndex("fecha_cobranza"))));
//                    if (c.getString(c.getColumnIndex("fecha_cheque")) != null) {
//                        cobranza.setFechaCheque(f.parse(c.getString(c.getColumnIndex("fecha_cheque"))));
//                    }
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                cobranza.setObsCobranza(c.getString(c.getColumnIndex("obs_cobranza")));
//                cobranza.setCodAreaEmpresa(c.getInt(c.getColumnIndex("cod_area_empresa")));
//
//                cobranza.setNroRecibo(c.getInt(c.getColumnIndex("nro_recibo")));
//                cobranza.setCodTipoPago(c.getInt(c.getColumnIndex("cod_tipo_pago")));
//                cobranza.setCodBanco(c.getInt(c.getColumnIndex("cod_banco")));
//                cobranza.setNroCheque(c.getString(c.getColumnIndex("nro_cheque")));
//
//                selectQuery = " select cod_salidaventa,cod_cobranza,cod_tipo_pago,monto_cobranza,cod_banco,nro_cheque,nro_cobranzadetalle,fecha_cobranzadetalle,cod_area_empresa from cobranza_detalle cd where cd.cod_cobranza=" + cobranza.getCodCobranza();
//
//                Log.i("INFO", selectQuery);
//
//                ArrayList<CobranzaDetalle> listadoDetalle = new ArrayList<CobranzaDetalle>();
//                Cursor cDetalle = db.rawQuery(selectQuery, null);
//                if (cDetalle.moveToFirst()) {
//                    do {
//                        CobranzaDetalle cobranzaDetalle = new CobranzaDetalle();
//                        cobranzaDetalle.setCodSalidaVenta(cDetalle.getInt(cDetalle.getColumnIndex("cod_salidaventa")));
//                        cobranzaDetalle.setCodCobranza(cDetalle.getInt(cDetalle.getColumnIndex("cod_cobranza")));
//                        cobranzaDetalle.setMontoCobranza(cDetalle.getDouble(cDetalle.getColumnIndex("monto_cobranza")));
//                        cobranzaDetalle.setNroCheque(cobranza.getNroCheque());
//                        cobranzaDetalle.setCodAreaEmpresa(cobranza.getCodAreaEmpresa());
//                        cobranzaDetalle.setNroCobranzaDetalle(cobranza.getNroRecibo());
//                        cobranzaDetalle.setFechaCobranzaDetalle(cobranza.getFechaRecibo());
//                        cobranzaDetalle.setCodTipoPago(cobranza.getCodTipoPago());
//                        cobranzaDetalle.setCodBanco(cobranza.getCodBanco());
//                        cobranzaDetalle.setFechaCheque(cobranza.getFechaCheque());
//                        listadoDetalle.add(cobranzaDetalle);
//                    } while (cDetalle.moveToNext());
//                    cDetalle.close();
//                }
//                cobranza.setDetalle(listadoDetalle);
//                listado.add(cobranza);
//            } while (c.moveToNext());
//            c.close();
//            db.close();
//        }
//        return listado;
//    }
//
//    public void updateCobranza(Cobranza cobranza) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues data = new ContentValues();
//        data.put("sincronizado_registro", SincronizadoBeanObject.SINCRONIZADO_SI.codigo);
//        data.put("cod_estado_cobranza", cobranza.getCodEstadoCobranza());
//        db.update("cobranza", data, " cod_cobranza in(" + cobranza.getCodCobranzaAndroid() + ")  and cod_personal=" + cobranza.getCodPersonal() + " and cod_cobranza_talonario=" + cobranza.getCodCobranzaTalonario(), null);
//    }
//
//    public void anularCobranza(Cobranza cobranza) {
//        List<CuentaCobrar> listado = getListadoCobranzaDetalle(cobranza.getCodCobranza());
//        Log.i("CobranzaController", "______________anularCobranza");
//        SQLiteDatabase db = this.getWritableDatabase();
//        for (CuentaCobrar bean : listado) {
//            String sqlUpdateCuentas = " update cuentas_porcobrar set monto_cancelado=monto_cancelado-" + bean.getMontoCancelado() + " where cod_salidaventa=" + bean.getCodSalidaVenta() + " and cod_area_empresa=" + bean.getCodAreaEmpresa();
//            Log.i("anularCobranza", sqlUpdateCuentas);
//            db.execSQL(sqlUpdateCuentas);
//        }
//        ContentValues data = new ContentValues();
//        data.put("cod_estado_cobranza", EstadoCobranza.ANULADO.codEstadoCobranza);
//        db.update("cobranza", data, " cod_cobranza in(" + cobranza.getNroCobranza() + ")  ", null);
//    }
//
//    public void registroCobranzaTalonario(Collection<CobranzaTalonario> listado) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        try {
//            db.beginTransaction();
//            db.delete("cobranza_talonario", null, null);
//            for (CobranzaTalonario c : listado) {
//                ContentValues values = new ContentValues();
//                values.put("cod_cobranza_talonario", c.getCodCobranzaTalonario());
//                values.put("inicio_talonario", c.getInicioTalonario());
//                values.put("fin_talonario", c.getFinTalonario());
//                values.put("cod_personal", c.getCodPersonal());
//                db.insert("cobranza_talonario", null, values);
//            }
//            db.setTransactionSuccessful();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            db.endTransaction();
//        }
//    }

    @Override
    public void onCreate(SQLiteDatabase arg0) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
    }
}