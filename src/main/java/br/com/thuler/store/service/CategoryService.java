package br.com.thuler.store.service;

import br.com.thuler.store.model.entities.Category;
import br.com.thuler.store.model.exceptions.NotFoundException;
import br.com.thuler.store.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

        if(foundCategory.isPresent()){
            return foundCategory.get();
        }else{
            throw new NotFoundException("category not found...");
        }

    }

    public List<Category> findAll(){

        List<Category> categories = categoryRepository.findAll();

        if(!categories.isEmpty()){
            return categories;
        }else{
            throw new NotFoundException("There are no categories...");
        }

    }

    public Category update(Category category){

        Optional<Category> foundCategory = categoryRepository.findById(category.getId());

        if(foundCategory.isPresent()){
            return categoryRepository.save(category);
        }else{
            throw new NotFoundException("category not found...");
        }

    }

    public void delete(Integer id){

        Optional<Category> foundCategory = categoryRepository.findById(id);

        if(foundCategory.isPresent()){
            categoryRepository.delete(foundCategory.get());
        }else{
            throw new NotFoundException("category not found...");
        }

    }

}
