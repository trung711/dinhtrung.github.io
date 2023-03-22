package com.example.demo.controller.khoaRestApi;

import com.example.demo.dto.request.BaseRequestData;
import com.example.demo.dto.request.khoa.KhoaReq;
import com.example.demo.dto.response.khoa.KhoaRes;
import com.example.demo.model.Khoa;
import com.example.demo.service.KhoaService;
import com.example.demo.utils.exception.ApplicationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class KhoaControllerTest {

    @MockBean
    private KhoaService khoaService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST /api/getListKhoa list isn't empty")
    void testListKhoa() throws Exception {
        KhoaRes khoaRes = new KhoaRes();

        List<Khoa> khoaList = new ArrayList<>();

        Khoa khoa = new Khoa();
        khoa.setId(1);
        khoa.setTen("khoa 1");
        khoa.setMota("mo ta 1");
        khoa.setTruongID(1);
        khoaList.add(khoa);

        khoa = new Khoa();
        khoa.setId(2);
        khoa.setTen("khoa 2");
        khoa.setMota("mo ta 2");
        khoa.setTruongID(1);
        khoaList.add(khoa);

        khoaRes.setListKhoa(khoaList);

        BaseRequestData<KhoaReq> baseRequestData = new BaseRequestData();
        baseRequestData.setWsRequest(new KhoaReq());

        doReturn(khoaRes).when(khoaService).getListKhoa();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/getListKhoa"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.wsResponse.listKhoa[0].id", is(1)))
                .andExpect(jsonPath("$.wsResponse.listKhoa[0].ten", is("khoa 1")))
                .andExpect(jsonPath("$.wsResponse.listKhoa[0].mota", is("mo ta 1")))
                .andExpect(jsonPath("$.wsResponse.listKhoa[0].truongID", is(1)))
                .andExpect(jsonPath("$.wsResponse.listKhoa[1].id", is(2)))
                .andExpect(jsonPath("$.wsResponse.listKhoa[1].ten", is("khoa 2")))
                .andExpect(jsonPath("$.wsResponse.listKhoa[1].mota", is("mo ta 2")))
                .andExpect(jsonPath("$.wsResponse.listKhoa[1].truongID", is(1)))
                .andExpect(jsonPath("$.wsResponse.khoa", nullValue()));
    }

    @Test
    @DisplayName("POST /api/getListKhoa list is empty")
    void getEmptyKhoaList() throws Exception{
        KhoaRes khoaRes = new KhoaRes();


        doReturn(khoaRes).when(khoaService).getListKhoa();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/getListKhoa"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.wsResponse.listKhoa", nullValue()))
                .andExpect(jsonPath("$.wsResponse.khoa", nullValue()));

    }

    @Test
    @DisplayName("POST /api/getListKhoa list have one item")
    void testKhoaList() throws Exception{
        KhoaRes khoaRes = new KhoaRes();

        List<Khoa> khoaList = new ArrayList<>();

        Khoa khoa = new Khoa();
        khoa.setId(1);
        khoa.setTen("khoa 1");
        khoa.setMota("mo ta 1");
        khoa.setTruongID(1);
        khoaList.add(khoa);

        khoaRes.setListKhoa(khoaList);

        BaseRequestData<KhoaReq> baseRequestData = new BaseRequestData();
        baseRequestData.setWsRequest(new KhoaReq());

        doReturn(khoaRes).when(khoaService).getListKhoa();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/getListKhoa"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.wsResponse.listKhoa[0].id", is(1)))
                .andExpect(jsonPath("$.wsResponse.listKhoa[0].ten", is("khoa 1")))
                .andExpect(jsonPath("$.wsResponse.listKhoa[0].mota", is("mo ta 1")))
                .andExpect(jsonPath("$.wsResponse.listKhoa[0].truongID", is(1)))
                .andExpect(jsonPath("$.wsResponse.khoa", nullValue()));
    }

}
