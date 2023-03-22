package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tblhocki")
@Data
public class HocKi extends BaseEntity{
    @Column(nullable = false)
    private String ten;
    private String mota;
}
