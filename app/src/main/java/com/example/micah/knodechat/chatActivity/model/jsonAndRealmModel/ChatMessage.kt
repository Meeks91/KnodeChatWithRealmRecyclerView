package com.example.micah.knodechat.chatActivity.model.jsonAndRealmModel

import io.realm.RealmObject

/**
* Created by Micah on 28/08/2017.
*/

open class ChatMessage : RealmObject{

    var id: Int? = -1
    lateinit var message: String
    lateinit var createdAt: String

    constructor(message: String, createdAt: String, id: Int){

        this.message = message

        this.createdAt = createdAt

        this.id = id
    }

    constructor()

    override fun toString(): String {

        return "id: $id, message: $message, createdAt: $createdAt"
    }
}