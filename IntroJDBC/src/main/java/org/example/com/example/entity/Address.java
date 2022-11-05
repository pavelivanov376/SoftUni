package org.example.com.example.entity;

import org.example.ormFramework.annotation.Column;
import org.example.ormFramework.annotation.Entity;
import org.example.ormFramework.annotation.Id;

import javax.print.DocFlavor;
import java.security.Key;

@Entity(tableName = "addresses")
public class Address {
    @Id
    private int id;

    @Column(name = "street", columnDefinition = "VARCHAR(255")
    private String streetName;

    @Column(name = "street_number", columnDefinition = "VARCHAR(255")
    private String streetNumber;

    @Column(name = "people_count", columnDefinition = "INT11")
    private int peopleCount;
}
