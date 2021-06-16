/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

/**
 *
 * @author Le Phuoc Duy
 */
public class checkValue {

    public static boolean checkRegistrationID(String registrationID) {
        if (!registrationID.matches("^[a-zA-Z0-9_]{1,10}$")) {
            return false;
        }
        return true;
    }

    public static boolean checkfullName(String fullName) {
        if (!fullName.matches("^[a-z A-Z]{1,50}$") || fullName.isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean checkEmail(String email) {
        if (!email.matches("^[\\w]{1,}+@([\\w]{1,}\\.[\\w]{1,}+|[\\w]{1,}\\.[\\w]{1,}\\.[\\w]{1,})$")) {
            return false;
        }
        return true;
    }

    public static boolean checkPhone(String phone) {
        if (!phone.matches("^[0-9]{1,15}$")) {
            return false;
        }
        return true;
    }
        
    public static boolean checkNumber(String number) {
        if (!number.matches("\\d+") || Integer.parseInt(number) <= 0) {
            return false;
        }
        return true;
    }
  
}
