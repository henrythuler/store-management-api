package br.com.thuler.store.controller;

import br.com.thuler.store.model.entities.User;
import br.com.thuler.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user){
        return userService.create(user);
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User findById(@PathVariable Integer id){
        return userService.findById(id);
    }

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public List<User> findAll(){
        return userService.findAll();
    }

    @PutMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public User update(User user){
        return userService.update(user);
    }

    @DeleteMapping("/user")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Integer id){
        userService.delete(id);
    }

}
