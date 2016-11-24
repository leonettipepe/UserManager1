package net.leonetti.usermanager.service;

import net.leonetti.usermanager.model.User;

import java.util.List;

/**
 * Created by Lenovo on 28.09.2016.
 */
public interface UserService {

    public void addUser(User user);
    public void updateUser(User user);
    public void deleteUser(int id);
    public List<User> listUsers();
    public User getUser(int id);
    public List<User> searchByName(String name);
  /*  public List<User> getAllUsers(String userName);*/
}
