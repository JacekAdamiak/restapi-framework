package pl.jaktestowac.restapi.tests.user;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pl.jaktestowac.restapi.main.pojo.EmptyResponse;
import pl.jaktestowac.restapi.main.pojo.user.User;
import pl.jaktestowac.restapi.main.pojo.user.response.UserCreatedResponse;
import pl.jaktestowac.restapi.main.request.configuration.RequestConfigurationBuilder;
import pl.jaktestowac.restapi.main.rop.CreateUserEndpoint;
import pl.jaktestowac.restapi.main.rop.DeleteUserEndpoint;
import pl.jaktestowac.restapi.main.test.data.UserTestDataGenerator;
import pl.jaktestowac.restapi.tests.testbases.SuiteTestBase;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CreateUserTests extends SuiteTestBase {

    UserCreatedResponse userCreatedResponse;
    User user;

    @TmsLink("ID-1")
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


//        userCreatedResponse = given()
//                .spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
//                .body(user)
//                .when().post("users")
//                .then().statusCode(HttpStatus.SC_CREATED).extract().as(UserCreatedResponse.class);

//        assertEquals(userCreatedResponse.getEmail(), user.getEmail(), "email");
//        assertEquals(userCreatedResponse.getFirstname(), user.getFirstname(), "First name");
//        assertEquals(userCreatedResponse.getLastname(), user.getLastname(), "Last name");

        System.out.println("\u001B[33m" + "\nNew User Id: " + userCreatedResponse.getId() + "\n" + "\u001B[0m");

        Assertions.assertThat(userCreatedResponse)
                .describedAs("Created user is different than send in request")
                .usingRecursiveComparison().ignoringFields("id").isEqualTo(user);
    }

    @AfterMethod
    public void CleanUpAfterTest() {

        System.out.println("\u001B[33m" + "\nDELETING User Id: " + userCreatedResponse.getId() + "\n" + "\u001B[0m");

        EmptyResponse userDeletedResponse = new DeleteUserEndpoint()
                .setUserId(userCreatedResponse.getId())
                .sendRequest()
                .assertRequestSuccess()
                .getResponseModel();

        Assertions.assertThat(userDeletedResponse).isNotNull();

//        given().spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
//                .when().delete("users/{userID}", userCreatedResponse.getId())
//                .then().statusCode(HttpStatus.SC_OK);

        System.out.println("\u001B[33m" + "\nChecking if user exists: " + userCreatedResponse.getId() + "\n" + "\u001B[0m");

//        new DeleteUserEndpoint()
//                .setUserId(userCreatedResponse.getId())
//                        .sendRequest()
//                .assertRequestSuccess();

        new DeleteUserEndpoint()
                .setUserId(userCreatedResponse.getId())
                .sendRequest()
                .assertStatusCode(HttpStatus.SC_NOT_FOUND);


//        given().spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
//                .when().get("users/{userID}", userCreatedResponse.getId())
//                .then().statusCode(HttpStatus.SC_NOT_FOUND);
    }

}


