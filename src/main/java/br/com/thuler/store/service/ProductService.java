package br.com.thuler.store.service;

import br.com.thuler.store.model.entities.Product;
import br.com.thuler.store.exceptions.NotFoundException;
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

        Optional<Product> foundProduct = productRepository.findById(product.getId());

        if(foundProduct.isPresent()){
            return productRepository.save(product);
        }else{
            throw new NotFoundException("Product");
        }

    }

    public void delete(Integer id){

        Optional<Product> foundProduct = productRepository.findById(id);

        productRepository.delete(foundProduct.orElseThrow(() -> new NotFoundException("Product")));

    }

}
