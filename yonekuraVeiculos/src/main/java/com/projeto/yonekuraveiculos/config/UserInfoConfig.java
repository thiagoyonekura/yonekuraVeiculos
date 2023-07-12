package com.projeto.yonekuraveiculos.config;

import com.projeto.yonekuraveiculos.entity.UserInfo;
import com.projeto.yonekuraveiculos.repository.UserInfoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserInfoConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserInfoRepository userInfoRepository) {
        return args -> {
            if (userInfoRepository.count() > 0) {
                // Já existem registros no banco de dados, então não é necessário criar novamente
                return;
            }
            //Admin "thiago" possui acesso a todas as páginas do sistema
            UserInfo userInfo = new UserInfo();
            userInfo.setName("Thiago");
            userInfo.setEmail("thiago@mail.com");
            userInfo.setRoles("ROLE_ADMIN");
            userInfo.setPassword(passwordEncoder().encode("123"));
            userInfoRepository.save(userInfo);

            /*O "visitante" possui somente acesso às páginas home e lista de veículos, mas não possui permissão para
            alterar ou excluir os veículos. Futuramente pode ser implementado páginas web voltadas aos visitantes*/
            userInfo = new UserInfo();
            userInfo.setName("Visitante");
            userInfo.setEmail("visitante@mail.com");
            userInfo.setRoles("ROLE_USER");
            userInfo.setPassword(passwordEncoder().encode("123"));
            userInfoRepository.save(userInfo);
        };
    }

    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}