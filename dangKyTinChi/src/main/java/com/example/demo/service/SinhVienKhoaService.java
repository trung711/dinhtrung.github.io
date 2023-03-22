package com.example.demo.service;

import com.example.demo.utils.exception.ApplicationException;

public interface SinhVienKhoaService {
    int idSinhVienKhoa(int idSinhVien, int idKhoa) throws ApplicationException;
}
