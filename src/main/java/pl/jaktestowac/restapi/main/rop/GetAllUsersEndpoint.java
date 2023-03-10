package pl.jaktestowac.restapi.main.rop;

import org.apache.http.HttpStatus;
import pl.jaktestowac.restapi.main.pojo.user.response.UserCreatedResponse;
import pl.jaktestowac.restapi.main.request.configuration.RequestConfigurationBuilder;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class GetAllUsersEndpoint extends BaseEndpoint<GetAllUsersEndpoint, UserCreatedResponse[]> {
    @Override
    protected Type getModelType() {
        return UserCreatedResponse[].class;
    }

    @Override
    public GetAllUsersEndpoint sendRequest() {
        response = given()
                .spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
                .when().get("users");
        return this;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }
}
