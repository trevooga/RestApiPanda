package code.trevooga.RestApiPanda.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) ->
                        authorize
                                .requestMatchers("/register/**").permitAll()
                                .requestMatchers("/changePassword").hasAnyAuthority("ADMIN")
                                .requestMatchers("/adminpage").hasAnyAuthority("ADMIN")
                                .requestMatchers("/panda/**").permitAll()
                                .requestMatchers("/index").authenticated()
                                .anyRequest().permitAll()
                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .successHandler(customAuthenticationSuccessHandler())
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler(userService);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    public static class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

        private final UserService userService;

        public CustomAuthenticationSuccessHandler(UserService userService) {
            this.userService = userService;

        }

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request,
                                            HttpServletResponse response,
                                            Authentication authentication) throws IOException {

            Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
            String username = ((UserDetails) authentication.getPrincipal()).getUsername();
            int userId = userService.findUserIdByUsername(username);

            // Сохраняем username в сессию
            request.getSession().setAttribute("username", username);
            // Также можно сохранить userId, если он понадобится
            request.getSession().setAttribute("userId", userId);

            if (roles.contains("WORKER")) {
                response.sendRedirect("/panda");
            } else if (roles.contains("ADMIN")) {
                response.sendRedirect("/adminpage");
            } else if (roles.contains("USER")) {
                response.sendRedirect("/panda/lk/find?customer_id=" + userId);
            }
        }
    }
    }