package com.pilot.inventory.repository;

import com.pilot.inventory.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {
    public Users findByName(String name);
    public List<Users> findByDeletedFalse();
    Users findByNameAndDeletedFalse(String name);
}
