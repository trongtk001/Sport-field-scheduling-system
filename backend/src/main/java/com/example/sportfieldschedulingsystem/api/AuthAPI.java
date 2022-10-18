package com.example.sportfieldschedulingsystem.api;

import com.example.sportfieldschedulingsystem.api.input.LoginInput;
import com.example.sportfieldschedulingsystem.api.output.ResponseMessage;
import com.example.sportfieldschedulingsystem.dto.UserDTO;
import com.example.sportfieldschedulingsystem.security.JwtUtils;
import com.example.sportfieldschedulingsystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:3000/", maxAge = (3600), allowCredentials = "true")
@RestController
@RequestMapping("api/auth")
@PreAuthorize("permitAll()")
public class AuthAPI {

    private UserService userService;


    private final ModelMapper modelMapper;

    private JwtUtils jwtUtils;

    public AuthAPI(UserService userService, ModelMapper modelMapper, JwtUtils jwtUtils) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/register")
    @PreAuthorize("!(#dto.roles.contains('ADMIN'))")
    public UserDTO registerUser(@RequestBody UserDTO dto) {

        if (dto.getRoles().isEmpty() || null == dto.getRoles()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Require role parameter");
        }
        if (dto.getUserName().isEmpty() || null == dto.getUserName() || dto.getPassword().isEmpty() ||  null == dto.getPassword() || "null".equals(dto.getStatus())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Require username, password and status parameter");
        }

        UserDTO user = userService.save(dto);

        return user;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginInput loginInput) {
        UserDTO dto = userService.login(loginInput.getUsername(), loginInput.getPassword());
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(dto.getUserName());
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(dto);

    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(HttpServletRequest request) {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new ResponseMessage(200, "You've been signed out!", request.getRequestURI()));
    }

}
