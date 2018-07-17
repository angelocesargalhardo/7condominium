package condominium.com.mural;

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

import comando.CriarMural;
import javassist.tools.web.BadHttpRequest;


@RestController
@RequestMapping("/mural")
public class MuralController {
	@Autowired
	private MuralService service;
	
	@GetMapping
	public ResponseEntity<List<Mural>> buscarTodosMurals() {
		Optional<List<Mural>> optionalMural = service.encontrar();
		return ResponseEntity.ok(optionalMural.get());	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Mural> buscar(@PathVariable MuralId id){
		System.out.println(id);
		Optional<Mural> optionalMural = service.encontrar(id);
		if(optionalMural.isPresent()) {
			return ResponseEntity.ok(optionalMural.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<MuralId> incluir(@RequestBody CriarMural comando) throws SQLException{
		Optional<MuralId> optionalMuralId = service.executar(comando);
		if(optionalMuralId.isPresent()) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(optionalMuralId.get()).toUri();
			return ResponseEntity.created(location).build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PutMapping
	public ResponseEntity<MuralId> alterar(@RequestBody EditarMural comando)throws SQLException{
		Optional<MuralId> optionalMuralId = service.alterar(comando);
		if(optionalMuralId.isPresent()) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(optionalMuralId.get()).toUri();
			return ResponseEntity.created(location).build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> excluir(@PathVariable MuralId id)throws TimeoutException, NullPointerException,  BadHttpRequest{
		if (verificaMuralExistente(id)) {
			Optional<String> optionalMuralId =  service.excluir(id);
			return ResponseEntity.ok(optionalMuralId.get());
		}
		throw new NullPointerException("Este aviso/mural não foi encontrado");
	}

	
	private boolean verificaMuralExistente(MuralId id) throws NullPointerException {
		if (!service.encontrar(id).isPresent()) {
			return false;
		}
		else {
			return true;
		}
	}

}
