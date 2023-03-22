package com.example.demo.service;

import com.example.demo.dto.request.BaseRequestData;
import com.example.demo.dto.request.monhoc.MonHocReq;
import com.example.demo.dto.response.monhoc.MonHocRes;
import com.example.demo.utils.exception.ApplicationException;

public interface MonHocService {
    MonHocRes getListMonHoc() throws ApplicationException;
    MonHocRes addMonHoc(BaseRequestData baseRequestData) throws  ApplicationException;
    MonHocRes deleteMonHoc(BaseRequestData<MonHocReq> baseRequestData) throws ApplicationException;
}
