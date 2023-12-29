package mocks;

import data.Nif;
import data.Password;
import exceptions.InvalidAccountException;
import services.LocalService;

import java.util.HashMap;

public class StubLocalService implements LocalService {
    private final HashMap<String, Password> supportUsers = new HashMap<>() {
        {
            put("alice", new Password("Alice1234"));
            put("bob", new Password("Bob5678"));
            put("charlie", new Password("Charlie!9"));
            put("david", new Password("DavidPwd1"));
            put("emma", new Password("EmmaPass22"));
            put("frank", new Password("FrankPwd33"));
            put("grace", new Password("Grace12345"));
            put("harry", new Password("HarryPwd678"));
            put("isabel", new Password("IsabelPass99"));
            put("jackson", new Password("JacksonPwd123"));
        }
    };

    @Override
    public void verifyAccount(String login, Password pssw) throws InvalidAccountException {
        if (supportUsers.containsKey(login)) {
            // Check if the provided password matches the stored password
            Password storedPassword = supportUsers.get(login);
            if (pssw != null && pssw.equals(storedPassword)) {
                System.out.println("Authentication successful. Welcome, " + login + "!");
            } else {
                throw new InvalidAccountException("Authentication failed. Incorrect password.");
            }
        } else {
            throw new InvalidAccountException("Authentication failed. User not found.");
        }
    }

}
