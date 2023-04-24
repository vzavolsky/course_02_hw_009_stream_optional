package com.zavolsky.course_02.service;

import com.zavolsky.course_02.domain.Employee;
import com.zavolsky.course_02.EmployeeService;
import com.zavolsky.course_02.exceptions.BadRequestException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class EmployeeImpl implements EmployeeService {

    List<Employee> employees = new ArrayList<>();
    public String[] departments = {"Sale Department", "Top Management Department", "Storage Department", "Manufactory Department"};

    public String greetings() {
        return "<p>Список команд:</p>\n" +
                "<ul>" +
                "<li>create - создать список сотрудников случайным образом;</li>\n" +
                "<li>show - вывести список сотрудников;</li>\n" +
                "<li>delete - удалить сотудника (ввод ID);</li>\n" +
                "<li>add - добавить сотрудника;</li>\n" +
                "<li>index - индексация зарплаты сотрудников (ввод % инднксации);</li>\n" +
                "<li>max - максимальная уровень ЗП;</li>\n" +
                "<li>min - минимальный уровень ЗП;</li>\n" +
                "<li>average - средняя ЗП;</li>\n" +
                "<li>exit - завершение программы.</li>" +
                "</ul>";
    }

    public Optional getMaxSalaryEmployee(int department) {
        return employees.stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.maxBy(Comparator.comparing(Employee:: getSalary)));
    }

    public Optional getMinSalaryEmployee(int department) {
        return employees.stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.minBy(Comparator.comparing(Employee:: getSalary)));
    }

    public Employee createEmployee() {
        String[] names = {"John", "Sarah", "Mike", "Bob", "Robert", "Donna", "Anna", "Lisa", "George", "Peter", "Denny"};
        String[] fNames = {"Melory", "Gray", "Berg", "Davis", "Wild", "Shield", "Chain", "Chan"};

        Random s = new Random();
        Employee employee = new Employee(
                names[s.nextInt(names.length - 1)],
                fNames[s.nextInt(fNames.length - 1)],
                s.nextInt(100_000) + 50_000,
                s.nextInt(departments.length)
        );
        employees.add(employee);
        return employee;
    }

    public List<Employee> showEmployees() {
        return employees;
    }

    public Map<Integer, List<Employee>> showEmployeesByDepartment() {
        return employees.stream().
                collect(Collectors.groupingBy(Employee::getDepartment));
    }

    public Map<Integer, List<Employee>> showEmployeesByDepartment(Integer department) {
        return employees.stream().
                filter(e -> e.getDepartment() == department)
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    public Employee addEmployee(String name, String fName) {
        if (StringUtils.removeAll(name, "[a-zA-Zа-яА-Я]+").length() > 0 || StringUtils.removeAll(fName, "[a-zA-Zа-яА-Я]+").length() > 0) {
            throw new BadRequestException();
        }
        Random s = new Random();
        Employee employee = new Employee(
                StringUtils.capitalize(name),
                StringUtils.capitalize(fName),
                s.nextInt(100_000) + 50_000,
                s.nextInt(departments.length)
        );
        employees.add(employee);
        return employee;
    }
}
