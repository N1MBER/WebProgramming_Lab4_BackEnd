package main.web.service;

import main.web.entity.User;
import main.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean getMatch(String password, User user){
        return bCryptPasswordEncoder.matches(password, user.getPassword());
    }


    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}