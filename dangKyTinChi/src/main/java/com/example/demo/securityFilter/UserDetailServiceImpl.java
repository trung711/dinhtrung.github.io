package com.example.demo.securityFilter;

import com.example.demo.model.ThanhVien;
import com.example.demo.repository.ThanhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private ThanhVienRepository thanhVienRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ThanhVien thanhVien = thanhVienRepository.getByUsername(username);
        if(thanhVien == null){
            throw new UsernameNotFoundException("Sai thong tin dang nhap");
        }
        return new MyUserDetails(thanhVien);
    }
}
