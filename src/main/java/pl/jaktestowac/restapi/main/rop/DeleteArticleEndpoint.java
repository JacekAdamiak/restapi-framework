package pl.jaktestowac.restapi.main.rop;

import io.qameta.allure.Step;
import org.apache.http.HttpStatus;
import pl.jaktestowac.restapi.main.pojo.EmptyResponse;
import pl.jaktestowac.restapi.main.request.configuration.RequestConfigurationBuilder;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class DeleteArticleEndpoint extends BaseEndpoint<DeleteArticleEndpoint, EmptyResponse>{

    Integer articleId;

    @Override
    protected Type getModelType() {
        return EmptyResponse.class;
    }

    @Step("Delete article")
    @Override
    public DeleteArticleEndpoint sendRequest() {
        response = given()
                .spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
                .when().delete("articles/{articleId}", articleId);
        return this;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }

    public DeleteArticleEndpoint setArticleId(Integer articleId) {
        this.articleId = articleId;
        return this;
    }
}
