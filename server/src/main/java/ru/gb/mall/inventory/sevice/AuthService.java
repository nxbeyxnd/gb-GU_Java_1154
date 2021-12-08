package ru.gb.mall.inventory.sevice;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gb.mall.inventory.dto.AuthRequestDto;
import ru.gb.mall.inventory.entity.Role;
import ru.gb.mall.inventory.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public AuthService(UserService userService, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    public User saveNewUser(AuthRequestDto authRequestDto){
        User user = new User();
        if(userService.findUserByLogin(authRequestDto.getLogin()).isEmpty()){
            user.setLogin(authRequestDto.getLogin());
            user.setPassword(passwordEncoder.encode(authRequestDto.getPassword()));
            user.setFirstName("test");
            user.setSecondName("test");
            List<Role> roles = new ArrayList<>();
            roles.add(roleService.findRoleByName("ADMINISTRATOR"));
            user.setRoles(roles);
            return userService.saveOrUpdate(user);
        }
        return user;
    }

    public Optional<User> findUserByLoginAndPassword(AuthRequestDto authRequestDto){
        Optional<User> user = userService.findUserByLogin(authRequestDto.getLogin());
        if (!user.isEmpty()){
            if (passwordEncoder.matches(authRequestDto.getPassword(), user.get().getPassword())){
                return user;
            }
        }
        return Optional.empty();
    }
}
