package ru.gb.mall.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.mall.inventory.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByName(String name);
}
