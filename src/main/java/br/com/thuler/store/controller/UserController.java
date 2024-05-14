package br.com.thuler.store.controller;

import br.com.thuler.store.model.entities.User;
import br.com.thuler.store.model.exceptions.NotFoundException;
import br.com.thuler.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id){
        try{
            return ResponseEntity.ok().body(userService.findById(id));
        }catch(NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        try{
            return ResponseEntity.ok().body(userService.findAll());
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody User user){
        try{
            return ResponseEntity.ok().body(userService.update(user));
        }catch(NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        try{
            userService.delete(id);
            return ResponseEntity.noContent().build();
        }catch(NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

}
