package employees;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class EmployeesController {
  
  @GetMapping("/employees")
  public List<Employee> getEmployees() {
    return Arrays.asList(
            new Employee(1, "John Doe", "Web developer"),
            new Employee(2, "Jane Doe", "Data analyst")
    );
  }
}