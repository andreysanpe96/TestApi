package com.service.wolox.api.controller.album;

import com.service.wolox.api.enums.album.Action;
import com.service.wolox.api.exception.ApiWoloxException;
import com.service.wolox.api.model.album.AlbumPermissionsDTO;
import com.service.wolox.api.service.album.AlbumPermissionServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albumPermissions")
public class AlbumPermissionsController {

    private final AlbumPermissionServiceInterface albumPermissionsService;

    public AlbumPermissionsController(AlbumPermissionServiceInterface albumPermissionsService) {
        this.albumPermissionsService = albumPermissionsService;
    }

    @Operation(summary = "Method to save permissions")
    @PostMapping
    public ResponseEntity<AlbumPermissionsDTO> saveAlbumRoles(@RequestBody AlbumPermissionsDTO albumPermissionsDTO) throws ApiWoloxException {
            return ResponseEntity.status(HttpStatus.CREATED).body(albumPermissionsService.create(albumPermissionsDTO));
    }

    @Operation(summary = "Method to update permissions")
    @PutMapping
    public ResponseEntity<AlbumPermissionsDTO> updateAlbumRoles(@RequestBody AlbumPermissionsDTO albumPermissionsDTO) throws ApiWoloxException {
        return ResponseEntity.status(HttpStatus.OK).body(albumPermissionsService.update(albumPermissionsDTO));
    }

    @Operation(summary = "Method to find the permissions of a user by albumId or action")
    @GetMapping
    public ResponseEntity<List<AlbumPermissionsDTO>> findAllUser
            (@RequestParam Long albumId, @RequestParam Action action) throws ApiWoloxException {
        return ResponseEntity.status(HttpStatus.OK).body(albumPermissionsService.findByAlbumIdAndActions(albumId, action));
    }
}
