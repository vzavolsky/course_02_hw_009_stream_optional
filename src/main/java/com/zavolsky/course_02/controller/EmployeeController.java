package com.zavolsky.course_02.controller;

import com.zavolsky.course_02.domain.Employee;
import com.zavolsky.course_02.service.EmployeeImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class EmployeeController {
    private final EmployeeImpl employeeImpl;

    EmployeeController(EmployeeImpl employee) {
        this.employeeImpl = employee;
    }

    @GetMapping
    public String greetings() {
        return employeeImpl.greetings();
    }

    @GetMapping(path = "/departments/max-salary")
    public Optional getMaxSalaryEmployee(@RequestParam("departmentId") int department) {
        return employeeImpl.getMaxSalaryEmployee(department);
    }

    @GetMapping(path = "/departments/min-salary")
    public Optional getMinSalaryEmployee(@RequestParam("departmentId") int department) {
        return employeeImpl.getMinSalaryEmployee(department);
    }

    @GetMapping(path = "/departments/all")
    public Map<Integer, List<Employee>> showEmployeesByDepartment(@RequestParam(value = "departmentId", required = false) Integer department) {
        return department == null ? employeeImpl.showEmployeesByDepartment() : employeeImpl.showEmployeesByDepartment(department);
    }

    @GetMapping(path = "/create")
    public Employee creatEmployee() {
        return employeeImpl.createEmployee();
    }

    @GetMapping(path = "/add")
    public Employee addEmployee(@RequestParam("name") String name, @RequestParam("fname") String fName) {
        return employeeImpl.addEmployee(name, fName);
    }

    @GetMapping(path = "/show")
    public List<Employee> showEmployees() {
        return employeeImpl.showEmployees();
    }
}
