package com.example.demo.service.impl;

import com.example.demo.dto.request.BaseRequestData;
import com.example.demo.dto.response.kihoc.KiHocRes;
import com.example.demo.model.HocKi;
import com.example.demo.model.KiHoc;
import com.example.demo.model.NamHoc;
import com.example.demo.repository.HocKiRepository;
import com.example.demo.repository.KiHocRepository;
import com.example.demo.repository.NamHocRepository;
import com.example.demo.service.KiHocService;
import com.example.demo.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KiHocServiceImpl implements KiHocService {

    @Autowired
    NamHocRepository namHocRepository;
    @Autowired
    HocKiRepository hocKiRepository;
    @Autowired
    KiHocRepository kiHocRepository;
    @Override
    public KiHocRes getListKyHoc() throws ApplicationException {
        KiHocRes kiHocRes = new KiHocRes();
        List<KiHoc> kiHocList = kiHocRepository.getListKiHoc();

        if(kiHocList == null){
            throw new ApplicationException("ERR_0000001");
        }

        for(int i = 0; i < kiHocList.size(); i++){
            KiHoc kiHoc = kiHocList.get(i);
            NamHoc namHoc = namHocRepository.getNamHoc(kiHoc.getTblnamhocID());
            HocKi hocKi = hocKiRepository.getHocKi(kiHoc.getTblhockiID());

            kiHoc.setHocKi(hocKi);
            kiHoc.setNamHoc(namHoc);
        }

        kiHocRes.setListKiHoc(kiHocList);
        return kiHocRes;
    }
}
