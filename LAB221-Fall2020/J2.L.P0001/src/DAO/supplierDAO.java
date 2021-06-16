/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Suppliers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author truon
 */
public class supplierDAO {
    public static ArrayList<Suppliers> getsSupplier() throws SQLException{
        ArrayList<Suppliers> listSupplier=new ArrayList<>();
        Connection cn=MyConnection.makeConnection();
        if(cn!=null){
            String sql="SELECT supCode,supName,address,collaborating\n" +
                        "FROM dbo.tblSuppliers";
            PreparedStatement pst=cn.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            while (rs.next()) {                
                String supCode=rs.getString("supCode");
                String supName=rs.getString("supName");
                String address=rs.getString("address");
                boolean collaborating=rs.getBoolean("collaborating");                            
                Suppliers s=new Suppliers(supCode, supName, address, collaborating);
                listSupplier.add(s);
            }
            cn.close();
        }
    return listSupplier;
    }
    public static String findUseSP(String supCode) throws SQLException{       
        Connection cn=MyConnection.makeConnection();
        String supName = null;
        if(cn!=null){
            String sql="SELECT supName\n" +
                    "FROM dbo.tblItems INNER JOIN dbo.tblSuppliers ON tblSuppliers.supCode = tblItems.supCode\n" +
                    "WHERE tblItems.supCode=?";
            PreparedStatement pst=cn.prepareStatement(sql);
            pst.setString(1, supCode);
            ResultSet rs=pst.executeQuery();
            
            while (rs.next()) {                
                supName=rs.getString("supName");
            }
            cn.close();
        }
    return supName;
    }
   
     public static int insertSupplier(String supCode,String supName,String address,boolean collaborating) throws SQLException{
        Connection cn=MyConnection.makeConnection();
        int result=0;
        if(cn!=null){
            String sql="insert dbo.tblSuppliers values(?,?,?,?)";
            PreparedStatement pst=cn.prepareStatement(sql);
            pst.setString(1, supCode);
            pst.setString(2, supName);
            pst.setString(3, address);
            pst.setBoolean(4, collaborating);
            result=pst.executeUpdate();
            cn.close();
        }
        return result;
    }
    public static int updateSupplier(String supCode,String supName,String address,boolean collaborating) throws SQLException{
        Connection cn=MyConnection.makeConnection();
        int result=0;
        if(cn!=null){
            String sql="UPDATE dbo.tblSuppliers SET supName=?,address=?,collaborating=? where supCode=?";
            PreparedStatement pst=cn.prepareStatement(sql);
            pst.setString(1, supName);
            pst.setString(2, address);
            pst.setBoolean(3, collaborating);
            pst.setString(4, supCode);
            result=pst.executeUpdate();
            cn.close();
        }
        return result;
    }
    public static int deleteSupplier(String supCode) throws SQLException{
        Connection cn=MyConnection.makeConnection();
        int result=0;
        if(cn!=null){
            String sql="DELETE FROM dbo.tblSuppliers WHERE supCode=?";
            PreparedStatement pst=cn.prepareStatement(sql);
            pst.setString(1, supCode);
            result=pst.executeUpdate();
            cn.close();
        }
        return result;
    }   
}
