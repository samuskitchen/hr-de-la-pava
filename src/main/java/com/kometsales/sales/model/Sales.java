package com.kometsales.sales.model;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;

    @Column(name = "id_seller")
    private Long seller;

    private Integer product;

    @Column(precision = 16, scale = 2)
    private BigDecimal rode;

    public Sales() {
    }

    public Sales(LocalDateTime date, Long seller, Integer product, BigDecimal rode) {
        this.date = date;
        this.seller = seller;
        this.product = product;
        this.rode = rode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getSeller() {
        return seller;
    }

    public void setSeller(Long seller) {
        this.seller = seller;
    }

    public Integer getProduct() {
        return product;
    }

    public void setProduct(Integer product) {
        this.product = product;
    }

    public BigDecimal getRode() {
        return rode;
    }

    public void setRode(BigDecimal rode) {
        this.rode = rode;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "id=" + id +
                ", date=" + date +
                ", seller=" + seller +
                ", product=" + product +
                ", rode=" + rode +
                '}';
    }
}
