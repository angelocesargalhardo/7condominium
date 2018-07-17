package condominium.com.ocorrencia;

import java.util.ArrayList;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import comando.CriarOcorrencia;

@Entity
public class Ocorrencia {

	@EmbeddedId
	@AttributeOverride(name="value",column=@Column(name="id"))
	private OcorrenciaId id;
	
	private String descrição;
	private String status;
	private ArrayList<String> comentario;
	
	public Ocorrencia() {
		
	}
	
	public Ocorrencia(CriarOcorrencia comando) {
		this.setId(new OcorrenciaId());
		this.setDescrição(comando.getDescrição());
		this.setComentario(comando.getComentario());
		this.setStatus(comando.getStatus());
		
	}


	public OcorrenciaId getId() {
		return id;
	}


	public void setId(OcorrenciaId id) {
		this.id = id;
	}


	public String getDescrição() {
		return descrição;
	}


	public void setDescrição(String descrição) {
		this.descrição = descrição;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public ArrayList<String> getComentario() {
		return comentario;
	}


	public void setComentario(ArrayList<String> comentario) {
		this.comentario = comentario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comentario == null) ? 0 : comentario.hashCode());
		result = prime * result + ((descrição == null) ? 0 : descrição.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Ocorrencia other = (Ocorrencia) obj;
		if (comentario == null) {
			if (other.comentario != null)
				return false;
		} else if (!comentario.equals(other.comentario))
			return false;
		if (descrição == null) {
			if (other.descrição != null)
				return false;
		} else if (!descrição.equals(other.descrição))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	
	public void aplicar(EditarOcorrencia comando) {
		this.status = comando.getStatus();
		this.comentario = comando.getComentario();
		this.descrição = comando.getDescrição();
	}
	
}
