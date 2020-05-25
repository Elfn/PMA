package com.pma.app.controllers;

import com.pma.app.dao.UserAccountRepository;
import com.pma.app.entities.UserAccount;
import com.pma.app.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by Elimane on May, 2020, at 20:36
 */
@CrossOrigin("*")
@Controller
public class SecurityController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    private UserAccountService accountService;

    @GetMapping("/register")
    public  String register(Model model)
    {
        UserAccount userAccount = new UserAccount();
        model.addAttribute("userAccount", userAccount);

        return "security/register";
    }

    //Here User argument object is mapped to userAccount
    // stored in the model above in register(model) method
    @PostMapping("/register/save")
    public  String saveUser(Model model,UserAccount user)
    {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        accountService.save(user);

        return "redirect:/";
    }

}
