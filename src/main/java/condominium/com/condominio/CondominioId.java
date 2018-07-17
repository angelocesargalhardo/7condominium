package condominium.com.condominio;


import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Embeddable;

@Embeddable
public class CondominioId implements Serializable {
	private static final long serialVersionUID = 1L;
	private String value;
	
	public CondominioId() {
		this.value = UUID.randomUUID().toString();
	}
	
	public CondominioId(String value) {
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
