package pl.jaktestowac.restapi.tests.user;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pl.jaktestowac.restapi.main.pojo.user.User;
import pl.jaktestowac.restapi.main.pojo.user.response.UserCreatedResponse;
import pl.jaktestowac.restapi.main.rop.CreateUserEndpoint;
import pl.jaktestowac.restapi.main.test.data.UserTestDataGenerator;
import pl.jaktestowac.restapi.tests.testbases.SuiteTestBase;


public class CreateUserTests extends SuiteTestBase {

    UserCreatedResponse userCreatedResponse;
    User user;

    @TmsLink("ID-11")
    @Severity(SeverityLevel.CRITICAL)
    @Description("The goal of this test is to create new user")
    @Test
    public void givenUserWhenPostUserThenUserIsCreatedTest() {

        UserTestDataGenerator userTestDataGenerator = new UserTestDataGenerator();
        user = userTestDataGenerator.generateUser();

        userCreatedResponse = new CreateUserEndpoint()
                .setUser(user)
                .sendRequest()
                .assertRequestSuccess()
                .getResponseModel();


        System.out.println("\u001B[33m" + "\nNew User Id: " + userCreatedResponse.getId() + "\n" + "\u001B[0m");

        Assertions.assertThat(userCreatedResponse)
                .describedAs("Created user is different than send in request")
                .usingRecursiveComparison().ignoringFields("id").isEqualTo(user);
    }

    @AfterMethod
    public void CleanUpAfterTest() {
        new CleanUserAfterTest().deleteUserAfterTest(userCreatedResponse.getId());
    }

}


