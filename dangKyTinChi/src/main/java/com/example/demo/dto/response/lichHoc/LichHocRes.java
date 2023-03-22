package com.example.demo.dto.response.lichHoc;

import com.example.demo.dto.response.IResponseData;
import com.example.demo.model.LichHoc;
import lombok.Data;

import java.util.List;

@Data
public class LichHocRes implements IResponseData {
    List<LichHoc> lichHocList;
}
