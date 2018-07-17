package condominium.com.apartamento;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import comando.CriarApartamento;
import condominium.com.bloco.Bloco;

@Entity
public class Apartamento {
	@Id
	@AttributeOverride(name="value",column=@Column(name="id"))
	private ApartamentoId id;
	private int nome;
	@ManyToOne
	private Bloco bloco;
	private int interfone;

	public Apartamento() {
		
	}
	
	public Apartamento(CriarApartamento comando) {
		this.setId(new ApartamentoId());
		this.setNome(comando.getNome());
		this.setBloco(comando.getBloco());
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bloco == null) ? 0 : bloco.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + interfone;
		result = prime * result + nome;
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
		Apartamento other = (Apartamento) obj;
		if (bloco == null) {
			if (other.bloco != null)
				return false;
		} else if (!bloco.equals(other.bloco))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (interfone != other.interfone)
			return false;
		if (nome != other.nome)
			return false;
		return true;
	}
	
	public void alterar(EditarApartamento comando) {
		this.nome = comando.getNome();
		this.bloco = comando.getBloco();
		this.interfone = comando.getInterfone();
	}
	
}
