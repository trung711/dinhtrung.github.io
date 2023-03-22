package com.example.demo.dto.response.dangKiHoc;

import com.example.demo.dto.response.IResponseData;
import com.example.demo.model.DangKiHoc;
import com.example.demo.model.SinhVienKhoa;
import com.example.demo.model.ThanhVien;
import lombok.Data;

import java.util.List;

@Data
public class DangKiHocRes implements IResponseData {
    List<DangKiHoc> dangKiHocList;
    SinhVienKhoa sinhVienKhoa;
}
