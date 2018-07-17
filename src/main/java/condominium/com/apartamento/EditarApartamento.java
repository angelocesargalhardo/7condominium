package condominium.com.apartamento;

import condominium.com.bloco.Bloco;

public class EditarApartamento {
	private ApartamentoId id;
	private int nome;
	private Bloco bloco;
	private int interfone;

	public EditarApartamento() {
		
	}

	public ApartamentoId getId() {
		return id;
	}

	public void setId(ApartamentoId id) {
		this.id = id;
	}

	public int getNome() {
		return nome;
	}

	public void setNome(int nome) {
		this.nome = nome;
	}

	public Bloco getBloco() {
		return bloco;
	}

	public void setBloco(Bloco bloco) {
		this.bloco = bloco;
	}
	
	public int getInterfone() {
		return interfone;
	}

	public void setInterfone(int interfone) {
		this.interfone = interfone;
	}
	
}
