package com.pavel.webapp.services;

import com.pavel.webapp.dto.UserLoginDto;
import com.pavel.webapp.dto.UserRegisterDto;

public interface PersonAccountService {

    boolean register(UserRegisterDto userDto);

    Long validateUserLogin(UserLoginDto userLoginDto);

}
