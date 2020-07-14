package com.example.demo.service.userService;

import com.example.demo.entity.user.Client;

public interface UserService {
    void save(Client client);
    void changeUserPassword(Client user, String password);
    Client findByUserName(String username);
}
