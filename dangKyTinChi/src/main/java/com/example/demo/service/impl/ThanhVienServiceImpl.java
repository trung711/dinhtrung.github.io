package com.example.demo.service.impl;

import com.example.demo.dto.request.BaseRequestData;
import com.example.demo.dto.request.thanhvien.ThanhVienReq;
import com.example.demo.dto.response.thanhvien.ThanhVienRes;
import com.example.demo.model.BoMon;
import com.example.demo.model.Khoa;
import com.example.demo.model.SinhVienKhoa;
import com.example.demo.model.ThanhVien;
import com.example.demo.repository.*;
import com.example.demo.service.ThanhVienService;
import com.example.demo.utils.RoleConstants;
import com.example.demo.utils.exception.ApplicationException;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ThanhVienServiceImpl implements ThanhVienService {
    @Autowired
    ThanhVienRepository thanhVienRepository;

    @Autowired
    DiaChiRepository diaChiRepository;

    @Autowired
    BoMonRepository boMonRepository;

    @Autowired
    KhoaRepository khoaRepository;

    @Autowired
    SinhVienKhoaRepository sinhVienKhoaRepository;

    @Override
    public ThanhVienRes dangNhap(BaseRequestData baseRequestData) throws ApplicationException {
        ThanhVienReq thanhVienReq = (ThanhVienReq) baseRequestData.getWsRequest();
        ThanhVienRes thanhVienRes = new ThanhVienRes();
        ThanhVien thanhVien = thanhVienRepository.dangNhap(thanhVienReq.getUsername(),thanhVienReq.getPassword());
        if(thanhVien == null)
            throw new ApplicationException("ERR_0000004");
        thanhVien.setDiaChi(diaChiRepository.getDiaChi(thanhVien.getTbldiachiID()));
        if(thanhVien.getTblbomonID() != 0) {
            BoMon boMon = boMonRepository.getBoMonbyId(thanhVien.getTblbomonID());
            boMon.setKhoa(khoaRepository.getKhoaById(boMon.getTblkhoaID()));
            thanhVien.setBoMon(boMon);
        }
        thanhVienRes.setThanhVien(thanhVien);
        return thanhVienRes;
    }

    @Override
    public ThanhVienRes doiMatKhau(BaseRequestData baseRequestData) throws ApplicationException {
        ThanhVienReq thanhVienReq = (ThanhVienReq) baseRequestData.getWsRequest();
        ThanhVienRes thanhVienRes = new ThanhVienRes();
        thanhVienRepository.doiMatKhau(thanhVienReq.getId(),thanhVienReq.getNewPassword());
        return thanhVienRes;
    }

    @Override
    public ThanhVienRes getListSinhVien(BaseRequestData baseRequestData) throws ApplicationException {
        ThanhVienReq thanhVienReq = (ThanhVienReq) baseRequestData.getWsRequest();
        ThanhVienRes thanhVienRes = new ThanhVienRes();
        List<ThanhVien> list = thanhVienRepository.getListSinhVien();
        if(list.isEmpty())
            throw new ApplicationException("ERR_0000001");
        for(ThanhVien i : list) {
            SinhVienKhoa sinhVienKhoa = sinhVienKhoaRepository.getSinhVienKhoaBySinhVienID(i.getId());
            Khoa khoa = khoaRepository.getKhoaById(sinhVienKhoa.getIDkhoa());
            sinhVienKhoa.setKhoa(khoa);
            i.setSinhVienKhoa(sinhVienKhoa);
        }
        thanhVienRes.setListThanhVien(list);
        return thanhVienRes;
    }

    @Override
    public ThanhVienRes getSinhVienByMSV(BaseRequestData baseRequestData) throws ApplicationException {
        ThanhVienReq thanhVienReq = (ThanhVienReq) baseRequestData.getWsRequest();
        ThanhVienRes thanhVienRes = new ThanhVienRes();
        List<ThanhVien> list = thanhVienRepository.getSinhVienByMSV(thanhVienReq.getMasv());
        if(list.isEmpty())
            throw new ApplicationException("ERR_0000001");
        for(ThanhVien i : list) {
            SinhVienKhoa sinhVienKhoa = sinhVienKhoaRepository.getSinhVienKhoaBySinhVienID(i.getId());
            Khoa khoa = khoaRepository.getKhoaById(sinhVienKhoa.getIDkhoa());
            sinhVienKhoa.setKhoa(khoa);
            i.setSinhVienKhoa(sinhVienKhoa);
        }
        thanhVienRes.setListThanhVien(list);
        return thanhVienRes;
    }

    @Override
    public ThanhVienRes themSinhVien(BaseRequestData baseRequestData) throws ApplicationException {
        ThanhVienReq thanhVienReq = (ThanhVienReq) baseRequestData.getWsRequest();
        ThanhVienRes thanhVienRes = new ThanhVienRes();
        ThanhVien thanhVien = new ThanhVien();
        BeanUtils.copyProperties(thanhVienReq,thanhVien);
        if(thanhVienRepository.isExisted(thanhVien.getUsername(),thanhVien.getMasv()) != null)
            throw new ApplicationException("ERR_0000002");
        SinhVienKhoa sinhVienKhoa = new SinhVienKhoa();
        sinhVienKhoa.setDanghoc(true); //TODO
        sinhVienKhoa.setNienkhoa(thanhVienReq.getNienKhoa());
        sinhVienKhoa.setIDkhoa(thanhVienReq.getTblkhoaID());
        sinhVienKhoa.setTblsinhvienID(thanhVienRepository.save(thanhVien).getId());
        sinhVienKhoaRepository.save(sinhVienKhoa);

        return thanhVienRes;
    }

    @Override
    public ThanhVienRes suaSinhVien(BaseRequestData baseRequestData) throws ApplicationException {
        ThanhVienReq thanhVienReq = (ThanhVienReq) baseRequestData.getWsRequest();
        ThanhVienRes thanhVienRes = new ThanhVienRes();
        if(thanhVienRepository.getThanhVienById(thanhVienReq.getId()) == null)
            throw new ApplicationException("ERR_0000001");
        sinhVienKhoaRepository.suaSinhVienKhoa(thanhVienReq.getNienKhoa(),thanhVienReq.getTblkhoaID(),thanhVienReq.getId());
        thanhVienRepository.suaThanhVien(thanhVienReq.getEmail(),thanhVienReq.getSdt(),thanhVienReq.getId());

        return thanhVienRes;
    }
}
