package ru.gb.mall.inventory.component;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ru.gb.mall.inventory.entity.Role;
import ru.gb.mall.inventory.repository.RoleRepository;

import javax.transaction.Transactional;

@Component
public class InitDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private boolean alreadyCreated = false;
    private final RoleRepository roleRepository;

    public InitDataLoader(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadyCreated) {
            return;
        }

        CreteRoleIfNotExists("ADMINISTRATOR");
        CreteRoleIfNotExists("MODERATOR");
        CreteRoleIfNotExists("CUSTOMER");
    }

    @Transactional
    Role CreteRoleIfNotExists(String name) {
        Role role = roleRepository.findRoleByName(name);
        if (role == null) {
            role = new Role();
            role.setName(name);
            roleRepository.save(role);
        }
        return role;
    }
}
