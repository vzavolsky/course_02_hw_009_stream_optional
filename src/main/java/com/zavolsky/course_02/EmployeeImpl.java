package com.zavolsky.course_02;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

@Service
public class EmployeeImpl implements EmployeeService {

    Map<Integer, Employee> employees = new HashMap<>();
    private static int employeeIDinc = 0;
    private int employeeID = ++employeeIDinc;

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

    public String getMaxSalaryEmployee(int department) {
        return "Hi!";
    }

    public String createEmployee() {
        String[] names = {"John", "Sarah", "Mike", "Bob", "Robert", "Donna", "Anna", "Lisa", "George", "Peter", "Denny"};
        String[] fNames = {"Melory", "Gray", "Berg", "Davis", "Wild", "Shield", "Chain", "Chan"};
        String[] departments = {"Sale Department", "Top Management Department", "Storage Department", "Manufactory Department"};
        Random s = new Random();
        Employee employee = new Employee(
                names[s.nextInt(names.length - 1)],
                fNames[s.nextInt(fNames.length - 1)],
                s.nextInt(100_000) + 50_000,
                s.nextInt(departments.length - 1)
        );
        this.employees.put(this.employeeID, employee);
        return "Сотрудник создан.";
    }

    public String addEmployee(String name, String familyName, int salary, int departent) {
        Employee employee = new Employee(name, familyName, salary, departent);
        this.employees.put(this.employeeID, employee);
        return "Сотрудник добавлен.";
    }

    private Map<Integer, Employee> addFunct(Map<Integer, Employee> employees, Employee employee) {
        /*TreeMap<Integer, Employee> sorted = new TreeMap<>();
        sorted.putAll(employees);
        Map.Entry<Integer, Employee> lastEntry = sorted.lastEntry();
        employees.put(lastEntry.getKey() + 1, employee);*/
        return employees;
    }

    public String showEmployees() {
        String res = "";
        for (Map.Entry<Integer, Employee> entry : this.employees.entrySet()) {
            res += entry.getKey() + " " + entry.getValue().getName() + " " + entry.getValue().getFamilyName() + " " + entry.getValue().getSalary() + " " + entry.getValue().getDepartment();//, entry.getValue().getSalary(), entry.getValue().getDepartment());
        }
        return employees.size() +"<br>\n" + res + "!";
    }
}
