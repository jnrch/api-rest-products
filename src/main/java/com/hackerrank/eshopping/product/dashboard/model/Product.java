package com.hackerrank.eshopping.product.dashboard.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * The type Product.
 *
 * @author Jonathan Rojas
 */
@Entity
@Table(name = "products")
@EntityListeners(AuditingEntityListener.class)
@JsonPropertyOrder({"id", "name", "category", "retailPrice", "discountedPrice", "availability"})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "retail_price", nullable = false)
    @JsonProperty("retail_price")
    private Double retailPrice;

    @Column(name = "discounted_price", nullable = false)
    @JsonProperty("discounted_price")
    private Double discountedPrice;

    @Column(name = "availability", nullable = false)
    private Boolean availability;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(Double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id" + id +
                ", name = '" + name + '\'' +
                ", category = '" + category + '\'' +
                ", retailPrice = '" + retailPrice + '\'' +
                ", discountedPrice = '" + discountedPrice + '\'' +
                ", availability = '" + availability + '\'' +
                '}';

    }
}
