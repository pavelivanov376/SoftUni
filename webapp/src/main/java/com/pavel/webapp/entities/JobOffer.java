package com.pavel.webapp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "job_offers")
public class JobOffer extends BaseEntity {
    private String name;
    private String description;
    private String requirements;
    private Double salary;
    private int applicantsCounter;

    @Column(name = "applicants_counter")
    public int getApplicantsCounter() {
        return applicantsCounter;
    }

    public void setApplicantsCounter(int applicantsCounter) {
        this.applicantsCounter = applicantsCounter;
    }


    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "requirements", nullable = false)
    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    @Column(name = "salary")
    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
