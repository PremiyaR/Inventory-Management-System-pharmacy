package com.pilot.inventory.service;

import com.pilot.inventory.dto.UsersDto;
import com.pilot.inventory.model.Users;

import java.util.List;

public interface UsersService {
    public Users addUsers(Users users);
    public Users updateUsers(Users users);
    public String deleteUsers(int id);
    public List<UsersDto> displayAllUsers();

}
