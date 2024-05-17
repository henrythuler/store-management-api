package br.com.thuler.store.service;

import br.com.thuler.store.model.entities.Category;
import br.com.thuler.store.exceptions.NotFoundException;
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

        Optional<Category> foundCategory = categoryRepository.findById(category.getId());

        if(foundCategory.isPresent()){
            return categoryRepository.save(category);
        }else{
            throw new NotFoundException("Category");
        }

    }

    public void delete(Integer id){

        Optional<Category> foundCategory = categoryRepository.findById(id);

        categoryRepository.delete(foundCategory.orElseThrow(() -> new NotFoundException("Category")));

    }

}
