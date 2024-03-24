package com.tsaidenis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NamedQuery;

@NoArgsConstructor
@AllArgsConstructor
//@NamedQuery(name = "Products.findByCategoryId", query = "from Products where category_id = :categoryId")

@Data
@Entity
public class Products {
    @EmbeddedId
    private ProductId id;
    private Double price;
    @Enumerated(EnumType.STRING)
    private Condition condition;
    @Embedded
    private  Manufacturer manufacturer;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id")
    private Categories categories;
    @Version
    private int version;

    public Products(ProductId id, Double price, Condition condition, Manufacturer manufacturer, Categories categories) {
        this.id = id;
        this.price = price;
        this.condition = condition;
        this.manufacturer = manufacturer;
        this.categories = categories;
    }
}
