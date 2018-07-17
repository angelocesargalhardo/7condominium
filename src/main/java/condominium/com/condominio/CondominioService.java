package condominium.com.condominio;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comando.CriarCondominio;


@Service
@Transactional
public class CondominioService {
	@Autowired
	private CondominioRepository repo;
	
	public Optional<CondominioId> executar(CriarCondominio comando) {
		Condominio novo = repo.save(new Condominio(comando));
		return Optional.of(novo.getId());
		}

	public Optional<Condominio> encontrar(CondominioId id) {
		return repo.findById(id);
	}
	
	public Optional<List<Condominio>> encontrar() {
		return Optional.of(repo.findAll());
	}
	
	public Optional<String> excluir(CondominioId id){
		repo.deleteById (id);
		return Optional.of("Deletado");
	}
	
	public Optional<CondominioId> alterar (EditarCondominio comando){
		Optional<Condominio> optional = repo.findById(comando.getId());
		if (optional.isPresent()) {
			Condominio condominio = optional.get();
			condominio.aplicar(comando);
			repo.save(condominio);
			return Optional.of(comando.getId());
		}
		return Optional.empty();
	}
}
