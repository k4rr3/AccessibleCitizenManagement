/*
package evoting;

<<<<<<< Updated upstream
=======
import data.Password;
import exceptions.InvalidAccountException;
>>>>>>> Stashed changes
import exceptions.ProceduralException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VotingKioskTest {

    private VotingKiosk votingKiosk;
<<<<<<< Updated upstream
    private HashMap<String, String> supportUsers;

    @BeforeEach
    public void setUp() {
        // Configuración común para todas las pruebas
        supportUsers = new HashMap<>();
=======

    @BeforeEach
    void setUp() {
        // Common setup before each test
        HashMap<String, String> supportUsers = new HashMap<>();
        supportUsers.put("user1", "Password1");
        supportUsers.put("user2", "Password2");
        // Add more support users as needed

>>>>>>> Stashed changes
        votingKiosk = new VotingKiosk(supportUsers);
    }

    @Test
<<<<<<< Updated upstream
    public void testSetDocumentValidOption() throws ProceduralException {
        // Prueba para asegurarse de que setDocument funciona correctamente con una opción válida
        votingKiosk.initVoting();  // Iniciar el proceso de votación
        votingKiosk.setDocument('n');
        assertEquals('n', votingKiosk.getOpt());
    }

    @Test
    public void testSetDocumentInvalidOption() throws ProceduralException {
        // Prueba para asegurarse de que setDocument maneja una opción no válida correctamente
        votingKiosk.initVoting();  // Iniciar el proceso de votación

=======
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
>>>>>>> Stashed changes
        assertThrows(ProceduralException.class, () -> votingKiosk.setDocument('x'));
    }

    @Test
<<<<<<< Updated upstream
    public void testGrantExplicitConsentValidOption() throws ProceduralException {
        // Prueba para asegurarse de que grantExplicitConsent funciona correctamente con una opción válida
        votingKiosk.initVoting();  // Iniciar el proceso de votación
        votingKiosk.grantExplicitConsent('y');
        assertEquals('y', votingKiosk.getExplicitConsentGiven());
    }

    @Test
    public void testGrantExplicitConsentInvalidOption() throws ProceduralException {
        // Prueba para asegurarse de que grantExplicitConsent maneja una opción no válida correctamente
        votingKiosk.initVoting();  // Iniciar el proceso de votación

        assertThrows(ProceduralException.class, () -> votingKiosk.grantExplicitConsent('z'));
    }


=======
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

>>>>>>> Stashed changes
}
*/
