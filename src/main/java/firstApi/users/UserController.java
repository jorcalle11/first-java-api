package firstApi.users;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
  private final UserRepository userRepository;
  
  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  
  @GetMapping("/users")
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }
  
  @GetMapping("/users/{id}")
  public User getUserById(@PathVariable int id) {
    if (id < 0) {
      throw new IllegalArgumentException("Invalid user ID");
    }
    
    return userRepository.findById(id).orElse(new UserNotFoundException("User not found with ID: " + id));
    
  }
}