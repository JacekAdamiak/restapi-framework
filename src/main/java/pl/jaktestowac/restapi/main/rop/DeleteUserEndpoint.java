package pl.jaktestowac.restapi.main.rop;

import io.qameta.allure.Step;
import org.apache.http.HttpStatus;
import pl.jaktestowac.restapi.main.pojo.EmptyResponse;
import pl.jaktestowac.restapi.main.request.configuration.RequestConfigurationBuilder;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class DeleteUserEndpoint extends BaseEndpoint<DeleteUserEndpoint, EmptyResponse> {

    Integer userId;

    @Override
    protected Type getModelType() {
        return EmptyResponse.class;
    }

    @Step("Delete user")
    @Override
    public DeleteUserEndpoint sendRequest() {
        response = given()
                .spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
                .when().delete("users/{userId}", userId);
        return this;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }

    public DeleteUserEndpoint setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

}
