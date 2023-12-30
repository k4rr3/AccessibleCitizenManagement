package data;

/**
 * Essential data classes
 */
public class Password {

    //Assuming password cannot be changed => final
    private final String password;

    public Password(String password) {
        if (isValidPassword(password)) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Invalid password. Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, and one digit.");
        }
    }

    private boolean isValidPassword(String password) {
        //Password requirements:
        //It must be at least 8 characters long.
        //It must contain at least one uppercase letter, one lowercase letter, and one digit.

        if (password.length() < 8) {
            return false;
        }

        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasLowercase = !password.equals(password.toUpperCase());
        boolean hasDigit = password.matches(".*\\d.*");

        return hasUppercase && hasLowercase && hasDigit;
    }


    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password1 = (Password) o;
        return password.equals(password1.password);
    }
}
