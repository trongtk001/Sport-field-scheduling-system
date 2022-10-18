package com.example.sportfieldschedulingsystem.testng;

import org.testng.annotations.DataProvider;

public class Parameters {

    @DataProvider(name = "register-data-provider")
    public Object[][] registerData() {
        return new Object[][]{
                {"user1", "password", "user1", "0123456789", "user1@gmail.com", 1, new String[]{"USER"}},
                {"user2", "password", "user2", "0123456710", "user2@gmail.com", 1, new String[]{"USER"}}
        };
    }
}
