package hu.elte.projeszk.fxjpacrud.helper;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class FormValidation {

    public static String validateForm(String name, String email, String password1, String password2) {
        String errorMessage = "";
        if (name == null || name.length() == 0) {
            errorMessage += "Missing data: name!\n";
        }
        if (email == null || email.length() == 0) {
            errorMessage += "Missing data: email!\n";
        }
        if(!isValidEmailAddress(email)){
            errorMessage += "Invalid email!\n";
        }
        if (password1 == null || password1.length() == 0) {
            errorMessage += "Missing data: password!\n";
        }
        if (password2 == null || password2.length() == 0) {
            errorMessage += "Missing data: password again!\n";
        }
        if (password1 != null && password2 != null) {
            if (!password2.equals(password1)) {
                errorMessage += "Password doesn't match!\n";
            }
        }
        return errorMessage;
    }
    
    private static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
    
    public static String validateLoginForm(String email, String password) {
        String errorMessage = "";
        if (email == null || email.length() == 0) {
            errorMessage += "Missing data: email!\n";
        }
        if(!isValidEmailAddress(email)){
            errorMessage += "Invalid email!\n";
        }
        if (password == null || password.length() == 0) {
            errorMessage += "Missing data: password!\n";
        }
        return errorMessage;
    }

}
