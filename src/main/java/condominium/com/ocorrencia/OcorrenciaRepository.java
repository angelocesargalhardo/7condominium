package condominium.com.ocorrencia;

import java.net.URI;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import comando.CriarOcorrencia;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, OcorrenciaId>{
	@RestController
	@RequestMapping("/Ocorrencias")
	public class OcorrenciaController {
		@Autowired
		private OcorrenciaService service;
		
		@GetMapping("/{id}")
		public ResponseEntity<Ocorrencia> buscar (@PathVariable OcorrenciaId id){
			System.out.println(id);
			Optional<Ocorrencia> optionalSindico = service.encontrar(id);
			if(optionalSindico.isPresent()) {
				return ResponseEntity.ok(optionalSindico.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		}
		
		@PostMapping
		public ResponseEntity<OcorrenciaId> post(@RequestBody CriarOcorrencia comando) throws SQLException{
			if(System.currentTimeMillis()%2 == 0) {
				throw new SQLException("Deu Ruim!!!");
			}
			Optional<OcorrenciaId> optionalOcorrenciaId = service.executar(comando);
			if(optionalOcorrenciaId.isPresent()) {
				URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(optionalOcorrenciaId.get()).toUri();
				return ResponseEntity.created(location).build();
			}
			return ResponseEntity.badRequest().build();
		}
	}
}
