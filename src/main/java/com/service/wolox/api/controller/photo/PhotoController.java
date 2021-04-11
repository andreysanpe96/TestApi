package com.service.wolox.api.controller.photo;

import com.service.wolox.api.exception.ApiWoloxException;
import com.service.wolox.api.model.photo.Photo;
import com.service.wolox.api.service.photo.PhotoServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/photos")
public class PhotoController {

    private final PhotoServiceInterface photoService;

    public PhotoController(PhotoServiceInterface photoService) {
        this.photoService = photoService;
    }

    @Operation(summary = "Method to search photo by id")
    @GetMapping("/{id}")
    public ResponseEntity<Photo> photo(@PathVariable Long id) throws ApiWoloxException {
        return ResponseEntity.status(HttpStatus.OK).body(photoService.findById(id));
    }

    @Operation(summary = "Method to search all photos")
    @GetMapping
    public ResponseEntity<List<Photo>> photos() throws ApiWoloxException {
        return ResponseEntity.status(HttpStatus.OK).body(photoService.findAll());
    }


    //Plus
    @Operation(summary = "Method to search all photos by userId")
    @GetMapping("/byUser")
    public ResponseEntity<List<Photo>> findByUserId(@RequestParam(value = "userId") Long userId) throws ApiWoloxException {
        return ResponseEntity.status(HttpStatus.OK).body(photoService.findByUserId(userId));
    }
}
