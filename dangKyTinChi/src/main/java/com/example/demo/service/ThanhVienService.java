package com.example.demo.service;

import com.example.demo.dto.request.BaseRequestData;
import com.example.demo.dto.response.thanhvien.ThanhVienRes;
import com.example.demo.utils.exception.ApplicationException;

public interface ThanhVienService {
    ThanhVienRes dangNhap(BaseRequestData baseRequestData) throws ApplicationException;
    ThanhVienRes doiMatKhau(BaseRequestData baseRequestData) throws ApplicationException;
    ThanhVienRes getListSinhVien(BaseRequestData baseRequestData) throws ApplicationException;
    ThanhVienRes getSinhVienByMSV(BaseRequestData baseRequestData) throws ApplicationException;
    ThanhVienRes themSinhVien(BaseRequestData baseRequestData) throws ApplicationException;
    ThanhVienRes suaSinhVien(BaseRequestData baseRequestData) throws ApplicationException;
}
