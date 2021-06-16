/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duylp.dao;

import duylp.dtos.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author LP D
 */
public class CategoryDAO {
    
    public static ArrayList<Category> getsCategory() throws SQLException {
        ArrayList<Category> listCategory = new ArrayList<>();
        Connection cn = MyConnection.makeConnection();
        if (cn != null) {
            String sql = "SELECT categoryID, categoryName, description\n" +
                        "FROM dbo.TblCategories";
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String categoryID = rs.getString("categoryID");
                String categoryName = rs.getString("categoryName");
                String description = rs.getString("description");
                Category c = new Category(categoryID, categoryName, description);
                listCategory.add(c);
            }
            cn.close();
        }
        return listCategory;
    }
    
    public static String findUseSP(String categoryID) throws SQLException{       
        Connection cn = MyConnection.makeConnection();
        String categoryName = null;
        if(cn!=null){
            String sql="SELECT categoryName\n" +
                    "FROM dbo.TblProducts INNER JOIN dbo.TblCategories ON TblCategories.categoryID = TblProducts.categoryID\n" +
                    "WHERE TblProducts.categoryID=?";
            PreparedStatement pst=cn.prepareStatement(sql);
            pst.setString(1, categoryID);
            ResultSet rs=pst.executeQuery();            
            while (rs.next()) {                
                categoryName=rs.getString("categoryName");
            }
            cn.close();
        }
        return categoryName;
    }
    
    public static int insertCategory(String categoryID,String categoryName,String description) throws SQLException{
        Connection cn = MyConnection.makeConnection();
        int result=0;
        if(cn!=null){
            String sql="INSERT dbo.TblCategories VALUES(?,?,?)";
            PreparedStatement pst=cn.prepareStatement(sql);
            pst.setString(1, categoryID);
            pst.setString(2, categoryName);
            pst.setString(3, description);
            result=pst.executeUpdate();
            cn.close();
        }
        return result;
    }
    
    public static int updateCategory(String categoryID,String categoryName,String description) throws SQLException{
        Connection cn = MyConnection.makeConnection();
        int result=0;
        if(cn!=null){
            String sql="UPDATE dbo.TblCategories SET categoryName=?, description=? where categoryID=?";
            PreparedStatement pst=cn.prepareStatement(sql);
            pst.setString(1, categoryName);
            pst.setString(2, description);
            pst.setString(3, categoryID);
            result=pst.executeUpdate();
            cn.close();
        }
        return result;
    }
    
    public static int deleteCategory(String categoryID) throws SQLException{
        Connection cn = MyConnection.makeConnection();
        int result=0;
        if(cn!=null){
            String sql="DELETE FROM dbo.TblCategories WHERE categoryID=?";
            PreparedStatement pst=cn.prepareStatement(sql);
            pst.setString(1, categoryID);
            result=pst.executeUpdate();
            cn.close();
        }
        return result;
    }
    
}
