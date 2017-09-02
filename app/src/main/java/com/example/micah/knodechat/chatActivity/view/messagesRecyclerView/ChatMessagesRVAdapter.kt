package com.example.micah.knodechat.chatActivity.view.messagesRecyclerView

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.micah.knodechat.ChatMessagesRVViewHolder
import com.example.micah.knodechat.R
import com.example.micah.knodechat.chatActivity.model.jsonAndRealmModel.ChatMessage
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter

/**
* Created by Micah on 28/08/2017.
*/

class ChatMessagesRVAdapter(val chatMessages: OrderedRealmCollection<ChatMessage>): RealmRecyclerViewAdapter<ChatMessage, ChatMessagesRVViewHolder>(chatMessages, true, true) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ChatMessagesRVViewHolder {

        val itemView = LayoutInflater.from(parent!!.context).inflate(R.layout.message_item, parent, false)

        return ChatMessagesRVViewHolder(itemView)
    }

    override fun getItemCount(): Int = chatMessages.size

    override fun onBindViewHolder(holder: ChatMessagesRVViewHolder?, position: Int) {

        holder!!.messageTV.text = getItem(position)!!.message
    }
}



