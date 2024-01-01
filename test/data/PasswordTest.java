package data;

import data.Password;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

    @Test
    void testValidPassword() {
        Password password = new Password("Password1");
        assertEquals("Password1", password.getPassword());
    }

    @Test
    void testInvalidLength() {
        assertThrows(IllegalArgumentException.class, () -> new Password("Pass1"));
    }

    @Test
    void testNoUppercaseLetter() {
        assertThrows(IllegalArgumentException.class, () -> new Password("password1"));
    }

    @Test
    void testNoLowercaseLetter() {
        assertThrows(IllegalArgumentException.class, () -> new Password("PASSWORD1"));
    }

    @Test
    void testNoDigit() {
        assertThrows(IllegalArgumentException.class, () -> new Password("Password"));
    }
}
