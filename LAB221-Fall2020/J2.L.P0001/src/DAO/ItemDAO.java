/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Item;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author truon
 */
public class ItemDAO {
    public static ArrayList<Item> getsItem() throws SQLException{
        ArrayList<Item> list=new ArrayList<>();
        Connection cn=MyConnection.makeConnection();
        if(cn!=null){
            String sql="SELECT itemCode,itemName,unit,price,supplying,tblItems.supCode AS supCode ,supName\n" +
                        "FROM dbo.tblItems INNER JOIN dbo.tblSuppliers ON tblSuppliers.supCode = tblItems.supCode";
            PreparedStatement pst=cn.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            while (rs.next()) {                
                String itemCode=rs.getString("itemCode");
                String itemName=rs.getString("itemName");
                String unit=rs.getString("unit");
                float price=rs.getFloat("price");
                boolean supplying=rs.getBoolean("supplying");
                String subCode=rs.getString("supCode");  
                String supName=rs.getString("supName");
                Item s=new Item(itemCode,itemName,unit,price,supplying,subCode,supName);
                list.add(s);
            }
            cn.close();
        }
    return list;
    }
    public static int insertItem(String itemCode,String itemName,String unit,float price,boolean supplying,String supCode) throws SQLException{
        Connection cn=MyConnection.makeConnection();
        int result=0;
        if(cn!=null){
            String sql="insert dbo.tblItems values(?,?,?,?,?,?)";
            PreparedStatement pst=cn.prepareStatement(sql);
            pst.setString(1, itemCode);
            pst.setString(2, itemName);
            pst.setString(3, unit);
            pst.setFloat(4, price);
            pst.setBoolean(5, supplying);
            pst.setString(6, supCode);
            result=pst.executeUpdate();
            cn.close();
        }
        return result;
    }
    public static int updateItem(String itemCode,String itemName,String unit,float price,boolean supplying,String supCode) throws SQLException{
        Connection cn=MyConnection.makeConnection();
        int result=0;
        if(cn!=null){
            String sql="UPDATE dbo.tblItems SET itemName=?,unit=?,price=?,supplying=?,supCode=? where itemCode=?";
            PreparedStatement pst=cn.prepareStatement(sql);            
            pst.setString(1, itemName);
            pst.setString(2, unit);
            pst.setFloat(3, price);
            pst.setBoolean(4, supplying);
            pst.setString(5, supCode);
            pst.setString(6, itemCode);
            result=pst.executeUpdate();
            cn.close();
        }
        return result;
    }
    public static int deleteItem(String itemCode) throws SQLException{
        Connection cn=MyConnection.makeConnection();
        int result=0;
        if(cn!=null){
            String sql="DELETE FROM dbo.tblItems WHERE itemCode=?";
            PreparedStatement pst=cn.prepareStatement(sql);
            pst.setString(1, itemCode);
            result=pst.executeUpdate();
            cn.close();
        }
        return result;
    }
//     public static void main(String[] args) throws SQLException {
//        ItemDAO dd=new ItemDAO();
//        ArrayList<Item> listt=dd.getsItem();
//        System.out.println(listt.toString());
//    }
   
}
