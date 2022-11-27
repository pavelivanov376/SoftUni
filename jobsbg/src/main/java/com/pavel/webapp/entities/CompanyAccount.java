package com.pavel.webapp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.Collection;

@Entity(name = "company_accounts")
public class CompanyAccount extends UserAccount {
    Collection<JobOffer> openPositions;

    @OneToMany
    public Collection<JobOffer> getOpenPositions() {
        return openPositions;
    }

    public void setOpenPositions(Collection<JobOffer> openPositions) {
        this.openPositions = openPositions;
    }
}

