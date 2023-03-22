package com.example.demo.dto.response.lopHocPhan;

import com.example.demo.dto.response.IResponseData;
import com.example.demo.model.LopHocPhan;
import lombok.Data;

import java.util.List;

@Data
public class LopHocPhanRes implements IResponseData {
    List<LopHocPhan> lopHocPhanList;
}
