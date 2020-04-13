package br.net.digitalzone.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.net.digitalzone.workshopmongo.domain.Post;
import br.net.digitalzone.workshopmongo.resources.util.URL;
import br.net.digitalzone.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
	
	@Autowired
	private PostService service;
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET) // or @GetMapping
	public ResponseEntity<Post> findById(@PathVariable String id) {
		
		Post obj = service.findById(id);

		return ResponseEntity.ok().body(obj);

	}
	
	@RequestMapping(value = "/titlesearch",method = RequestMethod.GET) // or @GetMapping
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
		
		text = URL.decodeParam(text);
		
		List<Post> list = service.findByTitle(text);

		return ResponseEntity.ok().body(list);

	}

}
