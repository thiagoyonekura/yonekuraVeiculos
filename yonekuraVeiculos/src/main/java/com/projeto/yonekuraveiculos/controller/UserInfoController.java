package com.projeto.yonekuraveiculos.controller;

import com.projeto.yonekuraveiculos.entity.UserInfo;
import com.projeto.yonekuraveiculos.repository.UserInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UserInfoController {
    private PasswordEncoder passwordEncoder;
    private UserInfoRepository userInfoRepository;

    @PostMapping("/adicionar")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return "Usuário foi adicionado ao banco de dados.";
    }

    @GetMapping("/listar")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<UserInfo> listUsers() {
        return userInfoRepository.findAll();
    }
}
