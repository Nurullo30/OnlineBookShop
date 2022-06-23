package com.company.entities;

import com.company.DTO.UserDTO;
import com.company.commonService.UserRole;
import org.apache.catalina.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EdsUser {
    private String UID;
    private String name;
    private String surname;
    private String age;
    private String login;
    private String password;
    private String gender;
    private UserRole userRole;

    public EdsUser(){

    }

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public EdsUser toEntity(ResultSet rs) throws SQLException {
        setUID(rs.getString("userid"));
        setName(rs.getString("name"));
        setSurname(rs.getString("surname"));
        setAge(rs.getString("age"));
        setLogin(rs.getString("login"));
        setPassword(rs.getString("password"));
        setGender(rs.getString("gender"));
        setUserRole(rs.getString("role").equals(UserRole.USER.toString()) ?
                UserRole.USER : UserRole.ADMIN);
        return this;
    }

    public UserDTO toDTO(EdsUser user){
        UserDTO userDTO = new UserDTO();

        userDTO.setUID(getUID());
        userDTO.setName(getName());
        userDTO.setSurname(getSurname());
        userDTO.setAge(getAge());
        userDTO.setUserRole(getUserRole());
        userDTO.setGender(getGender());

        return userDTO;
    }
}
