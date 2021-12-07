package com.afs.restapi;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {
    private List<Employee> employees = new ArrayList<>();

    public EmployeeRepository(){
        employees.add(new Employee (1,"Anna",20,"F",5000));
        employees.add(new Employee (2,"Johnson",20,"M",4000));
        employees.add(new Employee (3,"Apple",20,"F",4000));
        employees.add(new Employee (4,"April",20,"M",4000));
        employees.add(new Employee (5,"May",20,"M",4000));
        employees.add(new Employee (6,"June",20,"M",4000));
    }

    public List<Employee> findAll() {
        return employees;
    }

    public Employee findById(int id) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElseThrow(NoMatchIdFoundException::new);
    }

    public List<Employee> findByGender(String gender) {
        return employees.stream().filter(employee -> employee.getGender().equals(gender)).collect(Collectors.toList());
    }

    public List<Employee> displayEmployee(Integer page, Integer pageSize) {
        return employees.stream()
                .skip((long)page * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public Employee create(Employee employee) {
        Integer nextId = employees.stream().mapToInt(Employee::getId).max().orElse(0) + 1;
        employee.setId(nextId);
        employees.add(employee);
        return employee;
    }

    public Employee save(Integer id, Employee updatedEmployee) {
        Employee employee = findById(id);
        employees.remove(employee);
        employees.add(updatedEmployee);
        return employee;
    }

    public Employee delete(Integer id) {
        Employee employee = findById(id);
        employees.remove(employee);
        return null;
    }
}

