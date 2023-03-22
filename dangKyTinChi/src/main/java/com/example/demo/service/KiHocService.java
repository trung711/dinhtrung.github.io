package com.example.demo.service;

import com.example.demo.dto.request.BaseRequestData;
import com.example.demo.utils.exception.ApplicationException;
import com.example.demo.dto.response.kihoc.KiHocRes;

public interface KiHocService {
    KiHocRes getListKyHoc() throws ApplicationException;

}
