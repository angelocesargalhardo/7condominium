package condominium.com.condominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import comando.CriarCondominio;
import condominium.com.bloco.Bloco;

@Entity
public class Condominio {
	@EmbeddedId
	@AttributeOverride(name="value",column=@Column(name="id"))
	private CondominioId id;
	private String nome;
	private String endereço;
	private int numero;
	private Integer cep;
	private Integer telefone;
	@OneToMany(mappedBy = "condominio", cascade = CascadeType.ALL)
	private List<Bloco> blocos = new ArrayList<>();
	private String nomeSindico;
	
	public Condominio() {
		
	}
	
	public Condominio(CriarCondominio comando) {
		this.setId (new CondominioId());
		this.setNome(comando.getNome());
		this.setCep(comando.getCep());
		this.setEndereço(comando.getEndereço());
		this.setBlocos(comando.getBlocos());
		this.setNumero(comando.getNumero());
		this.setTelefone(comando.getTelefone());
		this.setNomeSindico(comando.getNomeSindico());
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

	public String getNomeSindico() {
		return nomeSindico;
	}

	public void setNomeSindico(String nomeSindico) {
		this.nomeSindico = nomeSindico;
	}

	public List<Bloco> getBlocos() {
		return blocos;
	}

	public void setBlocos(List<Bloco> blocos) {
		this.blocos = blocos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((blocos == null) ? 0 : blocos.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((endereço == null) ? 0 : endereço.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((nomeSindico == null) ? 0 : nomeSindico.hashCode());
		result = prime * result + numero;
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
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
		Condominio other = (Condominio) obj;
		if (blocos == null) {
			if (other.blocos != null)
				return false;
		} else if (!blocos.equals(other.blocos))
			return false;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (endereço == null) {
			if (other.endereço != null)
				return false;
		} else if (!endereço.equals(other.endereço))
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
		if (nomeSindico == null) {
			if (other.nomeSindico != null)
				return false;
		} else if (!nomeSindico.equals(other.nomeSindico))
			return false;
		if (numero != other.numero)
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}

	public void aplicar(EditarCondominio comando) {
		this.nome = comando.getNome();
		this.blocos = comando.getBlocos();
		this.cep = comando.getCep();
		this.endereço = comando.getEndereço();
		this.nomeSindico = comando.getNome();
		this.numero = comando.getNumero();
		this.telefone = comando.getTelefone();
	}
	
}
