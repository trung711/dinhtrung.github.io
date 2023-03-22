package com.example.demo.controller;

import com.example.demo.dto.request.BaseRequestData;
import com.example.demo.dto.request.monhoc.MonHocReq;
import com.example.demo.dto.response.monhoc.MonHocRes;
import com.example.demo.service.MonHocService;
import com.example.demo.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/monhoc/")
public class MonHocController extends BaseController {
    @Autowired
    MonHocService monHocService;

    @PostMapping(value = "getListMonHoc")
    @ResponseBody
    public ResponseEntity getListMonHoc() {
        try {
            MonHocRes response = monHocService.getListMonHoc();
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
}
