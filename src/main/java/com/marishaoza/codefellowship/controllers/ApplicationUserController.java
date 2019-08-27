package com.marishaoza.codefellowship.controllers;

import com.marishaoza.codefellowship.models.ApplicationUser;
import com.marishaoza.codefellowship.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.Date;
import java.util.ArrayList;

@Controller
public class ApplicationUserController {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @PostMapping("/users")
    public RedirectView createUser(String username, String password, String firstName, String lastName, String bio, Date dateOfBirth){
        ApplicationUser newUser = new ApplicationUser(username, encoder.encode(password), firstName, lastName, bio, dateOfBirth);
        applicationUserRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // TODO: change to /myprofile
        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
}
