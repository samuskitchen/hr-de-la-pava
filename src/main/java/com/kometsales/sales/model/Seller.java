package com.kometsales.sales.model;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.math.BigDecimal;

@Entity
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @Column(precision = 6, scale = 3)
    private Float commissionPercentage;

    private String avatar;

    @Column(precision = 16, scale = 2)
    private BigDecimal currentCommissions;

    public Seller() {
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Float getCommissionPercentage() {
        return commissionPercentage;
    }

    public void setCommissionPercentage(Float commissionPercentage) {
        this.commissionPercentage = commissionPercentage;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public BigDecimal getCurrentCommissions() {
        return currentCommissions;
    }

    public void setCurrentCommissions(BigDecimal currentCommissions) {
        this.currentCommissions = currentCommissions;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", commissionPercentage=" + commissionPercentage +
                ", avatar='" + avatar + '\'' +
                ", currentCommissions=" + currentCommissions +
                '}';
    }
}
