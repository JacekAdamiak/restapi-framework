package pl.jaktestowac.restapi.tests.testbases;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeSuite;
import pl.jaktestowac.restapi.main.properties.EnvironmentConfig;

public class SuiteTestBase {

    @BeforeSuite
    public void setupConfiguration() {
        EnvironmentConfig environmentConfig = ConfigFactory.create(EnvironmentConfig.class);

        RestAssured.baseURI = environmentConfig.baseUri();
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new AllureRestAssured());
    }
}
