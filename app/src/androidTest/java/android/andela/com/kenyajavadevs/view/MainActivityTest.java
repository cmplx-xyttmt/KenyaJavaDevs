package android.andela.com.kenyajavadevs.view;

import android.andela.com.kenyajavadevs.R;
import android.content.pm.ActivityInfo;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.linkedin.android.testbutler.TestButler;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        toggleConnectivity(false);
        threadSleep();

        CountingIdlingResource mainActivityIdlingResource =
                mMainActivityTestRule.getActivity().getEspressoTestIdlingResourceForMainActivity();
        IdlingRegistry.getInstance().register(mainActivityIdlingResource);
    }

    @Test
    public void clickOnUserItemOpensDetailActivity() {
        toggleConnectivity(true);
        onView(withId(R.id.swipe_refresh)).perform(swipeDown());
        threadSleep();

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

    @Test
    public void showsSnackbarWhenOffline() {
        toggleConnectivity(false);

        onView(withId(R.id.swipe_refresh)).perform(swipeDown());
        threadSleep();

        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(R.string.connectivity_status))).check(matches(isDisplayed()));
    }

    private void toggleConnectivity(boolean enable) {
        TestButler.setGsmState(enable);
        TestButler.setWifiState(enable);
    }

    private static void threadSleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void teardown() {
        toggleConnectivity(true);
    }
}
