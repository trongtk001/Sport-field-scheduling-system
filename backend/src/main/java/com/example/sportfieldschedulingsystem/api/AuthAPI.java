package com.example.sportfieldschedulingsystem.api;

import com.example.sportfieldschedulingsystem.api.input.LoginInput;
import com.example.sportfieldschedulingsystem.api.output.ResponseMessage;
import com.example.sportfieldschedulingsystem.dto.UserDTO;
import com.example.sportfieldschedulingsystem.security.JwtUtils;
import com.example.sportfieldschedulingsystem.security.UserDetailsImpl;
import com.example.sportfieldschedulingsystem.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = (3600))
@RestController
@RequestMapping("api/auth")
@PreAuthorize("permitAll()")
public class AuthAPI {

    private UserService userService;

    private AuthenticationManager authenticationManager;

    private JwtUtils jwtUtils;

    public AuthAPI(UserService userService, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/register")
    // @PreAuthorize("!(#dto.roles.contains('ADMIN'))")
    public UserDTO registerUser(@RequestBody UserDTO dto) {


        if (dto.getRoles().isEmpty() || dto.getRoles().equals("null")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Require role parameter");
        }
        if (dto.getUserName().isEmpty() || "null".equals(dto.getUserName()) || dto.getPassword().isEmpty() ||  "null".equals(dto.getPassword()) || "null".equals(dto.getStatus())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Require username, password and status parameter");
        }

        UserDTO user = null;

        user = userService.createNew(dto);

        return user;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginInput loginInput) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginInput.getUsername(), loginInput.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        UserDTO dto = new UserDTO();
        dto.setId(userDetails.getId());
        dto.setUserName(userDetails.getUsername());
        dto.setFullName(userDetails.getFullname());
        dto.setGmail(userDetails.getEmail());
        dto.setPhone(userDetails.getPhone());
        dto.setStatus(userDetails.getStatus());
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
        dto.setRoles(roles);

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(dto);

    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(HttpServletRequest request) {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new ResponseMessage(200, "You've been signed out!", request.getRequestURI()));
    }

}
