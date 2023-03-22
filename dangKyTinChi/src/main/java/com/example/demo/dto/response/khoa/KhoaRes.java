package com.example.demo.dto.response.khoa;

import com.example.demo.dto.response.IResponseData;
import com.example.demo.model.Khoa;
import lombok.Data;

import java.util.List;

@Data
public class KhoaRes implements IResponseData {
    Khoa khoa;
    List<Khoa> listKhoa;
}
