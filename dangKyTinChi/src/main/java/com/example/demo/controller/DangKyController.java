package com.example.demo.controller;

import com.example.demo.dto.response.dangKiHoc.DangKiHocRes;
import com.example.demo.dto.response.khoa.KhoaRes;
import com.example.demo.dto.response.kihoc.KiHocRes;
import com.example.demo.dto.response.lichHoc.LichHocRes;
import com.example.demo.dto.response.lopHocPhan.LopHocPhanRes;
import com.example.demo.dto.response.monhoc.MonHocRes;
import com.example.demo.service.*;
import com.example.demo.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@CrossOrigin
@RequestMapping("/dangky")
public class DangKyController extends BaseController{

    @Autowired
    KiHocService kiHocService;
    @Autowired
    KhoaService khoaService;
    @Autowired
    DangKiHocService dangKiHocService;
    @Autowired
    LichHocService lichHocService;
    @Autowired
    SinhVienKhoaService sinhVienKhoaService;

    @GetMapping("/dskihoc")
    @ResponseBody
    public ResponseEntity listKiHoc(){
        try {
            KiHocRes kiHocRes = kiHocService.getListKyHoc();
            return success(kiHocRes);
        }catch (ApplicationException e){
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping("/dskhoa")
    @ResponseBody
    public ResponseEntity listKhoa(@RequestParam(name = "id") int id){
        try {

            KhoaRes khoaRes = khoaService.getListKhoa(id);
            return success(khoaRes);
        }catch (ApplicationException e){
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping("/dsdangky")
    @ResponseBody
    public ResponseEntity listDangKy(@RequestParam(name = "idsvk") int idSVKhoa,
                               @RequestParam(name = "idkihoc") int idKiHoc){
        try {

            DangKiHocRes dangKiHocRes = dangKiHocService.getDangKiHocList(idSVKhoa, idKiHoc);
            return success(dangKiHocRes);
        }catch (ApplicationException exception){
            return error(exception.getCode(), exception.getMessage());
        }

    }

    @PostMapping("/getmonhocdangky")
    @ResponseBody
    ResponseEntity getListMonHocDangKy(@RequestParam(name = "idsvkhoa") int idSVKhoa,
                                       @RequestParam(name = "idkihoc") int idKiHoc){
        try{
            MonHocRes monHocRes = dangKiHocService.getMonHocDangKi(idSVKhoa, idKiHoc);
            return success(monHocRes);
        }catch (ApplicationException e){
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping("/dslophocphan")
    @ResponseBody
    ResponseEntity getLopHocPhanList(@RequestParam(name = "idmh") int idMonHoc,
                                     @RequestParam(name = "idkh") int idKiHoc){
        try {
            LopHocPhanRes lopHocPhanRes = dangKiHocService.getLopHocPhanList(idMonHoc, idKiHoc);
            return success(lopHocPhanRes);
        }catch (ApplicationException e){
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping("/dslichhoc")
    @ResponseBody
    ResponseEntity getLichHocList(@RequestParam(name = "idlhp") int idLopHocPhan){
        try {
            LichHocRes lichHocRes = lichHocService.getLichHocListByLopHocPhanID(idLopHocPhan);
            return success(lichHocRes);
        }catch (ApplicationException e){
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping("/xoalophoc")
    @ResponseBody
    ResponseEntity xoaLopHocPhan(@RequestParam(name = "idlhp") int idLopHocPhan, @RequestParam(name = "idsvk") int idSinhVienKhoa){
        try{
            boolean rd =dangKiHocService.deleteLopHocPhan(idLopHocPhan, idSinhVienKhoa);
            return error(""+200, "xoa thanh cong");
        }catch (ApplicationException e){
            return error(e.getCode(), e.getMessage());
        }

    }


    @PostMapping("/themlophoc")
    @ResponseBody
    ResponseEntity themLopHocPhan(@RequestParam(name = "idlhp") int idLopHocPhan,
                                  @RequestParam(name = "idsvk") int idSinhVienKhoa){
        try {
            if(dangKiHocService.themLopHocPhan(idLopHocPhan, idSinhVienKhoa)){
                return error(""+200,"Thêm thành công");

            }
            else {
                return error("500", "Thêm thất bại");
            }

        }catch (ApplicationException e){
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping("setCookie")
    @ResponseBody
    String setCookie(@RequestParam(name = "idsv") int idSinhVien,
                     @RequestParam(name = "idkhoa") int idKhoa,
                     @RequestParam(name = "idkihoc") int idKiHoc,
                     HttpServletResponse response){

        Cookie cookie = new Cookie("idsv", ""+idSinhVien);
        response.addCookie(cookie);

        cookie = new Cookie("idkhoa", ""+idKhoa);
        response.addCookie(cookie);

        cookie = new Cookie("idkihoc", ""+idKiHoc);
        response.addCookie(cookie);

        try {
            int idSinhVienKhoa = sinhVienKhoaService.idSinhVienKhoa(idSinhVien, idKhoa);

            cookie = new Cookie("idsvk", ""+idSinhVienKhoa);
            response.addCookie(cookie);
        }catch (ApplicationException e){
            return "{'errorCode':'"+e.getCode()+"'," +
                    "'message':'"+e.getMessage()+"'}";
        }

        return "200";
    }

    @GetMapping("")
    String dangKy(@CookieValue(value = "idsv", defaultValue = "0") int idSinhVien,
                  @CookieValue(value = "idkhoa", defaultValue = "0") int idKhoa,
                  @CookieValue(value = "idkihoc", defaultValue = "0") int idKiHoc,
                  Model model){

        if(idKhoa == 0 || idSinhVien == 0 || idKiHoc == 0){
            return "redirect:/dangky/nganh";
        }
        try {
            int idSinhVienKhoa = sinhVienKhoaService.idSinhVienKhoa(idSinhVien, idKhoa);
            model.addAttribute("idsvk", idSinhVienKhoa);
            model.addAttribute("idkihoc", idKiHoc);
            return "dang_ky";
        }catch (ApplicationException e){
            return "{'errorCode':'"+e.getCode()+"'," +
                    "'message':'"+e.getMessage()+"'}";
        }

    }

    @GetMapping("/nganh")
    String chonNganh(@CookieValue(value = "idsv", defaultValue = "0") int idSinhVien, Model model){
        model.addAttribute("idsv", idSinhVien);
        return "chon_nganh";
    }

    @GetMapping("/chonmon")
    String chonMonHoc(@CookieValue(value = "idsvk", defaultValue = "0") int idSinhVienKhoa,
                      @CookieValue(value = "idkihoc", defaultValue = "0") int idKiHoc,
                      Model model){
        model.addAttribute("idsvk", idSinhVienKhoa);
        model.addAttribute("idkihoc", idKiHoc);
        return "chon_mon_hoc";
    }

    @GetMapping("/chonlophoc")
    String chonLopHocPhan(@RequestParam(name = "idmh") int idMonHoc,
                          @CookieValue(value = "idsvk", defaultValue = "0") int idSinhVienKhoa,
                          @CookieValue(value = "idkihoc", defaultValue = "0") int idKiHoc,Model model){
        model.addAttribute("idmh", idMonHoc);
        model.addAttribute("idsvk", idSinhVienKhoa);
        model.addAttribute("idkihoc", idKiHoc);
        return "chon_lop_hoc_phan";
    }

    @GetMapping("/xemlich")
    String xemLich(@RequestParam(name = "idlhp") int idLopHocPhan, @RequestParam(name = "idmh") int idMonHoc,Model model){
        model.addAttribute("idlhp", idLopHocPhan);
        String str = "/dangky/chonlophoc?idmh="+idMonHoc;
        model.addAttribute("idmh", str);
        return "xem_lich";
    }

    @GetMapping("/xemlich2")
    String xemLich(@RequestParam(name = "idlhp") int idLopHocPhan, Model model){
        model.addAttribute("idlhp", idLopHocPhan);
        String str = "/dangky";
        model.addAttribute("idmh", str);
        return "xem_lich";
    }



//    @GetMapping("/demo")
//    @ResponseBody
//    String demo(@RequestParam(name = "id") int id){
//        KiHocRepository repository;
//        KiHoc kiHoc = repository.getListKiHoc(id);
//        return "";
//    }
}
