package com.lentatykalentarunewapp.presentation

import android.view.View
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.lentatykalentarunewapp.R
import com.lentatykalentarunewapp.common.DataBindingIdlingResource
import com.lentatykalentarunewapp.common.EspressoIdlingResource
import com.lentatykalentarunewapp.common.monitorActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Without fake viewModel
 * ViewModel will return real data
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest{


    private val dataBindingIdlingResource = DataBindingIdlingResource()

    @Before
    fun setUp(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().register(dataBindingIdlingResource)
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testMainActivityUI()= runTest{
        //launch and wait updates
       val scenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(scenario)
        //manual updates
        onView(withId(R.id.swipeLayout)).perform(swipeDown())
        //wait updates and click first item
        onView(withId(R.id.recycler)).perform(RecyclerViewActions.actionOnItemAtPosition<NewsAdapter.ViewHolder>(
            0, click()
        ))
        scenario.close()
    }
}