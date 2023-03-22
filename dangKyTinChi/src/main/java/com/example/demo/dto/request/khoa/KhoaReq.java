package com.example.demo.dto.request.khoa;

import com.example.demo.dto.request.IRequestData;
import com.example.demo.model.Khoa;
import lombok.Data;

@Data
public class KhoaReq extends Khoa implements IRequestData {
    private int idSv;

    @Override
    public boolean isValid() {
        return false;
    }
}
