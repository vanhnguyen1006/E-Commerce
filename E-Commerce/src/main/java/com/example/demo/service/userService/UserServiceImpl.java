package com.example.demo.service.userService;

import com.example.demo.config.SecurityConfig;
import com.example.demo.entity.user.Client;
import com.example.demo.repository.usersRepository.ClientRepository;
import com.example.demo.repository.usersRepository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService{

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SecurityConfig securityConfig;

    @Override
    public void save(Client client) {
        client.setPassword(securityConfig.bCryptPasswordEncoder().encode(client.getPassword()));
        client.setRoles(client.getRoles());
        client.setCity("null");
        client.setName("null");
        client.setPhone("null");
        client.setStreet("null");
        client.getAuthorities();
        client.addRole(roleRepository.findByName("USER").get());
        securityConfig.getUserDetailsManager().createUser(client);
        clientRepository.save(client);
    }

    @Override
    public void changeUserPassword(Client user, String password) {
        user.setPassword(securityConfig.bCryptPasswordEncoder().encode(password));
        securityConfig.getUserDetailsManager().updateUser(user);
        clientRepository.save(user);
    }

    @Override
    public Client findByUserName(String username) {
        return clientRepository.findByName(username).get();
    }
}
