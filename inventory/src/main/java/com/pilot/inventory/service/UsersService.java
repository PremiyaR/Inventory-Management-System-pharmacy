package com.pilot.inventory.service;

import com.pilot.inventory.model.entity.Orders;
import com.pilot.inventory.model.entity.Users;
import org.apache.catalina.User;

import java.util.List;

public interface UsersService {
    public Users addUsers(Users users);
    public Users updateUsers(Users users);
    public String deleteUsers(int id);
    public List<Users> displayAllUsers();

}
