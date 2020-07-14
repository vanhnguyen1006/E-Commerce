package com.example.demo.controller;

import com.example.demo.entity.user.Client;
import com.example.demo.repository.usersRepository.ClientRepository;
import com.example.demo.repository.usersRepository.RoleRepository;
import com.example.demo.repository.usersRepository.VerifyUserRepository;
import com.example.demo.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
public class AccountController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private VerifyUserRepository verifyUserRepository;

    public AccountController(UserService userService, RoleRepository roleRepository, ClientRepository clientRepository,
                             VerifyUserRepository verifyUserRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.clientRepository = clientRepository;
        this.verifyUserRepository = verifyUserRepository;
    }

    @GetMapping("/user/account/changePassword")
    public String changePasswordView(Model model){
        String username = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }
        else {
            username = principal.toString();
        }
        Optional<Client> temp = clientRepository.findByUserName(username);
        if (temp.isEmpty()) {
            model.addAttribute("message", "Sorry but we can't find information of account: " + username);
            return "Eror/ErorPage";
        }
        model.addAttribute("action", "/user/account/changePassword");
        model.addAttribute("title", "Change Password");
        return "change-password";
    }

    @PostMapping("/user/account/changePassword")
    public String changePassword(Model model,
                                 @RequestParam("password") String password,
                                 @RequestParam("password1") String password1,
                                 @RequestParam("password2") String password2,
                                 HttpServletResponse res) throws IOException {
        String username = "";
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (obj instanceof UserDetails) {
            username = ((UserDetails)obj).getUsername();
        }
        else {
            username = obj.toString();
        }

        Optional<Client> temp = clientRepository.findByUserName(username);
        if (temp.isEmpty()) {
            model.addAttribute("message", "Sorry but we can't find information of account: " + username);
            return "Error/ErrorPage";
        }

        Client user = temp.get();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(password,user.getPassword())) {
            model.addAttribute("wrongPassword","Wrong password.");
            return "change-password";
        }

        if (password.isEmpty() || password1.isEmpty() || password2.isEmpty()) {
            model.addAttribute("messageEmpty", "All field is mandatory.");
            return "change-password";
        }

        if (!password1.equals(password2)) {
            model.addAttribute("wrongPassword","Your new password don't match.");
            return "change-password";
        }

        userService.changeUserPassword(user,password1);

        res.sendRedirect("/logout");
        model.addAttribute("user", user);
        return "user/user-info";
    }

    
}
