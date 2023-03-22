package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/giaovu")
public class GiaoVuController {
    @GetMapping("")
    String index(){
        return "trang_chu_qly";
    }

    @GetMapping("/quanlysv")
    String getQuangLiSV(){
        return "danh_sach_sinh_vien";
    }

    @GetMapping("/quanlymonhoc")
    String getQuanLiMonHoc(){
        return "qly_mon_hoc";
    }


}
