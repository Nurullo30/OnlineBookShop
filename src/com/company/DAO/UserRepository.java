package com.company.DAO;

import com.company.DTO.UserDTO;
import com.company.Exceptions.UserNotFoundException;
import com.company.constants.CommonConstants;
import com.company.constants.Constants;
import com.company.commonService.UserRole;
import com.company.entities.EdsUser;
import com.company.enums.UserCredentials;
import com.company.factory.ClassFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserRepository implements UserDAO {
    private String userPath;
   // private Connection conn;

    public UserRepository(){
        init();
    }

    public void init() {
        //    conn = ClassFactory.connectToDB();
    }

    public String registerNewUser(EdsUser edsUser) throws SQLException {
        Connection conn = ClassFactory.connectToDB();
            String query = "insert into users (userid, name, surname, age, login, password, gender, role) values(?,?,?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, edsUser.getUID());
            pst.setString(2, edsUser.getName());
            pst.setString(3, edsUser.getSurname());
            pst.setString(4, edsUser.getAge());
            pst.setString(5, edsUser.getLogin());
            pst.setString(6, edsUser.getPassword());
            pst.setString(7, edsUser.getGender());
            pst.setString(8, edsUser.getUserRole().toString());
            pst.executeUpdate();
        conn.close();
        return Constants.SUCCESSFUL;
    }

    @Override
    public Boolean checkUserCredentials(UserCredentials userCredential, String userInfo) {
        String sql = null;
        switch (userCredential){
            case LOGIN:
                sql = "SELECT * FROM users where login = '" + userInfo + "'";
                break;
            case NAME:
                sql = "SELECT * FROM users where name = '" + userInfo + "'";
                break;
        }
        try {
            Statement stmt = ClassFactory.connectToDB().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                return true;
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public List<EdsUser> getUsers() {
        List<EdsUser> edsUserList = new ArrayList<>();
        try {
            Statement stmt = ClassFactory.connectToDB().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from users");
            EdsUser user = new EdsUser();
            while (rs.next()){
                user.setUID(rs.getString("userid"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setAge(rs.getString("age"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setGender(rs.getString("gender"));
                user.setUserRole(rs.getString("role").equals(UserRole.USER.toString()) ?
                        UserRole.USER : UserRole.ADMIN);
                edsUserList.add(user);
            }
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return edsUserList;
    }

    public EdsUser checkForUser(String login , String password) throws UserNotFoundException {
        try {
            Connection conn = ClassFactory.connectToDB();
            PreparedStatement pst= conn.prepareStatement("SELECT * FROM users where login=? and password= ?");
            pst.setString(1, login);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                EdsUser user = new EdsUser();
                user.setUID(rs.getString("userid"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setAge(rs.getString("age"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setGender(rs.getString("gender"));
                user.setUserRole(rs.getString("role").equals(UserRole.USER.toString()) ?
                        UserRole.USER : UserRole.ADMIN);

            conn.close();
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new UserNotFoundException();
    }

    public EdsUser getUserProfileByUserId(String userId) throws UserNotFoundException {
        try {
            Connection conn = ClassFactory.connectToDB();
            PreparedStatement pst= conn.prepareStatement("SELECT * FROM users where userid=?");
            pst.setString(1, userId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                EdsUser user = new EdsUser();
                user.toEntity(rs);
                conn.close();
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new UserNotFoundException();
    }

    @Override
    public String updateUserCredentials(UserDTO newUserData) {
        try {
            Connection conn = ClassFactory.connectToDB();
            PreparedStatement pst= conn.prepareStatement("UPDATE users set name=?,surname= ?,gender=?,age=?, role=? where userid=?");
            pst.setString(1, newUserData.getName());
            pst.setString(2, newUserData.getSurname());
            pst.setString(3, newUserData.getGender());
            pst.setString(4, newUserData.getAge());
            pst.setString(5, UserRole.USER.toString());
            pst.setString(6, newUserData.getUID());
            pst.executeUpdate();
            conn.close();
            return Constants.SUCCESSFUL;
        } catch (SQLException e) {
            e.printStackTrace();
        }
     return Constants.FAILED;
    }
}
