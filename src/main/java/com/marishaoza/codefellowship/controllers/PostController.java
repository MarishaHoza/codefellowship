package com.marishaoza.codefellowship.controllers;

import com.marishaoza.codefellowship.models.ApplicationUser;
import com.marishaoza.codefellowship.models.ApplicationUserRepository;
import com.marishaoza.codefellowship.models.Post;
import com.marishaoza.codefellowship.models.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Controller
public class PostController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PostRepository postRepository;


    @GetMapping("/posts/create")
    public String getPostForm(Principal p, Model m) {
        m.addAttribute("user", p);
        return "postform";
    }


    @PostMapping("/posts/create")
    public RedirectView createPost(String title, String body, Principal p, Model m) {
        ApplicationUser loggedInUser = applicationUserRepository.findByUsername(p.getName());

        Timestamp timeStamp = Timestamp.valueOf(LocalDateTime.now());
        Post newPost = new Post(title, body, timeStamp, loggedInUser);

        postRepository.save(newPost);


        m.addAttribute("profile", loggedInUser);
        m.addAttribute("user", p);


        return new RedirectView("/myprofile");
    }
}
