package com.dbit.model.examples;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@EqualsAndHashCode(exclude = "productType")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
@Entity
//@NamedQueries(
//        @NamedQuery(name = "byName", query = "select new com.dbit.dto.ProductDto(p.name, p.price) from Product p where p.name = :name")
//)
@NamedQuery(name = "byName", query = "select new com.dbit.dto.ProductDto(p.name, p.price) from Product p where p.name = :name")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int price;

//    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "product_type")
    private ProductType productType;

    public Product(String name) {
        this.name = name;
    }
}
