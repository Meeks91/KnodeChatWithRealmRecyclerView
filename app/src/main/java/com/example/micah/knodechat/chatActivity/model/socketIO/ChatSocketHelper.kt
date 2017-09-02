package com.example.micah.knodechat.chatActivity.model.socketIO

import com.example.micah.knodechat.chatActivity.model.jsonAndRealmModel.JsonChatMessagesParser
import com.example.micah.knodechat.rxBus.RxBus
import com.example.micah.knodechat.rxBus.RxBusNotificationType
import com.example.micah.knodechat.rxBus.RxBusEvent
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import org.json.JSONObject

/**
* Created by Micah on 22/08/2017.
*/

class ChatSocketHelper {

    private val TAG = ChatSocketHelper::class.simpleName
    private val chatMessageEventKey = "chat message"
    private val socketURL = "http://192.168.1.5:9000"
    private lateinit var socket: Socket

    init {

        initBaseSocketConnection()
    }

    //MARK: --------------- INITIALSATION

    /**
     * creates the socketIO object and connects to socketIO
     */
    private fun initBaseSocketConnection() {

        socket = IO.socket(socketURL)

        socket.connect()
    }

    /**
     * inits the connection to the chat socket and routes chat messages to the
     * onChatReceiverListener
     */
    fun initChatConnection() {

        socket
               .on(Socket.EVENT_CONNECT,  Emitter.Listener{})
               .on(chatMessageEventKey, generateOnChatReceiverListener())
               .on(Socket.EVENT_DISCONNECT,  Emitter.Listener{})

    }

    //MARK: --------------- INITIALSATION

    //MARK: --------------- RECEIVING AND SENDING CHAT MESSAGES

    /* generates an Emitter.Listener thay passes all chat messages
     from socketIO and emits them to the RxBus */
    private fun generateOnChatReceiverListener(): Emitter.Listener {

        return Emitter.Listener {

            //extract chatMessage from jsonChatMessage
            val chatMessage = JsonChatMessagesParser.extractChatMessageFrom(it.first() as JSONObject)

            //emit the received message
            RxBus.bus.onNext(RxBusEvent(RxBusNotificationType.messageReceived, chatMessage))
        }
    }

    /* sends the specified [message] to the socket to be handled as a chat Message Event */
    fun sendChatMessage(message: String) {

        socket.emit(chatMessageEventKey, message)
    }

    //MARK: --------------- RECEIVING AND SENDING CHAT MESSAGES
}