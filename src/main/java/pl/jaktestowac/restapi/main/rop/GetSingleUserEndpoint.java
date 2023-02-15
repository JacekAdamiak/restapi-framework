package pl.jaktestowac.restapi.main.rop;

import org.apache.http.HttpStatus;
import pl.jaktestowac.restapi.main.pojo.user.User;
import pl.jaktestowac.restapi.main.pojo.user.response.UserCreatedResponse;
import pl.jaktestowac.restapi.main.request.configuration.RequestConfigurationBuilder;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class GetSingleUserEndpoint extends BaseEndpoint<GetSingleUserEndpoint, UserCreatedResponse>{

    Integer userId;

    @Override
    protected Type getModelType() {
        return UserCreatedResponse.class;
    }

    @Override
    public GetSingleUserEndpoint sendRequest() {
        response = given()
                .spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
                .when().get("users/{userId}",userId );
        return this;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }

    public GetSingleUserEndpoint setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }
}
