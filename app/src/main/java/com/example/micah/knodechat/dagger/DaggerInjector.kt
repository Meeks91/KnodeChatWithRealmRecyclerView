package com.example.micah.knodechat.dagger

import com.example.micah.knodechat.chatActivity.view.ChatActivity
import com.example.micah.knodechat.dagger.modules.ChatActivityModule

/**
* Created by Micah on 26/08/2017.
*/

object DaggerInjector {

    private val daggerAppComponent = DaggerAppComonent.builder()

    /**
     * returns the appComponent configured to inject into the specified ChatActivity
     */
    fun configureInjectionFor(chatActivity: ChatActivity): AppComonent{

        return daggerAppComponent.chatActivityModule(ChatActivityModule(chatActivity)).build()
    }
}