package com.example.micah.knodechat

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.message_item.view.*

/**
* Created by Micah on 22/08/2017.
*/
class ChatMessagesRVViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    val messageTV: TextView

    init {

        messageTV = itemView.messageTV
    }
}