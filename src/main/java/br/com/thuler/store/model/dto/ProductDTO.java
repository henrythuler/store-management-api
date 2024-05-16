package br.com.thuler.store.model.dto;

import java.util.HashSet;
import java.util.Set;

public class ProductDTO {

    private String name;
    private String description;
    private Double price;
    private String imageURL;
    private Set<Integer> categoriesIds = new HashSet<>();

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public Set<Integer> getCategoriesIds() {
        return categoriesIds;
    }

}
