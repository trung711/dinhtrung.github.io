package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tbltruong")
@Data
public class Truong extends BaseEntity{
    @Column(nullable = false)
    private String ten;
    private String mota;
    @Column(nullable = false)
    private int diachiId;

    @Transient
    private DiaChi diaChi;
}
