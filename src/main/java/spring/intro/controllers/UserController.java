package spring.intro.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.intro.dto.UserResponseDto;
import spring.intro.model.User;
import spring.intro.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getListUsers().stream()
                .map(this::getDto)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return getDto(userService.getById(userId));
    }

    private UserResponseDto getDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setLogin(user.getLogin());
        userResponseDto.setPassword(user.getPassword());
        return userResponseDto;
    }

    @GetMapping("/inject")
    public void injectUserData() {
        userService.add(new User("Ivan", "1"));
        userService.add(new User("Maria", "2"));
        userService.add(new User("Petro", "3"));
        userService.add(new User("Sofia", "4"));
    }
}
