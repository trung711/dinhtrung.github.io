package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbldiachi")
@Data
public class DiaChi extends BaseEntity{
    private String sonha;
    private String toanha;
    private String xompho;
    @Column(nullable = false)
    private String phuongxa;
    @Column(nullable = false)
    private String quanhuyen;
    @Column(nullable = false)
    private String tinhthanh;
}
