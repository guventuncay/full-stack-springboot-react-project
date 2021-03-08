package com.wut.demo.service;

import com.wut.demo.entity.Comment;
import com.wut.demo.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getBooks() {
        return this.commentRepository.findAll();
    }

    public void postComment(Comment comment) {
        this.commentRepository.save(comment);
    }
}
