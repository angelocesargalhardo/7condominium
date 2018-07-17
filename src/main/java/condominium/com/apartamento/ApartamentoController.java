package condominium.com.apartamento;

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

import comando.CriarApartamento;
import javassist.tools.web.BadHttpRequest;

@Controller
@RestController
@RequestMapping("/apartamento")
public class ApartamentoController {
	@Autowired
	private ApartamentoService service;
	
	@GetMapping
	public ResponseEntity<List<Apartamento>> buscarTodosApartamentos() {
		Optional<List<Apartamento>> optionalApartamento = service.encontrar();
		return ResponseEntity.ok(optionalApartamento.get());	
	}

	@GetMapping("/{id}")
	public ResponseEntity<Apartamento> buscar(@PathVariable ApartamentoId id){
	System.out.println(id);	
	Optional<Apartamento> optionalApartamento =  service.encontrar(id);
	if(optionalApartamento.isPresent()) {
		return ResponseEntity.ok(optionalApartamento.get());
	} else {
		return ResponseEntity.notFound().build();
	}	
	}
	
	@PostMapping
	public ResponseEntity<ApartamentoId> criar(@RequestBody CriarApartamento comando)throws SQLException{
		Optional<ApartamentoId> optionalApartamentoId = service.executar(comando);
		if(optionalApartamentoId.isPresent()) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(optionalApartamentoId.get()).toUri();
			return ResponseEntity.created(location).build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PutMapping
	public ResponseEntity<ApartamentoId> alterar(@RequestBody EditarApartamento comando)throws SQLException{
		Optional<ApartamentoId> optionalApartamentoId = service.alterar(comando);
		if(optionalApartamentoId.isPresent()) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(optionalApartamentoId.get()).toUri();
			return ResponseEntity.created(location).build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> excluir(@PathVariable ApartamentoId id)throws TimeoutException, NullPointerException,  BadHttpRequest{
		if (verificaApartamentoExistente(id)) {
			Optional<String> optionalApartamento =  service.excluir(id);
			return ResponseEntity.ok(optionalApartamento.get());
		}
		throw new NullPointerException("Este apartamento não foi encontrado");
	}

	
	private boolean verificaApartamentoExistente(ApartamentoId id) throws NullPointerException {
		if (!service.encontrar(id).isPresent()) {
			return false;
		}
		else {
			return true;
		}
	}

}
