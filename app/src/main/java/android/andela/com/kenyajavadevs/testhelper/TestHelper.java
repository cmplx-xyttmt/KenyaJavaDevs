package android.andela.com.kenyajavadevs.testhelper;

import android.andela.com.kenyajavadevs.model.GithubUser;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;

public class TestHelper {

    private GithubUser[] users;

    public TestHelper() {
        String json = "[\n" +
                "  {\n" +
                "    \"login\": \"user1\",\n" +
                "    \"avatar_url\": \"user1.png\",\n" +
                "    \"url\": \"github.com/user1\",\n" +
                "    \"name\": \"user1\"\n" +
                "  }\n" +
                "]\n";

        GsonBuilder builder = new GsonBuilder();
        builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        Gson gson = builder.create();

        Reader reader = new BufferedReader(new StringReader(json));
        users = gson.fromJson(reader, GithubUser[].class);
    }

    public GithubUser[] getUsers() {
        return Arrays.copyOf(users, users.length);
    }
}
