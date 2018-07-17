package condominium.com.condominio;

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

import comando.CriarCondominio;
import javassist.tools.web.BadHttpRequest;

@Controller
@RestController
@RequestMapping("/condominio")
public class CondominioController {
	@Autowired
	private CondominioService service;
	
	@GetMapping
	public ResponseEntity<List<Condominio>> buscarTodosCondominios() {
		Optional<List<Condominio>> optionalCondominio = service.encontrar();
		return ResponseEntity.ok(optionalCondominio.get());	
	}

	@GetMapping("/{id}")
	public ResponseEntity<Condominio> buscar(@PathVariable CondominioId id){
	System.out.println(id);	
	Optional<Condominio> optionalCondominio =  service.encontrar(id);
	if(optionalCondominio.isPresent()) {
		return ResponseEntity.ok(optionalCondominio.get());
	} else {
		return ResponseEntity.notFound().build();
	}	
	}
	
	@PostMapping
	public ResponseEntity<CondominioId> criar(@RequestBody CriarCondominio comando)throws SQLException{
		Optional<CondominioId> optionalCondominioId = service.executar(comando);
		if(optionalCondominioId.isPresent()) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(optionalCondominioId.get()).toUri();
			return ResponseEntity.created(location).build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PutMapping
	public ResponseEntity<CondominioId> alterar(@RequestBody EditarCondominio comando)throws SQLException{
		Optional<CondominioId> optionalCondominioId = service.alterar(comando);
		if(optionalCondominioId.isPresent()) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(optionalCondominioId.get()).toUri();
			return ResponseEntity.created(location).build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> excluir(@PathVariable CondominioId id)throws TimeoutException, NullPointerException,  BadHttpRequest{
		if (verificaCondominioExistente(id)) {
			Optional<String> optionalCondominio =  service.excluir(id);
			return ResponseEntity.ok(optionalCondominio.get());
		}
		throw new NullPointerException("Este condominio não foi encontrado");
	}

	
	private boolean verificaCondominioExistente(CondominioId id) throws NullPointerException {
		if (!service.encontrar(id).isPresent()) {
			return false;
		}
		else {
			return true;
		}
	}


}
