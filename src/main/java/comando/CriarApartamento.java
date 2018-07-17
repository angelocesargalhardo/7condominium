package comando;

import condominium.com.apartamento.ApartamentoId;
import condominium.com.bloco.Bloco;

public class CriarApartamento {
	private ApartamentoId id;
	private int nome;
	private Bloco bloco;
	private int interfone;
	
	public CriarApartamento() {
		
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
