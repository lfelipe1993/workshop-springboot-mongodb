package br.net.digitalzone.workshopmongo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.net.digitalzone.workshopmongo.domain.Post;
import br.net.digitalzone.workshopmongo.domain.User;
import br.net.digitalzone.workshopmongo.repository.PostRepository;
import br.net.digitalzone.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;

	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}


}
