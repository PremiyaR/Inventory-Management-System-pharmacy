package com.pilot.inventory.controller;

import com.pilot.inventory.dto.UsersDto;
import com.pilot.inventory.model.Users;
import com.pilot.inventory.service.UsersService;
import com.pilot.inventory.util.EndPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping(EndPoint.USERS)
public class UsersController {
    @Autowired
    private UsersService usersService;

    @PostMapping
    public ResponseEntity<String> addUsers(@RequestBody Users users)
    {
        usersService.addUsers(users);
        return new ResponseEntity<>("Added successfully", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateUsers(@RequestBody Users users)
    {
        usersService.updateUsers(users);
        return new ResponseEntity<>("Update successful",HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUsers(@PathVariable int id)
    {
        usersService.deleteUsers(id);
        return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UsersDto>> displayAllUsers()
    {
        List<UsersDto> users=usersService.displayAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
