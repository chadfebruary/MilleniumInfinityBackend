package com.milleniuminfinity.app.domain.employee;
/**
 * Created by 208023429 on 4/14/2016.
 */
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;


@javax.persistence.Entity
public class Cleaner implements Serializable, Employee{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String name, surname;
    private String dateOfBirth;
    private String employeeID;
    String role = "Cleaner";

    public Cleaner(Builder builder)
    {
        this.name = builder.name;
        this.surname = builder.surname;
        this.dateOfBirth = builder.dateOfBirth;
        this.employeeID = builder.employeeID;
        this.role = builder.role;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String getSurname()
    {
        return surname;
    }

    @Override
    public String getDateOfBirth()
    {
        return dateOfBirth;
    }

    @Override
    public String getEmployeeID()
    {
        return employeeID;
    }

    @Override
    public String getEmployeeRole()
    {
        return role;
    }

    public static class Builder
    {
        private String name, surname;
        String dateOfBirth;
        String employeeID;
        String role;

        public Builder employeeID(String value)
        {
            this.employeeID = value;
            return this;
        }

        public Builder name(String value)
        {
            this.name = value;
            return this;
        }

        public Builder surname(String value)
        {
            this.surname = value;
            return this;
        }

        public Builder dateOfBirth(String value)
        {
            this.dateOfBirth = value;
            return this;
        }

        public Builder role(String role)
        {
            this.role = role;
            return this;
        }

        public Builder copy(Cleaner value)
        {
            this.name = value.getName();
            this.surname = value.getSurname();
            this.dateOfBirth = value.getDateOfBirth();
            this.employeeID = value.getEmployeeID();
            this.role = value.getEmployeeRole();

            return this;
        }

        public Cleaner build()
        {
            return new Cleaner(this);
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;

        if(o == null || getClass() != o.getClass()) return false;

        Cleaner cleaner = (Cleaner) o;

        return employeeID != null ? employeeID.equals(cleaner.employeeID) : cleaner.employeeID == null;

    }

    @Override
    public int hashCode()
    {
        return employeeID != null ? employeeID.hashCode() : 0;
    }


}