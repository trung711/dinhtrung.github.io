package com.example.demo.service.impl;

import com.example.demo.model.SinhVienKhoa;
import com.example.demo.repository.SinhVienKhoaRepository;
import com.example.demo.service.SinhVienKhoaService;
import com.example.demo.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SinhVienKhoaServiceImpl implements SinhVienKhoaService {

    @Autowired
    SinhVienKhoaRepository sinhVienKhoaRepository;

    @Override
    public int idSinhVienKhoa(int idSinhVien, int idKhoa) throws ApplicationException {
        SinhVienKhoa sinhVienKhoa = sinhVienKhoaRepository.findSinhVienKhoa(idSinhVien, idKhoa);

        if(sinhVienKhoa == null){
            throw  new ApplicationException("ERR_0000001");
        }

        return sinhVienKhoa.getId();
    }
}
