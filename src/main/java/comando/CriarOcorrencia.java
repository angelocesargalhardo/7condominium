package comando;

import java.util.ArrayList;

public class CriarOcorrencia {
	private String descrição;
	private ArrayList<String> comentario; 
	private String status;
	
	public CriarOcorrencia() {
		
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
