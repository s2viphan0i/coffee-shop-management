package com.csm.controller;

import com.csm.factory.ResponseFactory;
import com.csm.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/promote/{id}")
    public ResponseEntity<?> promoteUser(@PathVariable Long id) throws Exception {
        customUserDetailsService.promoteUser(id);
        return ResponseFactory.success();
    }
}
