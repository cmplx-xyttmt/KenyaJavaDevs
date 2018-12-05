package android.andela.com.kenyajavadevs.model;

import android.andela.com.kenyajavadevs.testhelper.TestHelper;
import org.junit.Test;

import static org.junit.Assert.*;

public class GithubUserTest {

    @Test
    public void testParseJsonGithubUsers() {

        GithubUser[] users = new TestHelper().getUsers();
        assertEquals(1, users.length);
        GithubUser user1 = users[0];
        assertEquals("user1", user1.getUsername());
        assertEquals("user1.png", user1.getAvatarUrl());
        assertEquals("github.com/user1", user1.getUrl());
        assertEquals("user1", user1.getName());
    }
}
