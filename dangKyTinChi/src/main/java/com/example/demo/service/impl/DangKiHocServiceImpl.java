package com.example.demo.service.impl;

import com.example.demo.dto.response.dangKiHoc.DangKiHocRes;
import com.example.demo.dto.response.lopHocPhan.LopHocPhanRes;
import com.example.demo.dto.response.monhoc.MonHocRes;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DangKiHocService;
import com.example.demo.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DangKiHocServiceImpl implements DangKiHocService {
    @Autowired
    SinhVienKhoaRepository sinhVienKhoaRepository;
    @Autowired
    DangKiHocRepository dangKiHocRepository;
    @Autowired
    LopHocPhanRepository lopHocPhanRepository;
    @Autowired
    ThanhVienRepository thanhVienRepository;
    @Autowired
    KhoaRepository khoaRepository;
    @Autowired
    MonHocKiHocRepository monHocKiHocRepository;
    @Autowired
    MonHocRepository monHocRepository;
    @Autowired
    LichHocRepository lichHocRepository;


    @Override
    public DangKiHocRes getDangKiHocList(int idSVKhoa, int idKiHoc) throws ApplicationException {
        DangKiHocRes dangKiHocRes = new DangKiHocRes();

        SinhVienKhoa sinhVienKhoa = sinhVienKhoaRepository.getById(idSVKhoa);

        if(sinhVienKhoa == null){
            throw new ApplicationException("ERR_0000001");
        }

        List<DangKiHoc> dangKiHocList = dangKiHocRepository.getDangKyHocBySinhVienKhoaId(sinhVienKhoa.getId());

        List<LopHocPhan> lopHocPhanList = lopHocPhanRepository.lopHocPhanList(idKiHoc);

        dangKiHocRes.setDangKiHocList(getListDangKiHocKiHoc(dangKiHocList, lopHocPhanList));
        dangKiHocRes.setSinhVienKhoa(sinhVienKhoa);

        ThanhVien thanhVien = thanhVienRepository.getThanhVienBYId(sinhVienKhoa.getTblsinhvienID());
        dangKiHocRes.getSinhVienKhoa().setSinhVien(thanhVien);

        Khoa khoa = khoaRepository.getKhoaById(sinhVienKhoa.getIDkhoa());
        dangKiHocRes.getSinhVienKhoa().setKhoa(khoa);

        return dangKiHocRes;
    }

    private List<DangKiHoc> getListDangKiHocKiHoc(List<DangKiHoc> dangKiHocList, List<LopHocPhan> lopHocPhanList){
        List<DangKiHoc> dangKiHocListFinal = new ArrayList<>();

        int size = dangKiHocList.size();

        for(int i = 0; i < size; i++){
            if(check(dangKiHocList.get(i), lopHocPhanList)){

                dangKiHocListFinal.add(dangKiHocList.get(i));
            }
        }

        return dangKiHocListFinal;
    }

    private boolean check(DangKiHoc dangKiHoc, List<LopHocPhan> lopHocPhanList){
        int size = lopHocPhanList.size();
        for(int i = 0; i < size; i++){
            if(dangKiHoc.getTbllophocphanID() == lopHocPhanList.get(i).getId()){
                LopHocPhan lopHocPhan = lopHocPhanList.get(i);
                MonHocKiHoc monHocKiHoc = monHocKiHocRepository.getMonHocKiHocByMonHocId(lopHocPhan.getTblmonhockihocID());

                MonHoc monHoc = monHocRepository.getById(monHocKiHoc.getIDmonhoc());
                monHocKiHoc.setMonHoc(monHoc);

                lopHocPhan.setMonHocKiHoc(monHocKiHoc);
                dangKiHoc.setLopHocPhan(lopHocPhan);
                return true;
            }
        }
        return false;
    }

    @Override
    public MonHocRes getMonHocDangKi(int idSVK, int idKiHoc) throws ApplicationException {
        SinhVienKhoa sinhVienKhoa = sinhVienKhoaRepository.getById(idSVK);

        List<MonHoc> monHocList = monHocRepository.getMonHocListByKhoaAndKiHoc(sinhVienKhoa.getIDkhoa(), idKiHoc);

        if(monHocList.size() == 0){
            throw new ApplicationException("ERR_0000001");
        }

        MonHocRes monHocRes = new MonHocRes();
        monHocRes.setListMonHoc(monHocList);
        return monHocRes;
    }

    @Override
    public LopHocPhanRes getLopHocPhanList(int idMonHoc, int idKiHoc) throws ApplicationException {
        List<LopHocPhan> lopHocPhanList = lopHocPhanRepository.getLopHocPhanListByMonHocAndKiHoc(idMonHoc, idKiHoc);

        if(lopHocPhanList.isEmpty()){
            throw new ApplicationException("ERR_0000001");
        }

        for (LopHocPhan lhp:
             lopHocPhanList) {
            MonHocKiHoc monHocKiHoc = monHocKiHocRepository.getMonHocKiHocById(lhp.getTblmonhockihocID());
            MonHoc monHoc = monHocRepository.getById(monHocKiHoc.getIDmonhoc());
            monHocKiHoc.setMonHoc(monHoc);
            lhp.setMonHocKiHoc(monHocKiHoc);
        }

        LopHocPhanRes lopHocPhanRes = new LopHocPhanRes();

        lopHocPhanRes.setLopHocPhanList(lopHocPhanList);

        return lopHocPhanRes;
    }

    @Override
    public boolean deleteLopHocPhan(int idLopHocPhan, int idSinhVienKhoa) throws ApplicationException {
        DangKiHoc dangKiHoc = dangKiHocRepository.getDangKiHoc(idLopHocPhan, idSinhVienKhoa);

        if (dangKiHoc == null){
            throw new ApplicationException("ERR_0000004");
        }

        dangKiHocRepository.delete(dangKiHoc);
        LopHocPhan lopHocPhan = lopHocPhanRepository.getLopHocPhanById(dangKiHoc.getTbllophocphanID());
        lopHocPhan.setSisohientai(lopHocPhan.getSisohientai()-1);
        lopHocPhanRepository.save(lopHocPhan);
        return true;
    }

    @Override
    public boolean themLopHocPhan(int idLopHocPhan, int idSinhVienKHoa) throws ApplicationException{
        try{
            DangKiHoc check = dangKiHocRepository.getDangKiHoc(idLopHocPhan, idSinhVienKHoa);
            if(check != null){
                return false;
            }


            LopHocPhan lopHocPhan = lopHocPhanRepository.getLopHocPhanById(idLopHocPhan);



            List<DangKiHoc> dangKiHocList = dangKiHocRepository.getDangKyHocBySinhVienKhoaId(idSinhVienKHoa);

            for (DangKiHoc k:
                 dangKiHocList) {
                LopHocPhan lhp = lopHocPhanRepository.getLopHocPhanById(k.getTbllophocphanID());

                if(lhp.getTblmonhockihocID() == lopHocPhan.getTblmonhockihocID()){
                    continue;
                }

                List<LichHoc> lichHocList = lichHocRepository.checkLichHoc(k.getTbllophocphanID(), idLopHocPhan);
                if(!lichHocList.isEmpty()){
                    return false;
                }
            }

            if(lopHocPhan.getSisohientai() == lopHocPhan.getSisotoida()){
                return false;
            }

            for (DangKiHoc k:
                    dangKiHocList) {
                LopHocPhan lhp = lopHocPhanRepository.getLopHocPhanById(k.getTbllophocphanID());

                if(lhp.getTblmonhockihocID() == lopHocPhan.getTblmonhockihocID()){
                    dangKiHocRepository.delete(k);
                }
            }

            DangKiHoc dangKiHoc = new DangKiHoc();
            dangKiHoc.setTbllophocphanID(idLopHocPhan);
            dangKiHoc.setTblsinhvienkhoaID(idSinhVienKHoa);
            dangKiHocRepository.save(dangKiHoc);
            lopHocPhan.setSisohientai(lopHocPhan.getSisohientai()+1);
            lopHocPhanRepository.save(lopHocPhan);
        }catch (Exception e){
            throw  new ApplicationException("ERR_0000005");
        }
        return true;
    }
}
