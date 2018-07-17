package condominium.com.usuario;

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

import comando.CriarUsuario;
import javassist.tools.web.BadHttpRequest;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService service;
	
	@GetMapping("/{id}")
		public ResponseEntity<Usuario> buscarId(@PathVariable UsuarioId id){
		System.out.println(id);
		Optional<Usuario> optionalUsuario = service.encontrarid(id);
		if(optionalUsuario.isPresent()) {
			return ResponseEntity.ok(optionalUsuario.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<UsuarioId> inserir(@RequestBody CriarUsuario comando) throws SQLException{
		if(System.currentTimeMillis()%2 == 0) {
			throw new SQLException("Deu Ruim!!!");
		}
		Optional<UsuarioId> optionalUsuarioId = service.executar(comando);
		if(optionalUsuarioId.isPresent()) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(optionalUsuarioId.get()).toUri();
			return ResponseEntity.created(location).build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	
	@PutMapping
	public ResponseEntity<UsuarioId> alterar(@RequestBody EditarUsuario comando)throws SQLException{
		Optional<UsuarioId> optionalUsuarioId = service.alterar(comando);
		if(optionalUsuarioId.isPresent()) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(optionalUsuarioId.get()).toUri();
			return ResponseEntity.created(location).build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> excluir(@PathVariable UsuarioId id)throws TimeoutException, NullPointerException,  BadHttpRequest{
		if (verificaUsuarioExistente(id)) {
			Optional<String> optionalUsuario =  service.excluir(id);
			return ResponseEntity.ok(optionalUsuario.get());
		}
		throw new NullPointerException("Este usuário não foi encontrado");
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> buscarTodosUsuarios() {
		Optional<List<Usuario>> optionalUsuario = service.encontrar();
		return ResponseEntity.ok(optionalUsuario.get());	
	}

	
	private boolean verificaUsuarioExistente(UsuarioId id) throws NullPointerException {
		if (!service.encontrarid(id).isPresent()) {
			return false;
		}
		else {
			return true;
		}
	}
}
	
	

