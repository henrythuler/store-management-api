package br.com.thuler.store.repository;

import br.com.thuler.store.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {}
