package data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VotingOptionTest {

    private VotingOption votingOption;

    @BeforeEach
    public void setUp() {
        // Inicializar objetos necesarios antes de cada prueba
        votingOption = new VotingOption("PartyA");
    }

    @Test
    public void testGetParty() {
        // Verificar que el método getParty devuelve la opción de voto correcta
        assertEquals("PartyA", votingOption.getParty());
    }

    @Test
    public void testEqualsSameObject() {
        // Verificar que un objeto es igual a sí mismo
        assertTrue(votingOption.equals(votingOption));
    }

    @Test
    public void testEqualsEqualObjects() {
        // Verificar que dos objetos son iguales si tienen la misma opción de voto
        VotingOption sameOption = new VotingOption("PartyA");
        assertTrue(votingOption.equals(sameOption));
    }

    @Test
    public void testEqualsDifferentObjects() {
        // Verificar que dos objetos no son iguales si tienen opciones de voto diferentes
        VotingOption differentOption = new VotingOption("PartyB");
        assertFalse(votingOption.equals(differentOption));
    }

    @Test
    public void testEqualsNull() {
        // Verificar que el objeto no es igual a null
        assertFalse(votingOption.equals(null));
    }

    @Test
    public void testHashCode() {
        // Verificar que el hashCode es consistente con el método equals
        VotingOption sameOption = new VotingOption("PartyA");
        assertEquals(votingOption.hashCode(), sameOption.hashCode());
    }

    @Test
    public void testToString() {
        // Verificar que el método toString devuelve la representación esperada
        assertEquals("Vote option {party='PartyA'}", votingOption.toString());
    }
}
