package mocks;

import data.Nif;
import data.Password;
import exceptions.InvalidAccountException;
import services.LocalService;

import java.util.HashMap;

public class StubLocalService implements LocalService {


    private final HashMap<String, Password> supportUsers = new HashMap<>();

    {
        try {
            supportUsers.put("alice", new Password("Alice1234"));
            supportUsers.put("bob", new Password("Bob59678"));
            supportUsers.put("charlie", new Password("Charlie!9"));
            supportUsers.put("david", new Password("DavidPwd1"));
            supportUsers.put("emma", new Password("EmmaPass22"));
            supportUsers.put("frank", new Password("FrankPwd33"));
            supportUsers.put("grace", new Password("Grace12345"));
            supportUsers.put("harry", new Password("HarryPwd678"));
            supportUsers.put("isabel", new Password("IsabelPass99"));
            supportUsers.put("jackson", new Password("JacksonPwd123"));
        } catch (IllegalArgumentException e) {
            // Handle the exception (e.g., log an error, rethrow, etc.)
            e.printStackTrace();
        }
    }


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
