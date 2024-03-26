package com.pilot.inventory.mapper;

import com.pilot.inventory.dto.UsersDto;
import com.pilot.inventory.model.Users;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UsersDtoMapper implements Function<Users, UsersDto> {
    @Override
    public UsersDto apply(Users users) {
        return new UsersDto(
                users.getName(),
                users.getContactNumber()
        );
    }
}
