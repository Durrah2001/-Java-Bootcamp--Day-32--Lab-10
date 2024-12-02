package org.example.jobseekingsystem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.jobseekingsystem.ApiResponse.ApiResponse;
import org.example.jobseekingsystem.Model.User;
import org.example.jobseekingsystem.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/job-system/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getUsers(){

        return ResponseEntity.status(200).body(userService.getUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors) {

        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());


        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added successfully!"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors){

        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        Boolean isUpdated = userService.updateUser(id, user);

        if(isUpdated)
            return ResponseEntity.status(200).body(new ApiResponse("User updated successfully!"));

        return ResponseEntity.status(400).body(new ApiResponse("User with this ID not found!"));


    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){

        Boolean isDeleted = userService.deleteUser(id);

        if(isDeleted)
            return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully!"));

        return ResponseEntity.status(400).body(new ApiResponse("User with this ID not found!"));


    }
















}//End controller
