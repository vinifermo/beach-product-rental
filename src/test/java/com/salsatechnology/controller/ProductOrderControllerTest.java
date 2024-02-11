package com.salsatechnology.controller;

import com.salsatechnology.BeachProductRentalApplicationTests;
import com.salsatechnology.model.ProductType;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.util.UriComponentsBuilder.fromPath;

public class ProductOrderControllerTest extends BeachProductRentalApplicationTests {

    private final String PRODUCT_ORDER_RESOURCE_PATH = "/orders";

    @Test
    public void shouldReturnCreatedWhenCreateProductOrder() throws Exception {
        String uri = fromPath(PRODUCT_ORDER_RESOURCE_PATH).toUriString();

        mockMvc.perform(post(uri)
                        .content(getContentFromResource())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnOkWhenGetProductOrderByUsername() throws Exception {
        String uri = fromPath(PRODUCT_ORDER_RESOURCE_PATH).toUriString();

        mockMvc.perform(get(uri)
                        .param("username", "LewisHamilton"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[0].productType").value(ProductType.SUNSHADE.name()))
                .andExpect(jsonPath("$[0].timeHour").value(16))
                .andExpect(jsonPath("$[0].productValue").value(4000))
                .andExpect(jsonPath("$[0].productTotal").value(64000))
                .andExpect(jsonPath("$[0].userAmount").value(6592))
                .andExpect(jsonPath("$[1].productType").value(ProductType.SURFBOARD.name()))
                .andExpect(jsonPath("$[1].timeHour").value(12))
                .andExpect(jsonPath("$[1].productValue").value(5000))
                .andExpect(jsonPath("$[1].productTotal").value(60000))
                .andExpect(jsonPath("$[1].userAmount").value(9360));
    }

    @Test
    public void shouldReturnNotFoundWhenGetProductOrderByNotExistingUsername() throws Exception {
        String uri = fromPath(PRODUCT_ORDER_RESOURCE_PATH).toUriString();

        mockMvc.perform(get(uri)
                        .param("username", "wrong"))
                .andExpect(status().isNotFound());
    }
}