package com.example.micah.knodechat.chatActivity.model.api

import com.example.micah.knodechat.chatActivity.model.jsonAndRealmModel.ChatMessage
import com.example.micah.knodechat.chatActivity.model.jsonParsers.ChatMessagesJsonParser
import okhttp3.*
import java.io.IOException

/**
 * Created by Micah on 28/08/2017.
 *
 * Handles the messages retrieval interactions with the api
 */
class MessagesApiHelper(val okHttpClient: OkHttpClient){

    val BASE_API_URL = "http://192.168.1.5:9000/api/messages/"

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
                   val chatMessagesArrayList = ChatMessagesJsonParser.extractChatMessagesArrayListFrom(response.body()?.string())

                   println("response.body is: ${chatMessagesArrayList}")

                   completion(chatMessagesArrayList)
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
                val chatMessagesArrayList = ChatMessagesJsonParser.extractChatMessagesArrayListFrom(response.body()?.string())

                completion(chatMessagesArrayList)
            }
        })
    }
}