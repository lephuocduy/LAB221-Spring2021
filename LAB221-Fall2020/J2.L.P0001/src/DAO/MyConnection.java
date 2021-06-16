/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author truon
 */
public class MyConnection {
    public static Connection makeConnection(){
        Connection cn=null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://localhost:1433;databaseName=ItemManagement";
            cn=DriverManager.getConnection(url,"sa","123456789");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "loi ket noi sever");
           // e.printStackTrace();
        }
        return cn;
    }
    
}
