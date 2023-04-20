package com.zavolsky.course_02;

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
        return employeeImpl.getLimitSalaryEmployee(department, true);
    }

    @GetMapping(path = "/departments/min-salary")
    public Optional getMinSalaryEmployee(@RequestParam("departmentId") int department) {
        return employeeImpl.getLimitSalaryEmployee(department, false);
    }

    @GetMapping(path = "/departments/all")
    public Map<Integer, List<Employee>> showEmployeesByDepartment(@RequestParam(value = "departmentId", required = false) Integer department) {
        return employeeImpl.showEmployeesByDepartment(department);
    }

    @GetMapping(path = "/create")
    public Employee creatEmployee() {
        return employeeImpl.createEmployee();
    }

    @GetMapping(path = "/show")
    public List<Employee> showEmployees() {
        return employeeImpl.showEmployees();
    }
}
