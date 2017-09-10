package com.example.micah.knodechat.dagger.modules

import com.example.micah.knodechat.chatActivity.ChatActivityPresenter
import com.example.micah.knodechat.chatActivity.model.api.MessagesApiHelper
import com.example.micah.knodechat.chatActivity.model.db.MessagesDBHelper
import com.example.micah.knodechat.chatActivity.model.socketIO.ChatSocketHelper
import com.example.micah.knodechat.chatActivity.view.ChatActivityDelegate
import dagger.Module
import dagger.Provides

/**
* Created by Micah on 26/08/2017.
*/

@Module (includes = arrayOf(SocketIOModule::class, DBModule::class, NetworkingModule::class))

class ChatActivityModule(val mChatActivityDelegate: ChatActivityDelegate){

    @Provides fun provideChatActivityDelegate(): ChatActivityDelegate {

        return mChatActivityDelegate
    }

    @Provides fun chatActivityPresenter(chatActivityDelegate: ChatActivityDelegate,
                                                    chatSocketHelper: ChatSocketHelper,
                                                          messagesApiHelper: MessagesApiHelper,
                                                                 messagesDBHelper: MessagesDBHelper): ChatActivityPresenter {

        return ChatActivityPresenter(chatActivityDelegate, chatSocketHelper, messagesApiHelper, messagesDBHelper)
    }
}