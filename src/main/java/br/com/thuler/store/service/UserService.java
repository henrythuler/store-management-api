package br.com.thuler.store.service;

import br.com.thuler.store.exceptions.DatabaseException;
import br.com.thuler.store.model.entities.User;
import br.com.thuler.store.exceptions.NotFoundException;
import br.com.thuler.store.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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

        return foundUser.orElseThrow(() -> new NotFoundException("User"));

    }

    public List<User> findAll(){

        List<User> users = userRepository.findAll();

        if(!users.isEmpty()){
            return users;
        }else{
            throw new NotFoundException("Users");
        }

    }

    public User update(User user){

        try{
            User foundUser = userRepository.getReferenceById(user.getId());

            foundUser.setName(user.getName());
            foundUser.setEmail(user.getEmail());
            foundUser.setPhone(user.getPhone());
            foundUser.setPassword(user.getPassword());

            return userRepository.save(foundUser);
        }catch(EntityNotFoundException e){
            throw new NotFoundException("User");
        }

    }

    public void delete(Integer id){

        try{
            userRepository.deleteById(id);
        }catch(EmptyResultDataAccessException e){
            throw new NotFoundException("User");
        }catch(DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }

    }

}
