package br.com.thuler.store.service;

import br.com.thuler.store.exceptions.DatabaseException;
import br.com.thuler.store.model.entities.Category;
import br.com.thuler.store.exceptions.NotFoundException;
import br.com.thuler.store.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category create(Category category){
        return categoryRepository.save(category);
    }

    public Category findById(Integer id){

        Optional<Category> foundCategory = categoryRepository.findById(id);

        return foundCategory.orElseThrow(() -> new NotFoundException("Category"));

    }

    public List<Category> findAll(){

        List<Category> categories = categoryRepository.findAll();

        if(!categories.isEmpty()){
            return categories;
        }else{
            throw new NotFoundException("Categories");
        }

    }

    public Category update(Category category){

        try {
            Category foundCategory = categoryRepository.getReferenceById(category.getId());
            foundCategory.setName(category.getName());

            return categoryRepository.save(foundCategory);
        }catch (EntityNotFoundException e) {
            throw new NotFoundException("Category");
        }

    }

    public void delete(Integer id){

        try{
            if(!categoryRepository.existsById(id)) throw new NotFoundException("Category");
            categoryRepository.deleteById(id);
        }catch(DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }

    }

}
