package pl.jaktestowac.restapi.tests.user;

import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jaktestowac.restapi.main.pojo.user.response.UserCreatedResponse;
import pl.jaktestowac.restapi.main.rop.GetAllUsersEndpoint;
import pl.jaktestowac.restapi.tests.testbases.SuiteTestBase;

import java.util.ArrayList;
import java.util.List;


public class GetAllUsersTest extends SuiteTestBase {

    List<UserCreatedResponse> listOfUsers = new ArrayList<>();


    @BeforeMethod
    public void beforeTest() {

        for (int i = 0; i < 10; i++) {
            listOfUsers.add(new GenerateUserForTest().createUserForTest());
        }
    }


    @Test
    public void usersWhenGetAllUsersAreGetTest() {
        UserCreatedResponse[] actualUsersList = new GetAllUsersEndpoint()
                .sendRequest()
                .assertRequestSuccess()
                .getResponseModel();

        Assertions.assertThat(actualUsersList).containsAll(listOfUsers);
    }

    @AfterMethod
    public void CleanUpAfterTest() {

        listOfUsers.stream()
                .map(userCreatedResponse -> userCreatedResponse.getId())
                .forEach(userId -> {
                    new CleanUserAfterTest().deleteUserAfterTest(userId);
                });
    }


}
