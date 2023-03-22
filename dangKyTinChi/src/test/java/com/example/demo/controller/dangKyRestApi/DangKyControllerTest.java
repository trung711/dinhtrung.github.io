package com.example.demo.controller.dangKyRestApi;

import com.example.demo.dto.response.dangKiHoc.DangKiHocRes;
import com.example.demo.dto.response.khoa.KhoaRes;
import com.example.demo.dto.response.kihoc.KiHocRes;
import com.example.demo.dto.response.lichHoc.LichHocRes;
import com.example.demo.dto.response.lopHocPhan.LopHocPhanRes;
import com.example.demo.dto.response.monhoc.MonHocRes;
import com.example.demo.model.*;
import com.example.demo.service.DangKiHocService;
import com.example.demo.service.KhoaService;
import com.example.demo.service.KiHocService;

import com.example.demo.service.LichHocService;
import com.example.demo.utils.exception.ApplicationException;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;


import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class DangKyControllerTest {

    @MockBean
    private KiHocService kiHocService;

    @MockBean
    private KhoaService khoaService;

    @MockBean
    private DangKiHocService dangKiHocService;

    @MockBean
    private LichHocService lichHocService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /dangky/dskihoc success")
    void testListKiHocSuccess() throws Exception{
        KiHocRes kiHocRes = new KiHocRes();

        List<KiHoc> kiHocList = new ArrayList<>();

        KiHoc kiHoc = new KiHoc();

        kiHoc.setId(1);
        kiHoc.setTblhockiID(1);
        kiHoc.setTblnamhocID(1);
        kiHoc.setDangdangky(false);
        kiHoc.setDanghoc(true);
        kiHocList.add(kiHoc);

        kiHoc = new KiHoc();

        kiHoc.setId(2);
        kiHoc.setTblhockiID(2);
        kiHoc.setTblnamhocID(1);
        kiHoc.setDangdangky(true);
        kiHoc.setDanghoc(false);
        kiHocList.add(kiHoc);

        kiHocRes.setListKiHoc(kiHocList);

        doReturn(kiHocRes).when(kiHocService).getListKyHoc();

        mockMvc.perform(MockMvcRequestBuilders.get("/dangky/dskihoc"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.wsResponse.listKiHoc[0].id", is(1)))
                .andExpect(jsonPath("$.wsResponse.listKiHoc[0].tblhockiID", is(1)))
                .andExpect(jsonPath("$.wsResponse.listKiHoc[0].tblnamhocID", is(1)))
                .andExpect(jsonPath("$.wsResponse.listKiHoc[0].dangdangky", is(false)))
                .andExpect(jsonPath("$.wsResponse.listKiHoc[0].danghoc", is(true)))
                .andExpect(jsonPath("$.wsResponse.listKiHoc[1].id", is(2)))
                .andExpect(jsonPath("$.wsResponse.listKiHoc[1].tblhockiID", is(2)))
                .andExpect(jsonPath("$.wsResponse.listKiHoc[1].tblnamhocID", is(1)))
                .andExpect(jsonPath("$.wsResponse.listKiHoc[1].dangdangky", is(true)))
                .andExpect(jsonPath("$.wsResponse.listKiHoc[1].danghoc", is(false)));

    }

    @Test
    @DisplayName("GET /dangky/dskihoc empty list")
    void testListKiHocEmpty() throws Exception {
        KiHocRes kiHocRes = new KiHocRes();

        doReturn(kiHocRes).when(kiHocService).getListKyHoc();

        mockMvc.perform(MockMvcRequestBuilders.get("/dangky/dskihoc"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.wsResponse.listKiHoc", nullValue()))
                .andExpect(jsonPath("$.wsResponse.kiHoc", nullValue()));
    }

    @Test
    @DisplayName("POST /dangky/dskhoa success")
    void testListKhoa() throws Exception{
        KhoaRes khoaRes = new KhoaRes();

        List<Khoa> khoaList = new ArrayList<>();

        Khoa khoa = new Khoa();
        khoa.setId(1);
        khoa.setTen("cong nghe thong tin");
        khoa.setMota("mo ta1");
        khoa.setTruongID(1);
        khoaList.add(khoa);

        khoa = new Khoa();
        khoa.setId(2);
        khoa.setTen("an toan thong tin");
        khoa.setMota("mo ta2");
        khoa.setTruongID(1);
        khoaList.add(khoa);

        khoaRes.setListKhoa(khoaList);

        doReturn(khoaRes).when(khoaService).getListKhoa(1);

        mockMvc.perform(MockMvcRequestBuilders.post("/dangky/dskhoa")
                .param("id", 1+""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.wsResponse.listKhoa[0].id", is(1)))
                .andExpect(jsonPath("$.wsResponse.listKhoa[0].ten", is("cong nghe thong tin")))
                .andExpect(jsonPath("$.wsResponse.listKhoa[0].mota", is("mo ta1")))
                .andExpect(jsonPath("$.wsResponse.listKhoa[0].truongID", is(1)))
                .andExpect(jsonPath("$.wsResponse.listKhoa[1].id", is(2)))
                .andExpect(jsonPath("$.wsResponse.listKhoa[1].ten", is("an toan thong tin")))
                .andExpect(jsonPath("$.wsResponse.listKhoa[1].mota", is("mo ta2")))
                .andExpect(jsonPath("$.wsResponse.listKhoa[1].truongID", is(1)))
                .andExpect(jsonPath("$.wsResponse.khoa", nullValue()));
    }

    @Test
    @DisplayName("POST /dangky/dskhoa Empty list")
    void testListKhoaEmpty() throws Exception{
        KhoaRes khoaRes = new KhoaRes();

        doReturn(khoaRes).when(khoaService).getListKhoa(1);

        mockMvc.perform(MockMvcRequestBuilders.post("/dangky/dskhoa")
                .param("id", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.wsResponse.listKhoa", nullValue()))
                .andExpect(jsonPath("$.wsResponse.khoa", nullValue()));

        mockMvc.perform(MockMvcRequestBuilders.post("/dangky/dskhoa")
                        .param("id", "2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.wsResponse", nullValue()));
    }

    @Test
    @DisplayName("POST /dangky/dsdangky success")
    void testListDangKy() throws Exception{
        DangKiHocRes dangKiHocRes = new DangKiHocRes();

        List<DangKiHoc> dangKiHocList = new ArrayList<>();

        DangKiHoc dangKiHoc = new DangKiHoc();
        dangKiHoc.setId(1);
        dangKiHoc.setTblsinhvienkhoaID(1);
        dangKiHoc.setTbllophocphanID(1);
        dangKiHoc.setGhichu("ghi chu 1");
        dangKiHocList.add(dangKiHoc);

        dangKiHoc = new DangKiHoc();
        dangKiHoc.setId(2);
        dangKiHoc.setTbllophocphanID(2);
        dangKiHoc.setTblsinhvienkhoaID(2);
        dangKiHoc.setGhichu("ghi chu 2");
        dangKiHocList.add(dangKiHoc);

        dangKiHocRes.setDangKiHocList(dangKiHocList);

        doReturn(dangKiHocRes).when(dangKiHocService).getDangKiHocList(1, 1);

        mockMvc.perform(MockMvcRequestBuilders.post("/dangky/dsdangky")
                .param("idsvk", "1")
                .param("idkihoc", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.wsResponse.dangKiHocList[0].id",is(1)))
                .andExpect(jsonPath("$.wsResponse.dangKiHocList[0].tbllophocphanID", is(1)))
                .andExpect(jsonPath("$.wsResponse.dangKiHocList[0].tblsinhvienkhoaID", is(1)))
                .andExpect(jsonPath("$.wsResponse.dangKiHocList[0].ghichu", is("ghi chu 1")))
                .andExpect(jsonPath("$.wsResponse.dangKiHocList[1].id",is(2)))
                .andExpect(jsonPath("$.wsResponse.dangKiHocList[1].tbllophocphanID", is(2)))
                .andExpect(jsonPath("$.wsResponse.dangKiHocList[1].tblsinhvienkhoaID", is(2)))
                .andExpect(jsonPath("$.wsResponse.dangKiHocList[1].ghichu", is("ghi chu 2")))
                .andExpect(jsonPath("$.wsResponse.sinhVienKhoa", nullValue()));

        mockMvc.perform(MockMvcRequestBuilders.post("/dangky/dsdangky")
                        .param("idsvk", "2")
                        .param("idkihoc", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.wsResponse", nullValue()));
    }

    @Test
    @DisplayName("POST /dangky/dsdangky empty list")
    void testListDangKyHocEmptyList() throws Exception{
        DangKiHocRes dangKiHocRes = new DangKiHocRes();

        doReturn(dangKiHocRes).when(dangKiHocService).getDangKiHocList(1, 1);

        mockMvc.perform(MockMvcRequestBuilders.post("/dangky/dsdangky")
                .param("idsvk", "1")
                .param("idkihoc", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.wsResponse.dangKiHocList", nullValue()))
                .andExpect(jsonPath("$.wsResponse.sinhVienKhoa", nullValue()));
    }

    @Test
    @DisplayName("POST /dangky/getmonhocdangky success")
    void getListMonHocDangKy() throws Exception{
        MonHocRes monHocRes = new MonHocRes();

        List<MonHoc> monHocList = new ArrayList<>();

        MonHoc monHoc = new MonHoc();
        monHoc.setId(1);
        monHoc.setTen("lap trinh huong doi tuong");
        monHoc.setSotinchi(3);
        monHoc.setMamonhoc("ma 1");
        monHoc.setMota("mo ta 1");
        monHoc.setIDtblbomon(1);
        monHocList.add(monHoc);

        monHoc = new MonHoc();
        monHoc.setId(2);
        monHoc.setTen("toan roi rac");
        monHoc.setSotinchi(2);
        monHoc.setMamonhoc("ma 2");
        monHoc.setMota("mo ta 2");
        monHoc.setIDtblbomon(1);
        monHocList.add(monHoc);

        monHocRes.setListMonHoc(monHocList);

        doReturn(monHocRes).when(dangKiHocService).getMonHocDangKi(1, 1);

        mockMvc.perform(MockMvcRequestBuilders.post("/dangky/getmonhocdangky")
                .param("idsvkhoa", "1")
                .param("idkihoc", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.wsResponse.listMonHoc[0].id", is(1)))
                .andExpect(jsonPath("$.wsResponse.listMonHoc[0].ten", is("lap trinh huong doi tuong")))
                .andExpect(jsonPath("$.wsResponse.listMonHoc[0].sotinchi", is(3)))
                .andExpect(jsonPath("$.wsResponse.listMonHoc[0].mamonhoc", is("ma 1")))
                .andExpect(jsonPath("$.wsResponse.listMonHoc[0].mota", is("mo ta 1")))
                .andExpect(jsonPath("$.wsResponse.listMonHoc[0].idtblbomon", is(1)))
                .andExpect(jsonPath("$.wsResponse.listMonHoc[1].id", is(2)))
                .andExpect(jsonPath("$.wsResponse.listMonHoc[1].ten", is("toan roi rac")))
                .andExpect(jsonPath("$.wsResponse.listMonHoc[1].sotinchi", is(2)))
                .andExpect(jsonPath("$.wsResponse.listMonHoc[1].mamonhoc", is("ma 2")))
                .andExpect(jsonPath("$.wsResponse.listMonHoc[1].mota", is("mo ta 2")))
                .andExpect(jsonPath("$.wsResponse.listMonHoc[1].idtblbomon", is(1)));

        mockMvc.perform(MockMvcRequestBuilders.post("/dangky/getmonhocdangky")
                .param("idsvkhoa", "1")
                .param("idkihoc", "2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.wsResponse", nullValue()));
    }

    @Test
    @DisplayName("POST /dangky/getmonhocdangky Empty list")
    void getEmptyListMonHocDangKy() throws Exception{
        MonHocRes monHocRes = new MonHocRes();

        doReturn(monHocRes).when(dangKiHocService).getMonHocDangKi(1,1);

        mockMvc.perform(MockMvcRequestBuilders.post("/dangky/getmonhocdangky")
                .param("idsvkhoa", "1")
                .param("idkihoc", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.wsResponse.listMonHoc", nullValue()));
    }

    @Test
    @DisplayName("POST /dangky/dslophocphan success")
    void getDSLopHocPhan() throws Exception{
        LopHocPhanRes lopHocPhanRes = new LopHocPhanRes();

        List<LopHocPhan> lopHocPhanList = new ArrayList<>();

        LopHocPhan lopHocPhan = new LopHocPhan();
        lopHocPhan.setId(1);
        lopHocPhan.setTen("lop 1");
        lopHocPhan.setSisotoida(40);
        lopHocPhan.setSisohientai(35);
        lopHocPhan.setTblmonhockihocID(1);
        lopHocPhan.setMota("mo ta 1");
        lopHocPhanList.add(lopHocPhan);

        lopHocPhan = new LopHocPhan();
        lopHocPhan.setId(2);
        lopHocPhan.setTen("lop 2");
        lopHocPhan.setSisotoida(40);
        lopHocPhan.setSisohientai(24);
        lopHocPhan.setTblmonhockihocID(1);
        lopHocPhan.setMota("mo ta 2");
        lopHocPhanList.add(lopHocPhan);

        lopHocPhanRes.setLopHocPhanList(lopHocPhanList);

        doReturn(lopHocPhanRes).when(dangKiHocService).getLopHocPhanList(1, 1);

        mockMvc.perform(MockMvcRequestBuilders.post("/dangky/dslophocphan")
                .param("idmh", "1")
                .param("idkh", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.wsResponse.lopHocPhanList[0].id", is(1)))
                .andExpect(jsonPath("$.wsResponse.lopHocPhanList[0].ten", is("lop 1")))
                .andExpect(jsonPath("$.wsResponse.lopHocPhanList[0].sisotoida", is(40)))
                .andExpect(jsonPath("$.wsResponse.lopHocPhanList[0].sisohientai", is(35)))
                .andExpect(jsonPath("$.wsResponse.lopHocPhanList[0].tblmonhockihocID", is(1)))
                .andExpect(jsonPath("$.wsResponse.lopHocPhanList[0].mota", is("mo ta 1")))
                .andExpect(jsonPath("$.wsResponse.lopHocPhanList[1].id", is(2)))
                .andExpect(jsonPath("$.wsResponse.lopHocPhanList[1].ten", is("lop 2")))
                .andExpect(jsonPath("$.wsResponse.lopHocPhanList[1].sisotoida", is(40)))
                .andExpect(jsonPath("$.wsResponse.lopHocPhanList[1].sisohientai", is(24)))
                .andExpect(jsonPath("$.wsResponse.lopHocPhanList[1].tblmonhockihocID", is(1)))
                .andExpect(jsonPath("$.wsResponse.lopHocPhanList[1].mota", is("mo ta 2")));

        mockMvc.perform(MockMvcRequestBuilders.post("/dangky/dslophocphan")
                .param("idmh", "1")
                .param("idkh", "2"))
                .andExpect(jsonPath("$.wsResponse", nullValue()));
    }

    @Test
    @DisplayName("POST /dangky/dslophocphan empty list")
    void getEmptyDsLopHocPhan() throws Exception{
        LopHocPhanRes lopHocPhanRes = new LopHocPhanRes();

        doReturn(lopHocPhanRes).when(dangKiHocService).getLopHocPhanList(1,1);

        mockMvc.perform(MockMvcRequestBuilders.post("/dangky/dslophocphan")
                .param("idmh", "1")
                .param("idkh", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.wsResponse.lopHocPhanList", nullValue()));
    }

    @Test
    @DisplayName("POST /dangky/dslichhoc success")
    void getDsLichHoc() throws Exception{
        LichHocRes lichHocRes = new LichHocRes();

        List<LichHoc> lichHocList = new ArrayList<>();

        LichHoc lichHoc = new LichHoc();
        lichHoc.setId(1);
        lichHoc.setTen("lich 1");
        lichHoc.setMota("mo ta 1");
        lichHoc.setTblkiphocID(1);
        lichHoc.setTblngayhocID(4);
        lichHoc.setTbltuanhocID(1);
        lichHoc.setTblphonghocID(3);
        lichHoc.setTblgiangvienID(3);
        lichHoc.setIDtbllophocphan(2);
        lichHocList.add(lichHoc);

        lichHoc = new LichHoc();
        lichHoc.setId(2);
        lichHoc.setTen("lich 2");
        lichHoc.setMota("mo ta 2");
        lichHoc.setTblkiphocID(1);
        lichHoc.setTblngayhocID(4);
        lichHoc.setTbltuanhocID(2);
        lichHoc.setTblphonghocID(3);
        lichHoc.setTblgiangvienID(3);
        lichHoc.setIDtbllophocphan(2);
        lichHocList.add(lichHoc);

        lichHocRes.setLichHocList(lichHocList);

        doReturn(lichHocRes).when(lichHocService).getLichHocListByLopHocPhanID(2);

        mockMvc.perform(MockMvcRequestBuilders.post("/dangky/dslichhoc")
                .param("idlhp", "2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.wsResponse.lichHocList[0].id", is(1)))
                .andExpect(jsonPath("$.wsResponse.lichHocList[0].ten", is("lich 1")))
                .andExpect(jsonPath("$.wsResponse.lichHocList[0].mota", is("mo ta 1")))
                .andExpect(jsonPath("$.wsResponse.lichHocList[0].tblkiphocID", is(1)))
                .andExpect(jsonPath("$.wsResponse.lichHocList[0].tblngayhocID", is(4)))
                .andExpect(jsonPath("$.wsResponse.lichHocList[0].tbltuanhocID", is(1)))
                .andExpect(jsonPath("$.wsResponse.lichHocList[0].tblphonghocID",is(3)))
                .andExpect(jsonPath("$.wsResponse.lichHocList[0].tblgiangvienID", is(3)))
                .andExpect(jsonPath("$.wsResponse.lichHocList[0].idtbllophocphan", is(2)))
                .andExpect(jsonPath("$.wsResponse.lichHocList[1].id", is(2)))
                .andExpect(jsonPath("$.wsResponse.lichHocList[1].ten", is("lich 2")))
                .andExpect(jsonPath("$.wsResponse.lichHocList[1].mota", is("mo ta 2")))
                .andExpect(jsonPath("$.wsResponse.lichHocList[1].tblkiphocID", is(1)))
                .andExpect(jsonPath("$.wsResponse.lichHocList[1].tblngayhocID", is(4)))
                .andExpect(jsonPath("$.wsResponse.lichHocList[1].tbltuanhocID", is(2)))
                .andExpect(jsonPath("$.wsResponse.lichHocList[1].tblphonghocID",is(3)))
                .andExpect(jsonPath("$.wsResponse.lichHocList[1].tblgiangvienID", is(3)))
                .andExpect(jsonPath("$.wsResponse.lichHocList[1].idtbllophocphan", is(2)));

        mockMvc.perform(MockMvcRequestBuilders.post("/dangky/dslichhoc")
                .param("idlhp", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.wsResponse", nullValue()));
    }

    @Test
    @DisplayName("POST /dangky/dslichhoc empty")
    void getEmptyDsLichHoc() throws Exception{
        LichHocRes lichHocRes = new LichHocRes();

        doReturn(lichHocRes).when(lichHocService).getLichHocListByLopHocPhanID(1);

        mockMvc.perform(MockMvcRequestBuilders.post("/dangky/dslichhoc")
                .param("idlhp", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.wsResponse.lichHocList", nullValue()));
    }

    @Test
    @DisplayName("POST /dangky/xoalophoc success")
    void xoaLopHocSuccess() throws Exception{
        int idLopHocPhan = 1;
        int idSinhVien = 1;

        Boolean checkOK = true;
        Boolean chekFail = false;

        doReturn(checkOK).when(dangKiHocService).deleteLopHocPhan(idLopHocPhan, idSinhVien);
        doReturn(chekFail).when(dangKiHocService).deleteLopHocPhan(2, 1);

        mockMvc.perform(MockMvcRequestBuilders.post("/dangky/xoalophoc")
                .param("idlhp", "1")
                .param("idsvk", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.message", is("xoa thanh cong")));
    }

    @Test
    @DisplayName("POST /dangky/xoalophoc fail")
    void xoaLopHocFail() throws Exception{
        int idLopHocPhan = 1;
        int idSinhVien = 1;

        Boolean checkOK = true;
        Boolean chekFail = false;

        doReturn(checkOK).when(dangKiHocService).deleteLopHocPhan(idLopHocPhan, idSinhVien);
        doReturn(chekFail).when(dangKiHocService).deleteLopHocPhan(2, 1);

        mockMvc.perform(MockMvcRequestBuilders.post("/dangky/xoalophoc")
                        .param("idlhp", "2")
                        .param("idsvk", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.message", is("xoa khong thanh cong")));
    }

    @Test
    @DisplayName("POST /dangky/themlophoc success")
    void themLopHocSuccess() throws Exception{
        Boolean ok = true;
        Boolean fail = false;

        doReturn(ok).when(dangKiHocService).themLopHocPhan(1,1);

        mockMvc.perform(MockMvcRequestBuilders.post("/dangky/themlophoc")
                .param("idlhp", "1")
                .param("idsvk", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.message", is("Thêm thành công")));
    }

    @Test
    @DisplayName("POST /dangky/themlophoc fail")
    void themLopHocFail() throws Exception{
        Boolean ok = true;
        Boolean fail = false;

        doReturn(fail).when(dangKiHocService).themLopHocPhan(2,1);

        mockMvc.perform(MockMvcRequestBuilders.post("/dangky/themlophoc")
                        .param("idlhp", "2")
                        .param("idsvk", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.message", is("Thêm thất bại")));
    }
}
