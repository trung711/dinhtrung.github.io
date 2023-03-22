package com.example.demo.dto.request.kihoc;

import com.example.demo.dto.request.IRequestData;
import com.example.demo.model.KiHoc;
import lombok.Data;

@Data
public class KiHocReq extends KiHoc implements IRequestData {


    @Override
    public boolean isValid() {
        return false;
    }
}
