package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tblkhoa")
@Data
public class Khoa extends BaseEntity{
    @Column(nullable = false)
    private String ten;
    private String mota;
    @Column(nullable = false)
    private int truongID;

    @Transient
    private Truong truong;
}
