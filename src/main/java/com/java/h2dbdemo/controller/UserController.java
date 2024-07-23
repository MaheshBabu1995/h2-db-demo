package com.java.h2dbdemo.controller;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.h2dbdemo.model.User;
// import com.java.h2dbdemo.repo.UserRepo;
import com.java.h2dbdemo.repo.UserRepoTwo;

@RestController
@RequestMapping(path = "api/v1/users/")
public class UserController {
    // private final UserRepo userRepo;
    private final UserRepoTwo userRepo;

    @Autowired
    public UserController(UserRepoTwo userRepo) {
        this.userRepo = userRepo;
    }

    /*
     * @PostMapping(path = "saveuser")
     * public ResponseEntity<User> addUser(@RequestBody User user) {
     * 
     * try {
     * User user2 = null;
     * if (Objects.nonNull(user)) {
     * user2 = new
     * User().builder().name(user.getName()).city(user.getCity()).build();
     * 
     * }
     * return ResponseEntity.ok(userRepo.saveAndFlush(user2));
     * 
     * } catch (Exception e) {
     * throw new RuntimeException(e.getLocalizedMessage());
     * }
     * 
     * }
     * }
     */
    @PostMapping(path = "saveuser")
    public ResponseEntity<Object> addUser(@RequestBody User user) {

        try {
            User user2 = null;
            if (Objects.nonNull(user)) {
                String uuid = UUID.randomUUID().toString();
                user2 = new User().builder().sno(uuid).name(user.getName()).city(user.getCity()).build();

            }
            Integer a = userRepo.saveAndFlush(user2);
            return ResponseEntity.ok(a);

        } catch (Exception e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }

    }

    @GetMapping("finduser/{name}")
    public ResponseEntity<Object> findByEntity(@PathVariable("name") String name) {
        try {
            User user = null;
            if (Objects.nonNull(name)) {
                user = (User) userRepo.findByUserByName(name);
            }
            return ResponseEntity.ok(user);

        } catch (Exception e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }
}
