package com.zeus.service;


import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zeus.android.controller.ConfiguracionController;
import com.zeus.android.entity.BoletaVisitaMedicoPersonal;
import com.zeus.android.entity.PersonalGeoreferenciado;
import com.zeus.android.util.Util;

import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

public class LocationManagerZeus extends Service implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener, LocationListener {



    private static final String TAG = LocationManagerZeus.class.getSimpleName();
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;

    private Location mLastLocation;
    private GoogleApiClient mGoogleApiClient;

    private boolean mRequestLocationUpdates = false;

    private LocationRequest mLocationRequest;

    private static int UPDATE_INTERVAL = 10000;
    private static int FATEST_INTERVAL = 5000;
    private static int DISPLACEMENT = 10;
    private BoletaVisitaMedicoPersonal boletaVisitaMedicoPersonal;
    private Context context;
    String correoPersonal="";

    String urlAgencia="";
    String device_id="";

    int versionGoogle;
    int versionZeus;

    public void mostrarMensaje(){
        if( mLastLocation !=null ){

            TareaInterna tareaInterna=new TareaInterna();

            PersonalGeoreferenciado personalGeoreferenciado=new PersonalGeoreferenciado();
            personalGeoreferenciado.setVersionGoogle(versionGoogle);
            personalGeoreferenciado.setVersionZeus(versionZeus);

            //
            //correoPersonal
            personalGeoreferenciado.setCorreoPersonal(correoPersonal);
            personalGeoreferenciado.setDeviceId(device_id);

            tareaInterna.execute(personalGeoreferenciado);
            // Toast.makeText(getApplicationContext(),"Latitud:"+mLastLocation.getLatitude()+":Longitud:"+mLastLocation.getLongitude()+":Proveedor:"+mLastLocation.getProvider()  ,Toast.LENGTH_LONG).show();
            // Log.i(TAG,"Latitud:"+mLastLocation.getLatitude()+":Longitud:"+mLastLocation.getLongitude()+":Proveedor:"+mLastLocation.getProvider()  );
            // DevicePolicyManager mDPM;


        }

    }

    @Override
    public void onCreate() {
        //super.onCreate();
        Log.i(TAG,"onCreate....");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //
        Log.i(TAG,"onStartCommand....");
        context=getApplicationContext();

        ConfiguracionController c = new ConfiguracionController(getApplicationContext());
        String data[] = c.getIpConfiguracion();

        urlAgencia= "http://" +data[0] + "/"+ Util.NAME_SERVER+"/treferencial/personalGeoreferencia";


        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        device_id = tm.getDeviceId();


        try {
            versionGoogle=getPackageManager().getPackageInfo("com.google.android.gms", 0 ).versionCode;
            versionZeus=getPackageManager().getPackageInfo("com.cofar", 0 ).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        Account[] accounts = AccountManager.get(context).getAccounts();

        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                String possibleEmail = account.name;
                Log.i(TAG,"possibleEmail:"+possibleEmail);
                if(possibleEmail.contains("@cofar.com.bo")){
                    if(!possibleEmail.equals("lab@cofar.com.bo") ){
                        correoPersonal=possibleEmail;

                    }
                }


            }
        }


        if(checkPlayServices()){
            buildGoogleApiClient();
            createLocationRequest();


            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            Log.i(TAG,"mLastLocation:"+mLastLocation);
            if( mLastLocation!=null ){
                mostrarMensaje();
            }






        }


        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {

        Toast.makeText(getApplicationContext(),"Iniciando Servicios",Toast.LENGTH_LONG);




        return null;
    }


    private boolean checkPlayServices() {
        Log.i( TAG,"checkPlayServices" );
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        if(resultCode != ConnectionResult.SUCCESS) {
            Toast.makeText(context, "Su dispositivo tiene problemas de Google Play Services", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    protected synchronized void buildGoogleApiClient() {
        Log.i( TAG,"buildGoogleApiClient" );

        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
        mGoogleApiClient.connect();

    }

    protected void createLocationRequest() {

        Log.i( TAG,"createLocationRequest" );
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FATEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_LOW_POWER);
        mLocationRequest.setSmallestDisplacement(DISPLACEMENT);


    }


    protected void startLocationUpdates() {

        Log.i( TAG,"startLocationUpdates:"+mGoogleApiClient.isConnected() );

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }
    protected void stopLocationUpdates() {
        Log.i( TAG,"stopLocationUpdates" );
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i( TAG,"startLocationUpdates:"+mGoogleApiClient.isConnected() );


        if( mGoogleApiClient!=null ){
            if( mGoogleApiClient.isConnected() ){
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
            }else{
                mGoogleApiClient.reconnect();
                if( mGoogleApiClient.isConnected() ){
                    LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

                }


            }



        }





    }

    @Override
    public void onConnectionSuspended(int i) {

        Log.i( TAG,"onConnectionSuspended" );
        mGoogleApiClient.connect();

    }

    @Override
    public void onLocationChanged(Location location) {

        Log.i( TAG,"onLocationChanged" );

        mLastLocation = location;
        if( mLastLocation!=null ){
            mostrarMensaje();

        }


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i( TAG,"onConnectionFailed" );


        Log.i(TAG, "Connection failed: " + connectionResult.getErrorCode());
    }


    private class TareaInterna  extends AsyncTask<PersonalGeoreferenciado,Void,String> {


        @Override
        protected String doInBackground(PersonalGeoreferenciado... params) {

            PersonalGeoreferenciado personalGeoreferenciado=params.clone()[0];
            try{

                URL url=new URL(urlAgencia);
                HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
                urlConnection.setRequestProperty("Accept", "application/json");
                urlConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

                urlConnection.setReadTimeout(HttpZeusConstants.TIME_OUT);
                urlConnection.setConnectTimeout(HttpZeusConstants.TIME_OUT);

                urlConnection.setRequestMethod("POST");
                urlConnection.setUseCaches(false);
                urlConnection.setDoOutput( true );
                urlConnection.setDoInput(true);


                personalGeoreferenciado.setLatitud( mLastLocation.getLatitude() );
                personalGeoreferenciado.setLongitud( mLastLocation.getLongitude() );
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();


                OutputStream output=urlConnection.getOutputStream();
                StringEntity entity = new StringEntity(gson.toJson( personalGeoreferenciado));
                InputStream input=entity.getContent();
                int data=0;
                while( (data=input.read())!=-1 ){
                    output.write(data);

                }
                input.close();
                output.flush();



                Log.i("ESTADO_SERVICIO:",HttpURLConnection.HTTP_OK+"");

                if( urlConnection.getResponseCode()==HttpURLConnection.HTTP_OK ){

                    String responseString = Util.convertInputStreamToString(urlConnection.getInputStream());
                    Log.i("qqqqqqqqqqqq:",responseString);



                }else{

//                throw new DownloadException("Failed to fetch data :"+urlConnection.getResponseCode());
                }

                urlConnection.disconnect();

            } catch(IOException ioe){
                Log.e(TAG,ioe.getMessage());

            }

            return "";
        }
    }

}
