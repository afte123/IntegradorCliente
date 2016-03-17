package login.integradorcliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.Observable;
import java.util.Observer;

import comun.Mensaje;

public class Registro extends AppCompatActivity implements Observer{
EditText creaNombre, creaContra;
    Mensaje mensaje;
    Comunicacion com;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        creaNombre = (EditText) findViewById(R.id.CreaNombre);
        creaContra = (EditText) findViewById(R.id.CreaContra);
        com= Comunicacion.getInstance();
        com.getInstance().setJefe(this);


    }




    public  void botonCrear(View v){
        mensaje = new Mensaje(creaNombre.getText().toString(),":",creaContra.getText().toString());
        new Tarea().execute(mensaje);
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
