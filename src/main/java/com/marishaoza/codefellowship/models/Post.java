package com.marishaoza.codefellowship.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String title;
    @Column(columnDefinition = "text") // https://docs.oracle.com/javaee/5/api/javax/persistence/Column.html#columnDefinition()
    String body;
    Timestamp createdAt;
    @ManyToOne
    ApplicationUser author;


    // ---------------------------- Constructors -------------------------------

    public Post(String title, String body, Timestamp createdAt, ApplicationUser author){
        this.title = title;
        this.body = body;
        this.createdAt = createdAt;
        this.author = author;
    }

    public Post(){}


    // ---------------------------- Getters & Setters -------------------------------

    public long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getBody() {
        return this.body;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public ApplicationUser getAuthor() {
        return this.author;
    }

    // ---------------------------- Methods -------------------------------

    public String toString(){
        return "A post by " + this.author.getUsername();
    }

}
