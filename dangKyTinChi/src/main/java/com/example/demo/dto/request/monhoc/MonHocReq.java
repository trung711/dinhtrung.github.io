package com.example.demo.dto.request.monhoc;

import com.example.demo.dto.request.BaseRequestData;
import com.example.demo.dto.request.IRequestData;
import com.example.demo.model.MonHoc;

public class MonHocReq extends MonHoc implements IRequestData {
    private int IDtblbomon;
    @Override
    public boolean isValid() {
        return false;
    }
}
