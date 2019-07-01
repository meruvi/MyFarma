package com.zeus;

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

import com.zeus.android.entity.Personal;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView nombrePersonal;

    private Personal personal;
    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = (View) navigationView.getHeaderView(0);

        //ActionBar actionBar = getActionBar();

        // actionBar.setDisplayShowTitleEnabled(false);

        // Enabling Spinner dropdown navigation
        //actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //mTitle = mDrawerTitle = getTitle();
        //mPlanetTitles = getResources().getStringArray(R.array.opciones_promotor_titulo);
        //mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //mDrawerList = (ListView) findViewById(R.id.left_drawer);

        //View header = getLayoutInflater().inflate(R.layout.nav_header_main, null);

        ///header.setBackgroundColor(Color.WHITE);
        // Establecemos header
        //mDrawerList.addHeaderView(header);

        //String options[] = getResources().getStringArray(R.array.opciones_promotor);

        // set a custom shadow that overlays the main content when the drawer
        // opens
        //mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,	GravityCompat.START);
        // set up the drawer's list view with items and click listener
        // mDrawerList.setAdapter(new
        // ArrayAdapter<String>(this,R.layout.drawer_list_item, mPlanetTitles));
        //ArrayList<MenuItemAdapter> menuOptions = new ArrayList<MenuItemAdapter>();
        /*mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, options));*/
        /*for (String name : options) {
            menuOptions.add(new MenuItemAdapter(name, 0));
        }*/

        //NavigationAdapterDrawerLayout navigationAdapterDrawerLayout = new NavigationAdapterDrawerLayout(this, menuOptions);

        // mDrawerList.setAdapter(navigationAdapterDrawerLayout);

        // Declaramos el header el caul sera el layout de header.xml
        //mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        //	getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
//        mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
//                mDrawerLayout, /* DrawerLayout object */
//                R.drawable.ic_action_search, /* nav drawer image to replace 'Up' caret */
//                R.string.drawer_open, /* "open drawer" description for accessibility */
//                R.string.drawer_close /* "close drawer" description for accessibility */
//        ) {
//            public void onDrawerClosed(View view) {
//
//                //	getActionBar().setTitle(mTitle);
//                invalidateOptionsMenu(); // creates call to
//                // onPrepareOptionsMenu()
//            }
//
//            public void onDrawerOpened(View drawerView) {
//                //getActionBar().setTitle(mDrawerTitle);
//
//                invalidateOptionsMenu(); // creates call to
//                // onPrepareOptionsMenu()
//            }
//        };

        //mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (getIntent().getExtras() != null) {
            personal = (Personal) getIntent().getExtras().getSerializable("personal");

            Log.i("CodigoPersonal", getIntent().getExtras().getSerializable("personal") + "");
            TextView nombrePersonal = (TextView) header.findViewById(R.id.nombrePersonal);
            if (personal != null)
                nombrePersonal.setText(personal.getNombresPersonal() + " " + personal.getApPaternoPersonal() + " " + personal.getApMaternoPersonal());

            /*Integer menuOpcion = (Integer) getIntent().getExtras().getSerializable("menuOpcion");
            if (menuOpcion == null) {
                selectItem(1);
            } else {
                selectItem(menuOpcion.intValue());
            }*/
        } /*else {
            selectItem(1);
        }*/

        List<BeanDao> a = new ArrayList<BeanDao>();
        BeanDao b = new BeanDao();
        b.setId(1);
        b.setNombre("Reporte Seguimiento Campana");

        BeanDao c = new BeanDao();
        c.setId(2);
        c.setNombre("Reporte Seguimiento Oferta");

        a.add(b);
        a.add(c);

		/*actionBar.setListNavigationCallbacks(new MySpinnerAdapter(getApplication(), a), new OnNavigationListener() {

			public boolean onNavigationItemSelected(int itemPosition, long itemId) {
				// TODO Auto-generated method stub
				return false;
			}
		});*/

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
//                fragment = TabbedActivityPedido.newInstance();
//                args.putSerializable("personal", personal);
                break;
            case R.id.nav_cuentas_cobrar:
//                fragment = new BuscarClienteFragment();
//                args.putSerializable("personal", personal);
                break;
            case R.id.nav_clientes:
//                fragment = new RegistroCliente();
//                args.putSerializable("personal", personal);
                break;
            case R.id.nav_cobranzas:
//                fragment = TabbedActivityCobranza.newInstance();
//                args.putSerializable("personal", personal);
                break;
            case R.id.nav_visitas:
//                fragment = TabbedActivityVisitaPersonal.newInstance();
//                args.putSerializable("personal", personal);
                break;
            case R.id.nav_dashboard:
//                fragment = new TabbedActivityDashBoard();
//                //fragment = new BuscarClienteCampaniaFragment();
//                //fragment = new VerificarInformacionFragment();
//                args.putSerializable("personal", personal);
                break;
            case R.id.nav_entrega_pedidos:
//                fragment = new TabbedActivitySalidaDespacho();
//                //fragment = new BuscarClienteCampaniaFragment();
//                //fragment = new VerificarInformacionFragment();
//                args.putSerializable("personal", personal);
                break;
            case R.id.nav_ajustes:
//                fragment = new SincronizacionConfigActivity();
//                args.putSerializable("personal", personal);
                break;
            case R.id.nav_salir:
//                fragment = new SincronizacionConfigActivity();
//                args.putSerializable("personal", personal);
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
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
