package com.milleniuminfinity.app.client;

import com.milleniuminfinity.app.domain.employee.Cleaner;
import com.milleniuminfinity.app.domain.employee.Employee;
import com.milleniuminfinity.app.domain.employee.Manager;
import com.milleniuminfinity.app.domain.employee.SalesRepresentative;
import com.milleniuminfinity.app.services.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

/**
 * Created by cfebruary on 2016/08/18.
 */
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    //Create an employee
    @RequestMapping(value = "/employee/", method = RequestMethod.POST)
    public ResponseEntity<Void> createEmployee(@RequestBody Employee employee, UriComponentsBuilder ucBuilder){
        employeeService.create(employee);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/employee/{employeeID}").buildAndExpand(employee.getEmployeeID()).toUri());

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //Retrieve a single employee
    @RequestMapping(value = "/employee/{employeeID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getEmployee(@PathVariable("employeeID") String employeeID){
        Employee employee = employeeService.readById(employeeID);

        if(employee == null){
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    //Retrieve all employees
    @RequestMapping(value = "/employees/", method = RequestMethod.GET)
    public ResponseEntity<Set<Employee>> getEmployees(){
        Set<Employee> employees = employeeService.readAll();

        if(employees.isEmpty()){
            return new ResponseEntity<Set<Employee>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Set<Employee>>(employees, HttpStatus.OK);
    }

    //Update an employee
    @RequestMapping(value = "/employee/{employeeID}", method = RequestMethod.PUT)
    public ResponseEntity<Employee> updateEmployee(@PathVariable("employeeID") String employeeID, @RequestBody Employee employee)
    {
        Employee currentEmployee = null;

        if(currentEmployee != null) {

            if (employee.getEmployeeRole().equalsIgnoreCase("Manager")) {
                currentEmployee = (Manager) employeeService.readById(employeeID);
            } else if (employee.getEmployeeRole().equalsIgnoreCase("Sales representative")) {
                currentEmployee = (SalesRepresentative) employeeService.readById(employeeID);
            } else {
                currentEmployee = (Cleaner) employeeService.readById(employeeID);
            }
        }
        else if(currentEmployee == null)
        {
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }

        Employee updatedEmployee = null;

        if(employee.getEmployeeRole().equalsIgnoreCase("Manager")) {
             updatedEmployee = new Manager.Builder().copy((Manager)currentEmployee)
                    .name(employee.getName())
                    .surname(employee.getSurname())
                    .dateOfBirth(employee.getDateOfBirth())
                    .role(employee.getEmployeeRole())
                    .build();
        }
        else if(employee.getEmployeeRole().equalsIgnoreCase("Sales representative"))
        {
             updatedEmployee = new SalesRepresentative.Builder().copy((SalesRepresentative)currentEmployee)
                    .name(employee.getName())
                    .surname(employee.getSurname())
                    .dateOfBirth(employee.getDateOfBirth())
                    .role(employee.getEmployeeRole())
                    .build();
        }
        else
        {
             updatedEmployee = new Cleaner.Builder().copy((Cleaner) currentEmployee)
                    .name(employee.getName())
                    .surname(employee.getSurname())
                    .dateOfBirth(employee.getDateOfBirth())
                    .role(employee.getEmployeeRole())
                    .build();
        }
        employeeService.update(updatedEmployee);
        return new ResponseEntity<Employee>(updatedEmployee, HttpStatus.OK);
    }

    @RequestMapping(value = "/employee/{employeeID}", method = RequestMethod.DELETE)
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("employeeID") String employeeID)
    {
        Employee employee = employeeService.readById(employeeID);

        if(employee == null)
        {
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }

        employeeService.delete(employee);
        return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
    }
}
