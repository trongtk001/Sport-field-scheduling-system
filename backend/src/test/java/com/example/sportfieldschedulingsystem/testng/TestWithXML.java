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
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@SpringBootTest(classes = SportFieldSchedulingSystemApplication.class)
public class TestWithXML extends AbstractTestNGSpringContextTests {

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

    @Test(dataProvider = "register-data-provider", dataProviderClass = com.example.sportfieldschedulingsystem.testng.Parameters.class)
    public void testRegisterSuccessful(String username, String password, String fullName, String phone, String gmail, int status, String[] roles) throws Exception {

        ObjectNode userJson = mapper.createObjectNode();

        userJson.put("userName", username);
        userJson.put("password", password);
        userJson.put("fullName", fullName);
        userJson.put("phone", phone);
        userJson.put("gmail", gmail);
        userJson.put("status", status);
        ArrayNode roleNode = mapper.createArrayNode();
        for (String role : roles) {
            roleNode.add(role);
        }
        userJson.set("roles", roleNode);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsBytes(userJson)))
                .andExpect(status().isOk());
    }

    @Test(dataProvider = "register-data-provider", dataProviderClass = com.example.sportfieldschedulingsystem.testng.Parameters.class)
    public void testRegisterFailed(String username, String password, String fullName, String phone, String gmail, int status, String[] roles) throws Exception {

        ObjectNode userJson = mapper.createObjectNode();

        userJson.put("userName", username);
        userJson.put("password", password);
        userJson.put("fullName", fullName);
        userJson.put("phone", phone);
        userJson.put("gmail", gmail);
        userJson.put("status", status);
        ArrayNode roleNode = mapper.createArrayNode();
        for (String role : roles) {
            roleNode.add(role);
        }
        userJson.set("roles", roleNode);

        List<String> errorMsg = new ArrayList<String>(){
            {
                add("User with the same identity already exists");
                add("Require username, password and status parameter");
            }
        };


        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsBytes(userJson)))
                .andExpect(result -> assertTrue(errorMsg.contains(result.getResponse().getErrorMessage())));
    }



    @Test(dependsOnMethods = "testRegisterSuccessful")
    @org.testng.annotations.Parameters({"username", "password"})
    public void testLoginSuccessful(String username, String password){
        try {
            username = null != username ? username : "user";
            password = null != password ? password : "password";

            ObjectNode userJson = mapper.createObjectNode();
            userJson.put("username", username);
            userJson.put("password", password);

            mockMvc.perform(post("/api/auth/login")
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(mapper.writeValueAsBytes(userJson)))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @org.testng.annotations.Parameters({"username", "password"})
    public void testLoginFailed(String username, String password) {
        try {
            username = null != username ? username : "user";
            password = null != password ? password : "password1";

            ObjectNode userJson = mapper.createObjectNode();
            userJson.put("username", username);
            userJson.put("password", password);

            mockMvc.perform(post("/api/auth/login")
                            .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(userJson)))
                    .andExpect(status().isForbidden())
                    .andExpect(result -> assertEquals("Access Denied", result.getResponse().getErrorMessage()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLogout() {
        try {
            mockMvc.perform(post("/api/auth/logout"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.message").value("You've been signed out!"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
