package com.example.demo.dto.response.monhoc;

import com.example.demo.dto.response.IResponseData;
import com.example.demo.model.MonHoc;
import lombok.Data;

import java.util.List;

@Data
public class MonHocRes implements IResponseData {
    List<MonHoc> listMonHoc;

}
