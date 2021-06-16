/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duylp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author LP D
 */
public class MyConnection {
    public static Connection makeConnection(){
        Connection cn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://localhost:1433;databaseName= ProductManagement";
            cn = DriverManager.getConnection(url, "sa", "123456");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "lỗi kết nối sever");
        }
        return cn;
    }
    
}
