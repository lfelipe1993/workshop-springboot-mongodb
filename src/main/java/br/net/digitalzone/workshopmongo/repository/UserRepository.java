package br.net.digitalzone.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.net.digitalzone.workshopmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
