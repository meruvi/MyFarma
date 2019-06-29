package com.zeus.android.controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.gson.Gson;
import com.zeus.android.util.Util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PedidoController extends SQLiteOpenHelper {

    Context ctx;

    private String TAG = PedidoController.class.getSimpleName();
    private static final String DATA_BASE_NAME = "zeusMobil";
    private static final int DATA_BASE_VERSION = Util.DATA_BASE_VERSION_ZEUS;

    private static final String CREATE_TABLE_PEDIDO = "create table pedido(cod_pedido integer primary key autoincrement,nro_pedido numeric,cod_area_empresa numeric,fecha_pedido text,cod_cliente numeric,"
            + " cod_personal numeric,porcentaje_descuento_fidelidad real,porcentaje_descuento_contado real,monto_cheque real) ";

    public PedidoController(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);

        this.ctx = context;

    }
    SimpleDateFormat f3=new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(CREATE_TABLE_PEDIDO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

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

    public boolean verificarColumnTabla(String nombreColumnaVerificar, String nombreTabla){
        String selectQuery = "PRAGMA table_info("+nombreTabla+")";
        Log.i("INFO", selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {

                String nombreColumna =c.getString(c.getColumnIndex("name")  ) ;

                if( nombreColumnaVerificar.equals( nombreColumna  ) ){
                    Log.i(TAG,"VERIFICANDO TABLA: "+nombreTabla+" COLUMNA :"+nombreColumnaVerificar + "............EXISTE");
                    return true;
                }
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        Log.i(TAG, "VERIFICANDO TABLA: "+nombreTabla+" COLUMNA :"+nombreColumnaVerificar + "..............NO EXISTE");
        return false;
    }
//
//
//
//
//    public void eliminarPedido(Pedido pedido) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues data = new ContentValues();
//
//        //prefiero que estes con tu esposo que es un verdadero hombre que te respeta no la basura como yo
//
//        if(pedido.getCodPedido()>0){
//            db.delete("pedidos_detalle", "cod_pedido in("+pedido.getCodPedido()+")",null);
//            db.delete("pedidos_detallepremios", "cod_pedido in("+pedido.getCodPedido()+")",null);
//            db.delete("pedidos", "cod_pedido in("+pedido.getCodPedido()+")",null);
//            db.delete("pedido_grupo_fidelidad", "COD_PEDIDO in("+pedido.getCodPedido()+")",null);
//
//        }
//        //		return db.insert("pedidos", null, data);
//
//    }
//
//
//    public long registrarPedido(Pedido pedido) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues data = new ContentValues();
//
//
//        String codigo[]={String.valueOf(pedido.getCodPedido())};
//        Log.i("codigo delete:", codigo[0]+"");
//        if(pedido.getCodPedido()>0){
//            db.delete("pedidos", "cod_pedido in("+codigo[0]+")",null);
//        }
//
//
//        if(pedido.getCodPedido()>0){
//            data.put("cod_pedido",pedido.getCodPedido() );
//        }
//
//        data.put("cod_estadopedido", 5);
//        data.put("nro_pedido", pedido.getNroPedido());
//        data.put("nro_pedidofisico", pedido.getNroPedido());
//        data.put("cod_cliente", pedido.getCliente().getCodCliente());
//        data.put("cod_personal", pedido.getPersonal().getCodPersonal());
//        data.put("cod_tipoventa", pedido.getCodTipoVenta());
//        data.put("fecha_pedido", pedido.getFechaPedidoString());
//        data.put("cod_area_empresa", pedido.getCodAreaEmpresa());
//        //data.put("obs_pedido", pedido.getObsPedido()+" Fecha Entrega:"+pedido.getFechaEntregaPedido()+" Razon Social:"+pedido.getRazonSocial()+" NIT :"+pedido.getNitPedido());
//        data.put("obs_pedido", pedido.getObsPedido());
//
//        data.put("cod_tipo_documento", pedido.getCodTipoDocumento());
//        data.put("descuento_fidelidad",pedido.getCliente().getDescuentoFidelidad());
//        data.put("descuento_contado", pedido.getCliente().getDescuentoContado());
//        data.put("descuento_fidelidad2", pedido.getDescuentoFidelidad2());
//        data.put("monto_desc_cheque", pedido.getMontoCheque());
//        data.put("cod_estadopedido_android", 1);
//        data.put("razon_social", pedido.getRazonSocial());
//        data.put("nit_pedido", pedido.getNitPedido());
//        data.put("fecha_entrega_cliente", pedido.getFechaEntregaPedido());
//        data.put("fecha_entregapedido", pedido.getFechaEntregaPedido());
//
//
//        data.put("porcentajedesc_preferencial", pedido.getCliente().getDescuentoPreferencial());
//        data.put("cod_turno_pedido", pedido.getCodTurnoPedido());
//
//
//        return db.insert("pedidos", null, data);
//
//    }
//    public void updatePedido(Pedido pedido) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues data = new ContentValues();
//
//
//		/*String codigo[]={String.valueOf(pedido.getCodPedido())};
//		Log.i("codigo delete:", codigo[0]+"");
//		if(pedido.getCodPedido()>0){
//			db.delete("pedidos", "cod_pedido in("+codigo[0]+")",null);
//		}
//
//
//		if(pedido.getCodPedido()>0){
//			data.put("cod_pedido",pedido.getCodPedido() );
//		}
//		*/
//        data.put("cod_estadopedido_android", 3);
//        data.put("cod_estadopedido", pedido.getCodEstadoPedido());
//        //data.put("fecha_entrega_cliente", f3.format( pedido.getFechaEntregaCliente() )  );
//
//        String sql2=" cod_pedido in("+pedido.getCodPedidoAndroid()+") and cod_cliente="+pedido.getCodCliente()+" and cod_personal="+pedido.getCodPersonal();
//
//        db.update("pedidos", data, sql2,null);
//
//
//    }
//
//
//
//    public ArrayList<Pedido> listadoPedido(int codEstadoPedido, int codPedido, java.util.Date fechaInicio, java.util.Date fechaFinal, int codCliente) {
//        SimpleDateFormat  f2=new SimpleDateFormat("yyyy-MM-dd");
//
//        SimpleDateFormat  f3=new SimpleDateFormat("dd/MM/yyyy");
//
//
//        ArrayList<Pedido> listado = new ArrayList<Pedido>();
//
//        String selectQuery = "SELECT (SELECT  cli_c.descuento_catalogo  FROM clientes cli_c   where cli_c.cod_cliente=p.cod_cliente) as descuento_catalogo,(select c.cod_cadenacliente from clientes c where c.cod_cliente=p.cod_cliente) as cod_cadenacliente,(select c.cod_tipo_cliente from clientes c where c.cod_cliente=p.cod_cliente) as cod_tipo_cliente,obs_pedido,fecha_pedido,(select c.nombre_cliente from clientes c where c.cod_cliente=p.cod_cliente) as nombre_cliente,cod_pedido,cod_estadopedido,nro_pedido,nro_pedidofisico,cod_cliente,cod_personal,cod_tipoventa,fecha_pedido,cod_area_empresa,cod_tipo_documento,descuento_fidelidad,descuento_contado,cod_estadopedido,razon_social,nit_pedido,fecha_entregapedido,(select e.nombre_estadopedido from estados_pedido e where e.cod_estadopedido=p.cod_estadopedido) as nombre_estadopedido,(select c.cod_modalidad from clientes c where p.cod_cliente=c.cod_cliente) cod_modalidad,(select c.nombre_modalidad from clientes c where p.cod_cliente=c.cod_cliente) nombre_modalidad ,(select c.descuento_fidelidad from clientes c where c.cod_cliente=p.cod_cliente) as descuento_fidelidad1,(select c.descuento_contado from clientes c where c.cod_cliente=p.cod_cliente) as descuento_contado1,(select c.zona_viaje from clientes c where c.cod_cliente=p.cod_cliente) as zona_viaje,porcentajedesc_preferencial,fecha_entrega_cliente,cod_turno_pedido,descuento_fidelidad2 from pedidos p ";
//        selectQuery+=" where cod_estadopedido_android="+codEstadoPedido+" and fecha_pedido BETWEEN '"+f2.format(fechaInicio)+" 00:00:00' and '"+f2.format(fechaFinal)+" 23:59:59'";
//        if(codPedido>0){
//            selectQuery+=" and cod_pedido="+codPedido;
//        }
//        if(codCliente!=0 ){
//            selectQuery+=" and cod_cliente="+codCliente;
//        }
//        selectQuery+=" order by cod_pedido desc";
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//
//        if (c.moveToFirst()) {
//            do {
//
//                Pedido td = new Pedido();
//
//                td.getCliente().setTieneConvenio(false);
//
//
//                td.getCliente().setDescuentoCatalogo( c.getDouble(c.getColumnIndex("descuento_catalogo")) );
//
//                td.setCodPedido(c.getInt(c.getColumnIndex("cod_pedido")));
//                td.setNroPedido(c.getInt(c.getColumnIndex("cod_pedido")));
//                td.setNroPedido(c.getInt(c.getColumnIndex("cod_pedido")));
//                td.setCodTurnoPedido(c.getInt(c.getColumnIndex("cod_turno_pedido")));
//
//
//
//                td.getCliente().setCodCliente(c.getInt(c.getColumnIndex("cod_cliente")));
//                //cod_tipo_cliente
//                td.getCliente().setCodTipoCliente( c.getInt(c.getColumnIndex("cod_tipo_cliente"))  );
//                //cod_cadenacliente
//                td.getCliente().setCodCadenaCliente( c.getInt(c.getColumnIndex("cod_cadenacliente"))  );
//
//                td.setCodCliente(c.getInt(c.getColumnIndex("cod_cliente")));
//                td.getCliente().setNombreCliente(c.getString(c.getColumnIndex("nombre_cliente")));
//
//                td.getCliente().setDescuentoContadoCopia(c.getInt(c.getColumnIndex("descuento_contado1")));
//                td.getCliente().setDescuentoFidelidadCopia(c.getInt(c.getColumnIndex("descuento_fidelidad1")));
//
//
//                td.getCliente().setNombreCliente(c.getString(c.getColumnIndex("nombre_cliente")));
//
//
//                td.getCliente().setNombreModalidadVenta(c.getString(c.getColumnIndex("nombre_modalidad")));
//
//                td.getCliente().setCodModalidadVenta(c.getInt(c.getColumnIndex("cod_modalidad")));
//
//
//                td.getPersonal().setCodPersonal(c.getInt(c.getColumnIndex("cod_personal")));
//                td.setCodPersonal(c.getInt(c.getColumnIndex("cod_personal")));
//                td.setCodTipoVenta(c.getInt(c.getColumnIndex("cod_tipoventa")));
//                td.setCodAreaEmpresa(c.getInt(c.getColumnIndex("cod_area_empresa")));
//
//                td.getCliente().setZonaViaje( c.getString(c.getColumnIndex("zona_viaje"))  );
//                td.getCliente().setCodTipoVenta( c.getInt(c.getColumnIndex("cod_tipoventa"))  );
//
//                td.setCodTipoDocumento(c.getInt(c.getColumnIndex("cod_tipo_documento")));
//
//                td.setPorcentajeDescuentoFidelidad(c.getInt(c.getColumnIndex("descuento_fidelidad")));
//                td.setPorcentajeDescuentoContado(c.getInt(c.getColumnIndex("descuento_contado")));
//
//                td.setDescuentoFidelidad2(c.getDouble(c.getColumnIndex("descuento_fidelidad2")));
//
//                td.setPorcentajedescPreferencial(c.getDouble(c.getColumnIndex("porcentajedesc_preferencial")));
//
//                td.getCliente().setDescuentoPreferencial(c.getDouble(c.getColumnIndex("porcentajedesc_preferencial")));
//
//
//
//                td.getCliente().setDescuentoFidelidad( td.getPorcentajeDescuentoFidelidad()  );
//                td.getCliente().setDescuentoContado( td.getPorcentajeDescuentoContado()  );
//
//
//                td.setRazonSocial(  c.getString(c.getColumnIndex("razon_social")) );
//
//
//                td.setNitPedido(  c.getString(c.getColumnIndex("nit_pedido")) );
//                td.setFechaEntregaPedido(  c.getString(c.getColumnIndex("fecha_entregapedido")) );
//
//                td.setFechaPedidoString(  c.getString(c.getColumnIndex("fecha_pedido")) );
//
//
//                td.setNombreEstadoPedido(c.getString(c.getColumnIndex("nombre_estadopedido")));
//
//                if( td.getNombreEstadoPedido()==null ){
//                    td.setNombreEstadoPedido(EstadoPedido.ENTREGADO_AL_CLIENTE.nombre);
//                }
//
//
//
//                td.setObsPedido(  c.getString(c.getColumnIndex("obs_pedido")));
//
//
//
//                SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                Log.i("eeeeeeeeeeeeeeeeeeee:",c.getString(c.getColumnIndex("fecha_entregapedido"))+"");
//                try {
//
//
//                    if(td.getFechaPedidoString()!=null){
//                        td.setFechaPedido( f.parse(td.getFechaPedidoString())  );
//
//                    }
//                    if(   c.getString(c.getColumnIndex("fecha_entregapedido"))!=null  )  {
//
//                        td.setFechaEntregaCliente(f3.parse(  c.getString(c.getColumnIndex("fecha_entregapedido")) ));
//                    }
//
//                } catch (ParseException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//
//                listado.add(td);
//            } while (c.moveToNext());
//            c.close();
//            db.close();
//        }
//
//        return listado;
//
//    }
//
//
//    public void actualizarDescuentoFidelidad2(Collection<PedidoGrupoFidelidad> listadoPedidoGrupoFidelidad){
//        ContentValues values = new ContentValues();
//        Double calculado;
//        for(PedidoGrupoFidelidad bean:listadoPedidoGrupoFidelidad) {
////			String selectQuery = "select count(*) as total from pedidos_detalle where id_grupo_politica_precio=" + bean.getIdGrupoPoliticaPrecio() + " and cod_pedido =" + bean.getCodPedido();
//            String selectQuery = "select count(*) as total from pedidos_detalle pd inner join pedido_grupo_fidelidad pgf on pd.cod_pedido = pgf.cod_pedido where pd.id_grupo_politica_precio=" + bean.getIdGrupoPoliticaPrecio() + " and pd.cod_pedido= " + bean.getCodPedido() + " and pgf.es_aplicado_descuento=1";
//            Log.i("INFO JACOBO", selectQuery);
//            SQLiteDatabase db = this.getReadableDatabase();
//            Cursor c = db.rawQuery(selectQuery, null);
//            if (c.moveToFirst()) {
//                SQLiteDatabase dbp = this.getWritableDatabase();
//                ContentValues data = new ContentValues();
//                calculado = bean.getMontoDescuento()/c.getDouble(c.getColumnIndex("total"));
//                data.put("descuento_fidelidad2", bean.getMontoDescuento()/c.getDouble(c.getColumnIndex("total")));
//                String sql2=" id_grupo_politica_precio="+ bean.getIdGrupoPoliticaPrecio();
//                dbp.update("pedidos_detalle", data, sql2,null);
//            }
//        }
//    }
//
//    public ArrayList<Pedido> listadoPedido(int codEstadoPedido,int codPedido) {
//        ArrayList<Pedido> listado = new ArrayList<Pedido>();
//
//        String selectQuery = "SELECT  obs_pedido,fecha_pedido,(select c.nombre_cliente from clientes c where c.cod_cliente=p.cod_cliente) as nombre_cliente,cod_pedido,cod_estadopedido,nro_pedido,nro_pedidofisico,cod_cliente,cod_personal,cod_tipoventa,fecha_pedido,cod_area_empresa,cod_tipo_documento,descuento_fidelidad,descuento_contado,cod_estadopedido,razon_social,nit_pedido,fecha_entregapedido,(select e.nombre_estadopedido from estados_pedido e where e.cod_estadopedido=p.cod_estadopedido) as nombre_estadopedido,(select c.cod_modalidad from clientes c where p.cod_cliente=c.cod_cliente) cod_modalidad,(select c.nombre_modalidad from clientes c where p.cod_cliente=c.cod_cliente) nombre_modalidad ,(select c.descuento_fidelidad from clientes c where c.cod_cliente=p.cod_cliente) as descuento_fidelidad1,(select c.descuento_contado from clientes c where c.cod_cliente=p.cod_cliente) as descuento_contado1,(select c.zona_viaje from clientes c where c.cod_cliente=p.cod_cliente) as zona_viaje,porcentajedesc_preferencial,cod_turno_pedido,descuento_fidelidad2 from pedidos p ";
//        selectQuery+=" where cod_estadopedido_android="+codEstadoPedido;
//        if(codPedido>0){
//            selectQuery+=" and cod_pedido="+codPedido;
//        }
//        selectQuery+=" order by cod_pedido desc";
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//
//        if (c.moveToFirst()) {
//            do {
//
//                Pedido td = new Pedido();
//
//
//                //cod_turno_pedido
//                td.setCodTurnoPedido(c.getInt(c.getColumnIndex("cod_turno_pedido")));
//                td.setCodPedido(c.getInt(c.getColumnIndex("cod_pedido")));
//                td.setNroPedido(c.getInt(c.getColumnIndex("cod_pedido")));
//                td.setNroPedido(c.getInt(c.getColumnIndex("cod_pedido")));
//
//                td.getCliente().setCodCliente(c.getInt(c.getColumnIndex("cod_cliente")));
//
//                td.setCodCliente(c.getInt(c.getColumnIndex("cod_cliente")));
//                td.getCliente().setNombreCliente(c.getString(c.getColumnIndex("nombre_cliente")));
//
//                td.getCliente().setDescuentoContadoCopia(c.getInt(c.getColumnIndex("descuento_contado1")));
//                td.getCliente().setDescuentoFidelidadCopia(c.getInt(c.getColumnIndex("descuento_fidelidad1")));
//
//
//                td.getCliente().setNombreCliente(c.getString(c.getColumnIndex("nombre_cliente")));
//
//
//                td.getCliente().setNombreModalidadVenta(c.getString(c.getColumnIndex("nombre_modalidad")));
//
//                td.getCliente().setCodModalidadVenta(c.getInt(c.getColumnIndex("cod_modalidad")));
//
//
//                td.getPersonal().setCodPersonal(c.getInt(c.getColumnIndex("cod_personal")));
//                td.setCodPersonal(c.getInt(c.getColumnIndex("cod_personal")));
//                td.setCodTipoVenta(c.getInt(c.getColumnIndex("cod_tipoventa")));
//                td.setCodAreaEmpresa(c.getInt(c.getColumnIndex("cod_area_empresa")));
//
//                td.getCliente().setZonaViaje( c.getString(c.getColumnIndex("zona_viaje"))  );
//
//                td.setCodTipoDocumento(c.getInt(c.getColumnIndex("cod_tipo_documento")));
//
//                td.setPorcentajeDescuentoFidelidad(c.getInt(c.getColumnIndex("descuento_fidelidad")));
//                td.setPorcentajeDescuentoContado(c.getInt(c.getColumnIndex("descuento_contado")));
//
//                td.setDescuentoFidelidad2(c.getDouble(c.getColumnIndex("descuento_fidelidad2")));
//
//                td.setPorcentajedescPreferencial(c.getDouble(c.getColumnIndex("porcentajedesc_preferencial")));
//
//                td.getCliente().setDescuentoPreferencial(c.getDouble(c.getColumnIndex("porcentajedesc_preferencial")));
//
//
//
//                td.getCliente().setDescuentoFidelidad( td.getPorcentajeDescuentoFidelidad()  );
//                td.getCliente().setDescuentoContado( td.getPorcentajeDescuentoContado()  );
//
//
//                td.setRazonSocial(  c.getString(c.getColumnIndex("razon_social")) );
//
//
//                td.setNitPedido(  c.getString(c.getColumnIndex("nit_pedido")) );
//                td.setFechaEntregaPedido(  c.getString(c.getColumnIndex("fecha_entregapedido")) );
//                td.setFechaPedidoString(  c.getString(c.getColumnIndex("fecha_pedido")) );
//
//
//
//                td.setNombreEstadoPedido(c.getString(c.getColumnIndex("nombre_estadopedido")));
//
//                if( td.getNombreEstadoPedido()==null ){
//                    td.setNombreEstadoPedido(EstadoPedido.ENTREGADO_AL_CLIENTE.nombre);
//                }
//
//
//
//                td.setObsPedido(c.getString(c.getColumnIndex("obs_pedido")));
//
//
//
//                SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                Log.i("QQQQQQQQQQQQQQ:0:- ",td.getFechaPedidoString());
//
//
//
//                try {
//                    td.setFechaPedido( f.parse(td.getFechaPedidoString())  );
//
//                    Log.i("22222222222222:0   ",td.getFechaPedido().toString() );
//
//                } catch (ParseException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//
//                listado.add(td);
//            } while (c.moveToNext());
//            c.close();
//            db.close();
//        }
//
//        return listado;
//
//    }
//
//
//    public int actualizarPresentacionesProducto2(Collection<PresentacionesProductos> listado,long codPedido,long codArea,String editar,Collection<PresentacionesProductos> listadoBonificacion){
//
//        SQLiteDatabase db=this.getWritableDatabase();
//        String codigo[]={String.valueOf(codPedido)};
//
//        if(editar.equals("editar")){
//            db.delete("pedidos_detalle", "cod_pedido in("+codigo[0]+")",null);
//        }
//
//
//        ComGrupoFidelidadController ppc = new ComGrupoFidelidadController(ctx);
//
//
//        int cantidadInsertada=0;
//
//        for(PresentacionesProductos bean:listado){
//            ContentValues values = new ContentValues();
//            values.put("cod_pedido", codPedido);
//            values.put("cod_presentacion", bean.getCodPresentacion());
//            values.put("cantidad_pedido", bean.getCantidad());
//            values.put("cantidad_unitariapedido", bean.getCantidadUnitariaCompra());
//            values.put("cod_estado_detallepedido", 0);
//            values.put("cantidad_bonificacion", 0);
//            values.put("cantidad_unitariabonificacion", bean.getGetCantidadUnitariaBonificacionCompra());
//            values.put("orden", bean.getCodPedidoDetalle());
//            values.put("precio_venta", bean.getPrecioCorriente());
//            values.put("descuento", bean.getPorcentajeDescuentoFinal());
//            values.put("cod_oferta", bean.getCodOferta());
//            values.put("precio_lista", bean.getPrecioLista());
//            values.put("cod_area_empresa", codArea);
//            values.put("descuento_jr", bean.getPorcentajeDescuentoJR());
//            values.put("descuento_oferta", bean.getPorcentajeDescuentoOferta());
//            values.put("descuento_estandar", bean.getPorcentajeDescuentoEstandar());
//
//            if(editar.equals("editar")){
//                values.put("id_grupo_politica_precio", bean.getIdGrupoPoliticaPrecio());
//            }else {
//                ComGrupoFidelidad cgf = ppc.obtenerCodGrupoFide(bean.getCodLineaMKT());
//                values.put("id_grupo_politica_precio", cgf.getIdGrupoPoliticaPrecio());
//            }
//
//            db.insert("pedidos_detalle", null, values);
//            cantidadInsertada++;
//        }
//
//
//
//        cantidadInsertada=0;
//        for(PresentacionesProductos bean:listadoBonificacion){
//            ContentValues values = new ContentValues();
//            //values.put("cod_pedido", codPedido);
//            //values.put("cod_presentacion", bean.getCodPresentacion());
//            //values.put("cantidad_pedido", bean.getCantidad());
//            //values.put("cantidad_unitariapedido", bean.getCantidadUnitaria());
//            //values.put("cod_estado_detallepedido", 0);
//            //values.put("cantidad_bonificacion", 0);
//            //values.put("cantidad_unitariabonificacion", bean.getCantidadUnitaria());
//            //values.put("orden", bean.getCodPedidoDetalle());
//            //values.put("precio_venta", bean.getPrecioCorriente());
//            //values.put("descuento", bean.getPorcentajeDescuentoFinal());
//            values.put("cod_oferta", bean.getCodOferta());
//            //values.put("precio_lista", bean.getPrecioLista());
//            //values.put("cod_area_empresa", codArea);
//            //values.put("descuento_jr", bean.getPorcentajeDescuentoJR());
//            //values.put("descuento_oferta", bean.getPorcentajeDescuentoOferta());
//            //values.put("descuento_estandar", bean.getPorcentajeDescuentoEstandar());
//
//            //db.insert("pedidos_detalle", null, values);
//            cantidadInsertada=db.update("pedidos_detalle", values, "cod_pedido in("+codPedido+") and cod_presentacion in("+bean.getCodPresentacion()+")",null);
//            //db.u
//            if( cantidadInsertada==0 ){
//
//                values = new ContentValues();
//                values.put("cod_pedido", codPedido);
//                values.put("cod_presentacion", bean.getCodPresentacion());
//                values.put("cantidad_pedido", 0);
//                values.put("cantidad_unitariapedido", 0);
//                values.put("cod_estado_detallepedido", 0);
//                values.put("cantidad_bonificacion", 0);
//                values.put("cantidad_unitariabonificacion", bean.getCantidadUnitaria());
//                values.put("orden", bean.getCodPedidoDetalle());
//                values.put("precio_venta", bean.getPrecioCorriente());
//                values.put("descuento", bean.getPorcentajeDescuentoFinal());
//                values.put("cod_oferta", bean.getCodOferta());
//                values.put("precio_lista", bean.getPrecioLista());
//                values.put("cod_area_empresa", codArea);
//                values.put("descuento_jr", bean.getPorcentajeDescuentoJR());
//                values.put("descuento_oferta", bean.getPorcentajeDescuentoOferta());
//                values.put("descuento_estandar", bean.getPorcentajeDescuentoEstandar());
//
//                db.insert("pedidos_detalle", null, values);
//
//
//
//
//            }
//
//
//
//
//        }
//
//        db.close();
//
//
//
//        return cantidadInsertada;
//    }
//
//    public double calculoDesctoFidelidad2(Collection<PresentacionesProductos> listado, int IdGrupoPoliticaPrecio){
//        double desctoFide2 = 0;
//
//        for(PresentacionesProductos bean:listado) {
//
//
//        }
//        return desctoFide2;
//
//    }
//
//    public int actualizarPremios(Collection<CampaniaOfertaPremio2> listado,long codPedido,long codArea,String editar){
//
//
//        SQLiteDatabase db=this.getWritableDatabase();
//        String codigo[]={String.valueOf(codPedido)};
//
//        if(editar.equals("editar")){
//            db.delete("pedidos_detallepremios", "cod_pedido in("+codigo[0]+")",null);
//        }
//
//
//
//
//        int cantidadInsertada=0;
//        for(CampaniaOfertaPremio2 bean:listado){
//
//            if( bean.isChecked() ){
//                ContentValues values = new ContentValues();
//
//                values.put("cod_pedido", codPedido);
//                values.put("cod_matpromocional", bean.getCodMatPromocional());
//                values.put("cantidad", bean.getCantidadPedidoPremio());
//                values.put("cod_area_empresa", 0);
//                values.put("cod_oferta", bean.getCodOferta());
//
//
//                db.insert("pedidos_detallepremios", null, values);
//                cantidadInsertada++;
//
//
//
//            }
//
//
//        }
//        db.close();
//
//
//
//        return cantidadInsertada;
//    }
//
//    //	pController.actualizarPedidoGrupoFidelidad(listadoPremiosGanados, codPedido, pedido.getCodAreaEmpresa(), op);
//    public int actualizarPedidoGrupoFidelidad(Collection<PedidoGrupoFidelidad> listado, long codPedido, long codArea, String editar){
//
//
//        SQLiteDatabase db=this.getWritableDatabase();
//        String codigo[]={String.valueOf(codPedido)};
//
//        if(editar.equals("editar")){
//            db.delete("pedido_grupo_fidelidad", "cod_pedido in("+codigo[0]+")",null);
//        }
//
//        int cantidadInsertada=0;
//        for(PedidoGrupoFidelidad bean:listado){
////            if( bean.isChecked() ){
//            ContentValues values = new ContentValues();
//            values.put("cod_pedido", codPedido);
//            values.put("id_grupo_politica_precio", bean.getIdGrupoPoliticaPrecio());
//            values.put("id_politica_precio_corte", bean.getIdPoliticaPrecioCorte());
//            values.put("cod_mes", bean.getCodMmes());
//            values.put("cod_gestion", bean.getCodGestion());
//            values.put("monto_acumulado", bean.getMontoAcumulado());
//            values.put("descuento_fidelidad", bean.getDescuentoFidelidad());
//            values.put("monto_descuento", bean.getMontoDescuento());
//            values.put("monto_acceso", bean.getMontoAcceso());
//            values.put("es_aplicado_descuento", bean.getEsAplicadoDescuento());
//            db.insert("pedido_grupo_fidelidad", null, values);
//            cantidadInsertada++;
//        }
////        }
//        db.close();
//        return cantidadInsertada;
//    }
//
//
//    public List<PresentacionesProductos> getListPedidoDetalle(String str) {
//
//        List<PresentacionesProductos> listado=new ArrayList<PresentacionesProductos>();
//        String selectQuery = "select id_grupo_politica_precio,cod_pedido,cod_presentacion,cantidad_pedido,precio_venta,descuento,precio_lista,cod_oferta,descuento_oferta,descuento_jr,descuento_estandar,orden,(select nombre_producto_presentacion from  presentaciones_producto pre where pre.cod_presentacion=pd.cod_presentacion) nombre_producto_presentacion,cantidad_unitariabonificacion,(select cantidad_presentacion from  presentaciones_producto pre where pre.cod_presentacion=pd.cod_presentacion) cantidad_presentacion  ,(select c.cod_tipooferta from campanias_ofertas c where c.cod_oferta=pd.cod_oferta ) as cod_tipo_oferta  from pedidos_detalle pd where cod_pedido in("+str +") and cantidad_pedido>0";
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//                PresentacionesProductos bean = new PresentacionesProductos();
//
//                bean.setNombreProductoPresentacion( c.getString(   c.getColumnIndex("nombre_producto_presentacion")  )  );
//                //cantidad_unitariabonificacion
//                bean.setCantidadUnitaria( c.getInt(   c.getColumnIndex("cantidad_unitariabonificacion")  )  );
//                bean.setCodPresentacion( c.getInt(   c.getColumnIndex("cod_presentacion")  )  );
//                bean.setCantidad( c.getInt(   c.getColumnIndex("cantidad_pedido")  )  );
//                bean.setPrecioCorriente( c.getDouble(   c.getColumnIndex("precio_venta")  )  );
//                bean.setPrecioFinal( c.getDouble(   c.getColumnIndex("precio_venta")  )  );
//
//                bean.setPorcentajeDescuentoFinal( c.getDouble(   c.getColumnIndex("descuento")  )  );
//                bean.setPrecioLista( c.getDouble(   c.getColumnIndex("precio_lista")  )  );
//
//                bean.setCodPedido( c.getInt(   c.getColumnIndex("cod_pedido")  )  );
//                bean.setCodOferta( c.getInt(   c.getColumnIndex("cod_oferta")  )  );
//
//                bean.setPorcentajeDescuentoOferta( c.getDouble(   c.getColumnIndex("descuento_oferta")  )  );
//                bean.setPorcentajeDescuentoJR( c.getDouble(   c.getColumnIndex("descuento_jr")  )  );
//                bean.setPorcentajeDescuentoEstandar( c.getDouble(   c.getColumnIndex("descuento_estandar")  )  );
//                bean.setCodPedidoDetalle( c.getInt(   c.getColumnIndex("orden")  )  );
//                bean.setImporteProducto(   bean.getPrecioFinal()*bean.getCantidad() );
//                bean.setCantidadPresentacion( c.getInt(   c.getColumnIndex("cantidad_presentacion")  )  );
//                bean.setCodTipoOferta(c.getInt(   c.getColumnIndex("cod_tipo_oferta")  ) );
//                bean.setIdGrupoPoliticaPrecio(c.getInt(   c.getColumnIndex("id_grupo_politica_precio")  ) );
//
//                //td.setCodCliente( c.getInt( c.getColumnIndex("cod_cliente")));
//                //td.setNombreCliente(  c.getString( c.getColumnIndex("nombre_cliente")));
//                listado.add(bean);
//
//
//            } while (c.moveToNext());
//        }
//        Log.i("CANTIDAD PRODUCTOS", String.valueOf( listado.size()));
//        return listado;
//    }
//
//
//
//    public List<PresentacionesProductos> getListPedidoDetalleActualizacion(String str) {
//
//        List<PresentacionesProductos> listado=new ArrayList<PresentacionesProductos>();
//        //String selectQuery = "select cantidad_unitariapedido,cod_pedido,cod_presentacion,cantidad_pedido,precio_venta,descuento,precio_lista,cod_oferta,descuento_oferta,descuento_jr,descuento_estandar,orden,(select nombre_producto_presentacion from  presentaciones_producto pre where pre.cod_presentacion=pd.cod_presentacion) nombre_producto_presentacion,cantidad_unitariabonificacion,(select cantidad_presentacion from  presentaciones_producto pre where pre.cod_presentacion=pd.cod_presentacion) cantidad_presentacion  ,(select c.cod_tipooferta from campanias_ofertas c where c.cod_oferta=pd.cod_oferta ) as cod_tipo_oferta, descuento_fidelidad2  from pedidos_detalle pd where cod_pedido in("+str +") ";
//        String selectQuery = "select cantidad_unitariapedido,cod_pedido,cod_presentacion,cantidad_pedido,precio_venta,descuento,precio_lista,cod_oferta,descuento_oferta,descuento_jr,descuento_estandar,orden,(select nombre_producto_presentacion from  presentaciones_producto pre where pre.cod_presentacion=pd.cod_presentacion) nombre_producto_presentacion,cantidad_unitariabonificacion,(select cantidad_presentacion from  presentaciones_producto pre where pre.cod_presentacion=pd.cod_presentacion) cantidad_presentacion  ,(select c.cod_tipooferta from campanias_ofertas c where c.cod_oferta=pd.cod_oferta ) as cod_tipo_oferta  from pedidos_detalle pd where cod_pedido in("+str +") ";
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//                PresentacionesProductos bean = new PresentacionesProductos();
//
//                bean.setNombreProductoPresentacion( c.getString(   c.getColumnIndex("nombre_producto_presentacion")  )  );
//                //cantidad_unitariabonificacion
//                bean.setCantidadUnitaria( c.getInt(   c.getColumnIndex("cantidad_unitariabonificacion")  )  );
//                bean.setCodPresentacion( c.getInt(   c.getColumnIndex("cod_presentacion")  )  );
//                bean.setCantidad( c.getInt(   c.getColumnIndex("cantidad_pedido")  )  );
//                bean.setPrecioCorriente( c.getDouble(   c.getColumnIndex("precio_venta")  )  );
//                bean.setPrecioFinal( c.getDouble(   c.getColumnIndex("precio_venta")  )  );
//
//                bean.setPorcentajeDescuentoFinal( c.getDouble(   c.getColumnIndex("descuento")  )  );
//                bean.setPrecioLista( c.getDouble(   c.getColumnIndex("precio_lista")  )  );
//
//                bean.setCodPedido( c.getInt(   c.getColumnIndex("cod_pedido")  )  );
//                bean.setCodOferta( c.getInt(   c.getColumnIndex("cod_oferta")  )  );
//
//                bean.setPorcentajeDescuentoOferta( c.getDouble(   c.getColumnIndex("descuento_oferta")  )  );
//                bean.setPorcentajeDescuentoJR( c.getDouble(   c.getColumnIndex("descuento_jr")  )  );
//                bean.setPorcentajeDescuentoEstandar( c.getDouble(   c.getColumnIndex("descuento_estandar")  )  );
//                bean.setCodPedidoDetalle( c.getInt(   c.getColumnIndex("orden")  )  );
//                bean.setImporteProducto(   bean.getPrecioFinal()*bean.getCantidad() );
//                bean.setCantidadPresentacion( c.getInt(   c.getColumnIndex("cantidad_presentacion")  )  );
//                bean.setCodTipoOferta(c.getInt(   c.getColumnIndex("cod_tipo_oferta")  ) );
//                //cantidad_unitariapedido
//                bean.setCantidadUnitariaCompra(c.getInt(   c.getColumnIndex("cantidad_unitariapedido")  )   );
//                //bean.setDescuentoFidelidad2( c.getDouble(   c.getColumnIndex("descuento_fidelidad2")  )  );
//
//                //td.setCodCliente( c.getInt( c.getColumnIndex("cod_cliente")));
//                //td.setNombreCliente(  c.getString( c.getColumnIndex("nombre_cliente")));
//                listado.add(bean);
//
//
//            } while (c.moveToNext());
//        }
//        Log.i("CANTIDAD PRODUCTOS", String.valueOf( listado.size()));
//        Log.i("PRODUCTOS JACOBO", String.valueOf(listado));
//        return listado;
//    }
//
//
//    public List<PedidoDetallePremio> getListPedidoPremios(String str) {
//
//        List<PedidoDetallePremio> listado=new ArrayList<PedidoDetallePremio>();
//        String selectQuery = "select p.cod_pedido,p.cod_matpromocional,p.cantidad,p.cod_oferta from pedidos_detallepremios p where cod_pedido in("+str +") ";
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//
//                PedidoDetallePremio  bean=new PedidoDetallePremio();
//                bean.setCodPedido( c.getInt(   c.getColumnIndex("cod_pedido")  )  );
//                bean.setCodMatPromocional( c.getInt(   c.getColumnIndex("cod_matpromocional")  )  );
//                bean.setCantidad( c.getInt(   c.getColumnIndex("cantidad")  )  );
//                bean.setCodOferta( c.getInt(   c.getColumnIndex("cod_oferta")  )  );
//                listado.add(bean);
//
//
//            } while (c.moveToNext());
//        }
//        Log.i("CANTIDAD PRODUCTOS", String.valueOf( listado.size()));
//        return listado;
//    }
//
//    public ArrayList<BeanObject> getListadoClientesPedido(String str) {
//
//        ArrayList<BeanObject> listado=new ArrayList<BeanObject>();
//        String selectQuery = "select  distinct c.cod_cliente,c.nombre_cliente  from pedidos p,clientes c where c.cod_cliente=p.cod_cliente and c.nombre_cliente like  '%"+str+"%' order by  nombre_cliente  ";
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//  	        	/*
//  	        	PedidoDetallePremio  bean=new PedidoDetallePremio();
//  	        	bean.setCodPedido( c.getInt(   c.getColumnIndex("cod_pedido")  )  );
//  	        	bean.setCodMatPromocional( c.getInt(   c.getColumnIndex("cod_matpromocional")  )  );
//  	        	bean.setCantidad( c.getInt(   c.getColumnIndex("cantidad")  )  );
//  	        	bean.setCodOferta( c.getInt(   c.getColumnIndex("cod_oferta")  )  );
//  	        	listado.add(bean);
//  	        	Cliente cliente=new Cliente();
//  	        	*/
//                BeanObject cliente=new BeanObject(c.getInt(   c.getColumnIndex("cod_cliente")  ),c.getString(   c.getColumnIndex("nombre_cliente")  ) );
//                //cliente.setCodBeanObject( c.getInt(   c.getColumnIndex("cod_cliente")  )  );
//                //cliente.setNombreBeanObject(  c.getString(   c.getColumnIndex("nombre_cliente")  )  );
//                listado.add(cliente);
//                //cliente.setCodMatPromocional( c.getInt(   c.getColumnIndex("cod_matpromocional")  )  );
//
//
//
//            } while (c.moveToNext());
//        }
//        db.close();
//        //Log.i("CANTIDAD PRODUCTOS", String.valueOf( listado.size()));
//        return listado;
//    }
//    public void closeDB() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        if (db != null && db.isOpen())
//            db.close();
//    }
//
////comentario axadido
//
//    public ArrayList<PedidoGrupoFidelidad> getIdGrupoPolPrecios(Long codPedido) {
//
//        ArrayList<PedidoGrupoFidelidad> listado=new ArrayList<PedidoGrupoFidelidad>();
//        String selectQuery = "select distinct id_grupo_politica_precio from pedidos_detalle where cod_pedido = " + codPedido;
//        Log.i("INFO", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//
//
//
//                PedidoGrupoFidelidad bean = new PedidoGrupoFidelidad();
//
//                bean.setCodPedido(codPedido.intValue());
//                bean.setIdGrupoPoliticaPrecio( c.getInt(   c.getColumnIndex("id_grupo_politica_precio")  )  );
//
//
//                listado.add(bean);
//
//
//            } while (c.moveToNext());
//
//        }
//        Log.i("CANTIDAD PRODUCTOS", String.valueOf( listado.size()));
//        return listado;
//    }
//
//    //	, String ip
//    public List<PedidoGrupoFidelidad> getPedidoGrupoFidelidadList(int codPedido, String ip, int codCliente) {
//
//        List<PedidoGrupoFidelidad> listado = new ArrayList<PedidoGrupoFidelidad>();
//        List<comPoliticaPrecioCorte> listadoEstado = new ArrayList<comPoliticaPrecioCorte>();
//        String selectQuery = "select * from pedido_grupo_fidelidad where cod_pedido=" + codPedido + ";";
//        Log.i("INFO A", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            do {
//                PedidoGrupoFidelidad pedido = new PedidoGrupoFidelidad();
//                comPoliticaPrecioCorte pedidoEstado = new comPoliticaPrecioCorte();
//                pedido.setCodPedido(c.getInt(c.getColumnIndex("COD_PEDIDO")));
//                pedido.setIdGrupoPoliticaPrecio(c.getInt(c.getColumnIndex("ID_GRUPO_POLITICA_PRECIO")));
//                pedido.setIdPoliticaPrecioCorte(c.getInt(c.getColumnIndex("ID_POLITICA_PRECIO_CORTE")));
//                pedido.setCodMmes(c.getInt(c.getColumnIndex("COD_MES")));
//                pedido.setCodGestion(c.getInt(c.getColumnIndex("COD_GESTION")));
//                pedido.setMontoAcumulado(c.getDouble(c.getColumnIndex("MONTO_ACUMULADO")));
//                pedido.setDescuentoFidelidad(c.getDouble(c.getColumnIndex("DESCUENTO_FIDELIDAD")));
//                //pedido.setMontoDescuento(c.getDouble(c.getColumnIndex("MONTO_DESCUENTO")));
//                pedido.setDescuento(c.getDouble(c.getColumnIndex("MONTO_DESCUENTO")));
//                pedido.setMontoAcceso(c.getDouble(c.getColumnIndex("MONTO_ACCESO")));
//                pedido.setEsAplicadoDescuento(c.getInt(c.getColumnIndex("ES_APLICADO_DESCUENTO")));
//                listado.add(pedido);
//
//                if (c.getInt(c.getColumnIndex("ES_APLICADO_DESCUENTO"))== 1) {
//                    pedidoEstado.setEstado(1);
//                    pedidoEstado.setCodCliente(codCliente);
//                    pedidoEstado.setId(c.getInt(c.getColumnIndex("ID_POLITICA_PRECIO_CORTE")));
//                    listadoEstado.add(pedidoEstado);
//                }
//            } while (c.moveToNext());
//        }
//
//        HttpClient client = new DefaultHttpClient();
////		if (c.getInt(c.getColumnIndex("ES_APLICADO_DESCUENTO"))== 1) {
//        String urlEstadoCorte = "http://" + ip + "/zeus/service/serviceCliente/registrarestadopoliticapreciocorte";
//        Log.i("urlEstadoCorte jacobo", urlEstadoCorte);
//
//        HttpPost sendPedidos = new HttpPost(urlEstadoCorte);
//        Gson gson = new Gson();
////			String json = gson.toJson("[{\"Estado\": 2,\"CodCliente\": 140082,\"IdGrupoPoliticaPrecio\":10}]");
//        String json = gson.toJson(listadoEstado);
//
//        try {
//            StringEntity entity = new StringEntity(json);
//            sendPedidos.setEntity(entity);
//
//            sendPedidos.setHeader("Accept", "application/json");
//            sendPedidos.setHeader("Content-type", "application/json");
//
//            HttpResponse responseSendPedido = client.execute(sendPedidos);
//            String a = convertStreamToString(responseSendPedido.getEntity().getContent());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
////		}
//
//        Log.i("CANTIDAD ALVARO", String.valueOf(listado.size()));
//        return listado;
//    }
//
//    private static String convertStreamToString(InputStream is) {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//        StringBuilder sb = new StringBuilder();
//        String line = null;
//        try {
//            while ((line = reader.readLine()) != null) {
//                sb.append(line + "\n");
//            }
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                is.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return sb.toString();
//    }
//
//    public boolean esAplicadoDescuento(int codCli, int idGrupo){
//
//        //String selectQuery = "select p.cod_cliente, p.cod_pedido, pg.id_grupo_politica_precio, pg.es_aplicado_descuento from pedidos p inner join pedidos_detalle pd on p.cod_pedido = pd.cod_pedido inner join pedido_grupo_fidelidad pg on pd.ID_GRUPO_POLITICA_PRECIO = pg.ID_GRUPO_POLITICA_PRECIO where cod_cliente = " + codCli + " and pg.ES_APLICADO_DESCUENTO = 1 and pg.ID_GRUPO_POLITICA_PRECIO = " + idGrupo + " group by p.cod_cliente, pg.ID_GRUPO_POLITICA_PRECIO";
//        String selectQuery = "select p.cod_cliente, p.cod_pedido, pg.id_grupo_politica_precio, pg.es_aplicado_descuento from pedido_grupo_fidelidad pg inner join pedidos p on p.cod_pedido =pg.cod_pedido and p.cod_cliente = " + codCli + " and pg.ID_GRUPO_POLITICA_PRECIO = " + idGrupo + " and pg.es_aplicado_descuento = 1 and p.cod_estadopedido <> 2";
//        Log.i("INFO A", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            int idg = c.getInt(c.getColumnIndex("ES_APLICADO_DESCUENTO"));
//            if(idg == 1){
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public boolean esAplicadoDescuento(int codCli, int codPedido, int idGrupo){
//
//        //String selectQuery = "select p.cod_cliente, p.cod_pedido, pg.id_grupo_politica_precio, pg.es_aplicado_descuento from pedidos p inner join pedidos_detalle pd on p.cod_pedido = pd.cod_pedido inner join pedido_grupo_fidelidad pg on pd.ID_GRUPO_POLITICA_PRECIO = pg.ID_GRUPO_POLITICA_PRECIO where cod_cliente = " + codCli + " and pg.ES_APLICADO_DESCUENTO = 1 and pg.ID_GRUPO_POLITICA_PRECIO = " + idGrupo + " and pg.cod_pedido = " + codPedido + " group by p.cod_cliente, pg.ID_GRUPO_POLITICA_PRECIO";
//        String selectQuery = "select p.cod_cliente, p.cod_pedido, pg.id_grupo_politica_precio, pg.es_aplicado_descuento from pedido_grupo_fidelidad pg inner join pedidos p on p.cod_pedido =pg.cod_pedido and p.cod_cliente = " + codCli + " and pg.cod_pedido = " + codPedido + " and pg.ID_GRUPO_POLITICA_PRECIO = " + idGrupo + " and pg.es_aplicado_descuento = 1";
//        Log.i("INFO A", selectQuery);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c.moveToFirst()) {
//            int idg = c.getInt(c.getColumnIndex("ES_APLICADO_DESCUENTO"));
//            if(idg == 1){
//                return true;
//            }
//        }
//        return false;
//    }

}