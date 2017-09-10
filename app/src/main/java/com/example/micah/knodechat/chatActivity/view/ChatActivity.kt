package com.example.micah.knodechat.chatActivity.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.micah.knodechat.R
import com.example.micah.knodechat.chatActivity.ChatActivityPresenter
import com.example.micah.knodechat.chatActivity.model.jsonAndRealmModel.ChatMessage
import com.example.micah.knodechat.chatActivity.view.messagesRecyclerView.ChatMessagesRVAdapter
import com.example.micah.knodechat.dagger.DaggerInjector
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class ChatActivity : AppCompatActivity(), ChatActivityDelegate {

    private val TAG = ChatActivity::class.simpleName
    private lateinit var rvAdapter: ChatMessagesRVAdapter
    @Inject lateinit var chatActivityPresenter: ChatActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Realm.init(this)

        DaggerInjector.configureInjectionFor(this).inject(this)

        initMessagesRecyclerView()
    }

    //MARK: -------------- INITIALISATION

    /**
     * inits the messagesRV using a realmRecyclerViewAdapter
     * and linearLayoutManager
     */
    fun initMessagesRecyclerView(){

        //create the recyclerViewAdapter:
        val realm = Realm.getDefaultInstance()
        rvAdapter = ChatMessagesRVAdapter(realm.where(ChatMessage::class.java).findAll())

        //assign the adapter and layoutManager:
        messagesRV.setAdapter(rvAdapter)
        messagesRV.setLayoutManager(LinearLayoutManager(this))
    }

    //MARK: -------------- INITIALISATION

    //MARK: ----------------- INPUT ROUTING

    /**
     * Handles: sending the message via the present and
     * and clearing the messageET after it's been sent
     */
    fun onSendMessageClicked(view: View){

        //send the message stored in the messageET
        chatActivityPresenter.onSendMessage(messageET.text.toString())

        //clear the message editText
        messageET.text.clear()
    }

    //MARK: ----------------- INPUT ROUTING
}