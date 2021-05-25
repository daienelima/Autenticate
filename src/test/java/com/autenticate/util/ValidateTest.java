package com.autenticate.util;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class ValidateTest {

    @Test
    void isEmail_valid(){
        var email = "test@gmail.com";
        var email_invalid = "test@gmailcom";
        var email_vazio = "";
        Assert.assertFalse(Validate.isEmail(email_invalid));
        Assert.assertTrue(Validate.isEmail(email));
        Assert.assertFalse(Validate.isEmail(email_vazio));
    }

    @Test
    void encoderPassword_test(){
        var senha = "admin";
        Assert.assertNotEquals(senha, Validate.encoderPassword(senha));
    }

}