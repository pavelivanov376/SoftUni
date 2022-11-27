package com.pavel.webapp.repositories;

import com.pavel.webapp.entities.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {
    /**
    Returns whether there is sth in the repository
     */
    boolean existsAllBy();
}
