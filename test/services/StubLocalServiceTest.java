package services;

import data.Password;
import exceptions.InvalidAccountException;
import mocks.StubLocalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class StubLocalServiceTest {

    StubLocalService stubLocalService = new StubLocalService();





    @Test
    public void testValidAuthentication() {
        try {
            stubLocalService.verifyAccount("alice", new Password("Alice1234"));
            // If no exception is thrown, the test passes
            assertTrue(true);
        } catch (InvalidAccountException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testInvalidPassword() {
        assertThrows(IllegalArgumentException.class,()->stubLocalService.verifyAccount("bob", new Password("InvalidPassword")));

    }

    @Test
    public void testUserNotFound() {
        assertThrows(InvalidAccountException.class,()->stubLocalService.verifyAccount("bobol", new Password("Alice123d")));


    }

    @Test
    public void testNullPassword() {
        try {
            stubLocalService.verifyAccount("charlie", null);
            // If an InvalidAccountException is thrown, the test passes
            fail("Expected InvalidAccountException");
        } catch (InvalidAccountException e) {
            assertEquals("Authentication failed. Incorrect password.", e.getMessage());
        }
    }
}
