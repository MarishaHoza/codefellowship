package com.marishaoza.codefellowship.controllers;

import com.marishaoza.codefellowship.models.ApplicationUser;
import com.marishaoza.codefellowship.models.ApplicationUserRepository;
import com.marishaoza.codefellowship.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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

        // auto login on signup
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new RedirectView("/myprofile");
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/myprofile")
    public String getMyProfile(Principal p, Model m) {
        m.addAttribute("profile", applicationUserRepository.findByUsername(p.getName()));
        m.addAttribute("me", applicationUserRepository.findByUsername(p.getName()));
        m.addAttribute("user", p);
        return "oneuser";
    }

    @GetMapping("/users/{id}")
    public String getOneUser(@PathVariable long id, Principal p, Model m) {
        m.addAttribute("profile", applicationUserRepository.findById(id).get());
        m.addAttribute("me", applicationUserRepository.findByUsername(p.getName()));
        m.addAttribute("user", p);
        return "oneuser";
    }

    @GetMapping("/users")
    public String getAllUsers(Model m, Principal p) {
        m.addAttribute("user", p);
        m.addAttribute("allAppUsers", applicationUserRepository.findAll());
        return "allusers";
    }

    @PostMapping("/users/follow")
    public RedirectView followAUser(long id, Principal p) {
        ApplicationUser loggedInUser = applicationUserRepository.findByUsername(p.getName());
        ApplicationUser followedUser = applicationUserRepository.findById(id).get();
        loggedInUser.addUserIFollow(followedUser);
        applicationUserRepository.save(loggedInUser);
        return new RedirectView("/feed");
    }

    @PostMapping("/users/unfollow")
    public RedirectView unfollowUser(long id, Principal p) {
        ApplicationUser loggedInUser = applicationUserRepository.findByUsername(p.getName());
        ApplicationUser followedUser = applicationUserRepository.findById(id).get();
        loggedInUser.removeUserIFollow(followedUser);
        applicationUserRepository.save(loggedInUser);
        return new RedirectView("/feed");
    }

    @GetMapping("/feed")
    public String getFeed(Principal p, Model m) {
        ApplicationUser loggedInUser = applicationUserRepository.findByUsername(p.getName());
        Set<ApplicationUser> usersWhoIFollow = loggedInUser.getUsersWhoIFollow();
        List<Post> followedPosts = new LinkedList<>();
        for ( ApplicationUser user : usersWhoIFollow ) {
            followedPosts.addAll( user.getPosts() );
        }
        m.addAttribute("feed", followedPosts);
        m.addAttribute("user", p);
        return "feed";
    }
}
