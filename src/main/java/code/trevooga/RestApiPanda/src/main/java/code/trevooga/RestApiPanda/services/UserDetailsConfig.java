package code.trevooga.RestApiPanda.services;

import code.trevooga.RestApiPanda.Entities.User;
import code.trevooga.RestApiPanda.Interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

@Configuration
public class UserDetailsConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return phone -> {
            User user = userRepository.findByPhone(phone)
                    .orElseThrow(() -> new UsernameNotFoundException("Пользователь с таким номером не найден: " + phone));
            return new CustomUserDetails(
                    user.getPhone(),
                    user.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority(user.getRoles())) // Присваиваем роль
            );
        };
    }
}
