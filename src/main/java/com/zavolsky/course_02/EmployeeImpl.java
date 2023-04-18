package com.zavolsky.course_02;

import org.springframework.stereotype.Service;

import java.util.*;
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

    public Employee getLimitSalaryEmployee(int department, boolean flag) {
        int maxSalary = employees.get(0).getSalary();
        Employee maxSalaryEmployee = null;
        int minSalary = employees.get(0).getSalary();
        Employee minSalaryEmployee = null;
        for (Employee entry : employees) {
            if (entry.getSalary() > maxSalary && entry.getDepartment() == department) {
                maxSalary = entry.getSalary();
                maxSalaryEmployee = entry;
            }
            if (entry.getSalary() < minSalary && entry.getDepartment() == department) {
                minSalary = entry.getSalary();
                minSalaryEmployee = entry;
            }
        }
        return flag ? maxSalaryEmployee : minSalaryEmployee;
    }

    public Employee createEmployee() {
        String[] names = {"John", "Sarah", "Mike", "Bob", "Robert", "Donna", "Anna", "Lisa", "George", "Peter", "Denny"};
        String[] fNames = {"Melory", "Gray", "Berg", "Davis", "Wild", "Shield", "Chain", "Chan"};

        Random s = new Random();
        Employee employee = new Employee(
                names[s.nextInt(names.length - 1)],
                fNames[s.nextInt(fNames.length - 1)],
                s.nextInt(100_000) + 50_000,
                s.nextInt(departments.length - 1)
        );
        employees.add(employee);
        return employee;
    }

    public String addEmployee(String name, String familyName, int salary, int departent) {
        Employee employee = new Employee(name, familyName, salary, departent);
        employees.add(employee);
        return "Сотрудник добавлен.";
    }

    private Map<Integer, Employee> addFunct(Map<Integer, Employee> employees, Employee employee) {
        /*TreeMap<Integer, Employee> sorted = new TreeMap<>();
        sorted.putAll(employees);
        Map.Entry<Integer, Employee> lastEntry = sorted.lastEntry();
        employees.put(lastEntry.getKey() + 1, employee);*/
        return employees;
    }

    public List<Employee> showEmployees() {
        return employees;
    }

    public List<Employee> showEmployeesByDepartment(int department) {
        final List<Employee> resListOfEmployees = employees.stream().
                filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
        return resListOfEmployees;
    }

    public List<Employee> showEmployeesByDepartment() {
        final List<Employee> resListOfEmployees = null;
        return resListOfEmployees;
    }

    public String showEmployeeByID(int employeeID) {
        String res = "";
        Employee employee = employees.get(employeeID);
        res = "<tr>" +
                "<td>" + employeeID + "</td>" +
                "<td>" + employee.getName() + "</td>" +
                "<td>" + employee.getFamilyName() + "</td>" +
                "<td>" + employee.getSalary() + "</td>" +
                "<td>" + departments[employee.getDepartment()] + "</td>" +
                "</tr>\n";
        return "<p>" + employees.size() +"</p>\n<table style='min-width: 700px;'>" + res + "</table>";
    }

}
