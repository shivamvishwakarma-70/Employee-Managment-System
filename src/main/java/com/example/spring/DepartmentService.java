package com.example.spring;

import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
	@Autowired
	private  DepartmentRepository departmentRepository;
	
	public List<Department>getdepaDepartments(){
		return departmentRepository.findAll();
	}
	public Optional<Department>getDeparmentById(long id)
	{
		return departmentRepository.findById(id);
	}
	public Department saveDepartment(Department department) {
		return departmentRepository.save(department);
	}
	public void deleteDepartment(long id ) {
		departmentRepository.deleteById(id);
	}
	public List<Department> getAllDepartments() {
		
		return departmentRepository.findAll();
	}
}
