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

    public static boolean checkBookID(String registrationID) {
        if (!registrationID.matches("^[a-zA-Z0-9_]{1,10}$")) {
            return false;
        }
        return true;
    }

    public static boolean checkString(String value) {
        if (!value.matches("^[\\w ]{1,50}$") || value.isEmpty()) {
            return false;
        }
        return true;
    }
  
}
