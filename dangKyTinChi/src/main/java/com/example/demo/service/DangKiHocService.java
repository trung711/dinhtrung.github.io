package com.example.demo.service;

import com.example.demo.dto.response.dangKiHoc.DangKiHocRes;
import com.example.demo.dto.response.lopHocPhan.LopHocPhanRes;
import com.example.demo.dto.response.monhoc.MonHocRes;
import com.example.demo.model.LopHocPhan;
import com.example.demo.model.MonHoc;
import com.example.demo.utils.exception.ApplicationException;

import java.util.List;

public interface DangKiHocService {
    DangKiHocRes getDangKiHocList(int idSVKhoa, int idKiHoc) throws ApplicationException;
    MonHocRes getMonHocDangKi(int idSVK, int idKiHoc) throws ApplicationException;
    LopHocPhanRes getLopHocPhanList(int idMonHoc, int idKiHoc) throws ApplicationException;
    boolean deleteLopHocPhan(int idLopHocPhan, int idSinhVienKhoa) throws ApplicationException;
    boolean themLopHocPhan(int idLopHocPhan, int idSinhVienKHoa) throws ApplicationException;
}
