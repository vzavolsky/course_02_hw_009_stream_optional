package com.zavolsky.course_02;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    private final EmployeeImpl employeeImpl;

    EmployeeController (EmployeeImpl employee) {
        this.employeeImpl = employee;
    }

    @GetMapping
    public String greetings() {
        return employeeImpl.greetings();
    }

    @GetMapping(path = "/departments/max-salary")
    public String getMaxSalaryEmployee(@RequestParam("departmentId") int department) {
        return employeeImpl.getMaxSalaryEmployee(department);
    }

    @GetMapping(path = "/create")
    public String creatEmployee() {
        return employeeImpl.createEmployee();
    }
    @GetMapping(path = "/show")
    public String showEmployees() {
        return employeeImpl.showEmployees();
    }
}
