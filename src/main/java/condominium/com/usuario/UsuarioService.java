package condominium.com.usuario;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comando.CriarUsuario;


@Service
@Transactional
public class UsuarioService {
	@Autowired
	private UsuarioRepository repo;
	
	public Optional<Usuario> encontrarid(UsuarioId id) {
		return repo.findById(id);
		
	}

	public Optional<UsuarioId> executar(CriarUsuario comando) {
		Usuario novo = repo.save(new Usuario(comando));
		return Optional.of(novo.getId());
	}
	
	public Optional<UsuarioId> alterar (EditarUsuario comando){
		Optional<Usuario> optional = repo.findById(comando.getId());
		if (optional.isPresent()) {
			Usuario usuario = optional.get();
			usuario.aplicar(comando);
			repo.save(usuario);
			return Optional.of(comando.getId());
	}
		return Optional.empty();
	}

	public Optional<String> excluir(UsuarioId id){
		repo.deleteById (id);
		return Optional.of("Deletado");
	}
	
	public Optional<List<Usuario>> encontrar() {
		return Optional.of(repo.findAll());
	}

}
