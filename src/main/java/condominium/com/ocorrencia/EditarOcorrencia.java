package condominium.com.ocorrencia;

import java.util.ArrayList;

public class EditarOcorrencia {
	
	private OcorrenciaId id;
	private String descrição;
	private String status;
	private ArrayList<String> comentario;
	
	public EditarOcorrencia() {
		
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
	
	
}
