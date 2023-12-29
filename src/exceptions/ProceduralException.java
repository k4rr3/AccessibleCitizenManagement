package exceptions;

public class ProceduralException extends Exception {
    public ProceduralException(String message) {
        super(message);
        System.out.println("Exception! " + message + "\n");
    }
}
