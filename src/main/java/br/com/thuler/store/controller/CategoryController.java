package br.com.thuler.store.controller;

import br.com.thuler.store.model.entities.Category;
import br.com.thuler.store.model.exceptions.NotFoundException;
import br.com.thuler.store.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Integer id){
        try{
            return ResponseEntity.ok().body(categoryService.findById(id));
        }catch(NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAll(){
        try{
            return ResponseEntity.ok().body(categoryService.findAll());
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<Category> update(@RequestBody Category category){
        try{
            return ResponseEntity.ok().body(categoryService.update(category));
        }catch(NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        try{
            categoryService.delete(id);
            return ResponseEntity.noContent().build();
        }catch(NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

}
