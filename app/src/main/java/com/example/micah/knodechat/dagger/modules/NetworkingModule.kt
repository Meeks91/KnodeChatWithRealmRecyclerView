package com.example.micah.knodechat.dagger.modules

import com.example.micah.knodechat.chatActivity.model.api.MessagesApiHelper
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

/**
* Created by Micah on 28/08/2017.
*/

@Module
class NetworkingModule {


    @Provides fun provideOkHttp(): OkHttpClient = OkHttpClient()

    @Provides fun provideMessagesApiHelper(okHttpClient: OkHttpClient): MessagesApiHelper{

     return MessagesApiHelper(okHttpClient)
    }
}