package com.company.DTO;

import com.company.commonService.UserRole;

public class UserDTO {

    private String UID;
    private String name;
    private String surname;
    private String age;
    private String gender;
    private UserRole userRole;
    private String getAllUsersSize;

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getGetAllUsersSize() {
        return getAllUsersSize;
    }

    public void setGetAllUsersSize(String getAllUsersSize) {
        this.getAllUsersSize = getAllUsersSize;
    }
}
