package pl.jaktestowac.restapi.main.rop;

import io.qameta.allure.Step;
import org.apache.http.HttpStatus;
import pl.jaktestowac.restapi.main.pojo.user.User;
import pl.jaktestowac.restapi.main.pojo.user.response.UserCreatedResponse;
import pl.jaktestowac.restapi.main.request.configuration.RequestConfigurationBuilder;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class CreateUserEndpoint extends BaseEndpoint<CreateUserEndpoint, UserCreatedResponse>{

    User user;

    @Override
    protected Type getModelType() {
        return UserCreatedResponse.class;
    }

    @Step("Create user")
    @Override
    public CreateUserEndpoint sendRequest() {
        response = given()
                .spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
                .body(user)
                .when().post("users");
        return this;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_CREATED;
    }

    public CreateUserEndpoint setUser(User user) {
        this.user = user;
        return this;
    }
}
