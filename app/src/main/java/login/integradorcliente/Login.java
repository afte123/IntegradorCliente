package login.integradorcliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;


import comun.Mensaje;


public class Login extends AppCompatActivity implements Observer {
EditText nickName, contra;
    Mensaje mensaje;
    Comunicacion com;

    private String mostrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        nickName =(EditText)findViewById(R.id.nickName);
        contra = (EditText)findViewById(R.id.contrasena);
        com= Comunicacion.getInstance();
        com.getInstance().setJefe(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    public void bLogin(View v){
        mensaje = new Mensaje("Login", nickName.getText().toString(), contra.getText().toString());
        new Tarea().execute(mensaje);
        if(com.isConectado() == true){
            Intent intent = new Intent(this, Producto.class);
            startActivity(intent);
        }
    }

    public void bRegistro(View v){
        Intent intent = new Intent(this, Registro.class);
        startActivity(intent);
    }


    private void mostrarMensaje(String mostrarMs) {
        mostrar = mostrarMs;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), mostrar, Toast.LENGTH_LONG).show();
            }
        });
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


    @Override
    public void update(Observable observable, Object data) {

    }
}
