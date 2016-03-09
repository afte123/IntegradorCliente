package login.integradorcliente;

import android.os.AsyncTask;


public class Tarea extends AsyncTask<Object, Integer, String>{
    Comunicacion com;



Tarea(){
    com=Comunicacion.getInstance();
}

    @Override
    protected String doInBackground(Object... params) {

        com.enviar(params[0]);
        return null;
    }
}
