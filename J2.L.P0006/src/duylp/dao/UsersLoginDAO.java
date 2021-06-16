/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duylp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author LP D
 */
public class UsersLoginDAO {
    public static boolean checkLogin(String userID, String password) throws SQLException {
        boolean status = false;
        Connection cn = null;
        cn = MyConnection.makeConnection();
        if (cn != null) {
            String sql="SELECT status\n" +
                        "FROM dbo.tblUsers\n" +
                        "WHERE userID=? AND password=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, userID);
            pst.setString(2, password);
            ResultSet rs=pst.executeQuery();
            if(rs != null && rs.next()){
                status = rs.getBoolean("status");
                cn.close();
            }
        }
        return status;
    }
    
}
