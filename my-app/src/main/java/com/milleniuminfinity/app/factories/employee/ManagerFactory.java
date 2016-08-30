package com.milleniuminfinity.app.factories.employee;

import com.milleniuminfinity.app.domain.employee.Employee;
import com.milleniuminfinity.app.domain.employee.Manager;

import java.io.Serializable;

/**
 * Created by 208023429 on 5/13/2016.
 */
public class ManagerFactory implements Serializable {

    public static Employee getManager(String employeeId, String name, String surname, String dateOfBirth, String role) {

        Employee manager = new Manager.Builder()
                .employeeID(employeeId)
                .name(name)
                .surname(surname)
                .dateOfBirth(dateOfBirth)
                .role(role)
                .build();

        return manager;
    }
}
