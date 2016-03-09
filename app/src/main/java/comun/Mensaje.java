package comun;
import java.io.Serializable;


public class Mensaje implements Serializable {
	String nombre, contra;


	public Mensaje (String nombre, String contra){
	this.nombre=nombre;
		this.contra=contra;
	}

	public String getNombre(){
		return nombre;
	}

	public String getContra() {
		return contra;
	}

}
