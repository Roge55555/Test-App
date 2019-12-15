package com.simpleapp.rest;

import com.simpleapp.Exceptions.NoSuchIdException;
import com.simpleapp.entity.Employee;
import com.simpleapp.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class EmployeeController {

    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeService.findAll();
    }

    @GetMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Employee getById(@PathVariable("id") Long id) throws NoSuchIdException {
//        try {
            return employeeService.getById(id);
//        }catch (Exception ex){
//            throw new NoSuchIdException(id, "show");
//        }
    }

    @PostMapping("/addEmployee")
    @ResponseStatus(HttpStatus.CREATED)
    public void addEmployee(@RequestBody Employee employee) {
        employeeService.add(employee);
    }

    @PostMapping("/updateEmployee")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateEmployee(@RequestBody Employee employee) {
        employeeService.update(employee);
    }

    @PostMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable("id") Long id) {
            employeeService.delete(id);

    }
}