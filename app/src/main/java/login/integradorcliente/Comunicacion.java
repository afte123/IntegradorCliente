package login.integradorcliente;

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;


public class Comunicacion  extends Observable implements Runnable{


    Observer jefe;
    private final int PORT = 5000;
    private DatagramSocket socket;
    private DatagramPacket packet;
    private String command="";
    static  Comunicacion singleton;
    InetAddress IPGrupo;
    byte[] datos;
    byte[] buzon;
    DatagramPacket pEnviar, pRecibir;

    private Comunicacion(){

        try {
            // Initialize the DatagramSocket to receive commands
            socket = new DatagramSocket();
            Thread hilo = new Thread(this);
            //IPGrupo = InetAddress.getByName("10.0.2.2");
          //  IPGrupo = InetAddress.getByName("192.168.0.11");
            IPGrupo = InetAddress.getByName("172.30.158.62");
            hilo.start();


            // Create the buffer and the receiving packet
            byte[] buffer = new byte[64];
            packet = new DatagramPacket(buffer, buffer.length);

        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


    }

    public void run() {
        while (true) {
            try {
                // Receive packets and process the information
                socket.receive(packet);
                command= new String(packet.getData(),0,packet.getLength());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    public byte[] serializar(Object obj) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        oos.flush();
        oos.close();
        return bos.toByteArray();

    }


    public void enviar(Object obj) {

        try {
            datos = serializar(obj);
            pEnviar = new DatagramPacket(datos, datos.length, IPGrupo, PORT);
            socket.send(pEnviar);
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public Object deserializar(byte[] des) throws IOException,
            ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(des);
        ObjectInputStream is = new ObjectInputStream(bais);
        Object obj = (Object) is.readObject();
        is.close();
        return obj;

    }
public static Comunicacion getInstance(){
    if(singleton ==null){
        singleton = new Comunicacion();

    }
    return singleton;
}
    public void setJefe(Observer jefe){
        this.jefe=jefe;
    }

}
