package com.example.micah.knodechat.chatActivity.model.jsonAndRealmModel

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject

/**
* Created by Micah on 29/08/2017.
*/

object JsonChatMessagesParser {

    /**
     * returns a parsed chatMessage made using the specified [jsonChatMessage]
     */
   fun extractChatMessageFrom(jsonChatMessage: JSONObject): ChatMessage{

       //get the chat message type for Gson
       val chatMessagesArrayListType = ChatMessage::class.java

       //parse and return jsonChatMessage
       return Gson().fromJson(jsonChatMessage.toString(), chatMessagesArrayListType)
   }

    /**
     * returns a parsed ArrayList<ChatMessage> made using the specified [jsonArrayOfChatMessages]
     */
    fun extractChatMessagesFrom(jsonArrayOfChatMessages: JSONArray): ArrayList<ChatMessage>{

        //get the ArrayList<ChatMessage> type for Gson
        val chatMessagesArrayListType = object: TypeToken<ArrayList<ChatMessage>>() {}.getType()

        //parse and return the jsonArrayOfChatMessages
        return Gson().fromJson(jsonArrayOfChatMessages.toString(), chatMessagesArrayListType)
    }
}