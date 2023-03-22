package com.example.demo.repository;

import com.example.demo.model.ThanhVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
public interface ThanhVienRepository extends JpaRepository<ThanhVien, Integer> {
    @Query("SELECT t FROM ThanhVien t WHERE t.id = ?1")
    ThanhVien getThanhVienBYId(int id);

    @Query("SELECT m FROM ThanhVien m WHERE m.id = ?1")
    ThanhVien getThanhVienById(int id);
    @Query("SELECT m FROM ThanhVien m WHERE m.vaitro = 1")
    List<ThanhVien> getListSinhVien();
    @Query("SELECT m FROM ThanhVien m WHERE m.vaitro = 1 AND m.masv LIKE %?1%")
    List<ThanhVien> getSinhVienByMSV(String msv);
    @Query("SELECT m FROM ThanhVien m WHERE m.username = ?1 AND m.password = ?2")
    ThanhVien dangNhap(String username,String password);
    @Modifying
    @Query("UPDATE ThanhVien SET password = ?2 WHERE id = ?1")
    void doiMatKhau(int id, String newPassword);
    @Query("SELECT m FROM ThanhVien m WHERE m.username = ?1 OR m.masv = ?2")
    ThanhVien isExisted(String username,String masv);
    @Modifying
    @Query("UPDATE ThanhVien SET email = ?1, sdt = ?2 WHERE id = ?3")
    void suaThanhVien(String email, String sdt,int id);

    @Query("SELECT t FROM ThanhVien t WHERE t.username = ?1")
    ThanhVien getByUsername(String userName);
}
