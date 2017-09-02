package com.example.micah.knodechat.dagger.modules

import android.content.Context
import com.example.micah.knodechat.chatActivity.model.api.MessagesApiHelper
import com.example.micah.knodechat.chatActivity.model.db.MessagesDBHelper
import com.example.micah.knodechat.chatActivity.model.socketIO.ChatSocketHelper
import com.example.micah.knodechat.chatActivity.view.ChatActivityDelegate
import com.example.micah.knodechat.chatActivity.ChatActivityPresenter
import dagger.Module
import dagger.Provides

/**
* Created by Micah on 26/08/2017.
*/

@Module (includes = arrayOf(SocketIOModule::class, DBModule::class, NetworkingModule::class))

class ChatActivityModule(val mContext: Context){

    @Provides fun provideContext(): Context {

        return mContext
    }

    @Provides fun chatActivityPresenter(chatActivityDelegate: ChatActivityDelegate,
                                                    chatSocketHelper: ChatSocketHelper,
                                                          messagesApiHelper: MessagesApiHelper,
                                                                 messagesDBHelper: MessagesDBHelper): ChatActivityPresenter {

        return ChatActivityPresenter(chatActivityDelegate, chatSocketHelper, messagesApiHelper, messagesDBHelper)
    }

    @Provides fun ChatActivityViewDelegate(): ChatActivityDelegate{

        return mContext as ChatActivityDelegate
    }
}