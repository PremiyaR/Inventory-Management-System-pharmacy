package com.pilot.inventory.repository;

import com.pilot.inventory.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {
    public Users findByName(String name);
}
