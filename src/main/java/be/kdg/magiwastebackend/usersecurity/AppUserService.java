package be.kdg.magiwastebackend.usersecurity;

import be.kdg.magiwastebackend.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository userRepository;

    public AppUser authenticate(String email, String rawPassword) {
        AppUser user = userRepository.findByEmail(email);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (user != null && passwordEncoder.matches(rawPassword, user.getPassword())) {
            System.out.println("password is correct");
            return user;

        }
        System.out.println("password is wrong");
        return null;
    }
}
