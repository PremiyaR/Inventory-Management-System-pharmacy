package com.pilot.inventory.mapper;

import com.pilot.inventory.dto.UsersRequestDto;
import com.pilot.inventory.exception.NoEntriesFound;
import com.pilot.inventory.model.Users;
import com.pilot.inventory.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UsersRequestDtoMapper implements Function<UsersRequestDto, Users> {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Users apply(UsersRequestDto usersRequestDto) {
        Users users=usersRepository.findByName(usersRequestDto.name());

        if(users==null)
        {
            throw new NoEntriesFound("No users found with name"+usersRequestDto.name());
        }
        Users users1=new Users();
        users1.setId(users.getId());
        users1.setName(users.getName());
        users1.setContactNumber(users.getContactNumber());

        return users1;
    }
}
