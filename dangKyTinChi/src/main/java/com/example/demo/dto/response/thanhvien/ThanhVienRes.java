package com.example.demo.dto.response.thanhvien;

import com.example.demo.dto.response.IResponseData;
import com.example.demo.model.ThanhVien;
import lombok.Data;

import java.util.List;

@Data
public class ThanhVienRes implements IResponseData {
    ThanhVien thanhVien;
    List<ThanhVien> listThanhVien;
}
