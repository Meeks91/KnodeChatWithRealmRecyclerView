package com.example.micah.knodechat.chatActivity.model.api

import com.example.micah.knodechat.chatActivity.model.jsonAndRealmModel.ChatMessage
import com.example.micah.knodechat.chatActivity.model.jsonAndRealmModel.JsonChatMessagesParser
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

/**
 * Created by Micah on 28/08/2017.
 */

class MessagesApiHelper(val okHttpClient: OkHttpClient){

    val BASE_API_URL = "http://192.168.1.5:9000/api/messages/"
    val MESSAGES_JSON_KEY = "messages"

    /**
     * gets all of the messages from the api and provides them in the [completion] hanlder
     */
    fun getAllMessages(completion: (ArrayList<ChatMessage>?) -> Unit){

       val getAllMessagesRequest = Request.Builder().url("$BASE_API_URL/getAllMessages").build()

       okHttpClient.newCall(getAllMessagesRequest).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {

                e.printStackTrace()

                //send null to represent error
                completion(null)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {

                   //extract the chatMessages from the json chatMessages
                   val chatMessagesArrayList = extractChatMessagesArrayListFrom(response.body()?.string())

                   println("response.body is: ${chatMessagesArrayList}")

                   return completion(chatMessagesArrayList)
            }
        })
    }

    /**
     * gets all of the messages from the api and provides them in the [completion] hanlder
     */
    fun getAllMessagesSince(date: String, completion: (ArrayList<ChatMessage>?) -> Unit){

        val getAllMessagesRequest = Request.Builder().url("$BASE_API_URL/getAllMessagesFrom?date=$date").build()

        okHttpClient.newCall(getAllMessagesRequest).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {

                e.printStackTrace()

                //send null to represent error
                completion(null)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {

                //extract the chatMessages from the json chatMessages
                val chatMessagesArrayList = extractChatMessagesArrayListFrom(response.body()?.string())

                return completion(chatMessagesArrayList)
            }
        })
    }

    /**
     * returns an ArrayList<ChatMessage> extracted from the [chatMessagesJsonObjectString].
     * The [chatMessagesJsonObjectString] is a string represnetation of a json object which
     * contains an array of json chat messages. Its json structure is: {messages: [...]}
     */
    private fun extractChatMessagesArrayListFrom(chatMessagesJsonObjectString: String?): ArrayList<ChatMessage>{

            if (chatMessagesJsonObjectString == null) {

                return ArrayList()
            }

            //get the json array of json chat messages
            val jsonArrayOfMessages = JSONObject(chatMessagesJsonObjectString).getJSONArray(MESSAGES_JSON_KEY)

            //parse and return the jsonArrayOfMessages
            return JsonChatMessagesParser.extractChatMessagesFrom(jsonArrayOfMessages)
    }
}