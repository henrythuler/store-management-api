package br.com.thuler.store.repository;

import br.com.thuler.store.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {}
