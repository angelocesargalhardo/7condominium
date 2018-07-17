package condominium.com.bloco;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Embeddable;

@Embeddable
public class BlocoId implements Serializable {
	private static final long serialVersionUID = 1L;
	private String value;
	
	public BlocoId() {
		this.value = UUID.randomUUID().toString();
	}
	
	public BlocoId(String value) {
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
