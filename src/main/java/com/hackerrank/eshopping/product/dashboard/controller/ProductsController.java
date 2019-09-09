package com.hackerrank.eshopping.product.dashboard.controller;

import com.hackerrank.eshopping.product.dashboard.exception.ResourceBadRequestException;
import com.hackerrank.eshopping.product.dashboard.exception.ResourceNotFoundException;
import com.hackerrank.eshopping.product.dashboard.model.Product;
import com.hackerrank.eshopping.product.dashboard.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductsController {

   @Autowired
    private ProductRepository productRepository;

    /**
     * Get all products list.
     *
     * @return the list
     */

    @GetMapping("/")
    public List<Product> findAllProducts() { return productRepository.findAll();}

    /**
     * Gets products by id.
     *
     * @param productId the product id
     * @return the products by id
     * @throws ResourceNotFoundException the resource not found exception
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductsById(@PathVariable(value = "id") Long productId)
            throws ResourceNotFoundException {
        Product product =
                productRepository
                        .findById(productId)
                        .orElseThrow(() -> new ResourceNotFoundException("Product not found on:: " + productId));
        return ResponseEntity.ok().body(product);
    }

    /*
    * Gets products by category
    * @param category
    * */
    @GetMapping("/category/{category}")
    public List<Product> findProductsByCategory(@PathVariable(value = "category")String category)
    {
        List<Product> product = productRepository.findByCategory(category);
        //return ResponseEntity.ok().body(product);
        return product;
    }

    /**
     * Gets products by id.
     *
     * @param productId the product id
     * @return the products by id
     * @throws ResourceNotFoundException the resource not found exception
     */

    @GetMapping("/category/{category}/availability/{availability}")
    public List<Product> findProductsByCategoryAndAvailability(@PathVariable(value = "category")String category, @PathVariable(value = "availability")Boolean availability)
    {
        List<Product> product = productRepository.findProductsByCategoryAndAvailability(category, availability);
        //return ResponseEntity.ok().body(product);
        return product;
    }
    /**
     * Create product product.
     *
     * @param product the product
     * @return the product
     */
    @PostMapping("/")
    public Product createProduct(@Valid @RequestBody Product product) {
        return productRepository.save(product);
    }

    /**
     * Update product response entity.
     *
     * @param productId the product id
     * @param productDetails the product details
     * @return the response entity
     * @throws ResourceNotFoundException the resource not found exception
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable(value = "id") Long productId, @Valid @RequestBody Product productDetails)
            throws ResourceBadRequestException {

        Product product =
                productRepository
                        .findById(productId)
                        .orElseThrow(() -> new ResourceBadRequestException("Product not found on :: " + productId));

        product.setName(productDetails.getName());
        product.setCategory(productDetails.getCategory());
        product.setRetailPrice(productDetails.getRetailPrice());
        product.setDiscountedPrice(productDetails.getDiscountedPrice());
        product.setAvailability(productDetails.getAvailability());
        final Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }
}
