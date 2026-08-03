package com.example.spring;

import com.example.spring.*;
import com.example.spring.*;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@RequestMapping("/departments")
public class DepartmentController {
	@Autowired
    private  DepartmentService departmentService;
	@GetMapping("/test")
	@ResponseBody
	public String test() {
		return "hello";
	}
    

    // 1. List all departments
    @GetMapping
    public String getAllDepartments(Model model) {
        List<Department> departments =  departmentService.getAllDepartments();
        model.addAttribute("departments", departments); // fixed
        return "department-list";
    }

    // 2. Show Add form
    @GetMapping("/add")
    public String showAddDepartmentForm(Model model) {
        model.addAttribute("department", new Department()); // fixed
        return "Department-form";
    }

    // 3. Show Edit form
    @GetMapping("/edit/{id}")
    public String showEditDepartmentForm(@PathVariable Long id, Model model) {
        Optional<Department> department = departmentService.getDeparmentById(id);
        if (department.isPresent()) {
            model.addAttribute("department", department.get()); // fixed
            return "department-form";
        } else {
            return "redirect:/departments";
        }
    }

    // 4. Save Department
    @PostMapping("/save")
    public String saveDepartment(@ModelAttribute("department") Department department) {
        departmentService.saveDepartment(department);
        return "redirect:/departments";
    }

    // 5. Delete Department
    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            departmentService.deleteDepartment(id);
            redirectAttributes.addFlashAttribute("message", "Department deleted successfully"); // fixed
        } catch (NoSuchElementException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Department not found"); // fixed
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Cannot delete. Employees are assigned to this department"); // fixed
        }
        return "redirect:/departments";
    }
}