package com.example.demo.dto.request.lophocphan;

import com.example.demo.dto.request.IRequestData;
import com.example.demo.model.LopHocPhan;
import lombok.Data;

@Data
public class LopHocPhanReq extends LopHocPhan implements IRequestData {
    String idsv;
    int tblmonhocId;
    int tblkihocId;
    @Override
    public boolean isValid() {
        return false;
    }
}
