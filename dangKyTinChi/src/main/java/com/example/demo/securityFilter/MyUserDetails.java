package com.example.demo.securityFilter;

import com.example.demo.model.ThanhVien;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class MyUserDetails implements UserDetails {
    private ThanhVien thanhVien;

    public MyUserDetails(ThanhVien thanhVien) {
        this.thanhVien = thanhVien;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(thanhVien.getVaitro());
        return Arrays.asList(authority);
    }

    @Override
    public String getPassword() {
        return thanhVien.getPassword();
    }

    @Override
    public String getUsername() {
        return thanhVien.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
