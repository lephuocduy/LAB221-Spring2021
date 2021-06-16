/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validation;



/**
 *
 * @author truon
 */
public class checkValue {

    public static boolean checkArmorId(String ArmorId) {
        try {       
            if (ArmorId.contains("#") || ArmorId.contains("@") || ArmorId.contains("$")) {              
                return false;
            }else if (ArmorId.length() > 10) {               
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean checkClassification(String Classification) {
        if (Classification.length() > 30) {            
            return false;
        }
        return true;
    }

    public static boolean checkDescription(String Description) {
        if (Description.length() > 300) {           
            return false;
        }
        return true;
    }

    public static boolean checkDefense(String Defense) {
        if (!Defense.matches("\\d+") || Integer.parseInt(Defense) <= 0) {           
            return false;
        }
        return true;
    }

}
