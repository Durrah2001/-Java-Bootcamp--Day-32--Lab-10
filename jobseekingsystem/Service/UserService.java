package org.example.jobseekingsystem.Service;

import lombok.RequiredArgsConstructor;
import org.example.jobseekingsystem.Model.User;
import org.example.jobseekingsystem.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;


    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){

        userRepository.save(user);

    }


    public Boolean updateUser(Integer id, User user){

        User oldUser = userRepository.getById(id);

        if(oldUser == null) //User with this id not found in user table
            return false;


        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        oldUser.setRole(user.getRole());
        oldUser.setAge(user.getAge());

        userRepository.save(oldUser);
        return true;

    }

    public Boolean deleteUser(Integer id){

        User oldUser = userRepository.getById(id);

        if(oldUser == null)
            return false;

        userRepository.delete(oldUser);
        return true;

    }












}//End service
