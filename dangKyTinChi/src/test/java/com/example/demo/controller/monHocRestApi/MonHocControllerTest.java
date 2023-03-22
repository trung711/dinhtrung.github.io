package com.example.demo.controller.monHocRestApi;

import com.example.demo.dto.request.BaseRequestData;
import com.example.demo.dto.request.monhoc.MonHocReq;
import com.example.demo.dto.response.monhoc.MonHocRes;
import com.example.demo.model.MonHoc;
import com.example.demo.service.MonHocService;
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

import static org.mockito.Mockito.doReturn;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.nullValue;

@SpringBootTest
@AutoConfigureMockMvc
public class MonHocControllerTest {
    @MockBean
    private MonHocService monHocService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST /api/getListMonHoc list isn't empty")
    void getMonHocList() throws Exception{
        MonHocRes monHocRes = new MonHocRes();

        List<MonHoc> monHocList = new ArrayList<>();

        MonHoc monHoc = new MonHoc();
        monHoc.setId(1);
        monHoc.setTen("mon hoc 1");
        monHoc.setSotinchi(3);
        monHoc.setMamonhoc("ma 1");
        monHoc.setMota("mo ta 1");
        monHoc.setIDtblbomon(1);
        monHocList.add(monHoc);

        monHoc = new MonHoc();
        monHoc.setId(2);
        monHoc.setTen("mon hoc 2");
        monHoc.setSotinchi(2);
        monHoc.setMamonhoc("ma 2");
        monHoc.setMota("mo ta 2");
        monHoc.setIDtblbomon(2);
        monHocList.add(monHoc);

        monHocRes.setListMonHoc(monHocList);

        doReturn(monHocRes).when(monHocService).getListMonHoc();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/getListMonHoc"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.wsResponse.listMonHoc[0].id", is(1)))
                .andExpect(jsonPath("$.wsResponse.listMonHoc[0].ten", is("mon hoc 1")))
                .andExpect(jsonPath("$.wsResponse.listMonHoc[0].sotinchi", is(3)))
                .andExpect(jsonPath("$.wsResponse.listMonHoc[0].mamonhoc", is("ma 1")))
                .andExpect(jsonPath("$.wsResponse.listMonHoc[0].mota", is("mo ta 1")))
                .andExpect(jsonPath("$.wsResponse.listMonHoc[0].idtblbomon", is(1)))
                .andExpect(jsonPath("$.wsResponse.listMonHoc[1].id", is(2)))
                .andExpect(jsonPath("$.wsResponse.listMonHoc[1].ten", is("mon hoc 2")))
                .andExpect(jsonPath("$.wsResponse.listMonHoc[1].sotinchi", is(2)))
                .andExpect(jsonPath("$.wsResponse.listMonHoc[1].mamonhoc", is("ma 2")))
                .andExpect(jsonPath("$.wsResponse.listMonHoc[1].mota", is("mo ta 2")))
                .andExpect(jsonPath("$.wsResponse.listMonHoc[1].idtblbomon", is(2)));
    }

    @Test
    @DisplayName("POST /api/getListMonHoc list is empty")
    void getEmptyMonHocList() throws Exception{
        MonHocRes monHocRes = new MonHocRes();

        doReturn(monHocRes).when(monHocService).getListMonHoc();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/getListMonHoc"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$wsResponse.listMonHoc", nullValue()));
    }

    @Test
    @DisplayName("POST /api/getListMonHoc list have one item")
    void getMonHocList1() throws Exception{
        MonHocRes monHocRes = new MonHocRes();

        List<MonHoc> monHocList = new ArrayList<>();

        MonHoc monHoc = new MonHoc();
        monHoc.setId(1);
        monHoc.setTen("mon hoc 1");
        monHoc.setSotinchi(3);
        monHoc.setMamonhoc("ma 1");
        monHoc.setMota("mo ta 1");
        monHoc.setIDtblbomon(1);
        monHocList.add(monHoc);

        monHocRes.setListMonHoc(monHocList);

        doReturn(monHocRes).when(monHocService).getListMonHoc();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/getListMonHoc"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.wsResponse.listMonHoc[0].id", is(1)))
                .andExpect(jsonPath("$.wsResponse.listMonHoc[0].ten", is("mon hoc 1")))
                .andExpect(jsonPath("$.wsResponse.listMonHoc[0].sotinchi", is(3)))
                .andExpect(jsonPath("$.wsResponse.listMonHoc[0].mamonhoc", is("ma 1")))
                .andExpect(jsonPath("$.wsResponse.listMonHoc[0].mota", is("mo ta 1")))
                .andExpect(jsonPath("$.wsResponse.listMonHoc[0].idtblbomon", is(1)));
    }

}
