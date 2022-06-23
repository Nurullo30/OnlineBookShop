package com.company.UI;

import com.company.constants.CommonConstants;
import com.company.constants.Constants;
import com.company.constants.UserConstant;
import com.company.signUp.RegistrationService;

import java.util.Scanner;

public class RegistrationView implements UserConstant {
    RegistrationService registrationService;
    Scanner scanner;

    public RegistrationView(RegistrationService registrationService){
        this.registrationService = registrationService;
        scanner = new Scanner(System.in);
    }

//    public void startReg() { // Change Method length (too long) // Done
//        while (true) {
//            String name = registerName();
//            String surname = registerSurname();
//            String age = registerAge();
//            String login = registerLogin();
//            String password = registerPassword();
//
//            boolean isUserSuccessful = registerUser(name, surname, age, login, password);
//
//            if (!isUserSuccessful)
//                break;
//
//            if (exitMenu())
//                break;
//        }
//    }
    private String registerName(){
        System.out.println(CommonConstants.NAME + ":");
        return scanner.nextLine();
    }

    private String registerSurname(){
        System.out.println(CommonConstants.SURNAME + ":");
        return scanner.nextLine();
    }

    private String registerAge(){
        int age = 0;
        while(true){
            Scanner ageScanner = new Scanner(System.in);
            System.out.println(CommonConstants.AGE + ":");
            try {
                age = ageScanner.nextInt();
                break;
            } catch (Exception e){
                System.out.println(CommonConstants.TRY_AGAIN);
            }
        }
        return String.valueOf(age);
    }
    private String registerLogin(){
        String login ="";
        while (true){
            System.out.println(CommonConstants.LOGIN);
            login = scanner.nextLine();
            if (registrationService.checkIfLoginExist(login).equals(CommonConstants.LOGIN_FREE)){
                break;
            }
            System.out.println(CommonConstants.USER_EXIST);
        }
       return login;
    }
    public String registerPassword(){
        System.out.println(CommonConstants.PASSWORD);
        return scanner.nextLine();
    }

    private boolean registerUser(String name, String surname, String age, String login, String password , String gender){
       String regStatus = registrationService.registration(name, surname, age, login , password , gender);
        if (regStatus.equals(Constants.SUCCESSFUL)){
            System.out.println(REGISTRATION_SUCCESS);
            return true;
        }
       System.out.println(REGISTRATION_FAIL);
       return false;
    }

    public boolean exitMenu(){
        while (true) {
            System.out.println(CommonConstants.ONE + " " + CommonConstants.MAIN_MENU);
            String mainMenuNum = scanner.nextLine();
            boolean isMainMenu = mainMenuNum.equals(CommonConstants.ONE);
            if(isMainMenu){
               break;
            }
            System.out.println(CommonConstants.TRY_AGAIN);
        }
        return true;
    }
}
