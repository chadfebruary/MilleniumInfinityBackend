package com.milleniuminfinity.app.repository.employee;

import com.milleniuminfinity.app.domain.employee.Employee;
import com.milleniuminfinity.app.repository.Repository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Chad on 4/21/2016.
 */
@org.springframework.stereotype.Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {

}
