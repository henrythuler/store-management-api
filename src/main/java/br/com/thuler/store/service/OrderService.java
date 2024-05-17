package br.com.thuler.store.service;

import br.com.thuler.store.exceptions.DatabaseException;
import br.com.thuler.store.model.entities.Order;
import br.com.thuler.store.exceptions.NotFoundException;
import br.com.thuler.store.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

        try{

            Order foundOrder = orderRepository.getReferenceById(order.getId());
            foundOrder.setOrderStatus(order.getOrderStatus());

            return orderRepository.save(foundOrder);

        }catch(EntityNotFoundException e){
            throw new NotFoundException("Order");
        }

    }

    public void deleteById(Integer id){

        try{
            if(!orderRepository.existsById(id)) throw new NotFoundException("Order");
            orderRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }

    }

}
