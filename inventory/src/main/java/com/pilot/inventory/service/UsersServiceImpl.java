package com.pilot.inventory.service;

import com.pilot.inventory.exception.DuplicateName;
import com.pilot.inventory.exception.EntryAlreadyExists;
import com.pilot.inventory.exception.ItemAlreadyExistsException;
import com.pilot.inventory.exception.NoEntriesFound;
import com.pilot.inventory.model.entity.Categories;
import com.pilot.inventory.model.entity.Product;
import com.pilot.inventory.model.entity.Users;
import com.pilot.inventory.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService{
    @Autowired
    private UsersRepository usersRepository;
    @Override
    public Users addUsers(Users users) {
        Users existingUsers= usersRepository.findByName(users.getName());

        if(existingUsers!=null)
        {
            throw new DuplicateName();
        }
        return usersRepository.save(users);
    }

    @Override
    public Users updateUsers(Users updatedUsers) {
        Users existingUsers = usersRepository.findById(updatedUsers.getId())
                .orElseThrow(() -> new ItemAlreadyExistsException());

        if (!existingUsers.getName().equals(updatedUsers.getName())) {
            Users existingByName = usersRepository.findByNameAndDeletedFalse(updatedUsers.getName());
            if (existingByName != null) {
                throw new ItemAlreadyExistsException("User with name " + updatedUsers.getName() + " already exists");
            }
        }

        if (existingUsers.isDeleted()) {
            throw new NoEntriesFound("User is deleted");
        }

        if (existingUsers.getName().equals((updatedUsers.getName()))) {
            throw new EntryAlreadyExists();
        }

        existingUsers.setName(updatedUsers.getName());
        return usersRepository.save(existingUsers);
    }

    @Override
    public String deleteUsers(int id) {
        Optional<Users> optional = usersRepository.findById(id);
        if (optional.isPresent()) {
            Users users = optional.get();
            users.setDeleted(true);
            usersRepository.save(users);
            return "User deleted";
        } else {
            return "User Not Found";
        }
    }

    @Override
    public List<Users> displayAllUsers() {
        List<Users> usersList=usersRepository.findByDeletedFalse();
        if(usersList.isEmpty()){
            throw new NoEntriesFound();
        }
        return usersList;
    }
}
