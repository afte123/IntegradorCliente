package comun;
import java.io.Serializable;


public class Mensaje implements Serializable {
	String nombre, contra, nickName, apellido;
	public Mensaje (String nombre, String apellido,  String contra, String nickName){
		this.nombre=nombre;
		this.apellido=apellido;
		this.contra=contra;
		this.nickName=nickName;
	}

	public String getNombre(){
		return nombre;
	}

	public String getContra() {
		return contra;
	}

	public String getApellido() {
		return apellido;
	}

	public String getnickName() {
		return nickName;
	}
}
