package data;

import data.Nif;
import exceptions.InvalidDNIDocumException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NifTest {
    private Nif nif;


    @Test
    void testValidNif() throws InvalidDNIDocumException {
        nif = new Nif("99572958R");
        assertEquals("99572958R", nif.getNif());
    }

    @Test
    void testInvalidLength() {
        assertThrows(IllegalArgumentException.class, () -> new Nif("12345678"));
    }

    @Test
    void testInvalidDigits() {
        assertThrows(IllegalArgumentException.class, () -> new Nif("1234567Z8"));
    }

    @Test
    void testInvalidLetter() {
        assertThrows(IllegalArgumentException.class, () -> new Nif("123456789"));
    }

    @Test
    void testInvalidNifLetter() {
        assertThrows(IllegalArgumentException.class, () -> new Nif("12345678A")); // Assuming 'A' is not a valid letter for these digits
    }
}
