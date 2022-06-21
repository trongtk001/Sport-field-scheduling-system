package com.example.sportfieldschedulingsystem.api;

import com.example.sportfieldschedulingsystem.dto.UserDTO;
import com.example.sportfieldschedulingsystem.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = (3600))
@RestController
@RequestMapping("/api/users")
public class UserAPI {

    private UserService userService;

    public UserAPI(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public UserDTO getUserInfo(@PathVariable("id") long id) {

        UserDTO dto = userService.getInfo(id);
        return dto;

    }

    @PutMapping("{id}")
    @PreAuthorize("#dto.userName.equals(authentication.principal)")
    public UserDTO editUserInfo(@PathVariable("id") long id,
                                @RequestBody UserDTO dto) {

        dto.setId(id);
        dto.setUserName(null);
        dto.setPassword(null);

        return userService.save(dto);

    }
}
