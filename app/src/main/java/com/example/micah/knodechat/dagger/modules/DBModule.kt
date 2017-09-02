package com.example.micah.knodechat.dagger.modules

import com.example.micah.knodechat.chatActivity.model.db.MessagesDBHelper
import dagger.Module
import dagger.Provides

/**
* Created by Micah on 28/08/2017.
*/


@Module
class DBModule {

    @Provides fun provideMessagesDBHelper(): MessagesDBHelper {

        return MessagesDBHelper()
    }
}