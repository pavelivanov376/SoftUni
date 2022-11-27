package com.pavel.webapp.services;

import com.pavel.webapp.dto.CreateJobDto;
import com.pavel.webapp.entities.JobOffer;
import com.pavel.webapp.entities.PersonAccount;
import com.pavel.webapp.repositories.JobOfferRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class JobOfferServiceImpl implements JobOfferService {
    private final JobOfferRepository jobOfferRepository;
    public final ModelMapper mapper;

    public JobOfferServiceImpl(JobOfferRepository jobOfferRepository, ModelMapper mapper) {
        this.jobOfferRepository = jobOfferRepository;
        this.mapper = mapper;
    }

    @Override
    public void create(CreateJobDto jobDto) {
        var jobOffer = mapper.map(jobDto, JobOffer.class);

        jobOfferRepository.save(jobOffer);
    }
}
