package LoginAuthenticator.Controllers;

import LoginAuthenticator.DTOS.UserDto;
import LoginAuthenticator.models.UsersModel;
import LoginAuthenticator.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(UserDto userDto) {
        UsersModel user = new UsersModel();
        user.setUsername(userDto.getUsername().trim());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()).trim());
        user.setName(userDto.getName().trim());
        user.setLast_name(userDto.getLast_name().trim());
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "login";
    }

    @GetMapping("/index")
public String showIndexPage(Model model) {
    model.addAttribute("user", new UserDto());
    return "index";
}
}