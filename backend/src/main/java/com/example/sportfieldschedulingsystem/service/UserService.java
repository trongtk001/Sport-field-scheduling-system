package com.example.sportfieldschedulingsystem.service;

import com.example.sportfieldschedulingsystem.dto.UserDTO;

public interface UserService extends BaseService<UserDTO>{

    UserDTO login(String username, String password);

    UserDTO getInfo(long id);

    UserDTO getInfoByUsername(String username);

}
