package android.andela.com.kenyajavadevs.model;

import android.andela.com.kenyajavadevs.testhelper.TestHelper;
import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class GithubUserParcelTest {

    @Test
    public void testGithubUserParcelable() {
        GithubUser user = new TestHelper().getUsers()[0];

        Parcel parcel = Parcel.obtain();
        System.out.println(parcel);

        user.writeToParcel(parcel, user.describeContents());
        parcel.setDataPosition(0);

        GithubUser createdFromParcel = GithubUser.CREATOR.createFromParcel(parcel);
        assertThat(createdFromParcel.getUsername(), is("user1"));
        assertThat(createdFromParcel.getAvatarUrl(), is("user1.png"));
    }
}
