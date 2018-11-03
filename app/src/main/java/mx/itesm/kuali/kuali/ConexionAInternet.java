package mx.itesm.kuali.kuali;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;

public class ConexionAInternet {

    public static boolean obtenerConexion(Context context, boolean con_dialogo){
        if(!validarConexionARed(context)){
            if(con_dialogo) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Error en la Conexión");
                builder.setMessage("No hay conexión a internet");
                builder.setPositiveButton("Aceptar", null);
                builder.show();
            }
            return false;
        }
        return true;
    }

    private static boolean validarConexionARed(Context context) {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }

}
