package org.otus.akurus.repository;

import org.springframework.data.repository.CrudRepository;
import org.otus.akurus.model.User;

import java.util.List;

public interface UsersRepository extends CrudRepository<User, Integer> {
    List<User> findAll();
}
