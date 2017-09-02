package com.example.micah.knodechat.dagger.modules

import com.example.micah.knodechat.chatActivity.model.socketIO.ChatSocketHelper
import dagger.Module
import dagger.Provides

/**
* Created by Micah on 26/08/2017.
*/

@Module
class SocketIOModule{

    @Provides fun provideChatSocketHelper(): ChatSocketHelper {

        return ChatSocketHelper()
    }
}