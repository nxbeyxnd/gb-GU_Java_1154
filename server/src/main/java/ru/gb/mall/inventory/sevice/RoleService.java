package ru.gb.mall.inventory.sevice;

import org.springframework.stereotype.Service;
import ru.gb.mall.inventory.entity.Role;
import ru.gb.mall.inventory.repository.RoleRepository;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findRoleByName(String name){
        return roleRepository.findRoleByName(name);
    }
}
