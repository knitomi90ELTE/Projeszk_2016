package test;

import org.junit.Test;

import hu.elte.projeszk.fxjpacrud.helper.FormValidation;
import static org.junit.Assert.assertEquals;

public class FormValidationTest {
    
    @Test
    public void TestFormValidation(){
        assertEquals("", FormValidation.validateForm("Tamás", "tamas@mail.com", "test", "test"));
        assertEquals("Missing data: name!\n", FormValidation.validateForm("", "tamas@mail.com", "test", "test"));
        assertEquals("Missing data: email!\nInvalid email!\n", FormValidation.validateForm("Tamás", "", "test", "test"));
        assertEquals("Missing data: password!\nPassword doesn't match!\n", FormValidation.validateForm("Tamás", "tamas@mail.com", "", "test"));
        assertEquals("Missing data: password again!\nPassword doesn't match!\n", FormValidation.validateForm("Tamás", "tamas@mail.com", "test", ""));
        assertEquals("Invalid email!\n", FormValidation.validateForm("Tamás", "invalidemail", "test", "test"));
        assertEquals("Password doesn't match!\n", FormValidation.validateForm("Tamás", "tamas@mail.com", "no", "match"));
    }
    
}
