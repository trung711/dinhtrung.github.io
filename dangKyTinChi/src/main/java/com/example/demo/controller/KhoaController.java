package com.example.demo.controller;

import com.example.demo.dto.request.BaseRequestData;
import com.example.demo.dto.request.khoa.KhoaReq;
import com.example.demo.dto.request.monhoc.MonHocReq;
import com.example.demo.dto.response.khoa.KhoaRes;
import com.example.demo.service.KhoaService;
import com.example.demo.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/khoa/")
public class KhoaController extends BaseController {

    @Autowired
    KhoaService khoaService;

    @PostMapping(value = "getListKhoa")
    @ResponseBody
    public ResponseEntity getListKhoa() {
        try {
            KhoaRes response = khoaService.getListKhoa();
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
}
