package com.lentatykalentarunewapp.presentation

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.lentatykalentarunewapp.R
import com.lentatykalentarunewapp.data.FakeNewsRepositoryImpl
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MainActivityWithHiltTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @ExperimentalCoroutinesApi
    @Test
    fun test() = runTest {

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.swipeLayout)).perform(ViewActions.swipeDown())
        onView(withId(R.id.recycler)).perform(
            RecyclerViewActions.scrollToPosition<NewsAdapter.ViewHolder>(0)
        )
        for (news in FakeNewsRepositoryImpl.articlesList) {
            onView(withText(news.title)).check(matches(isDisplayed()))
        }
        onView(withId(R.id.recycler)).perform(
            RecyclerViewActions.actionOnItemAtPosition<NewsAdapter.ViewHolder>(
                0, click()
            )
        )
        activityScenario.close()
    }
}