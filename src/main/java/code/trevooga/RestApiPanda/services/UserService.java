package code.trevooga.RestApiPanda.services;

import code.trevooga.RestApiPanda.Entities.User;
import code.trevooga.RestApiPanda.Interfaces.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder; // Используем внедрение через конструктор
    private final UserRepository userRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder; // Внедрение PasswordEncoder
        this.userRepository = userRepository; // Внедрение UserRepository
    }

    public User addNewUser(String user_name, String password) {
        String encodedPassword = passwordEncoder.encode(password); // Кодируем пароль
        return userRepository.save(new User(user_name, encodedPassword)); // Сохраняем нового пользователя
    }

    @Transactional
    public String changeThePassword(String username, String password) {
        int updatedRows = userRepository.changeThePassword(username, passwordEncoder.encode(password));
        if (updatedRows > 0) {
            return "Пароль успешно обновлен";
        } else {
            return "Пользователь не найден";
        }
    }
    public int findUserIdByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"))
                .getId();
    }

}
