package com.example.demo.service.impl;

import com.example.demo.dto.request.BaseRequestData;
import com.example.demo.dto.request.khoa.KhoaReq;
import com.example.demo.dto.response.khoa.KhoaRes;
import com.example.demo.model.Khoa;
import com.example.demo.model.SinhVienKhoa;
import com.example.demo.repository.KhoaRepository;
import com.example.demo.repository.SinhVienKhoaRepository;
import com.example.demo.repository.ThanhVienRepository;
import com.example.demo.service.KhoaService;
import com.example.demo.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KhoaServiceImpl implements KhoaService {
    @Autowired
    KhoaRepository khoaRepository;
    @Autowired
    SinhVienKhoaRepository sinhVienKhoaRepository;
    @Autowired
    ThanhVienRepository thanhVienRepository;
    @Override
    public KhoaRes getListKhoa(int id) throws ApplicationException {
        KhoaRes khoaRes = new KhoaRes();

        List<SinhVienKhoa> sinhVienKhoaList = sinhVienKhoaRepository.getSVKhoaByThanhVienId(id);

        if(sinhVienKhoaList.isEmpty()){
            throw new ApplicationException("ERR_0000001");
        }

        List<Khoa> khoaList = new ArrayList<>();
        for(SinhVienKhoa svk : sinhVienKhoaList){
            Khoa khoa = khoaRepository.getKhoaById(svk.getIDkhoa());
            if(khoa != null){
                khoaList.add(khoa);
            }
        }

        if(khoaList.isEmpty()){
            throw  new ApplicationException("ERR_0000001");
        }

        khoaRes.setListKhoa(khoaList);
        return khoaRes;
    }

    @Override
    public KhoaRes getListKhoa() throws ApplicationException {
        KhoaRes khoaRes = new KhoaRes();
        List<Khoa> list = khoaRepository.getListKhoa();
        if(list == null)
            throw new ApplicationException("ERR_0000001");
        khoaRes.setListKhoa(list);
        return khoaRes;
    }
}
