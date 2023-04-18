package com.zavolsky.course_02;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        return employeeImpl.getLimitSalaryEmployee(department, true).toString();
    }

    @GetMapping(path = "/departments/min-salary")
    public String getMinSalaryEmployee(@RequestParam("departmentId") int department) {
        return employeeImpl.getLimitSalaryEmployee(department, false).toString();
    }

    @GetMapping(path = "/departments/all")
    public String showEmployeesByDepartment(@RequestParam("departmentId") int department) {
        List<Employee> employees = employeeImpl.showEmployeesByDepartment(department);
        String res = "";
        for(Employee employee: employees) {
            res += "<tr>" +
                    "<td>" + employee.getEmployeeID() + "</td>" +
                    "<td>" + employee.getName() + "</td>" +
                    "<td>" + employee.getFamilyName() + "</td>" +
                    "<td>" + employee.getSalary() + "</td>" +
                    "<td>" + employee.getDepartment() + "</td>" +
                    "</tr>\n";
        }
        String tableHeader = "<tr>" +
                "<td><strong>#ID</strong></td>" +
                "<td><strong>Name</strong></td>" +
                "<td><strong>Family name</strong></td>" +
                "<td><strong>Salary</strong></td>" +
                "<td><strong>Department</strong></td>" +
                "</tr>\n";
        return  "<p>" + employees.size() +"</p>\n<table style='min-width: 700px;'>" + tableHeader + res + "</table>";
    }

    @GetMapping(path = "/create")
    public String creatEmployee() {
        return employeeImpl.createEmployee().toString();
    }
    @GetMapping(path = "/show")
    public String showEmployees() {
        return employeeImpl.showEmployees();
    }
}
