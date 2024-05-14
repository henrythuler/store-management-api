package br.com.thuler.store.service;

import br.com.thuler.store.model.entities.User;
import br.com.thuler.store.model.exceptions.NotFoundException;
import br.com.thuler.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create(User user){
        return userRepository.save(user);
    }

    public User findById(Integer id){

        Optional<User> foundUser = userRepository.findById(id);

        if(foundUser.isPresent()){
            return foundUser.get();
        }else{
            throw new NotFoundException("User not found...");
        }

    }

    public List<User> findAll(){

        List<User> users = userRepository.findAll();

        if(!users.isEmpty()){
            return users;
        }else{
            throw new NotFoundException("There are no users...");
        }

    }

    public User update(User user){

        Optional<User> foundUser = userRepository.findById(user.getId());

        if(foundUser.isPresent()){
            return userRepository.save(user);
        }else{
            throw new NotFoundException("User not found...");
        }

    }

    public void delete(Integer id){

        Optional<User> foundUser = userRepository.findById(id);

        if(foundUser.isPresent()){
            userRepository.delete(foundUser.get());
        }else{
            throw new NotFoundException("User not found...");
        }

    }

}
