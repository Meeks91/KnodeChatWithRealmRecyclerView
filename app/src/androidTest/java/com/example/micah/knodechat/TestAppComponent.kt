package com.example.micah.knodechat

import com.example.micah.knodechat.ChatActivity.ChatActivityTest
import com.example.micah.knodechat.dagger.modules.ChatActivityModule
import com.example.micah.knodechat.dagger.modules.NetworkingModule
import dagger.Component

/**
 * Created by Micah on 10/09/2017.
 */

@Component(modules = arrayOf(ChatActivityModule::class, NetworkingModule::class, NetworkingModule::class))
interface TestAppComponent {

    fun inject(chatActivityTest: ChatActivityTest)
}