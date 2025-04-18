package com.example.mdd.controllers;

import com.example.mdd.dto.CommentRequest;
import com.example.mdd.dto.ResponseMessage;
import com.example.mdd.exceptions.NotFoundException;
import com.example.mdd.models.CommentEntity;
import com.example.mdd.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping
    public ResponseEntity<?> addMessage(@RequestBody CommentRequest message) throws NotFoundException {
        try {
            commentService.addComment(message.getComment(), message.getArticalId(), message.getUserId());
            ResponseMessage responseMessage = new ResponseMessage("Message send with success !");
            return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
        }catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyMap());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
        commentService.deleteById(id);
    }

    @GetMapping("/{id}")
    public List<CommentEntity> getCommentsByArticle(@PathVariable Integer id){
        return commentService.getCommentsByArticle(id);
    }
}
