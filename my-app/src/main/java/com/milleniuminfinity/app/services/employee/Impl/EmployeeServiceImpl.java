package com.milleniuminfinity.app.services.employee.Impl;

import com.milleniuminfinity.app.domain.employee.Employee;
import com.milleniuminfinity.app.repository.employee.EmployeeRepository;
import com.milleniuminfinity.app.services.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by Chad on 5/8/2016.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee)
    {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee readById(String employeeID)
    {
        return employeeRepository.findOne(employeeID);
    }

    @Override
    public Set<Employee> readAll()
    {
        Set<Employee> result = new HashSet<Employee>();
        while(employeeRepository.findAll().iterator().hasNext())
        {
            result.add(employeeRepository.findAll().iterator().next());
        }
        return result;
    }

    @Override
    public Employee update(Employee employee)
    {
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(Employee employee)
    {
        employeeRepository.delete(employee);
    }
}
