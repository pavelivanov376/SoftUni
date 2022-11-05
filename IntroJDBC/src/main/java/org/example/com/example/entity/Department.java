package org.example.com.example.entity;

import org.example.ormFramework.annotation.Column;
import org.example.ormFramework.annotation.Entity;
import org.example.ormFramework.annotation.Id;

@Entity(tableName = "departments")
public class Department {
    @Id
    private int id;
    @Column(name = "name", columnDefinition = "VARCHAR(211)")
    private String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
