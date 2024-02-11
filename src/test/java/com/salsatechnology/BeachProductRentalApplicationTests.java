package com.salsatechnology;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Sql("/dataset.sql")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class BeachProductRentalApplicationTests {

    @Autowired
    protected MockMvc mockMvc;

    protected String getContentFromResource() {
        try {
            InputStream stream = ResourceUtils.class.getResourceAsStream("/json/create-order.json");
            return StreamUtils.copyToString(stream, StandardCharsets.UTF_8);
        } catch (IOException ignored) {
            return null;
        }
    }

}
