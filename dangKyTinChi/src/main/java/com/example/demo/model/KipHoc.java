package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tblkiphoc")
@Data
public class KipHoc extends BaseEntity{
    @Column(nullable = false)
    private String ten;
    private String mota;
}
