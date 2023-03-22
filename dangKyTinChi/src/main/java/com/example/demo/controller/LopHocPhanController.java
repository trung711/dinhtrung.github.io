package com.example.demo.controller;

import com.example.demo.dto.request.BaseRequestData;
import com.example.demo.dto.request.lophocphan.LopHocPhanReq;
import com.example.demo.dto.request.monhoc.MonHocReq;
import com.example.demo.dto.response.lopHocPhan.LopHocPhanRes;
import com.example.demo.dto.response.monhoc.MonHocRes;
import com.example.demo.service.LopHocPhanService;
import com.example.demo.service.MonHocService;
import com.example.demo.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
@RequestMapping("/lophocphan/")
public class LopHocPhanController extends BaseController{
    @Autowired
    LopHocPhanService lopHocPhanService;

    @PostMapping(value = "getListLopHocPhan")
    @ResponseBody
    public ResponseEntity getListLopHocPhan(BaseRequestData<LopHocPhanReq> baseRequestData) {
        try {
            LopHocPhanRes response = lopHocPhanService.getListLopHocPhan(baseRequestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "getListLopHocPhanByMonHocKiHoc")
    @ResponseBody
    public ResponseEntity getListLopHocPhanByMonHocKiHoc(BaseRequestData<LopHocPhanReq> baseRequestData) {
        try {
            LopHocPhanRes response = lopHocPhanService.getListLopHocPhanByMonHocKiHoc(baseRequestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
    @PostMapping(value = "addLopHocPhan")
    @ResponseBody
    public ResponseEntity addLopHocPhan(BaseRequestData<LopHocPhanReq> baseRequestData) {
        try {
            LopHocPhanRes response = lopHocPhanService.addLopHocPhan(baseRequestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
    @PostMapping(value = "editLopHocPhan")
    @ResponseBody
    public ResponseEntity editLopHocPhan(BaseRequestData<LopHocPhanReq> baseRequestData) {
        try {
            LopHocPhanRes response = lopHocPhanService.editLopHocPhan(baseRequestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
    @PostMapping(value = "deleteLopHocPhan")
    @ResponseBody
    public ResponseEntity deleteLopHocPhan(BaseRequestData<LopHocPhanReq> baseRequestData) {
        try {
            LopHocPhanRes response = lopHocPhanService.deleteLopHocPhan(baseRequestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
}
