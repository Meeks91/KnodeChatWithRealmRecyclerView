package com.example.micah.knodechat.chatActivity.model.jsonParsers

import com.example.micah.knodechat.chatActivity.model.jsonAndRealmModel.ChatMessage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

/**
* Created by Micah on 29/08/2017.
*/

object ChatMessagesJsonParser {

    private val chatMessagesArrayListType = object: TypeToken<ArrayList<ChatMessage>>() {}.getType()
    private val MESSAGES_JSON_KEY = "messages"

    /**
     * returns a parsed chatMessage made using the specified [jsonChatMessage]
     */
   fun extractChatMessageFrom(jsonChatMessage: JSONObject): ChatMessage {

       //get the chat message type for Gson
       val chatMessagesArrayListType = ChatMessage::class.java

       //parse and return jsonChatMessage
       return Gson().fromJson(jsonChatMessage.toString(), chatMessagesArrayListType)
   }

    /**
     * returns an ArrayList<ChatMessage> extracted from the [chatMessagesJsonObjectString].
     * The [chatMessagesJsonObjectString] is a string representation of a json object which
     * contains an array of json chat messages. Its json structure is: {messages: [...]}
     */
    fun extractChatMessagesArrayListFrom(chatMessagesJsonObjectString: String?): ArrayList<ChatMessage>{

        if (chatMessagesJsonObjectString == null) {

            return ArrayList()
        }

        //get the json array of json chat messages
        val jsonArrayOfMessages = JSONObject(chatMessagesJsonObjectString).getJSONArray(MESSAGES_JSON_KEY)

        //parse and return the jsonArrayOfMessages
        return Gson().fromJson(jsonArrayOfMessages.toString(), chatMessagesArrayListType)
    }
}