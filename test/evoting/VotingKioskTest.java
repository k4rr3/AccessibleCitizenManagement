package evoting;

import data.Password;
import exceptions.InvalidAccountException;
import exceptions.ProceduralException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VotingKioskTest {

    private VotingKiosk votingKiosk;

    @BeforeEach
    void setUp() {
        // Common setup before each test
        HashMap<String, String> supportUsers = new HashMap<>();
        supportUsers.put("user1", "Password1");
        supportUsers.put("user2", "Password2");
        // Add more support users as needed

        votingKiosk = new VotingKiosk(supportUsers);
    }

    @Test
    void testGrantExplicitConsent() throws ProceduralException {
        // Test explicit consent granting
        votingKiosk.grantExplicitConsent('y');
        assertEquals('y', votingKiosk.getExplicitConsentGiven());

        // Test invalid explicit consent
        assertThrows(ProceduralException.class, () -> votingKiosk.grantExplicitConsent('a'));
    }

    @Test
    void testSetDocument() throws ProceduralException {
        // Test setting document option
        votingKiosk.setDocument('n');
        assertEquals('n', votingKiosk.getOpt());

        // Test invalid document option
        assertThrows(ProceduralException.class, () -> votingKiosk.setDocument('x'));
    }

    @Test
    void testEnterAccount() throws InvalidAccountException {
        // Test entering valid account
        votingKiosk.enterAccount("user1", new Password("Password1"));

        // Test entering invalid account
        assertThrows(InvalidAccountException.class,
                () -> votingKiosk.enterAccount("nonexistentUser", new Password("invalidPassword1")));

        // Test entering account with incorrect password
        assertThrows(InvalidAccountException.class,
                () -> votingKiosk.enterAccount("user2", new Password("incorrectPassword1")));
    }

}
