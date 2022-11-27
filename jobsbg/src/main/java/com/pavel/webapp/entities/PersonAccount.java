package com.pavel.webapp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.Collection;

@Entity(name = "person_accounts")
public class PersonAccount extends UserAccount {
    Collection<JobOffer> appliedJobOffers;

    @OneToMany
    public Collection<JobOffer> getAppliedJobOffers() {
        return appliedJobOffers;
    }

    public void setAppliedJobOffers(Collection<JobOffer> appliedJobOffers) {
        this.appliedJobOffers = appliedJobOffers;
    }
}
