package org.thevault.videoclubAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.thevault.videoclubAPI.exceptionHandler.custom.IncorrectCredentialsException;
import org.thevault.videoclubAPI.model.User;
import org.thevault.videoclubAPI.model.dto.UserSessionDTO;
import org.thevault.videoclubAPI.repository.UserRepository;
import org.thevault.videoclubAPI.util.ValidationUtil;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserSessionDTO login(String username, String password){
        Optional<User> optionalUser;

        if(ValidationUtil.isEmail(username))
            optionalUser = userRepository.findByEmail(username);
        else
            optionalUser = userRepository.findByUsername(username);

        User user = optionalUser.orElseThrow(() ->
                new IncorrectCredentialsException("You entered the incorrect credentials. Try again")
        );

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new IncorrectCredentialsException("You entered the incorrect credentials. Try again");
        }

        return userRepository.findClientSessionByUsername(user.getUsername())
                .or(() -> userRepository.findEmployeeSessionByUsername(user.getUsername()))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "There was an error obtaining your data. Try again later"
                ));

    }
}
