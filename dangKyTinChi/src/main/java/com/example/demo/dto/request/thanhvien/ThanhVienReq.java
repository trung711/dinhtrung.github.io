package com.example.demo.dto.request.thanhvien;

import com.example.demo.dto.request.BaseRequestData;
import com.example.demo.dto.request.IRequestData;
import com.example.demo.model.ThanhVien;
import lombok.Data;

@Data
public class ThanhVienReq extends ThanhVien implements IRequestData {
    private int tblkhoaID;
    private String nienKhoa;
    private String newPassword;
    @Override
    public boolean isValid() {
        return false;
    }
}
