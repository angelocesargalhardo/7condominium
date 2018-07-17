package condominium.com.mural;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import comando.CriarMural;
@Entity
public class Mural {
	
	@EmbeddedId
	@AttributeOverride(name="value",column=@Column(name="id"))
	private MuralId id;
	private String data;
	private String descricao;
	private String responsavel;
	
	public Mural() {
		
	}
	
	public Mural(CriarMural comando) {
		this.setId(new MuralId());
		this.setData(comando.getData());
		this.setDescricao(comando.getDescricao());
		this.setResponsavel(comando.getResponsavel());
	}

	public MuralId getId() {
		return id;
	}

	public void setId(MuralId id) {
		this.id = id;
	}

	
	public void setData(String string) {
		this.data = string;
	}
	
	public String getData() {
		return data;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	
	public String getResponsavel() {
		return responsavel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mural other = (Mural) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public void aplicar(EditarMural comando) {
		this.descricao = comando.getDescricao();
		this.data = comando.getData();
		this.responsavel = comando.getResponsavel();
	}
	
}
