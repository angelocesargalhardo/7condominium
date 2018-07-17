package condominium.com.condominio;

import java.util.ArrayList;
import java.util.List;

import condominium.com.bloco.Bloco;

public class EditarCondominio {
	private CondominioId id;
	private String nome;
	private String endereço;
	private int numero;
	private Integer cep;
	private Integer telefone;
	private List<Bloco> blocos = new ArrayList<>();
	private String nomeSindico;
	private int numeroAP;
	
	public  EditarCondominio() {
		
	}

	public CondominioId getId() {
		return id;
	}

	public void setId(CondominioId id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereço() {
		return endereço;
	}

	public void setEndereço(String endereço) {
		this.endereço = endereço;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	public Integer getTelefone() {
		return telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}

	public List<Bloco> getBlocos() {
		return blocos;
	}

	public void setBlocos(List<Bloco> blocos) {
		this.blocos = blocos;
	}

	public String getNomeSindico() {
		return nomeSindico;
	}

	public void setNomeSindico(String nomeSindico) {
		this.nomeSindico = nomeSindico;
	}

	public int getNumeroAP() {
		return numeroAP;
	}

	public void setNumeroAP(int numeroAP) {
		this.numeroAP = numeroAP;
	}
	
	
}
