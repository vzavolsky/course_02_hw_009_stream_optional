package com.zavolsky.course_02;

import java.util.Optional;

public interface EmployeeService {
    String greetings();
    Optional getLimitSalaryEmployee(int department, boolean flag);
}
