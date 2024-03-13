package com.pilot.inventory.service;

import com.pilot.inventory.exception.DuplicateName;
import com.pilot.inventory.exception.EntryAlreadyExists;
import com.pilot.inventory.exception.ItemAlreadyExistsException;
import com.pilot.inventory.exception.NoEntriesFound;
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

        if(existingUsers.getId()==updatedUsers.getId())
        {
            throw new EntryAlreadyExists();
        }
        existingUsers.setName(updatedUsers.getName());
        return usersRepository.save(existingUsers);
    }

    @Override
    public String deleteUsers(int id) {
        String status=null;
        Optional<Users> optional=usersRepository.findById(id);
        if(optional.isPresent())
        {
            usersRepository.deleteById(id);
            status="Product deleted";
        }
        else {
            status="Product not deleted,Please check your id is valid";
        }
        return status;
    }

    @Override
    public List<Users> displayAllUsers() {
        List<Users> users=usersRepository.findAll();
        if(users.isEmpty()){
            throw new NoEntriesFound();
        }
        return users;
    }
}
