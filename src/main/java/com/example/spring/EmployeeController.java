package com.example.spring;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller  // ye class ke upar rahega
public class EmployeeController {

    @Autowired 
    private EmployeeService employeeService;
    
    @Autowired 
    private DepartmentService departmentService;
    
    // 1. List dikhane ke liye
    @GetMapping("/employees")
    public String getAllEmployees(Model model) {
    	List<Employee> employees = employeeService.getAllEmployees();
    	model.addAttribute("employees", employees); // list bhejo
    	return "Employee-list";
    }
    
    // 2. Naya form kholne ke liye
    @GetMapping("/add")
    public String showAddEmployeeForm(Model model) {
    	model.addAttribute("employee", new Employee());
    	model.addAttribute("departments", departmentService.getAllDepartments());
    	return "Employee-form";
    }
    
    // 3. Edit ke liye
    @GetMapping("/edit/{id}")
    public String showEditEmployeeForm(@PathVariable long id, Model model) {
    	Optional<Employee> employee = employeeService.getEmployeeById(id);
    	if(employee.isPresent()) {
    		model.addAttribute("employee", employee.get());
    		model.addAttribute("departments", departmentService.getAllDepartments());
    		return "Employee-form";
    	} else {
    		return "redirect:/employees";
    	}
    }
    
    // 4. Save karne ke liye
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
    	employeeService.saveEmployee(employee);
    	return "redirect:/employees"; // list pe wapas
    }
    
    // 5. Delete ke liye
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable long id) {
    	employeeService.deleteEmployee(id);
    	return "redirect:/employees";
    }
    

    @Controller
    public class LoginController {

        @GetMapping("/login")
        public String login() {
            return "login"; // ye login.html ko call karega
        }
    }
    @GetMapping("/logout")
     public String logout() {
    	return "logout";
    }
}