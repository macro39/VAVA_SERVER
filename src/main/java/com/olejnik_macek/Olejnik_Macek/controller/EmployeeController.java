package com.olejnik_macek.Olejnik_Macek.controller;

import com.olejnik_macek.Olejnik_Macek.exception.ResourceNotFoundException;
import com.olejnik_macek.Olejnik_Macek.model.Employee;
import com.olejnik_macek.Olejnik_Macek.model.EmployeeType;
import com.olejnik_macek.Olejnik_Macek.repository.EmployeeRepository;
import com.olejnik_macek.Olejnik_Macek.repository.EmployeeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(value = "/api")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeTypeRepository employeeTypeRepository;

    // get all
    @GetMapping("/employee")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // get 500
    @GetMapping("/employee/byOffSet/{offSet}")
    public List<Employee> getEmployeesWithOffSet(@PathVariable(value = "offSet") Integer offSet) {
        Pageable pageable = PageRequest.of(offSet,500, Sort.by("lastName").and(Sort.by("firstName")));

        Page<Employee> employees = employeeRepository.findAll(pageable);

        List<Employee> listOfEmployees = employees.getContent();

        return listOfEmployees;
    }

    // create new employee
    @PostMapping("/employee")
    public Boolean createEmployee(@Valid @RequestBody Employee employee) {
        if(employeeRepository.existsEmployeeByLogin(employee.getLogin())) {
            return false;
        }
        employeeRepository.save(employee);

        return true;
    }

    // check if exists
    @GetMapping("/employee/{login}/{password}")
    public Employee getEmployeeByLoginAndPassword(@PathVariable(value = "login") String login,
                                          @PathVariable(value = "password") String password) {

        return employeeRepository.findByLoginAndPassword(login, password);
    }

    // get employee by ID
    @GetMapping("/employee/{employee_id}")
    public Employee getEmployeeByID(@PathVariable(value = "employee_id") Integer employeeID) {
        return employeeRepository.findById(employeeID)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeID));
    }

    // get employee by Last Name
    @GetMapping("/employee/searchByLastName/{lastName}/{offSet}")
    public List<Employee> getEmployeeByFirstAndLastName(@PathVariable(value = "lastName") String lastName,
                                                        @PathVariable(value = "offSet") Integer offSet) {
        Pageable pageable = PageRequest.of(offSet,500);

        return employeeRepository.findAllByLastNameContainingOrderByLastNameAscFirstNameAsc(lastName, pageable);
    }

    // get types of employee
    @GetMapping("/employee/getTypes")
    public ArrayList<String> getTypes() {
        ArrayList<String> stringList = new ArrayList<>();

        for(EmployeeType employeeType : employeeTypeRepository.findAll()) {
            stringList.add(employeeType.getEmployee_type());
        }

        return stringList;
    }

    // update
    @PutMapping("/employee")
    public Boolean updateEmployee(@Valid @RequestBody Employee employeeDetail) {

        Employee employee = employeeRepository.findById(employeeDetail.getEmployee_id())
                .orElse(null);

        if(employee == null) {
            return false;
        }

        employee.setFirstName(employeeDetail.getFirstName());
        employee.setLastName(employeeDetail.getLastName());
        employee.setPassword(employeeDetail.getPassword());
        employee.setPhone(employeeDetail.getPhone());

        employeeRepository.save(employee);

        return true;
    }

    // delete
    @DeleteMapping("/employee/{employee_id}")
    public boolean deleteEmployee(@PathVariable(value = "employee_id") Integer employeeID) {

        Employee employee = employeeRepository.findById(employeeID).orElse(null);

        if(employee == null) {
            return false;
        } else {
            employeeRepository.delete(employee);
            return true;
        }
    }
}
