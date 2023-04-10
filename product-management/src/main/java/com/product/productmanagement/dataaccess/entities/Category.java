package com.product.productmanagement.dataaccess.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String description;

    private String slug;

    @Column(name = "date_creation")
    private Date dateCreation;

    @Column(name = "date_modification")
    private Date dateModification;

    private Boolean supprimer;

    public Category(Long category_id) {
        this.id = category_id;
    }
}
