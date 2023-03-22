package com.example.demo.controller;

import com.example.demo.dto.request.BaseRequestData;
import com.example.demo.dto.request.monhoc.MonHocReq;
import com.example.demo.dto.request.thanhvien.ThanhVienReq;
import com.example.demo.dto.response.monhoc.MonHocRes;
import com.example.demo.dto.response.thanhvien.ThanhVienRes;
import com.example.demo.model.ThanhVien;
import com.example.demo.service.ThanhVienService;
import com.example.demo.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@CrossOrigin
@RequestMapping("/thanhvien/")
public class ThanhVienController extends BaseController {
    @Autowired
    ThanhVienService thanhVienService;

    @PostMapping(value = "getListSinhVien")
    @ResponseBody
    public ResponseEntity getListSinhVien(@RequestBody BaseRequestData<ThanhVienReq> baseRequestData) {
        try {
            ThanhVienRes response = thanhVienService.getListSinhVien(baseRequestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "getSinhVienByMSV")
    @ResponseBody
    public ResponseEntity getSinhVienByMSV(@RequestBody BaseRequestData<ThanhVienReq> baseRequestData) {
        try {
            ThanhVienRes response = thanhVienService.getSinhVienByMSV(baseRequestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "suaSinhVien")
    @ResponseBody
    public ResponseEntity suaSinhVien(@RequestBody BaseRequestData<ThanhVienReq> baseRequestData) {
        try {
            ThanhVienRes response = thanhVienService.suaSinhVien(baseRequestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
}
