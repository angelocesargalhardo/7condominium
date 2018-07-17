package condominium.com.bloco;

import java.util.ArrayList;
import java.util.List;

import condominium.com.apartamento.Apartamento;
import condominium.com.condominio.Condominio;

public class EditarBloco {
	private BlocoId id;
	private String nome;
	private Condominio condominio;
	private List<Apartamento> apartamentos = new ArrayList<>();
	
	public EditarBloco() {
		
	}

	public BlocoId getId() {
		return id;
	}

	public void setId(BlocoId id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Condominio getCondominio() {
		return condominio;
	}

	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}

	public List<Apartamento> getApartamentos() {
		return apartamentos;
	}

	public void setApartamentos(List<Apartamento> apartamentos) {
		this.apartamentos = apartamentos;
	}
	
	
}
