package com.example.micah.knodechat.chatActivity

import com.example.micah.knodechat.chatActivity.model.api.MessagesApiHelper
import com.example.micah.knodechat.chatActivity.model.db.MessagesDBHelper
import com.example.micah.knodechat.chatActivity.model.jsonAndRealmModel.ChatMessage
import com.example.micah.knodechat.chatActivity.model.socketIO.ChatSocketHelper
import com.example.micah.knodechat.chatActivity.view.ChatActivityDelegate
import com.example.micah.knodechat.rxBus.RxBus
import com.example.micah.knodechat.rxBus.RxBusNotificationType
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

/**
* Created by Micah on 19/08/2017.
*/

class ChatActivityPresenter {

    private val TAG = ChatActivityPresenter::class.simpleName
    private val mCompositeDisposable = CompositeDisposable()
    private val mChatActivityDelegate: ChatActivityDelegate
    private val mChatSocketHelper: ChatSocketHelper
    private val mMessagesApiHelper: MessagesApiHelper
    private val mMessagesDBHelper: MessagesDBHelper

    constructor(chatActivityDelegate: ChatActivityDelegate, chatSocketHelper: ChatSocketHelper, messagesApiHelper: MessagesApiHelper, messagesDBHelper: MessagesDBHelper){

        this.mChatActivityDelegate = chatActivityDelegate

        this.mChatSocketHelper = chatSocketHelper

        this.mMessagesApiHelper = messagesApiHelper

        this.mMessagesDBHelper = messagesDBHelper

        initChatSocketConnection()

        initSubscriptionToNewChatMessageEvents()

        syncMessagesFromApiToDB()
    }

    //MARK: ---------------------- INITIALISE SOCKET CONNECTION & MESSAGE EVENT SUBSCRIPTION

    /* inits the connection to the chat socket using the mChatSocketHelper */
    fun initChatSocketConnection(){

        //init the connection to the chat socket
        mChatSocketHelper.initChatConnection()
    }

    /**
     * inits the subscibtion to the rxBus to get messageReceived events.
     * The messages are routed to the mChatActivityDelegate
     */
    private fun initSubscriptionToNewChatMessageEvents() {

        //listen for chat messages using rxBus
        RxBus.bus.subscribe {

            //make sure this is a chat message
            if (it.type == RxBusNotificationType.messageReceived)

                mMessagesDBHelper.saveChatMessage(it.data as ChatMessage)
        }
        .addTo(mCompositeDisposable)
    }

    //MARK: ---------------------- INITIALISE SOCKET CONNECTION & MESSAGE EVENT SUBSCRIPTION

    //MARK: ---------------------- SYNCING MESSAGES FROM API TO DB & VIEW

    /**
     * Gets and saves all messages from api since the last message we retrieved.
     * If we have no previous messages it gets and saves all of the messages from
     * the api. The provided [completion] handler says if the operation was a success.
     */
    private fun syncMessagesFromApiToDB() {

        //get last local chat message we received
        val message = mMessagesDBHelper.getLastReceivedMessage()

        //check if there was a message
        if (message == null)

            //as we have no past messages get and save all of them
            getAndSaveAllMessagesFromApi()

        //here we have synced a message before
        else

           //get and save all messages since the message.createdAt date
           getAndSaveAllMessagesFromApiSince(message.createdAt)
    }

    /**
     * retrieves *all* of the chat messages from the api and then saves them.
     */
    private fun getAndSaveAllMessagesFromApi() {

        mMessagesApiHelper.getAllMessages { messages ->

            //save messages if call was a success
            if (messages != null)

                mMessagesDBHelper.saveAllChatMessagesIn(messages)
        }
    }

    /**
     * retrieves all of the chat messages from the api that were created
     * after the specified [date] and then saves them.
     */
    private fun getAndSaveAllMessagesFromApiSince(createdAt: String) {

            mMessagesApiHelper.getAllMessagesSince(createdAt) { messages ->

                //save messages if call was a success
                if (messages != null)

                    mMessagesDBHelper.saveAllChatMessagesIn(messages)
            }
        }

    //MARK: ---------------------- SYNCING MESSAGES FROM API TO DB & VIEW

    //MARK: ---------------------- SENDING CHAT MESSAGES

    /* is called by the view to send chat messages */
    fun onSendMessage(messageText: String) {

        mChatSocketHelper.sendChatMessage(messageText)
    }

    //MARK: ---------------------- SENDING CHAT MESSAGES
}