package com.wut.demo.controller;

import com.wut.demo.entity.Comment;
import com.wut.demo.service.CommentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/comments")
@CrossOrigin(origins = "http://localhost:3000")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getComments() {
        return this.commentService.getBooks();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void postComment(@RequestBody Comment comment) {
        this.commentService.postComment(comment);
    }


 /*   @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void postComment(Comment comment) {
        this.commentService.postComment(comment);
    }*/
}
