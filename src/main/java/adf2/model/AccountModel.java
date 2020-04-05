package adf2.model;

import adf2.entily.Account;
import adf2.helper.ConnectionHelper;

import java.sql.*;
import java.util.ArrayList;

public class AccountModel {


    public boolean save(Account account){
        try {
            String cmd = "insert into accounts(userName,password,fullName,createdDate)  values(?,?,?,?)";
            PreparedStatement preparedStatement = ConnectionHelper.getConnection().prepareStatement(cmd);
            preparedStatement.setString(1,account.getUsername());
            preparedStatement.setString(2,account.getPassword());
            preparedStatement.setString(3,account.getFullName());
            Date date = Date.valueOf(account.getCreatedDate());
            preparedStatement.setDate(4, date);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Account> loadAccount(){
        ArrayList<Account> accountArrayList = new ArrayList<Account>();
        try {
            String cmd = "select * from accounts";
            PreparedStatement preparedStatement = ConnectionHelper.getConnection().prepareStatement(cmd);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String userName = resultSet.getString("userName");
                String password1 = resultSet.getString("password");
                String fullname = resultSet.getString("fullName");
                Date date = resultSet.getDate("createdDate");
                String dateCreate = date.toString();
                Account account = new Account(userName,password1,fullname,dateCreate);
                accountArrayList.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountArrayList;
    }

    public static Account getAccount(String userNanme,String password){
        try {
            String cmd = "select  * from accounts where userName = ? and password = ?";
            PreparedStatement preparedStatement = ConnectionHelper.getConnection().prepareStatement(cmd);
            preparedStatement.setString(1,userNanme);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String userName = resultSet.getString("userName");
                String password1 = resultSet.getString("password");
                String fullname = resultSet.getString("fullName");
                Date date = resultSet.getDate("createdDate");
                String dateCreate = date.toString();
                Account account = new Account(userName,password1,fullname,dateCreate);
                return account;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void test() throws SQLException{
        Statement stt = ConnectionHelper.getConnection().createStatement();
        stt.execute("truncate table accounts");
    }
}
