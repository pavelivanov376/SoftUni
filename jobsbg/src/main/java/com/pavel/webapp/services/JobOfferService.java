package com.pavel.webapp.services;

import com.pavel.webapp.dto.CreateJobDto;

public interface JobOfferService {
    void create(CreateJobDto jobDto);
}
