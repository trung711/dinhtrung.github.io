package com.example.demo.service.impl;

import com.example.demo.dto.response.lichHoc.LichHocRes;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.LichHocService;
import com.example.demo.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LichHocImpl implements LichHocService {

    @Autowired
    TuanHocRepository tuanHocRepository;
    @Autowired
    KipHocRepository kipHocRepository;
    @Autowired
    NgayHocRepository ngayHocRepository;
    @Autowired
    PhongHocRepository phongHocRepository;
    @Autowired
    ThanhVienRepository thanhVienRepository;
    @Autowired
    LichHocRepository lichHocRepository;
    @Autowired
    LopHocPhanRepository lopHocPhanRepository;

    @Override
    public LichHocRes getLichHocListByLopHocPhanID(int idLopHocPhan) throws ApplicationException {
        List<LichHoc> lichHocList = lichHocRepository.getLichHocForHocPhan(idLopHocPhan);

        if (lichHocList.isEmpty()){
            throw new ApplicationException("ERR_0000001");
        }

        LichHocRes lichHocRes = new LichHocRes();

        for (LichHoc lichHoc:
             lichHocList) {
            KipHoc kipHoc = kipHocRepository.getById(lichHoc.getTblkiphocID());
            lichHoc.setKipHoc(kipHoc);

            TuanHoc tuanHoc = tuanHocRepository.getById(lichHoc.getTbltuanhocID());
            lichHoc.setTuanHoc(tuanHoc);

            NgayHoc ngayHoc = ngayHocRepository.getById(lichHoc.getTblngayhocID());
            lichHoc.setNgayHoc(ngayHoc);

            PhongHoc phongHoc = phongHocRepository.getById(lichHoc.getTblphonghocID());
            lichHoc.setPhongHoc(phongHoc);

            ThanhVien thanhVien = thanhVienRepository.getThanhVienBYId(lichHoc.getTblgiangvienID());
            lichHoc.setGiangVien(thanhVien);

            LopHocPhan lopHocPhan = lopHocPhanRepository.getLopHocPhanById(lichHoc.getIDtbllophocphan());
            lichHoc.setLopHocPhan(lopHocPhan);
        }
        lichHocRes.setLichHocList(lichHocList);
        return lichHocRes;
    }
}
