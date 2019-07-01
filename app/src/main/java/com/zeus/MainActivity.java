package com.zeus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zeus.android.entity.CargoEmpresa;
import com.zeus.android.entity.Personal;
import com.zeus.pedido.TabbedActivityPedido;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView nombrePersonal, cargoPersonal;
    private Intent intent;

    private Personal personal;
    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarToolbar();

        inicializarNavigationMenu();

        List<BeanDao> a = new ArrayList<BeanDao>();
        BeanDao b = new BeanDao();
        b.setId(1);
        b.setNombre("Reporte Seguimiento Campana");

        BeanDao c = new BeanDao();
        c.setId(2);
        c.setNombre("Reporte Seguimiento Oferta");

        a.add(b);
        a.add(c);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    private void inicializarToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.app_name));
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }

    private void inicializarNavigationMenu() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        nombrePersonal = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nombrePersonal);
        cargoPersonal = (TextView) navigationView.getHeaderView(0).findViewById(R.id.cargoPersonal);

        if (getIntent().getExtras() != null) {

            personal = (Personal) getIntent().getExtras().getSerializable("personal");

            if (personal != null) {
                nombrePersonal.setText(personal.getNombresPersonal() + " " + personal.getApPaternoPersonal() + " " + personal.getApMaternoPersonal());
                cargoPersonal.setText(recuperarCargo(personal));
            }

        }

    }

    @Override
    public void onBackPressed() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        Fragment fragment = null;

        Bundle args = new Bundle();
        switch (item.getItemId()) {
            case R.id.nav_pedidos:
                fragment = TabbedActivityPedido.newInstance();
                //args.putSerializable("personal", personal);
                break;
            case R.id.nav_cuentasPorCobrar:
                break;
            case R.id.nav_georefClientes:
                break;
            case R.id.nav_georefMedicos:
                break;
            case R.id.nav_cobranzas:
                break;
            case R.id.nav_boletaVisita:
                break;
            case R.id.nav_visitas:
                break;
            case R.id.nav_presupuesto:
                break;
            case R.id.nav_entregaDePedidos:
                break;
            case R.id.nav_ajustes:
                //fragment = new SincronizacionConfigActivity();
                //args.putSerializable("personal", personal);
                break;
            case R.id.nav_salir:
                intent = new Intent(getApplicationContext(), LoginActivity.class);
                break;
            case R.id.nav_acerca:
//                fragment = new SincronizacionConfigActivity();
//                args.putSerializable("personal", personal);
                break;

        }

        if(fragment != null) {
            fragment.setArguments(args);
            FragmentManager fragmentManager = this.getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        }else if(intent != null){
            startActivity(intent);
            finish();
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public String recuperarCargo(Personal personal){
        String cargo = "";

        if(personal.getCodCargo() == 1081) {
            cargo = "Administrador";
        }else if(personal.getCodCargo() == CargoEmpresa.PROMOTOR_VENTA.codCargo) {
            cargo = "Promotor de Ventas";
            navigationView.getMenu().findItem(R.id.nav_pedidos).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_cuentasPorCobrar).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_georefClientes).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_cobranzas).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_visitas).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_presupuesto).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_entregaDePedidos).setVisible(true);
        }else if(personal.getCodCargo() == CargoEmpresa.VISITADOR.codCargo) {
            cargo = "Visitador Médico";
            navigationView.getMenu().findItem(R.id.nav_pedidos).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_georefMedicos).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_cobranzas).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_boletaVisita).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_entregaDePedidos).setVisible(true);
        }else if(personal.getCodCargo() == CargoEmpresa.DESPACHADOR.codCargo) {
            cargo = "Despachador";
            navigationView.getMenu().findItem(R.id.nav_entregaDePedidos).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_cobranzas).setVisible(true);
        }else if(personal.getCodCargo() == CargoEmpresa.COBRADOR.codCargo) {
            cargo = "Cobrador";
            navigationView.getMenu().findItem(R.id.nav_cobranzas).setVisible(true);
        }else if(personal.getCodCargo() == CargoEmpresa.JEFE_REGIONAL.codCargo) {
            cargo = "Jefe Regional";
        }else if(personal.getCodCargo() == CargoEmpresa.VISITADOR_FARMACIAS.codCargo) {
            cargo = "Visitador de Farmacias";
        }else{
            cargo = "Bienvenido";
        }

        return cargo;
    }
}
