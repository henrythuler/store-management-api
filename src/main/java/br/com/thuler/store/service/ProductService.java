package br.com.thuler.store.service;

import br.com.thuler.store.exceptions.DatabaseException;
import br.com.thuler.store.model.entities.Product;
import br.com.thuler.store.exceptions.NotFoundException;
import br.com.thuler.store.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product create(Product product){
        return productRepository.save(product);
    }

    public Product findById(Integer id){

        Optional<Product> foundProduct = productRepository.findById(id);

        return foundProduct.orElseThrow(() -> new NotFoundException("Product"));

    }

    public List<Product> findAll(){

        List<Product> products = productRepository.findAll();

        if(!products.isEmpty()){
            return products;
        }else{
            throw new NotFoundException("Products");
        }

    }

    public Product update(Product product){

        try{
            Product foundProduct = productRepository.getReferenceById(product.getId());

            foundProduct.setName(product.getName());
            foundProduct.setDescription(product.getDescription());
            foundProduct.setPrice(product.getPrice());
            foundProduct.setImageURL(product.getImageURL());

            return productRepository.save(foundProduct);
        }catch(EntityNotFoundException e){
            throw new NotFoundException("Product");
        }

    }

    public void deleteById(Integer id){

        try{
            if(!productRepository.existsById(id)) throw new NotFoundException("Product");
            productRepository.deleteById(id);
        }catch(DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }

    }

}
