package com.example.jvspringbootdockerstart.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "product")
@SQLDelete(sql = "UPDATE monitored_endpoint SET deleted = true WHERE id = ?")
@Where(clause = "deleted=false")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "deleted", nullable = false)
    private Boolean deleted = Boolean.FALSE;
}
