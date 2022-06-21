package com.example.sportfieldschedulingsystem.service;

import com.example.sportfieldschedulingsystem.dto.UserDTO;

public interface UserService extends BaseService<UserDTO>{

    UserDTO getInfo(long id);


}
