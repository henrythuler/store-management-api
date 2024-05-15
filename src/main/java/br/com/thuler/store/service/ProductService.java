package br.com.thuler.store.service;

import br.com.thuler.store.model.entities.Product;
import br.com.thuler.store.model.exceptions.NotFoundException;
import br.com.thuler.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

        if(foundProduct.isPresent()){
            return foundProduct.get();
        }else{
            throw new NotFoundException("Product not found...");
        }

    }

    public List<Product> findAll(){

        List<Product> products = productRepository.findAll();

        if(!products.isEmpty()){
            return products;
        }else{
            throw new NotFoundException("There are no products...");
        }

    }

    public Product update(Product product){

        Optional<Product> foundProduct = productRepository.findById(product.getId());

        if(foundProduct.isPresent()){
            return productRepository.save(product);
        }else{
            throw new NotFoundException("Product not found...");
        }

    }

    public void delete(Integer id){

        Optional<Product> foundProduct = productRepository.findById(id);

        if(foundProduct.isPresent()){
            productRepository.delete(foundProduct.get());
        }else{
            throw new NotFoundException("Product not found...");
        }

    }

}
