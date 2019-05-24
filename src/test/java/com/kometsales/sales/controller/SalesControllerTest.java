package com.kometsales.sales.controller;

import com.kometsales.sales.model.Seller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import java.util.List;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@RunWith(SpringRunner.class)
@WebMvcTest(SalesController.class)
public class SalesControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SalesController salesController;

    @Test
    public void getAllSeller() throws Exception {
        Seller seller = new Seller();
        seller.setName("Daniel De La Pava Suarez");

        List<Seller> sellerList = Collections.singletonList(seller);

        given(salesController.getAllSeller()).willReturn(sellerList);

        mvc.perform(get("/seller/findall/")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(seller.getName())));
    }

    @Test
    public void getSellerById() throws Exception {
        Seller seller = new Seller();
        seller.setId(1L);
        seller.setName("Daniel De La Pava Suarez");

        given(salesController.getSellerById(seller.getId())).willReturn(seller);

        mvc.perform(get("/seller/" + seller.getId())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is(seller.getName())));
    }

    @Test
    public void upLoadSales() throws Exception {

        MockMultipartFile file = new MockMultipartFile("file", "hello.csv", "text/csv", "05-02-2019 17:01:35,1,10,30000".getBytes());
        mvc.perform(multipart("/load").file(file))
                .andDo(print())
                .andExpect(status().isOk());

        then(this.salesController).should().upLoadSales(file);
    }
}