package com.milleniuminfinity.app.factories.employee;

import com.milleniuminfinity.app.domain.employee.Employee;
import com.milleniuminfinity.app.domain.employee.SalesRepresentative;

import java.io.Serializable;

/**
 * Created by 208023429 on 5/13/2016.
 */
public class SalesRepresentativeFactory implements Serializable {

    public static Employee getSalesRepresentative(String employeeID, String type, String name, String surname, String dateOfBirth) {
        Employee salesRep = new SalesRepresentative.Builder()
                .name(name)
                .surname(surname)
                .dateOfBirth(dateOfBirth)
                .employeeID(employeeID)
                .build();

        return salesRep;
    }
}
