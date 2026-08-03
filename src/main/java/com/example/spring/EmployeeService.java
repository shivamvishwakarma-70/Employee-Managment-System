package com.example.spring;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	public Optional<Employee> getEmployeeById(long id){
		return employeeRepository.findById(id);
	}
	public void saveEmployee(Employee employee)
	{
		employeeRepository.save(employee);
	}
	public void deleteEmployee(long id) {
		employeeRepository.deleteById(id);
	}
}
