package com.wut.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String author;

    private int pageCount;

    private LocalDate releaseDate;

    private String imageUrl;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<Comment> comments;

    public Book() {
    }

    public Book(String name, String author, int pageCount, LocalDate releaseDate) {
        this.name = name;
        this.author = author;
        this.pageCount = pageCount;
        this.releaseDate = releaseDate;
    }


}
