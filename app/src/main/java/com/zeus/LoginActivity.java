package com.zeus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;
import com.zeus.android.controller.PersonalController;
import com.zeus.android.entity.Personal;
import com.zeus.android.util.Util;
import com.zeus.service.LocationManagerZeus;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    TextView versionName;

    PersonalController personalController;
    EditText usuarioEdit;
    EditText passwordEdit;

    Map<String, Personal> mapPersonal;
    Intent intent;
//    Intent intentDespachador;
//    Intent intentCobrador;
//    Intent intentMedico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.i(TAG, "--------------------INICIANDO-----------------");
        Intent service = new Intent(this, LocationManagerZeus.class);
        startService(service);
        Log.i(TAG, "--------------------FIN-----------------");
        usuarioEdit = (EditText) findViewById(R.id.usuarioEdit);
        passwordEdit = (EditText) findViewById(R.id.passwordEdit);
        versionName = (TextView) findViewById(R.id.versionName);
        versionName.setText("Version " + Util.getVersionName(getApplicationContext()));

        personalController = new PersonalController(this);
        Log.i(TAG, mapPersonal+"" );
        mapPersonal = personalController.getListPersonal();

        intent = new Intent(this, MainActivity.class);
//        intentDespachador = new Intent(this, MainDespachadorDrawerLayout.class);
//        intentCobrador = new Intent(this, MainCobradorDrawerLayout.class);
//        intentMedico = new Intent(this, MainMedicoDrawerLayout.class);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapPersonal != null) {
                    if (mapPersonal.containsKey(usuarioEdit.getText().toString() + passwordEdit.getText().toString())) {
                        Personal personal = mapPersonal.get(usuarioEdit.getText().toString() + passwordEdit.getText().toString());
                            intent.putExtra("personal", personal);
//                            intent.putExtra("tipoVista", TipoVista.PROMOTOR.ordinal());
                            startActivity(intent);
                            finish();

//                        if (personal.getCodCargo() == CargoEmpresa.PROMOTOR_VENTA.codCargo) {
//                            intent.putExtra("personal", personal);
//                            intent.putExtra("tipoVista", TipoVista.PROMOTOR.ordinal());
//                            startActivity(intent);
//                            finish();
//                        } else if (personal.getCodCargo() == CargoEmpresa.DESPACHADOR.codCargo) {
//                            intentDespachador.putExtra("personal", personal);
//                            intent.putExtra("tipoVista", TipoVista.DESPACHADOR.ordinal());
//                            startActivity(intentDespachador);
//                            finish();
//                        } else if (personal.getCodCargo() == CargoEmpresa.COBRADOR.codCargo) {
//                            intentCobrador.putExtra("personal", personal);
//                            intent.putExtra("tipoVista", TipoVista.COBRADOR.ordinal());
//                            Toast.makeText(getApplicationContext(), "SI", Toast.LENGTH_LONG).show();
//                            Log.i(TAG, personal.getCodPersonal() + "");
//                            startActivity(intentCobrador);
//                            finish();
//                        } else if (personal.getNombreUsuario().equals(getMD5(usuarioEdit.getText().toString())) && personal.getContraseniaUsuario().equals(getMD5(passwordEdit.getText().toString())) && personal.getCodPersonal() == 1081) {
//                            intent.putExtra("personal", personal);
//                            intent.putExtra("tipoVista", TipoVista.PROMOTOR.ordinal());
//                            startActivity(intent);
//                            finish();
//                        } else if (personal.getCodCargo() == CargoEmpresa.VISITADOR.codCargo || personal.getCodCargo() == CargoEmpresa.VISITADOR_FARMACIAS.codCargo) {
//                            intentMedico.putExtra("personal", personal);
//                            Log.i(TAG, personal.getCodPersonal() + "");
//                            intent.putExtra("tipoVista", TipoVista.VISITADOR.ordinal());
//                            startActivity(intentMedico);
//                            finish();
//                        }
                    } else {
//                        FragmentManager fragmentManager = getSupportFragmentManager();
//                        DialogoAlerta dialogo = new DialogoAlerta("Usuario o Password Incorrectos.");
//                        dialogo.show(fragmentManager, "tagAlerta");
                    }
                } else {
//                    FragmentManager fragmentManager = getSupportFragmentManager();
//                    DialogoAlerta dialogo = new DialogoAlerta("Usuario o Password Incorrectos..");
//                    dialogo.show(fragmentManager, "tagAlerta");
                }
            }
        });
    }
}
