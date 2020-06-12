package com.firestarters.factory;

import com.firestarters.models.User;

import static com.firestarters.tools.utils.Utils.getRandomString;

public class UserFactory {
    public static User getUserForRegister(){
        User user=new User();
        user.setFirstName(getRandomString(6));
        user.setLastName(getRandomString(6));
        user.setEmail(getRandomString(6)+"@yahoo.com");
        String pass=getRandomString(10);
        user.setPass(pass);
        user.setConfirmPass(pass);
        return user;
    }
}
