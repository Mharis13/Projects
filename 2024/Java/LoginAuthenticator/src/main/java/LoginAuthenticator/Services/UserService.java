package LoginAuthenticator.Services;

import LoginAuthenticator.DTOS.UserDto;
import LoginAuthenticator.models.UsersModel;
import LoginAuthenticator.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public UsersModel findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UsersModel save(UserDto userDto) {
        UsersModel user = new UsersModel();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setName(userDto.getName());
        user.getLast_name(userDto.getLast_name());

        return userRepository.save(user);
    }
}