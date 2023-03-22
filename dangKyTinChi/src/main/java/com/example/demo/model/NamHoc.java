package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tblnamhoc")
@Data
public class NamHoc extends BaseEntity{
    @Column(nullable = false)
    private String ten;
    private String mota;

}
