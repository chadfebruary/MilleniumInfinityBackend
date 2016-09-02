package com.milleniuminfinity.app.client;

import com.milleniuminfinity.app.domain.employee.Employee;
import com.milleniuminfinity.app.services.employee.Impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by cfebruary on 2016/08/18.
 */
@RestController
public class EmployeeController implements Serializable{

    //Inject service
    @Autowired
    private EmployeeServiceImpl employeeService;

    //Create an employee
    @RequestMapping(value = "/employee/", method = RequestMethod.POST)
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee newEmployee, UriComponentsBuilder ucBuilder){
        System.out.println("Creating request for: " + newEmployee.toString());

        Employee employee = employeeService.create(newEmployee);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/employee/{employeeID}").buildAndExpand(newEmployee.getEmployeeID()).toUri());

        return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
    }

    //Retrieve a single employee
    @RequestMapping(value = "/employee/{employeeID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getEmployee(@PathVariable("employeeID") String employeeID){
        System.out.println("Fetching employee with employee ID: " + employeeID);

        Employee request = employeeService.readById(employeeID);
        if(request == null){
            System.out.println("Employee with employee ID: " + employeeID + " NOT FOUND");
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Employee>(request, HttpStatus.OK);
    }

    //Retrieve all employees
    @RequestMapping(value = "/employees/", method = RequestMethod.GET)
    public ResponseEntity<Set<Employee>> getEmployees(){
        Set<Employee> requests = employeeService.readAll();

        if(requests.isEmpty()){
            return new ResponseEntity<Set<Employee>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Set<Employee>>(requests, HttpStatus.OK);
    }

    //Update an employee
    @RequestMapping(value = "/employee/{employeeID}", method = RequestMethod.PUT)
    public ResponseEntity<Employee> updateEmployee(@PathVariable("employeeID") String employeeID, @RequestBody String employee)
    {
        Employee currentRequest = (Employee) employeeService.readById(employeeID);

        if(currentRequest == null) {
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }

        Gson gson = new Gson();
        Employee updatedEmployee = new Employee.Builder()
                .copy(currentRequest)
                .name(gson.fromJson(employee, Employee.class).getName())
                .surname(gson.fromJson(employee, Employee.class).getSurname())
                .dateOfBirth(gson.fromJson(employee, Employee.class).getDateOfBirth())
                .role(gson.fromJson(employee, Employee.class).getEmployeeRole())
                .build();

        employeeService.update(updatedEmployee);
        return new ResponseEntity<Employee>(updatedEmployee, HttpStatus.OK);
    }

    //Delete an employee
    @RequestMapping(value = "/employee/{employeeID}", method = RequestMethod.DELETE)
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("employeeID") String employeeID)
    {
        Employee request = employeeService.readById(employeeID);

        if(request == null)
        {
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }

        employeeService.delete(request);
        return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
    }
}
