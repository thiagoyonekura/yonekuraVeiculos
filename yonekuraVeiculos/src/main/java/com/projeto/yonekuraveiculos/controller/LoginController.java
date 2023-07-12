package com.projeto.yonekuraveiculos.controller;

import com.projeto.yonekuraveiculos.service.UserInfoUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class LoginController {

    private final UserInfoUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public LoginController(UserInfoUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("name") String name,
                               @RequestParam("password") String password,
                               RedirectAttributes redirectAttributes) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(name);

        if (userDetails != null && passwordEncoder.matches(password, userDetails.getPassword())) {
            // Autenticação bem-sucedida, redirecionar para a página principal
            return "redirect:/";
        } else {
            // Autenticação falhou, redirecionar para a página de login com mensagem de erro
            redirectAttributes.addAttribute("error", true);
            return "redirect:/login?error=true";
        }
    }
}



