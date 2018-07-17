package condominium.com.usuario;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;


import comando.CriarUsuario;

@Entity 
public class Usuario {
	
	@EmbeddedId
	@AttributeOverride (name = "value", column = @Column(name="id"))
	private UsuarioId Id;
	
	private String nome;
	private String email;
	private String senha;
	private String bloco;
	private String apartamento;
	private String cargo;
	
	public Usuario() {
		
	}
	
	public Usuario(CriarUsuario comando) {
		this.setId(new UsuarioId());
		this.setNome(comando.getNome());
		this.setApartamento(comando.getApartamento());
		this.setEmail(comando.getEmail());
		this.setSenha(comando.getSenha());
		this.setBloco(comando.getBloco());
		this.setCargo(comando.getCargo());
	}
	public UsuarioId getId() {
		return Id;
	}
	public void setId(UsuarioId id) {
		this.Id = id;
	}
	public String getNome() {
		return this.nome;
	}
	public String getEmail() {
		return this.email;
	}
	public String getSenha() {
		return this.senha;
	}
	public String getBloco() {
		return this.bloco;
	}
	public String getApartamento() {
		return this.apartamento;
	}
	public void setApartamento(String apartamento) {
		this.apartamento = apartamento;
	}
	public void setBloco(String bloco) {
		this.bloco = bloco;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		result = prime * result + ((apartamento == null) ? 0 : apartamento.hashCode());
		result = prime * result + ((bloco == null) ? 0 : bloco.hashCode());
		result = prime * result + ((cargo == null) ? 0 : cargo.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
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
		Usuario other = (Usuario) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		if (apartamento == null) {
			if (other.apartamento != null)
				return false;
		} else if (!apartamento.equals(other.apartamento))
			return false;
		if (bloco == null) {
			if (other.bloco != null)
				return false;
		} else if (!bloco.equals(other.bloco))
			return false;
		if (cargo == null) {
			if (other.cargo != null)
				return false;
		} else if (!cargo.equals(other.cargo))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}

	public void aplicar(EditarUsuario comando) {
		this.nome = comando.getNome();
		this.apartamento = comando.getApartamento();
		this.bloco = comando.getBloco();
		this.email = comando.getEmail();
		this.senha= comando.getSenha();
		this.cargo = comando.getCargo();
	}
}
