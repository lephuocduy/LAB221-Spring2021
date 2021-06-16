/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duylp.dao;

import duylp.dtos.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author LP D
 */
public class ProductDAO {
    
    public static ArrayList<Product> getsProduct() throws SQLException{
        ArrayList<Product> listProduct=new ArrayList<>();
        Connection cn = MyConnection.makeConnection();
        if(cn!=null){
            String sql="SELECT productID, productName, unit, price, quantity,TblProducts.categoryID AS categoryID ,categoryName\n" +
                        "FROM dbo.TblProducts INNER JOIN dbo.TblCategories ON TblCategories.categoryID = TblProducts.categoryID";
            PreparedStatement pst=cn.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            while (rs.next()) {                
                String productID = rs.getString("productID");
                String productName = rs.getString("productName");
                String unit = rs.getString("unit");
                float price = rs.getFloat("price");
                int quantity = rs.getInt("quantity");
                String categoryID = rs.getString("categoryID");
                String categoryName = rs.getString("categoryName");  
                Product p = new Product(productID, productName, unit, price, quantity, categoryID, categoryName);
                listProduct.add(p);
            }
            cn.close();
        }
    return listProduct;
    }
    public static int insertProduct(String productID, String productName, String unit, float price, int quantity, String categoryID) throws SQLException{
        Connection cn = MyConnection.makeConnection();
        int result=0;
        if(cn!=null){
            String sql="INSERT dbo.TblProducts VALUES(?,?,?,?,?,?)";
            PreparedStatement pst=cn.prepareStatement(sql);
            pst.setString(1, productID);
            pst.setString(2, productName);
            pst.setString(3, unit);
            pst.setFloat(4, price);
            pst.setInt(5, quantity);
            pst.setString(6, categoryID);
            result=pst.executeUpdate();
            cn.close();
        }
        return result;
    }
    public static int updateProduct(String productID, String productName, String unit, float price, int quantity, String categoryID) throws SQLException{
        Connection cn = MyConnection.makeConnection();
        int result=0;
        if(cn!=null){
            String sql="UPDATE dbo.TblProducts SET productName=?, unit=?, price=?, quantity=?, categoryID=? WHERE productID=?";
            PreparedStatement pst=cn.prepareStatement(sql);            
            pst.setString(1, productName);
            pst.setString(2, unit);
            pst.setFloat(3, price);
            pst.setInt(4, quantity);
            pst.setString(5, categoryID);
            pst.setString(6, productID);
            result=pst.executeUpdate();
            cn.close();
        }
        return result;
    }
    public static int deleteProduct(String productID) throws SQLException{
         int result = 0;
         Connection cn = MyConnection.makeConnection();
         if ( cn != null ) {
             String sql="delete from dbo.TblProducts where productID=?";
             PreparedStatement pst = cn.prepareStatement(sql);
             pst.setString(1, productID);
             result = pst.executeUpdate();
             cn.close();
         }
         return result;
    }
    
}
