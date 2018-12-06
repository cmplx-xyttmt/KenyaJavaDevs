package android.andela.com.kenyajavadevs.view;

import android.andela.com.kenyajavadevs.R;
import android.content.pm.ActivityInfo;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickOnUserItemOpensDetailActivity() {

        CountingIdlingResource mainActivityIdlingResource =
                mMainActivityTestRule.getActivity().getEspressoTestIdlingResourceForMainActivity();
        IdlingRegistry.getInstance().register(mainActivityIdlingResource);

        onView(withId(R.id.recyclerview))
                .perform(actionOnItemAtPosition(0, click()));

        onView(withId(R.id.profile_username)).check(matches(withText("k33ptoo")));
    }

    @Test
    public void testOrientationChanges() {
        mMainActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        threadSleep();

        mMainActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private static void threadSleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
