package condominium.com.mural;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comando.CriarMural;

@Service
@Transactional
public class MuralService {
	@Autowired
	private MuralRepository repo;
	
	public Optional<MuralId> executar(CriarMural comando){
		Mural novo = repo.save(new Mural(comando));
		return Optional.of(novo.getId());
		}
	public Optional<Mural> encontrar(MuralId id) {
		return repo.findById(id);
	}
	
	public Optional<List<Mural>> encontrar() {
		return Optional.of(repo.findAll());
	}
	
	public Optional<String> excluir(MuralId id){
		repo.deleteById (id);
		return Optional.of("Deletado");
	}
	
	public Optional<MuralId> alterar (EditarMural comando){
		Optional<Mural> optional = repo.findById(comando.getId());
		if (optional.isPresent()) {
			Mural mural = optional.get();
			mural.aplicar(comando);
			repo.save(mural);
			return Optional.of(comando.getId());
		}
		return Optional.empty();
	}
}
