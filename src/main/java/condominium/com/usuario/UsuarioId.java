package condominium.com.usuario;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Embeddable;



@Embeddable
public class UsuarioId implements Serializable{
	private static final long serialVersionUID = 1l;
	private String value;
	
	public UsuarioId() {
		this.value = UUID.randomUUID().toString();
		
	}
	public UsuarioId(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
}
