package com.example.iprwcbe.controller;

import com.example.iprwcbe.DAO.UserDAO;
import com.example.iprwcbe.exceptions.RecordNotFoundException;
import com.example.iprwcbe.model.database.User;
import com.example.iprwcbe.model.http.ErrorResponse;
import com.example.iprwcbe.services.ReflectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserDAO userDAO;
    private final ReflectionService reflectionService;
    public UserController(UserDAO userDAO, ReflectionService reflectionService) {
        this.userDAO = userDAO;
        this.reflectionService = reflectionService;
    }

    @GetMapping("/me")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<User> getMe(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userDAO.getById(user.getId()).orElseThrow(()->new RecordNotFoundException("User with id: " + user.getId() + " not found")));
    }

    @DeleteMapping("/deleteMe")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<?> deleteMe(@AuthenticationPrincipal User user) {
        try {
            userDAO.deleteById(user.getId());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@AuthenticationPrincipal User user, @PathVariable("id") String id, @RequestBody Map<Object, Object> userToUpdate) {


        if (user.getId().equals(id)) {
            User foundUser = userDAO.getById(id).orElseThrow(()-> new RecordNotFoundException("Location with id: " + id + " not found"));
            try {
                return ResponseEntity.ok(userDAO.save(ReflectionService.patchEntity(foundUser, userToUpdate, User.class)));
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Invalid data", List.of()));
            }

        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
