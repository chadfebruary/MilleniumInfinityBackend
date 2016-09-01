package com.milleniuminfinity.app.repository.employee;

import com.milleniuminfinity.app.domain.employee.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Chad on 4/21/2016.
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {

}
