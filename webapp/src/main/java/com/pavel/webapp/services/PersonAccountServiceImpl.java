package com.pavel.webapp.services;

import com.pavel.webapp.dto.UserLoginDto;
import com.pavel.webapp.dto.UserRegisterDto;
import com.pavel.webapp.entities.PersonAccount;
import com.pavel.webapp.repositories.PersonAccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PersonAccountServiceImpl implements PersonAccountService {

    public final ModelMapper mapper;
    private final PersonAccountRepository personAccountRepository;

    public PersonAccountServiceImpl(PersonAccountRepository personAccountRepository, ModelMapper mapper) {
        this.personAccountRepository = personAccountRepository;
        this.mapper = mapper;
    }

    @Override
    public boolean register(UserRegisterDto userDto) {

        if (personAccountRepository.existsByUsernameOrEmail(userDto.getUsername(), userDto.getEmail())) {
            return false;
        }
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            return false;
        }

        var user = mapper.map(userDto, PersonAccount.class);

        personAccountRepository.save(user);

        return true;
    }

    @Override
    public Long validateUserLogin(UserLoginDto userLoginDto) {

        var personAccount = personAccountRepository.findFirstByUsername(userLoginDto.getUsername());

        if (personAccount == null) {
            return null;
        }
        if (!personAccount.getPassword().equals(userLoginDto.getPassword())) {
            return null;
        }

        return personAccount.getId();
    }

}
