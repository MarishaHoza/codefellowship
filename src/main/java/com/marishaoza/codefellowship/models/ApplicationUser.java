package com.marishaoza.codefellowship.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String username;
    String password;
    String firstName;
    String lastName;
    String bio;
    Date dateOfBirth;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author")
    List<Post> posts;
    @ManyToMany
    @JoinTable(
            name="user_follows",
            joinColumns = { @JoinColumn(name="primaryUser") },
            inverseJoinColumns = { @JoinColumn(name = "followedUser") }
    )
    Set<ApplicationUser> usersWhoIFollow;
    @ManyToMany( mappedBy = "usersWhoIFollow" )
    Set<ApplicationUser> usersWhoFollowMe;



    // ---------------------------- Constructors -------------------------------

    public ApplicationUser(String username, String password, String firstName, String lastName, String bio, Date dateOfBirth) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.dateOfBirth = dateOfBirth;
    }

    public ApplicationUser(){}


    // ---------------------------- Getters & Setters -------------------------------

    public long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getBio() {
        return this.bio;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public List<Post> getPosts() {
        return this.posts;
    }

    public Set<ApplicationUser> getUsersWhoIFollow() {
        return this.usersWhoIFollow;
    }

    public Set<ApplicationUser> getUsersWhoFollowMe() {
        return this.usersWhoFollowMe;
    }

    // ---------------------------- Methods -------------------------------

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public void addUserIFollow(ApplicationUser followedUser){
        usersWhoIFollow.add(followedUser);
    }

    public void removeUserIFollow(ApplicationUser followedUser){
        usersWhoIFollow.remove(followedUser);
    }


    public String toString() {
        return String.format("%s (%s %s)", this.username, this.firstName, this.lastName);
    }
}
