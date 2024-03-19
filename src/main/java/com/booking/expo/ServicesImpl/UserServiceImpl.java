package com.booking.expo.ServicesImpl;

import com.booking.expo.Entities.User;
import com.booking.expo.Repositories.UserRepository;
import com.booking.expo.Services.UserService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e){
            throw new RuntimeException("Failed to create user" + e);
        }
    }

    @Override
    public User updateUser(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if(optionalUser.isPresent()) {
            User newUser = optionalUser.get();
            newUser.setName(user.getName());
            newUser.setAge(user.getAge());
            newUser.setEmail(user.getEmail());
            newUser.setAddress(user.getAddress());
            userRepository.save(newUser);
            return newUser;
        } else {
            throw new IllegalArgumentException("User with "+ user.getId() + "doesn't exist");
        }
    }

    @Override
    public User showUser(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseThrow(() -> new RuntimeException("User not found with userId:" + id));
    }

    @Override
    public List<User> showAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            userRepository.deleteById(id);
        } else throw new RuntimeException("User not found with userId:" + id);
    }


}
