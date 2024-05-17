package br.com.thuler.store.controller;

import br.com.thuler.store.model.entities.Order;
import br.com.thuler.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody Order order){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(orderService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Order>> findAll(){
        return ResponseEntity.ok().body(orderService.findAll());
    }

    @PutMapping
    public ResponseEntity<Order> update(@RequestBody Order order){
        return ResponseEntity.ok().body(orderService.update(order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        orderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
