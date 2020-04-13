package br.net.digitalzone.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.net.digitalzone.workshopmongo.domain.User;
import br.net.digitalzone.workshopmongo.dto.UserDTO;
import br.net.digitalzone.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;

	@RequestMapping(method = RequestMethod.GET) // or @GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {

		List<User> list = service.findAll();
		List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listDTO);

	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET) // or @GetMapping
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		
		User obj = service.findById(id);

		return ResponseEntity.ok().body(new UserDTO(obj));

	}
	
	@PostMapping
	//@RequestMapping(method = RequestMethod.POST) 
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDTO) {
		
		User obj = service.fromDTO(objDTO);
		
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();

	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE) // or @GetMapping
	public ResponseEntity<Void> delete(@PathVariable String id) {
		
		service.delete(id);

		return ResponseEntity.noContent().build();

	}
	
	@PutMapping(value = "/{id}")
	//@RequestMapping(method = RequestMethod.POST) 
	public ResponseEntity<Void> update(@RequestBody UserDTO objDTO, @PathVariable String id) {
		
		User obj = service.fromDTO(objDTO);
		obj.setId(id);
		
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();

	}

}
