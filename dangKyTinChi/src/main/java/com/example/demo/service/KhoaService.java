package com.example.demo.service;

import com.example.demo.dto.request.BaseRequestData;
import com.example.demo.dto.response.khoa.KhoaRes;
import com.example.demo.utils.exception.ApplicationException;

public interface KhoaService {
    KhoaRes getListKhoa(int id) throws ApplicationException;
    KhoaRes getListKhoa() throws ApplicationException;
}
