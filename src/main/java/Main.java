import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.intro.config.AppConfig;
import spring.intro.model.User;
import spring.intro.service.UserService;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);

        userService.add(new User("Ivan", "1"));
        userService.add(new User("Maria", "2"));
        userService.add(new User("Petro", "3"));

        List<User> users = userService.getListUsers();
        users.forEach(System.out::println);
    }
}
