package com.example.demo.service;

import com.example.demo.dto.request.BaseRequestData;
import com.example.demo.dto.request.lophocphan.LopHocPhanReq;
import com.example.demo.dto.response.lopHocPhan.LopHocPhanRes;
import com.example.demo.utils.exception.ApplicationException;

public interface LopHocPhanService {
    LopHocPhanRes getListLopHocPhan(BaseRequestData<LopHocPhanReq> baseRequestData) throws ApplicationException;
    LopHocPhanRes getListLopHocPhanByMonHocKiHoc(BaseRequestData<LopHocPhanReq> baseRequestData) throws ApplicationException;
    LopHocPhanRes addLopHocPhan(BaseRequestData<LopHocPhanReq> baseRequestData) throws ApplicationException;
    LopHocPhanRes editLopHocPhan(BaseRequestData<LopHocPhanReq> baseRequestData) throws ApplicationException;
    LopHocPhanRes deleteLopHocPhan(BaseRequestData<LopHocPhanReq> baseRequestData) throws ApplicationException;
}
