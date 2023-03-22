package com.example.demo.dto.response.kihoc;

import com.example.demo.dto.response.IResponseData;
import com.example.demo.model.KiHoc;
import lombok.Data;

import java.util.List;

@Data
public class KiHocRes implements IResponseData {
    KiHoc kiHoc;
    List<KiHoc> listKiHoc;
}
