package com.company.commonService;

import com.company.entities.Book;
import com.company.entities.EdsUser;
import com.company.DAO.UserRepository;

import java.util.List;

public class Validation {
    UserRepository userRepository;
    List<EdsUser> edsUserList;

    public Validation(){
        userRepository = new UserRepository();
    }

    public Boolean checkIfLoginExist(String userTypedLogin){
        for (EdsUser edsUser : userRepository.getUsers()) {
            if (userTypedLogin.equals(edsUser.getLogin()))
             return false;
        }
        return true;
    }

    public Boolean isValueMinMax(int userValue , int min, int max) {
        return userValue >= min && userValue <= max;
    }
        public Boolean isValueNumber(String str){
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j <= 9; j++) {
                if (String.valueOf(j).equals(String.valueOf(str.charAt(i)))){
                    break;
                } else if (j == 9){
                    return false;
                }
            }
        }
        return true;
    }

    public Boolean checkIdRange(int bookId, List<Book> bookList){
        if (bookId <= bookList.size() && bookId >= 1){return true;}
        return false;
    }
}
