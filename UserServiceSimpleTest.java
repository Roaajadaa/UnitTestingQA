package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import main.najah.code.UserService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.BeforeAll;

class UserServiceSimpleTest {
    
    private static UserService user; 

    @BeforeAll
    static void setUp() {
        user = new UserService(); 
    }

    @ParameterizedTest
    @ValueSource(strings = {"Roaa@gmail.com", "test@example.com", "yousef@hotmailcom"})
    void testValidEmails(String email) {
        assertTrue(user.isValidEmail(email));
    }
    @Test
    @DisplayName("Test Admin account")
    @Timeout(value = 10 , unit = TimeUnit.MILLISECONDS)
    void testauthenticate() 
    {
    	assertTrue(user.authenticate("admin", "1234"));
    }
    
}