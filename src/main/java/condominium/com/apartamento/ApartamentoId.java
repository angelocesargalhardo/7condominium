package condominium.com.apartamento;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Embeddable;

@Embeddable
public class ApartamentoId implements Serializable {
	private static final long serialVersionUID = 1L;
	private String value;
	
	public ApartamentoId() {
		this.value = UUID.randomUUID().toString();
	}
	
	public ApartamentoId(String value) {
		this.value=value;	
	}
	
	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
}
