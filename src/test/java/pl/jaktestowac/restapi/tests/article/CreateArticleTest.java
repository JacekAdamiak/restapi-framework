package pl.jaktestowac.restapi.tests.article;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pl.jaktestowac.restapi.main.pojo.article.Article;
import pl.jaktestowac.restapi.main.pojo.article.response.ArticleCreatedResponse;
import pl.jaktestowac.restapi.main.request.configuration.RequestConfigurationBuilder;
import pl.jaktestowac.restapi.main.rop.CreateArticleEndpoint;
import pl.jaktestowac.restapi.main.rop.DeleteArticleEndpoint;
import pl.jaktestowac.restapi.main.test.data.ArticleTestDataGenerator;
import pl.jaktestowac.restapi.tests.testbases.SuiteTestBase;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CreateArticleTest extends SuiteTestBase {

    ArticleCreatedResponse articleCreatedResponse;
    Article article;

    @TmsLink("ID-2")
    @Severity(SeverityLevel.CRITICAL)
    @Description("The goal of this test is to create new article")
    @Test
    public void givenArticleWhenPostArticleThenArticleIsCreatedTest() {

        ArticleTestDataGenerator articleTestDataGenerator = new ArticleTestDataGenerator();
        Article article = articleTestDataGenerator.generateArticle();
        article.setUser_id(1);

        articleCreatedResponse = new CreateArticleEndpoint()
                .setArticle(article)
                .sendRequest()
                .assertRequestSuccess()
                .getResponseModel();

//        articleCreatedResponse = given()
//                .spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
//                .body(article)
//                .when().post("articles")
//                .then().statusCode(HttpStatus.SC_CREATED).extract().as(ArticleCreatedResponse.class);

//        assertEquals(articleCreatedResponse.getTitle(), article.getTitle(), "Title");
//        assertEquals(articleCreatedResponse.getBody(), article.getBody(), "Body");
//        assertEquals(articleCreatedResponse.getDate(), article.getDate(), "Date");

        Assertions.assertThat(articleCreatedResponse)
                .describedAs("Created article is different than send in request")
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(article);

    }

    @AfterMethod
    public void CleanUpAfterTest() {

        new DeleteArticleEndpoint()
                .setArticleId(articleCreatedResponse.getId())
                .sendRequest()
                .assertRequestSuccess();

//        given().spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
//                .when().delete("articles/{articleID}", articleCreatedResponse.getId())
//                .then().statusCode(HttpStatus.SC_OK);

        new DeleteArticleEndpoint()
                .setArticleId(articleCreatedResponse.getId())
                .sendRequest()
                .assertStatusCode(HttpStatus.SC_NOT_FOUND);


//        given().spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
//                .when().get("articles/{articleID}", articleCreatedResponse.getId())
//                .then().statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
