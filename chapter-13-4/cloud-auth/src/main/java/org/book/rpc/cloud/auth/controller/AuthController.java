package org.book.rpc.cloud.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AuthController {

    @GetMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }

}
