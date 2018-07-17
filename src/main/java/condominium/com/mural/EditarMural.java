package condominium.com.mural;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditarMural {
	private MuralId id;
	private Date data = new Date();
	private String descricao;
	private String responsavel = System.getProperty("user.name");
	
	
	public EditarMural() {	
	
	}

	public MuralId getId() {
		return id;
	}

	public void setId(MuralId id) {
		this.id = id;
	}

	public String getData() {
		DateFormat dateFormat = new SimpleDateFormat(" HH:mm:ss dd/MM/yyyy"); 
		return dateFormat.format(data);
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	
	
}
