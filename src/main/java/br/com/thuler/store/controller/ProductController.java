package br.com.thuler.store.controller;

import br.com.thuler.store.model.dto.ProductDTO;
import br.com.thuler.store.model.entities.Product;
import br.com.thuler.store.service.CategoryService;
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
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductDTO productDTO){

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImageURL(productDTO.getImageURL());
        productDTO.getCategoriesIds().forEach(id -> product.getCategories().add(categoryService.findById(id)));

        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(productService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity.ok().body(productService.findAll());
    }

    @PutMapping
    public ResponseEntity<Product> update(@RequestBody Product product){
        return ResponseEntity.ok().body(productService.update(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
