package pl.jaktestowac.restapi.main.rop;

import io.qameta.allure.Step;
import org.apache.http.HttpStatus;
import pl.jaktestowac.restapi.main.pojo.article.Article;
import pl.jaktestowac.restapi.main.pojo.article.response.ArticleCreatedResponse;
import pl.jaktestowac.restapi.main.request.configuration.RequestConfigurationBuilder;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class CreateArticleEndpoint extends BaseEndpoint<CreateArticleEndpoint, ArticleCreatedResponse> {

    Article article;

    @Override
    protected Type getModelType() {
        return ArticleCreatedResponse.class;
    }

    @Step("Create Article")
    @Override
    public CreateArticleEndpoint sendRequest() {
        response = given()
                .spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
                .body(article)
                .when().post("articles");
        return this;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_CREATED;
    }

    public CreateArticleEndpoint setArticle(Article article) {
        this.article = article;
        return this;
    }
}
