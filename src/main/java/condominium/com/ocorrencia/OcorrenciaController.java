package condominium.com.ocorrencia;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import comando.CriarOcorrencia;
import javassist.tools.web.BadHttpRequest;

@RestController
@RequestMapping("/ocorrencias")
public class OcorrenciaController {

		@Autowired
		private OcorrenciaService service;
		
		@GetMapping
		public ResponseEntity<List<Ocorrencia>> buscarTodasOcorrencias() {
			Optional<List<Ocorrencia>> optionalOcorrencia = service.encontrar();
			return ResponseEntity.ok(optionalOcorrencia.get());	
		}
		
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
		
		@PutMapping
		public ResponseEntity<OcorrenciaId> alterar(@RequestBody EditarOcorrencia comando)throws SQLException{
			Optional<OcorrenciaId> optionalOcorrenciaId = service.alterar(comando);
			if(optionalOcorrenciaId.isPresent()) {
				URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(optionalOcorrenciaId.get()).toUri();
				return ResponseEntity.created(location).build();
			}
			return ResponseEntity.badRequest().build();
		}
		
		@DeleteMapping("/{id}")
		public ResponseEntity<String> excluir(@PathVariable OcorrenciaId id)throws TimeoutException, NullPointerException,  BadHttpRequest{
			if (verificaOcorrenciaExistente(id)) {
				System.out.println();
				Optional<String> optionalOcorrenciaId =  service.excluir(id);
				return ResponseEntity.ok(optionalOcorrenciaId.get());
			}
			throw new NullPointerException("Esta Ocorrencia não foi encontrado");
		}

		
		private boolean verificaOcorrenciaExistente(OcorrenciaId id) throws NullPointerException {
			if (!service.encontrar(id).isPresent()) {
				return false;
			}
			else {
				return true;
			}
		}

}
