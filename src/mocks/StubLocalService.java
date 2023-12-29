package mocks;

import data.Nif;
import data.Password;
import exceptions.InvalidAccountException;
import services.LocalService;

import java.util.HashMap;

public class StubLocalService implements LocalService {
    private final HashMap<String, String> supportUsers = new HashMap<>() {
        {
            put("alice", "pass123");
            put("bob", "securePwd");
            put("charlie", "p@ssw0rd");
            put("david", "1q2w3e4r");
            put("emma", "password123");
            put("frank", "passWord!456");
            put("grace", "letmein789");
            put("harry", "h@rryPotter");
            put("isabel", "pa$$w0rd");
            put("jackson", "j@ckson123");
        }
    };

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
