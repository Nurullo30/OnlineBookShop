package com.company.signUp;

import com.company.entities.EdsUser;

import java.util.List;

public interface RegistrationService {

    String registration(String name, String surname, String age, String login, String password , String gender);

    String checkIfLoginExist(String userCredentials);

}
