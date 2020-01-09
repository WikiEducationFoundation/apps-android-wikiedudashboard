package org.wikiedufoundation.wikiedudashboard;

import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.shadows.ShadowLooper;
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs;
import org.wikiedufoundation.wikiedudashboard.ui.home.HomeActivity;
import org.wikiedufoundation.wikiedudashboard.ui.splash.SplashActivity;
import org.wikiedufoundation.wikiedudashboard.ui.welcome.onboarding.WelcomeHostActivity;

import static org.junit.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;
@RunWith(RobolectricTestRunner.class)
public class SplashScreenTest {
   private Intent expectedIntent;
    @Test
    public void splashScreenUITest() throws Exception
    {
        ActivityController<SplashActivity> controller = Robolectric.buildActivity(SplashActivity.class).create().start();
        ShadowLooper.runUiThreadTasksIncludingDelayedTasks();
        SplashActivity splashScreenActivity = controller.get();
        SharedPrefs sharedPrefs = new SharedPrefs(ApplicationProvider.getApplicationContext());
        if(sharedPrefs.isLoggedIn()){
            expectedIntent= new Intent(splashScreenActivity, HomeActivity.class);
        }
        else{
            expectedIntent = new Intent(splashScreenActivity, WelcomeHostActivity.class);
        }
        assertEquals(shadowOf(splashScreenActivity).getNextStartedActivity().getComponent(),expectedIntent.getComponent());
    }

}
