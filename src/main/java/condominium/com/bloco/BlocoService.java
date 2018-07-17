package condominium.com.bloco;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comando.CriarBloco;

@Service
public class BlocoService {
	@Autowired
	private BlocoRepository repo;
	
	public Optional<BlocoId> executar (CriarBloco comando){
		Bloco novo = repo.save(new Bloco(comando));
		return Optional.of(novo.getId());
	}
	
	public Optional<Bloco> encontrar (BlocoId id){
		return repo.findById(id);
	}
	
	public Optional<List<Bloco>> encontrar(){
		return Optional.of(repo.findAll());
	}
	
	public Optional<String> excluir(BlocoId id){
		repo.deleteById(id);
		return Optional.of("Deletado");
	}
	
	public Optional<BlocoId> alterar(EditarBloco comando){
		Optional<Bloco> optional = repo.findById(comando.getId());
		if (optional.isPresent()) {
			Bloco bloco = optional.get();
			bloco.alterar(comando);
			repo.save(bloco);
			return Optional.of(comando.getId());
		}
		return Optional.empty();
	}
}
