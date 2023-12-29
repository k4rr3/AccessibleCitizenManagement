/*
package evoting;

import exceptions.ProceduralException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VotingKioskTest {

    private VotingKiosk votingKiosk;
    private HashMap<String, String> supportUsers;

    @BeforeEach
    public void setUp() {
        // Configuración común para todas las pruebas
        supportUsers = new HashMap<>();
        votingKiosk = new VotingKiosk(supportUsers);
    }

    @Test
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

        assertThrows(ProceduralException.class, () -> votingKiosk.setDocument('x'));
    }

    @Test
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


}
*/
