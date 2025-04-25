package firstApi.users;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class UsersController {
  
  // Define your endpoints here,
  // For example, a simple GET endpoint
  @GetMapping("/users")
  public List<User> getUsers() {
    return Arrays.asList(
            new User(1, "Alice", "Gomez"),
            new User(2, "Bob", "Smith"),
            new User(3, "Charlie", "Brown")
    );
  }
}