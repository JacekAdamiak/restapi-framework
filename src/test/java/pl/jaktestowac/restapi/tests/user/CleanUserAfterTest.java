package pl.jaktestowac.restapi.tests.user;

import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import pl.jaktestowac.restapi.main.pojo.EmptyResponse;
import pl.jaktestowac.restapi.main.rop.DeleteUserEndpoint;

public class CleanUserAfterTest {

    public void deleteUserAfterTest(Integer userId) {

        System.out.println("\u001B[33m" + "\nDELETING User Id: " + userId + "\n" + "\u001B[0m");

        EmptyResponse userDeletedResponse = new DeleteUserEndpoint()
                .setUserId(userId)
                .sendRequest()
                .assertRequestSuccess()
                .getResponseModel();

        Assertions.assertThat(userDeletedResponse).isNotNull();

        System.out.println("\u001B[33m" + "\nChecking if user exists: " + userId + "\n" + "\u001B[0m");

        new DeleteUserEndpoint()
                .setUserId(userId)
                .sendRequest()
                .assertStatusCode(HttpStatus.SC_NOT_FOUND);
    }
}
