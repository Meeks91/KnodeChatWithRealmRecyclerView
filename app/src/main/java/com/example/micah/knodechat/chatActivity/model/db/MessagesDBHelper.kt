package com.example.micah.knodechat.chatActivity.model.db

import com.example.micah.knodechat.chatActivity.model.jsonAndRealmModel.ChatMessage
import io.realm.Realm
import io.realm.Sort

/**
 * Created by Micah on 28/08/2017.
 */

class MessagesDBHelper{

    /**
     * returns all of the chat messages ordered by id in ascending order.
     */
    fun getAllChatMessages(): ArrayList<ChatMessage> {

        val realm = Realm.getDefaultInstance()

        return ArrayList<ChatMessage>(realm.where(ChatMessage::class.java).findAllSorted("id", Sort.ASCENDING))
    }

    /**
     * returns the last chatMessage we received. This is determined by
     * the chatMessage which has the biggest ID. If there is no chatMessage
     * to return the the method returns null
     */
    fun getLastReceivedMessage(): ChatMessage?{

        val realm = Realm.getDefaultInstance()

        val results = realm.where(ChatMessage::class.java).findAllSorted("id", Sort.ASCENDING)

        return if (results.size > 0) results.last() else null
    }

    /**
     * saves all of the messages in the specified [chatMessagesArrayList]
     * to the db.
     */
    fun saveAllChatMessagesIn(chatMessagesArrayList: ArrayList<ChatMessage>){

        val realm = Realm.getDefaultInstance()

        realm.beginTransaction()

        realm.copyToRealm(chatMessagesArrayList)

        realm.commitTransaction()

        realm.close()
    }

    /**
     * saves the specified [chatMessage] to the db.
     */
    fun saveChatMessage(chatMessage: ChatMessage){

        val realm = Realm.getDefaultInstance()

        realm.beginTransaction()

        realm.copyToRealm(chatMessage)

        realm.commitTransaction()

        realm.close()
    }
}
