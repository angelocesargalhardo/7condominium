package condominium.com.apartamento;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comando.CriarApartamento;


@Service
public class ApartamentoService {
	@Autowired
	private ApartamentoRepository repo;
	
	public Optional<ApartamentoId> executar (CriarApartamento comando){
		Apartamento novo = repo.save(new Apartamento(comando));
		return Optional.of(novo.getId());
	}
	
	public Optional<Apartamento> encontrar (ApartamentoId id){
		return repo.findById(id);
	}
	
	public Optional<List<Apartamento>> encontrar(){
		return Optional.of(repo.findAll());
	}
	
	public Optional<String> excluir(ApartamentoId id){
		repo.deleteById(id);
		return Optional.of("Deletado");
	}
	
	public Optional<ApartamentoId> alterar(EditarApartamento comando){
		Optional<Apartamento> optional = repo.findById(comando.getId());
		if (optional.isPresent()) {
			Apartamento apartamento = optional.get();
			apartamento.alterar(comando);
			repo.save(apartamento);
			return Optional.of(comando.getId());
		}
		return Optional.empty();
	}
}
