package firstApi.users;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
  private final UserRepository userRepository;
  
  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  
  @PostMapping("/users")
  public Object createUser(User user) {
    if (user.getName() == null || user.getName().isEmpty()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
              .body("{ \"code\": \"400\", \"error\": \"Name cannot be null or empty\"}");
    }
    
    if (user.getEmail() == null || user.getEmail().isEmpty()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
              .body("{ \"code\": \"400\", \"error\": \"Email cannot be null or empty\"}");
    }
    
    // Check if the user already exists in the database
    final String sanitizedEmail = user.getEmail().toLowerCase().trim();
    final User existingUser = userRepository.findByEmail(sanitizedEmail);
    
    if (existingUser != null) {
      return ResponseEntity.status(HttpStatus.CONFLICT)
              .contentType(MediaType.APPLICATION_JSON)
              .body("{ \"code\": \"409\", \"error\": \"User already exists with this email\"}");
    }
    
    
    // make sure the email is stored in lowercase and trimmed
    user.setEmail(sanitizedEmail);
    return userRepository.save(user);
  }
  
  @GetMapping("/users")
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }
  
  @GetMapping("/users/{id}")
  public Object getUserById(@PathVariable int id) {
    final User existingUser = userRepository.findById(id).orElse(null);
    
    if (existingUser == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON)
              .body("{ \"code\": \"404\", \"error\": \"User not found with ID: " + id + "\"}");
    }
    
    return existingUser;
  }
}