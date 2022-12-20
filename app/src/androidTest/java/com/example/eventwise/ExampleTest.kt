package com.example.eventwise

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.eventwise.screens.login.LoginActivity
import org.hamcrest.Matchers.anything
import org.hamcrest.Matchers.endsWith
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4::class)
class ExampleTest {

    private var username = "kavrazUser"
    private var password = "stringxxx"
    private var email = "$username@gmail.com"
    private var displayedName = "Kavraz"
    private var newPassword = "stringPassxxx"

    private var mockGroupName = "NewGroup"

    private var mockEventName = "NewEvent"
    private var desscription = "Description"
    private var location = "Location"
    private var mockEventNewLocation = "NewLocation"

    private val addMemberUsername = "balik18"

    @get:Rule
    var activity = ActivityScenarioRule(LoginActivity::class.java)

    fun login(){
        onView(withId(R.id.loginActivityEditTextPersonName)).perform(typeText(username))
        onView(withId(R.id.loginActivityEditTextPassword)).perform(typeText(password))
        onView(withText("Login")).perform(click())
        Thread.sleep(1000)
    }

    @Test
    fun test0_checkSignup(){
        onView(withText("Sign Up")).perform(click())
        Thread.sleep(1000)

        onView(withHint("Username")).perform(typeText(username))
        onView(withHint("Email")).perform(typeText(email))
        onView(withHint("Displayed Name")).perform(typeText(displayedName))
        onView(withHint("Location")).perform(typeText(location))
        onView(withHint("Password")).perform(typeText(password))
        onView(withHint("Password Confirmation")).perform(typeText(password))
            .perform(ViewActions.closeSoftKeyboard())

        onView(withText("Sign Up")).perform(click())
        Thread.sleep(1000)

        test1_checkLogin()
    }

    @Test
    fun test1_checkLogin(){
        login()
        onView(withText("Approved Events")).check(matches(isDisplayed()))
    }

    @Test
    fun test20_checkCreateGroup(){
        login()
        Thread.sleep(1000)

        // home screen
        onView(withId(R.id.fragmentHomeEventsLayout)).perform(swipeLeft())
        Thread.sleep(1000)
        onView(withId(R.id.fragmentHomeGroupsCreateGroupButton)).perform(click())

        Thread.sleep(1000)

        // create group
        onView(withId(R.id.createGroupActivityEditTextGroupName)).perform(typeText(mockGroupName))
        onView(withId(R.id.createGroupActivityEditTextGroupDescription)).perform(typeText(desscription))
        onView(withId(R.id.createGroupActivityEditTextGroupLocation)).perform(typeText(location))
        onView(withId(R.id.createGroupActivitySaveButton)).perform(click())

        Thread.sleep(1000)

        onView(withText(mockGroupName)).check(matches(isDisplayed()))
        onView(withText(location)).check(matches(isDisplayed()))
        onView(withText(mockGroupName)).perform(click())

        Thread.sleep(1000)

        onView(withText(mockGroupName))
            .perform(click())
    }

    @Test
    fun test21_checkUpdateGroup(){
        login()

        // home screen
        onView(withId(R.id.fragmentHomeEventsLayout)).perform(swipeLeft())
        Thread.sleep(1000)
        onView(withText(mockGroupName))
            .perform(click())

        Thread.sleep(1000)
        openActionBarOverflowOrOptionsMenu(
            ApplicationProvider.getApplicationContext())
        onView(withText("Update Group")).perform(click())

        Thread.sleep(1000)

        onView(withId(R.id.updateGroupActivityEditTextGroupLocation))
            .perform(clearText()).perform(typeText(mockEventNewLocation))
            .perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.updateGroupActivitySaveButton))
            .perform(click())

        Thread.sleep(1000)

        onView(withText(endsWith(mockEventNewLocation))).check(matches(isDisplayed()))
    }

    @Test
    fun test30_checkCreateEvent(){
        login()

        // home screen
        onView(withId(R.id.fragmentHomeEventsLayout)).perform(swipeLeft())
        Thread.sleep(1000)
        onView(withText(mockGroupName))
            .perform(click())

        Thread.sleep(1000)

        onView(withText("Create Event")).perform(click())
        onView(withText("Choose Date Time")).perform(click())
        onView(withText("OK")).perform(click())
        onView(withText("OK")).perform(click())

        onView(withHint("Event Name")).perform(typeText(mockEventName))
        onView(withHint("Event Location")).perform(typeText(location))
        onView(withId(R.id.createEventActivityEditTextEventType))
            .perform(click())
            .perform(click())

        onData(anything())
            .atPosition(0)
            .inRoot(RootMatchers.isPlatformPopup())
            .perform(click())

        onView(withHint("Event Description"))
            .perform(typeText(desscription))
            .perform(ViewActions.closeSoftKeyboard())

        onView(withText("Save")).perform(click())
    }

    @Test
    fun test31_checkUpdateEvent(){
        login()

        onView(withText(mockEventName))
            .perform(click())

        Thread.sleep(1000)
        onView(withText("Update Event")).perform(click())

        onView(withHint("Event Location"))
            .perform(clearText())
            .perform(typeText(mockEventNewLocation))
            .perform(ViewActions.closeSoftKeyboard())

        onView(withText("Save"))
            .perform(click())

        onView(withText(endsWith(mockEventNewLocation)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test32_checkRejectEvent(){
        login()

        onView(withText(mockEventName))
            .perform(click())

        Thread.sleep(1000)
        onView(withText("Reject")).perform(click())

        Thread.sleep(1000)

        onView(withText(username))
            .check(doesNotExist())
    }

    @Test
    fun test33_checkAcceptEvent(){
        login()

        // home screen
        onView(withId(R.id.fragmentHomeEventsLayout)).perform(swipeLeft())
        Thread.sleep(1000)
        onView(withText(mockGroupName))
            .perform(click())

        Thread.sleep(1000)
        onView(withText(mockEventName)).perform(click())

        Thread.sleep(1000)
        onView(withText("Accept")).perform(click())

        Thread.sleep(1000)

        onView(withText(username))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test40_checkAddPeopleToGroup(){
        login()

        // home screen
        onView(withId(R.id.fragmentHomeEventsLayout)).perform(swipeLeft())
        Thread.sleep(1000)
        onView(withText(mockGroupName))
            .perform(click())

        onView(withId(R.id.groupDetailActivityAddRemoveButton))
            .perform(click())

        Thread.sleep(1000)

        onView(withHint("Username"))
            .perform(typeText(addMemberUsername))

        onView(withText("Search People"))
            .perform(click())

        Thread.sleep(1000)

        onView(withId(R.id.activityUserSearchAddRemoveButton))
            .perform(click())

        Thread.sleep(1000)

        onView(withText(addMemberUsername))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test41_checkRemovePeopleToGroup(){
        login()

        // home screen
        onView(withId(R.id.fragmentHomeEventsLayout)).perform(swipeLeft())
        Thread.sleep(1000)
        onView(withText(mockGroupName))
            .perform(click())

        onView(withId(R.id.groupDetailActivityAddRemoveButton))
            .perform(click())

        Thread.sleep(1000)

        onView(withHint("Username"))
            .perform(typeText(addMemberUsername))

        onView(withText("Search People"))
            .perform(click())

        Thread.sleep(1000)

        onView(withId(R.id.activityUserSearchAddRemoveButton))
            .perform(click())

        Thread.sleep(1000)

        onView(withText(addMemberUsername))
            .check(doesNotExist())
    }

    @Test
    fun test50_checkDeleteEvent(){
        login()

        onView(withText(mockEventName))
            .perform(click())

        Thread.sleep(1000)
        onView(withText("Update Event")).perform(click())
        Thread.sleep(1000)
        onView(withText("Delete Event")).perform(click())
        onView(withText("Yes")).perform(click())
    }

    @Test
    fun test51_checkDeleteGroup(){
        login()

        // home screen
        onView(withId(R.id.fragmentHomeEventsLayout)).perform(swipeLeft())
        Thread.sleep(1000)
        onView(withText(mockGroupName))
            .perform(click())

        Thread.sleep(1000)

        openActionBarOverflowOrOptionsMenu(
            ApplicationProvider.getApplicationContext())
        onView(withText("Update Group")).perform(click())

        Thread.sleep(1000)

        onView(withId(R.id.updateGroupActivityDeleteGroupButton))
            .perform(click())

        onView(withText("Yes"))
            .perform(click())
    }

    @Test
    fun test52_createGroup(){
        test20_checkCreateGroup()
    }

    @Test
    fun test53_checkExitFromGroup(){
        login()

        // home screen
        onView(withId(R.id.fragmentHomeEventsLayout)).perform(swipeLeft())
        Thread.sleep(1000)
        onView(withText(mockGroupName))
            .perform(click())

        Thread.sleep(1000)
        openActionBarOverflowOrOptionsMenu(
            ApplicationProvider.getApplicationContext())
        onView(withText("Exit from Group")).perform(click())

        onView(withText("Yes")).perform(click())

        Thread.sleep(1000)

        onView(withText(mockGroupName))
            .check(doesNotExist())
    }

    @Test
    fun test60_checkUpdateProfile(){
        login()

        // home screen
        onView(withId(R.id.activityHomeViewPager)).perform(swipeLeft())
        Thread.sleep(1000)

        onView(withId(R.id.activityHomeViewPager)).perform(swipeLeft())
        Thread.sleep(1000)

        onView(withId(R.id.homeUserFragmentEditTextLocation))
            .perform(clearText())
            .perform(typeText(mockEventNewLocation))
            .perform(ViewActions.closeSoftKeyboard())

        onView(withText("Save"))
            .perform(click())

        Thread.sleep(1000)

        onView(withText("Log Out")).perform(click())
        Thread.sleep(1000)

        login()

        Thread.sleep(1000)

        onView(withId(R.id.activityHomeViewPager)).perform(swipeLeft())
        Thread.sleep(1000)

        onView(withId(R.id.activityHomeViewPager)).perform(swipeLeft())
        Thread.sleep(1000)

        onView(withText(mockEventNewLocation))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test61_checkChangePasswordAndLogOut(){

        login()
        Thread.sleep(1000)

        // home screen
        onView(withId(R.id.activityHomeViewPager)).perform(swipeLeft())
        Thread.sleep(1000)

        onView(withId(R.id.activityHomeViewPager)).perform(swipeLeft())
        Thread.sleep(1000)

        onView(withId(R.id.homeUserFragmentChangePasswordButton))
            .perform(click())

        Thread.sleep(1000)

        onView(withHint("Password"))
            .perform(typeText(password))

        onView(withHint("New Password"))
            .perform(typeText(newPassword))

        onView(withHint("New Password Confirmation"))
            .perform(typeText(newPassword))

        onView(withText("Save"))
            .perform(click())

        Thread.sleep(1000)

        onView(withText("Log Out"))
            .perform(click())

        Thread.sleep(1000)

        onView(withId(R.id.loginActivityEditTextPersonName))
            .perform(clearText())
            .perform(typeText(username))
        onView(withId(R.id.loginActivityEditTextPassword))
            .perform(clearText())
            .perform(typeText(password))
        onView(withText("Login")).perform(click())

        Thread.sleep(1000)
        onView(withText("Approved Events")).check(doesNotExist())

        onView(withId(R.id.loginActivityEditTextPersonName))
            .perform(clearText())
            .perform(typeText(username))
        onView(withId(R.id.loginActivityEditTextPassword))
            .perform(clearText())
            .perform(typeText(newPassword))
        onView(withText("Login")).perform(click())
        Thread.sleep(1000)

        onView(withText("Approved Events")).check(matches(isDisplayed()))
    }

    @Test
    fun test70_checkDeleteAccount(){
        onView(withId(R.id.loginActivityEditTextPersonName))
            .perform(typeText(username))
        onView(withId(R.id.loginActivityEditTextPassword))
            .perform(typeText(newPassword))
        onView(withText("Login")).perform(click())
        Thread.sleep(1000)

        // home screen
        onView(withId(R.id.activityHomeViewPager)).perform(swipeLeft())
        Thread.sleep(1000)

        onView(withId(R.id.activityHomeViewPager)).perform(swipeLeft())
        Thread.sleep(1000)

        onView(withId(R.id.homeUserFragmentDeleteAccountButton))
            .perform(click())

        onView(withText("Yes"))
            .perform(click())

        Thread.sleep(1000)

        onView(withText("Login")).check(matches(isDisplayed()))

        login()
        Thread.sleep(1000)

        onView(withText("Approved Events")).check(doesNotExist())
    }
}
