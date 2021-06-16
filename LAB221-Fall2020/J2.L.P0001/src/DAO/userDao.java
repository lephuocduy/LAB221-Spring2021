/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author truon
 */
public class userDao {
    public static String checkLogin(String userID,String password) throws SQLException{
        String result="";
        Connection cn=null;
        cn=MyConnection.makeConnection();
        if(cn!=null){         
            String sql="SELECT fullName\n" +
                        "FROM dbo.tblUsers\n" +
                        "WHERE userID=? AND password=?";
            PreparedStatement pst=cn.prepareStatement(sql);
            pst.setString(1, userID);
            pst.setString(2, password);
            ResultSet rs=pst.executeQuery();
            
            if(rs.next()){
                result=rs.getString("fullName");
            cn.close();
            }
        }      
        return result;
    }
//    public static void main(String[] args) {
//        userDao pp=new userDao();
//        try {
//            System.out.println(""+pp.checkLogin("abc@fe.vn", "123456"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }  
//    }
}
