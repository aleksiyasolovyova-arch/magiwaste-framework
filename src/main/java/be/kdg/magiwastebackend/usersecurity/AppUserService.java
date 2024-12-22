package be.kdg.magiwastebackend.usersecurity;

import be.kdg.magiwastebackend.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository userRepository;

    public AppUser authenticate(String email, String rawPassword) {
        AppUser user = userRepository.findByEmail(email);

        if (user != null && rawPassword.equals(user.getPassword())) {
            return user;
        }
        return null;
    }
}
