package br.com.thuler.store.controller;

import br.com.thuler.store.model.entities.Product;
import br.com.thuler.store.model.exceptions.NotFoundException;
import br.com.thuler.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Integer id){
        try{
            return ResponseEntity.ok().body(productService.findById(id));
        }catch(NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        try{
            return ResponseEntity.ok().body(productService.findAll());
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<Product> update(@RequestBody Product product){
        try{
            return ResponseEntity.ok().body(productService.update(product));
        }catch(NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        try{
            productService.delete(id);
            return ResponseEntity.noContent().build();
        }catch(NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

}
