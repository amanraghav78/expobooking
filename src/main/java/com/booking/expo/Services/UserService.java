package com.booking.expo.Services;

import com.booking.expo.Entities.User;
import java.util.List;

public interface UserService {
    public User createUser(User user);
    public User updateUser(User user);
    public User showUser(Integer id);
    public List<User> showAllUsers();
    public void deleteUser(Integer id);
}
