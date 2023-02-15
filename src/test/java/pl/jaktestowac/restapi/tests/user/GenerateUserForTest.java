package pl.jaktestowac.restapi.tests.user;

import org.assertj.core.api.Assertions;
import pl.jaktestowac.restapi.main.pojo.user.User;
import pl.jaktestowac.restapi.main.pojo.user.response.UserCreatedResponse;
import pl.jaktestowac.restapi.main.rop.CreateUserEndpoint;
import pl.jaktestowac.restapi.main.test.data.UserTestDataGenerator;

public class GenerateUserForTest {

    public UserCreatedResponse createUserForTest() {

        UserTestDataGenerator userTestDataGenerator = new UserTestDataGenerator();
        User user = userTestDataGenerator.generateUser();

        UserCreatedResponse userCreatedResponse = new CreateUserEndpoint()
                .setUser(user)
                .sendRequest()
                .assertRequestSuccess()
                .getResponseModel();

        System.out.println("\u001B[33m" + "\nNew User Id: " + userCreatedResponse.getId() + "\n" + "\u001B[0m");

        Assertions.assertThat(userCreatedResponse)
                .describedAs("Created user is different than send in request")
                .usingRecursiveComparison().ignoringFields("id").isEqualTo(user);

        return userCreatedResponse;
    }
}
