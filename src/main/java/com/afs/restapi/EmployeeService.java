package com.afs.restapi;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee edit(Integer id, Employee updatedEmployee) {
        Employee employee = employeeRepository.findById(id);
        if(updatedEmployee.getAge() != null){
            employee.setAge(updatedEmployee.getAge());
        }
        if (updatedEmployee.getSalary() != null){
            employee.setSalary(updatedEmployee.getSalary());
        }
        return employeeRepository.save(id,employee);
    }

    public Employee findById(Integer id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> findByGender(String gender) {
        return employeeRepository.findByGender(gender);
    }

    public List<Employee> displayEmployee(Integer page, Integer pageSize) {
        return employeeRepository.displayEmployee(page,pageSize);
    }

    public Employee create(Employee newEmployee) {
        return employeeRepository.create(newEmployee);
    }

    public Employee delete(Integer id) {
        return employeeRepository.delete(id);
    }
}
