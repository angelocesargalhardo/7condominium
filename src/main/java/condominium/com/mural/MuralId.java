package condominium.com.mural;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Embeddable;

@Embeddable
public class MuralId implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	
	public MuralId() {
		this.id = UUID.randomUUID().toString();
	}
	
	public MuralId(String id) {
		this.id = id;
	}

	public String getValue() {
		return id;
	}

	@Override
	public String toString() {
		return this.id;
	}

}
