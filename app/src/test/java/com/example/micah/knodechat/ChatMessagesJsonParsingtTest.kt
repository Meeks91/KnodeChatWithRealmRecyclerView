package com.example.micah.knodechat

import com.example.micah.knodechat.chatActivity.model.jsonParsers.ChatMessagesJsonParser
import junit.framework.Assert
import org.json.JSONObject
import org.junit.Test

/**
 * Units tests for the ChatMessagesJsonParser
 */
class ChatMessagesJsonParsingtTest {

   private val testMessageOne = "Test text"
   private val testMessageTwo = "This is more test text"
   private val testDateOne = "2017-08-27T19:23:34.000Z"
   private val testDateTwo = "2017-09-28T19:21:34.000Z"
   private val testIDOne = 34
   private val testIDTwo = 35

    /**
     * Tests parsing a single json message
     */
    @Test
    @Throws(Exception::class)
    fun parseSingleJsonChatMessageText() {

        val jsonChatMessageObject = JSONObject("{\"createdAt\": \"$testDateOne\", \"id\": \"$testIDOne\", \"message\": \"$testMessageOne\"}")

        val chatMessage = ChatMessagesJsonParser.extractChatMessageFrom(jsonChatMessageObject)

        Assert.assertTrue("Failed to parse json createdAt date", chatMessage.createdAt == testDateOne)

        Assert.assertTrue("Failed to parse id of message", chatMessage.id == testIDOne)

        Assert.assertTrue("Failed to parse message test", chatMessage.message == testMessageOne)
    }

    /**
     * Tests parsing multiple json messages
     */
    @Test
    @Throws(Exception::class)
    fun parseJsonChatMessages(){

        val jsonChatMessagesObjectString =  "{\"messages\":" +
                                                                "[" +
                                                                  "{\"createdAt\": \"$testDateOne\", \"id\": \"$testIDOne\", \"message\": \"$testMessageOne\"}," +
                                                                  "{\"createdAt\": \"$testDateTwo\", \"id\": \"$testIDTwo\", \"message\": \"$testMessageTwo\"}"  +
                                                                "]" +
                                            "}"

        val chatMessagesArrayList = ChatMessagesJsonParser.extractChatMessagesArrayListFrom(jsonChatMessagesObjectString)

        //make sure we extracted the right amount of chat messages
        Assert.assertTrue("Failed to parse the correct number of chat messages",chatMessagesArrayList.size == 2)

        //get the two chat messages
        val chatMessageOne = chatMessagesArrayList.first()
        val chatMessageTwo = chatMessagesArrayList.get(1)

        //test chatMessageOne:
        Assert.assertTrue("chatMessageOne: Failed to parse json createdAt date", chatMessageOne.createdAt == testDateOne)
        Assert.assertTrue("chatMessageOne: Failed to parse id of message", chatMessageOne.id == testIDOne)
        Assert.assertTrue("chatMessageOne: Failed to parse message test", chatMessageOne.message == testMessageOne)

        //test chatMessageTwo:
        Assert.assertTrue("chatMessageTwo: Failed to parse json createdAt date", chatMessageTwo.createdAt == testDateTwo)
        Assert.assertTrue("chatMessageTwo: Failed to parse id of message", chatMessageTwo.id == testIDTwo)
        Assert.assertTrue("chatMessageTwo: Failed to parse message test", chatMessageTwo.message == testMessageTwo)
    }
}