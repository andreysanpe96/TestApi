package com.service.wolox.api.controller.comment;

import com.service.wolox.api.exception.ApiWoloxException;
import com.service.wolox.api.model.comment.Comment;
import com.service.wolox.api.service.comment.CommentServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentServiceInterface commentService;

    public CommentController(CommentServiceInterface commentService) {
        this.commentService = commentService;
    }

    @Operation(summary = "Method to search by id a comment")
    @GetMapping("/{id}")
    public ResponseEntity<Comment> comment(@PathVariable Long id) throws ApiWoloxException {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.findById(id));
    }

    @Operation(summary = "Method to search all comments")
    @GetMapping
    public ResponseEntity<List<Comment>> comments() throws ApiWoloxException {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.findAll());
    }

    @Operation(summary = "Method to filter by name or userId")
    @GetMapping("/filter")
    public ResponseEntity<List<Comment>> filterComment(
            @RequestParam(required = false) String name, @RequestParam(required = false) Long userId) {

        return ResponseEntity.status(HttpStatus.OK).body(commentService.findByUserOrName(userId, name));
    }
}
