package com.example.demo.service.impl;

import com.example.demo.dto.request.BaseRequestData;
import com.example.demo.dto.request.lophocphan.LopHocPhanReq;
import com.example.demo.dto.response.lopHocPhan.LopHocPhanRes;
import com.example.demo.model.LopHocPhan;
import com.example.demo.model.MonHocKiHoc;
import com.example.demo.repository.DangKiHocRepository;
import com.example.demo.repository.LopHocPhanRepository;
import com.example.demo.repository.MonHocKiHocRepository;
import com.example.demo.service.LopHocPhanService;
import com.example.demo.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LopHocPhanServiceImpl implements LopHocPhanService {

    @Autowired
    LopHocPhanRepository lopHocPhanRepository;

    @Autowired
    DangKiHocRepository dangKiHocRepository;

    @Autowired
    MonHocKiHocRepository monHocKiHocRepository;

    @Override
    public LopHocPhanRes getListLopHocPhan(BaseRequestData<LopHocPhanReq> baseRequestData) throws ApplicationException {
        LopHocPhanRes lopHocPhanRes = new LopHocPhanRes();
        List<LopHocPhan> list = lopHocPhanRepository.findAll();
        if(list.isEmpty())
            throw new ApplicationException("ERR_0000001");
        lopHocPhanRes.setLopHocPhanList(list);
        return lopHocPhanRes;
    }

    @Override
    public LopHocPhanRes getListLopHocPhanByMonHocKiHoc(BaseRequestData<LopHocPhanReq> baseRequestData) throws ApplicationException {
        LopHocPhanRes lopHocPhanRes = new LopHocPhanRes();
        LopHocPhanReq lopHocPhanReq = baseRequestData.getWsRequest();
        List<LopHocPhan> list = lopHocPhanRepository.getLopHocPhanListByMonHocAndKiHoc(lopHocPhanReq.getTblmonhocId(),lopHocPhanReq.getTblkihocId());
        if(list.isEmpty()) {
            throw new ApplicationException("ERR_0000001");
        }
        lopHocPhanRes.setLopHocPhanList(list);
        return lopHocPhanRes;
    }

    @Override
    public LopHocPhanRes addLopHocPhan(BaseRequestData<LopHocPhanReq> baseRequestData) throws ApplicationException {
        LopHocPhanRes lopHocPhanRes = new LopHocPhanRes();
        LopHocPhanReq lopHocPhanReq = baseRequestData.getWsRequest();
        MonHocKiHoc monHocKiHoc = monHocKiHocRepository.getMonHocKiHocByMonHocAndKiHoc(lopHocPhanReq.getTblmonhocId(),lopHocPhanReq.getTblkihocId());
        if(monHocKiHoc == null) {
            throw new ApplicationException("ERR_0000007");
        }
        LopHocPhan lopHocPhan = new LopHocPhan();
        BeanUtils.copyProperties(lopHocPhanReq,lopHocPhan);
        lopHocPhan.setTblmonhockihocID(monHocKiHoc.getId());
        lopHocPhanRepository.save(lopHocPhan);
        return lopHocPhanRes;
    }

    @Override
    public LopHocPhanRes editLopHocPhan(BaseRequestData<LopHocPhanReq> baseRequestData) throws ApplicationException {
        LopHocPhanRes lopHocPhanRes = new LopHocPhanRes();
        LopHocPhanReq lopHocPhanReq = baseRequestData.getWsRequest();
        MonHocKiHoc monHocKiHoc = monHocKiHocRepository.getMonHocKiHocByMonHocAndKiHoc(lopHocPhanReq.getTblmonhocId(),lopHocPhanReq.getTblkihocId());
        if(monHocKiHoc == null) {
            throw new ApplicationException("ERR_0000007");
        }
        LopHocPhan lopHocPhan = new LopHocPhan();
        BeanUtils.copyProperties(lopHocPhanReq,lopHocPhan);
        lopHocPhan.setTblmonhockihocID(monHocKiHoc.getId());
        lopHocPhanRepository.deleteById(lopHocPhan.getId());
        lopHocPhanRepository.save(lopHocPhan);
        return lopHocPhanRes;
    }

    @Override
    public LopHocPhanRes deleteLopHocPhan(BaseRequestData<LopHocPhanReq> baseRequestData) throws ApplicationException {
        LopHocPhanRes lopHocPhanRes = new LopHocPhanRes();
        LopHocPhanReq lopHocPhanReq = baseRequestData.getWsRequest();
        LopHocPhan lopHocPhan = new LopHocPhan();
        BeanUtils.copyProperties(lopHocPhanReq,lopHocPhan);
        dangKiHocRepository.deleteAllByLopHocPhanId(lopHocPhan.getId());
        lopHocPhanRepository.deleteById(lopHocPhan.getId());
        return lopHocPhanRes;
    }


}
