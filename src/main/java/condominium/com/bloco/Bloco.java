package condominium.com.bloco;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import comando.CriarBloco;
import condominium.com.apartamento.Apartamento;
import condominium.com.condominio.Condominio;

@Entity
public class Bloco {
	@Id
	@AttributeOverride(name="value",column=@Column(name="id"))
	private BlocoId id;
	private String nome;
	@ManyToOne
	private Condominio condominio;
	@OneToMany(mappedBy = "bloco", cascade = CascadeType.ALL)
	private List<Apartamento> apartamentos = new ArrayList<>();
	

	public Bloco() {
				
	}
	
	public Bloco(CriarBloco comando) {
		this.setId(new BlocoId());
		this.setNome(comando.getNome());
		this.setApartamentos(comando.getApartamentos());
		this.setCondominio(comando.getCondominio());
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apartamentos == null) ? 0 : apartamentos.hashCode());
		result = prime * result + ((condominio == null) ? 0 : condominio.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Bloco other = (Bloco) obj;
		if (apartamentos == null) {
			if (other.apartamentos != null)
				return false;
		} else if (!apartamentos.equals(other.apartamentos))
			return false;
		if (condominio == null) {
			if (other.condominio != null)
				return false;
		} else if (!condominio.equals(other.condominio))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	public void alterar(EditarBloco comando) {
		this.nome = comando.getNome();
		this.condominio = comando.getCondominio();
		this.apartamentos = comando.getApartamentos();
	}
	
}
