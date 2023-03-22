package com.example.demo.service.impl;

import com.example.demo.dto.request.BaseRequestData;
import com.example.demo.dto.request.monhoc.MonHocReq;
import com.example.demo.dto.response.monhoc.MonHocRes;
import com.example.demo.model.BoMon;
import com.example.demo.model.Khoa;
import com.example.demo.model.MonHoc;
import com.example.demo.repository.BoMonRepository;
import com.example.demo.repository.KhoaRepository;
import com.example.demo.repository.MonHocKiHocRepository;
import com.example.demo.repository.MonHocRepository;
import com.example.demo.service.MonHocService;
import com.example.demo.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonHocServiceImpl implements MonHocService {
    @Autowired
    MonHocRepository monHocRepository;
    @Autowired
    BoMonRepository boMonRepository;
    @Autowired
    KhoaRepository khoaRepository;
    @Autowired
    MonHocKiHocRepository monHocKiHocRepository;

    @Override
    public MonHocRes getListMonHoc() throws ApplicationException {
        MonHocRes monHocRes = new MonHocRes();
        List<MonHoc> listMonHoc = monHocRepository.getListMonHoc();
        if (listMonHoc.isEmpty())
            throw new ApplicationException("ERR_0000001");
        for (MonHoc i : listMonHoc) {
            BoMon bomon = boMonRepository.getBoMonbyId(i.getIDtblbomon());
            Khoa khoa = khoaRepository.getKhoaById(bomon.getTblkhoaID());
            bomon.setKhoa(khoa);
            i.setBoMon(bomon);
        }
        monHocRes.setListMonHoc(listMonHoc);
        return monHocRes;
    }

    @Override
    public MonHocRes addMonHoc(BaseRequestData baseRequestData) throws ApplicationException {
        MonHocRes monHocRes = new MonHocRes();
        MonHocReq monHocReq = (MonHocReq) baseRequestData.getWsRequest();
        MonHoc monHoc = new MonHoc();
        BeanUtils.copyProperties(monHocReq,monHoc);
        if(monHocRepository.getMonHocByMaMonHoc(monHoc.getMamonhoc()) != null) {
            throw new ApplicationException("ERR_0000002");
        }
        monHocRepository.save(monHoc);
        return monHocRes;
    }

    @Override
    public MonHocRes deleteMonHoc(BaseRequestData<MonHocReq> baseRequestData) throws ApplicationException {
        MonHocRes monHocRes = new MonHocRes();
        MonHocReq monHocReq = (MonHocReq) baseRequestData.getWsRequest();
        if(monHocKiHocRepository.getMonHocKiHocByMonHocId(monHocReq.getId()) != null)
            throw new ApplicationException("ERR_0000003");
        // TODO
        MonHoc monHoc = new MonHoc();
        BeanUtils.copyProperties(monHocReq,monHoc);
        monHocRepository.delete(monHoc);
        return monHocRes;
    }
}
