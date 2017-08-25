package ru.technoserv.services;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ru.technoserv.controller.Employee;

import java.util.ArrayList;
import java.util.List;

@Component
@ComponentScan("ru")
public class EmployeeService {

    private List<Employee> stuffList = new ArrayList<>();

    public EmployeeService(){
        stuffList.add(new Employee("Nanami", "Aoyma"));
        stuffList.add(new Employee("Suszuka", "Halozy"));
        stuffList.add(new Employee("Kanda","Masiro"));
    }

    public Employee getEmployeeById(int id){
        return stuffList.get(id);
    }

    public void addEmployee(String firstName, String lastName){
        stuffList.add(new Employee(firstName, lastName));
    }
}

