package condominium.com.ocorrencia;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comando.CriarOcorrencia;


@Service
@Transactional
public class OcorrenciaService {
	@Autowired
	private OcorrenciaRepository repo;
	
	public Optional<OcorrenciaId> executar(CriarOcorrencia comando){
		Ocorrencia novo = repo.save(new Ocorrencia(comando));
		return Optional.of(novo.getId());
		}
	public Optional<Ocorrencia> encontrar (OcorrenciaId id){
		return repo.findById(id);
	}
	
	public Optional<List<Ocorrencia>> encontrar() {
		return Optional.of(repo.findAll());
	}
	
	public Optional<String> excluir(OcorrenciaId id){
		repo.deleteById (id);
		return Optional.of("Deletado");
	}
	
	public Optional<OcorrenciaId> alterar (EditarOcorrencia comando){
		Optional<Ocorrencia> optional = repo.findById(comando.getId());
		if (optional.isPresent()) {
			Ocorrencia ocorrencia = optional.get();
			ocorrencia.aplicar(comando);
			repo.save(ocorrencia);
			return Optional.of(comando.getId());
		}
		return Optional.empty();
	}
}
