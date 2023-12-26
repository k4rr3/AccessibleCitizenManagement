package mocks;

import data.Password;
import exceptions.InvalidAccountException;
import services.LocalService;

import java.util.HashMap;

public class StubLocalService implements LocalService {
    private final HashMap<String, String> supportUsers = new HashMap<>();

    @Override
    public void verifyAccount(String login, Password pssw) throws InvalidAccountException {
        if (supportUsers.containsKey(login)) {
            // Check if the provided password matches the stored password
            String storedPassword = supportUsers.get(login);
            if (pssw != null && pssw.getPassword().equals(storedPassword)) {
                System.out.println("Authentication successful. Welcome, " + login + "!");
            } else {
                throw new InvalidAccountException("Authentication failed. Incorrect password.");
            }
        } else {
            throw new InvalidAccountException("Authentication failed. User not found.");
        }
    }
}
