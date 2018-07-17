package condominium.com.bloco;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import comando.CriarBloco;
import javassist.tools.web.BadHttpRequest;

@Controller
@RestController
@RequestMapping("/bloco")
public class BlocoController {
	@Autowired
	private BlocoService service;
	
	@GetMapping
	public ResponseEntity<List<Bloco>> buscarTodosBlocos() {
		Optional<List<Bloco>> optionalBloco = service.encontrar();
		return ResponseEntity.ok(optionalBloco.get());	
	}

	@GetMapping("/{id}")
	public ResponseEntity<Bloco> buscar(@PathVariable BlocoId id){
	System.out.println(id);	
	Optional<Bloco> optionalBloco =  service.encontrar(id);
	if(optionalBloco.isPresent()) {
		return ResponseEntity.ok(optionalBloco.get());
	} else {
		return ResponseEntity.notFound().build();
	}	
	}
	
	@PostMapping
	public ResponseEntity<BlocoId> criar(@RequestBody CriarBloco comando)throws SQLException{
		Optional<BlocoId> optionalBlocoId = service.executar(comando);
		if(optionalBlocoId.isPresent()) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(optionalBlocoId.get()).toUri();
			return ResponseEntity.created(location).build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PutMapping
	public ResponseEntity<BlocoId> alterar(@RequestBody EditarBloco comando)throws SQLException {
		Optional<BlocoId> optionalBlocoId = service.alterar(comando);
		if(optionalBlocoId.isPresent()) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(optionalBlocoId.get()).toUri();
			return ResponseEntity.created(location).build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> excluir(@PathVariable BlocoId id)throws TimeoutException, NullPointerException,  BadHttpRequest{
		if (verificaBlocoExistente(id)) {
			Optional<String> optionalBloco =  service.excluir(id);
			return ResponseEntity.ok(optionalBloco.get());
		}
		throw new NullPointerException("Este bloco não foi encontrado");
	}

	
	private boolean verificaBlocoExistente(BlocoId id) throws NullPointerException {
		if (!service.encontrar(id).isPresent()) {
			return false;
		}
		else {
			return true;
		}
	}

}
