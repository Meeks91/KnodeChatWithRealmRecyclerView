package com.example.micah.knodechat.ChatActivity

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.micah.knodechat.R
import com.example.micah.knodechat.chatActivity.view.ChatActivity
import io.realm.Realm
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Integration tests for ChatActivity
 */
@RunWith(AndroidJUnit4::class)
class ChatActivityTest {

    @get: Rule val mChatActivity = ActivityTestRule<ChatActivity>(ChatActivity::class.java)
    private val messageET = onView(withId(R.id.messageET))
    private val sendMessageButton = onView(withId(R.id.sendMessageButton))

    /**
     * Makes sure text is present after input and cleared after pressing send
     */
    @Test
    fun testSendingMessage() {

        //setup realm and the presenter
        Realm.init(mChatActivity.activity)

     //->make sure the app doesn't crash when pressing send with no text input
        sendMessageButton.perform(ViewActions.click())

        messageET.perform(typeText("This is some test text."))

     //->Make sure text is present after input
        messageET.check(matches(withText("This is some test text.")))

        sendMessageButton.perform(click())

     //->make sure text is gone after sending message
        messageET.check(matches(withText("")))
    }
}
