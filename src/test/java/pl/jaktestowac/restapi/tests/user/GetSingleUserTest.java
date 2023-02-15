package pl.jaktestowac.restapi.tests.user;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jaktestowac.restapi.main.pojo.user.User;
import pl.jaktestowac.restapi.main.pojo.user.response.UserCreatedResponse;
import pl.jaktestowac.restapi.main.rop.GetSingleUserEndpoint;
import pl.jaktestowac.restapi.tests.testbases.SuiteTestBase;

public class GetSingleUserTest extends SuiteTestBase {

    UserCreatedResponse userCreatedResponse;
    User user;


    @BeforeMethod
    public void beforeTest() {
        userCreatedResponse = new GenerateUserForTest().createUserForTest();
    }

    @TmsLink("ID-3")
    @Severity(SeverityLevel.CRITICAL)
    @Description("The goal of this test is to get created user")
    @Test
    public void givenUserWhenGetUserIdThenUserIsGetTest() {

        UserCreatedResponse actualUser = new GetSingleUserEndpoint()
                .setUserId(userCreatedResponse.getId())
                .sendRequest()
                .assertRequestSuccess()
                .getResponseModel();

        Assertions.assertThat(actualUser)
                .describedAs("Get user is different than expected when send created user id")
                        .usingRecursiveComparison().isEqualTo(userCreatedResponse);

    }

    @AfterMethod
    public void CleanUpAfterTest() {
        new CleanUserAfterTest().deleteUserAfterTest(userCreatedResponse.getId());
    }




}
