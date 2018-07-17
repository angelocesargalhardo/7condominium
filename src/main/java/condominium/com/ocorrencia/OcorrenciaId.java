package condominium.com.ocorrencia;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Embeddable;


@Embeddable
public class OcorrenciaId implements Serializable {
	private static final long serialVersionUID = 1L; 
	private String value;
	
	
	public OcorrenciaId() {
		this.value = UUID.randomUUID().toString();
	}
	
	public OcorrenciaId(String value) {
		this.value= value;
	}

	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
	
	
}
