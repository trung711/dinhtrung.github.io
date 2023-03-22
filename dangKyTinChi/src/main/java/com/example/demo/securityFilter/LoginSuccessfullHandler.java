package com.example.demo.securityFilter;

import com.example.demo.model.ThanhVien;
import com.example.demo.repository.ThanhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessfullHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private ThanhVienRepository thanhVienRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        ThanhVien thanhVien = thanhVienRepository.dangNhap(myUserDetails.getUsername(), myUserDetails.getPassword());

        Cookie cookie = new Cookie("idsv", thanhVien.getId()+"");
        cookie.setPath("/dangky");
        response.addCookie(cookie);


        Cookie cookie1 = new Cookie("idkhoa", null);
        cookie1.setMaxAge(0);
        cookie1.setPath("/dangky");
        response.addCookie(cookie1);

        Cookie cookie2 = new Cookie("idkihoc", null);
        cookie2.setMaxAge(0);
        cookie2.setPath("/dangky");
        response.addCookie(cookie2);

        String redirectURL = "";

        if(thanhVien.getVaitro().equals("SINH_VIEN")){
            redirectURL = "/dangky";
        }
        if(thanhVien.getVaitro().equals("GIANG_VIEN") || thanhVien.getVaitro().equals("GIAO_VU")){
            redirectURL = "/giaovu";
        }

        response.sendRedirect(redirectURL);
    }
}
