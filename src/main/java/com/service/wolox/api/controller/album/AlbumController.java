package com.service.wolox.api.controller.album;

import com.service.wolox.api.exception.ApiWoloxException;
import com.service.wolox.api.model.album.Album;
import com.service.wolox.api.service.album.AlbumServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumServiceInterface albumService;

    public AlbumController(AlbumServiceInterface albumService) {
        this.albumService = albumService;
    }

    @Operation(summary = "Method to find album by id")
    @GetMapping("/{id}")
    public ResponseEntity<Album> album(@PathVariable Long id) throws ApiWoloxException {
        return ResponseEntity.status(HttpStatus.OK).body(albumService.findById(id));
    }

    @Operation(summary = "Method to search all albums")
    @GetMapping
    public ResponseEntity<List<Album>> albums() throws ApiWoloxException {
        return ResponseEntity.status(HttpStatus.OK).body(albumService.findAll());
    }

    @Operation(summary = "Method to find all albums by userId")
    @GetMapping("/byUser")
    public ResponseEntity<List<Album>> albumsByUser(@RequestParam(value = "userId") Long userId) throws ApiWoloxException {
        return ResponseEntity.status(HttpStatus.OK).body(albumService.findByUserId(userId));
    }
}
