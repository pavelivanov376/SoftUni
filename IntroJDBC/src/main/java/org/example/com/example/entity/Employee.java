package org.example.com.example.entity;

import org.example.ormFramework.annotation.Column;
import org.example.ormFramework.annotation.Entity;
import org.example.ormFramework.annotation.Id;

@Entity(tableName = "employees")
public class Employee {
@Id
private  int employeeId;
    @Column(name = "employee_salary", columnDefinition = "DECIMAL(19,4)")
    private double salary;

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
