package com.favcode.favschool.repository;

import com.favcode.favschool.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {

    Roles getByRoleName(String roleName);
}
