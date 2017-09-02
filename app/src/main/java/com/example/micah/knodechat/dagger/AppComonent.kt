package com.example.micah.knodechat.dagger

import com.example.micah.knodechat.chatActivity.model.api.MessagesApiHelper
import com.example.micah.knodechat.chatActivity.view.ChatActivity
import com.example.micah.knodechat.dagger.modules.ChatActivityModule
import com.example.micah.knodechat.dagger.modules.NetworkingModule
import dagger.Component
import okhttp3.OkHttpClient

/**
* Created by Micah on 26/08/2017.
*/

@Component (modules = arrayOf(ChatActivityModule::class, NetworkingModule::class, NetworkingModule::class))
interface AppComonent {

    fun inject(chatActivity: ChatActivity)
}