package com.example.sportfieldschedulingsystem.testng;

import com.example.sportfieldschedulingsystem.SportFieldSchedulingSystemApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@SpringBootTest(classes = SportFieldSchedulingSystemApplication.class)
public class TestAPI extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private ObjectMapper mapper;

    @BeforeClass
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
        mapper = new ObjectMapper();
    }

    @Test
    public void testLoginSuccessful(){
        try {
            ObjectNode userJson = mapper.createObjectNode();
            userJson.put("username", "admin");
            userJson.put("password", "password");

            mockMvc.perform(post("/api/auth/login")
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(mapper.writeValueAsBytes(userJson)))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLoginFailed() {
        try {
            ObjectNode userJson = mapper.createObjectNode();
            userJson.put("username", "admin");
            userJson.put("password", "password1");

            mockMvc.perform(post("/api/auth/login")
                            .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(userJson)))
                    .andExpect(status().isForbidden())
                    .andExpect(result -> assertEquals("Access Denied", result.getResponse().getErrorMessage()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
