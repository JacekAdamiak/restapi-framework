package pl.jaktestowac.restapi.main.test.data;

import pl.jaktestowac.restapi.main.pojo.user.User;

import java.util.Random;

public class UserTestDataGenerator extends TestDataGenerator{

    public User generateUser() {

        String userFirstName = faker().name().firstName();
        String userLastName = faker().name().lastName();
        int number = new Random().nextInt(100);
        String userEmail = userFirstName+"."+userLastName+"@"+"pl.zyzio-"+number+".org";

        User user = new User();
        user.setEmail(userEmail);
        user.setFirstname(userFirstName);
        user.setLastname(userLastName);
        user.setPassword("passwd");
        user.setAvatar("https://kitcat.com.sg/wp-content/uploads/2020/07/Kit-Cat.png");
        return user;
    }
}
