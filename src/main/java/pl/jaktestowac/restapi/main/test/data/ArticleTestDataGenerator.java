package pl.jaktestowac.restapi.main.test.data;

import pl.jaktestowac.restapi.main.pojo.article.Article;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ArticleTestDataGenerator extends TestDataGenerator {

    public Article generateArticle() {

        LocalDate fullDate = LocalDate.now();
        String articleDate = fullDate.getDayOfMonth() + "-" + fullDate.getMonthValue() + "-" + fullDate.getYear();

        Article article = new Article();
        article.setUser_id(1);
        article.setTitle(faker().chuckNorris().fact());
        article.setBody(faker().yoda().quote());
        article.setDate(articleDate);
        article.setImage("https://kitcat.com.sg/wp-content/uploads/2020/07/Kit-Cat.png");
        return article;
    }
}
