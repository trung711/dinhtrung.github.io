package com.example.demo.service;

import com.example.demo.dto.response.lichHoc.LichHocRes;
import com.example.demo.model.LichHoc;
import com.example.demo.utils.exception.ApplicationException;

import java.util.List;

public interface LichHocService {
    LichHocRes getLichHocListByLopHocPhanID(int idLopHocPhan) throws ApplicationException;
}
