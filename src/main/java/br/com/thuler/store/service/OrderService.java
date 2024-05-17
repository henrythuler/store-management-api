package br.com.thuler.store.service;

import br.com.thuler.store.model.entities.Order;
import br.com.thuler.store.exceptions.NotFoundException;
import br.com.thuler.store.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order create(Order order){
        return orderRepository.save(order);
    }

    public Order findById(Integer id){

        Optional<Order> foundOrder = orderRepository.findById(id);

        return foundOrder.orElseThrow(() -> new NotFoundException("Order"));

    }

    public List<Order> findAll(){

        List<Order> orders = orderRepository.findAll();

        if(!orders.isEmpty()){
            return orders;
        }else{
            throw new NotFoundException("Orders");
        }

    }

    public Order update(Order order){

        Optional<Order> foundOrder = orderRepository.findById(order.getId());

        if(foundOrder.isPresent()){
            return orderRepository.save(order);
        }else{
            throw new NotFoundException("Order");
        }

    }

    public void delete(Integer id){

        Optional<Order> foundOrder = orderRepository.findById(id);

        orderRepository.delete(foundOrder.orElseThrow(() -> new NotFoundException("Order")));

    }

}
