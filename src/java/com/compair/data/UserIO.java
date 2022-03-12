package com.compair.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import com.compair.business.User;

public class UserIO {

    public static User getUser(String filePath, String username, String password) {
        File file = new File(filePath);
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            while (line != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(line, ";");
                String usernameToken = stringTokenizer.nextToken();
                String passwordToken = stringTokenizer.nextToken();
                if (usernameToken.equals(username) && passwordToken.equals(password)) {
                    String firstName = stringTokenizer.nextToken();
                    String lastName = stringTokenizer.nextToken();
                    String email = stringTokenizer.nextToken();

                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setEmail(email);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);

                    bufferedReader.close();
                    return user;
                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            return null;
        } catch (IOException e) {
            System.err.println(e);
            return null;
        }
    }

    
}
